package com.dodo.module.sales;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dodo.Constants;
import com.dodo.module.member.MemberDto;
import com.dodo.module.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value={"/xdm/sales/", "/usr/sales/"})
public class SalesController {
	
	private String path_admin = "xdm/sales/";
	private String path_user = "usr/sales/";
	
	@Autowired
	SalesService service;
	
	@Autowired
	MemberService memberService;
	
	@Value("${toss.secretKey}")
    private String tossSecretKey;
	
	/**
	 * 전체 데이터 읽어오기 - 페이징 기능 들어감
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "SalesXdmList")
	public String salesXdmList(Model model, @ModelAttribute("vo") SalesVo vo) {
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(service.selectXdmListCount(vo));
		
		if (vo.getTotalRows() > 0) {
			model.addAttribute("salesList", service.selectXdmList(vo));
		}
		
		return path_admin + "SalesXdmList";
	}
	
	/**
	 * 데이터 추가/수정 폼
	 * @return
	 */
	@RequestMapping(value = "SalesXdmForm")
	public String salesXdmForm(@ModelAttribute("vo") SalesVo vo, Model model, SalesDto salesDto) {
		model.addAttribute("salesItem", service.selectXdmOne(salesDto));
		
		return path_admin + "SalesXdmForm";
	}
	
	/**
	 * 데이터 삭제하기
	 * @return redirect: 데이터 삭제 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "SalesXdmDele")
	public String salesXdmDele(SalesDto salesDto) {
		service.deleteOrder(salesDto);	
		service.delete(salesDto);	

		return "redirect:SalesXdmList";
	}
	
	/**
	 * 데이터 삭제 옵션 세팅 - update 이용
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "SalesXdmUele")
	public String salesXdmUele(SalesDto salesDto) {
		service.uelete(salesDto);	

		return "redirect:SalesXdmList";
	}
	
	/**
	 * Ajax를 통한 여러건 데이터 삭제
	 * @param seqList
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "SalesListXdmDeleProc")
	public Map<String, Object> salesListXdmDeleProc(
			@RequestParam(value="chbox") List<String> seqList) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if (seqList == null || (seqList != null && seqList.size() == 0)) {
			returnMap.put("rt", "fail");
		} else {
			service.listDeleteOrder(seqList);
			int successCnt = service.listDelete(seqList);
			
			if (successCnt > 0) {
				returnMap.put("rt", "success");
			} else {
				returnMap.put("rt", "fail");
			}
		}

		return returnMap;
	}
	
	/**
	 * Ajax를 통한 여러건 데이터 삭제 옵션 세팅 - update 이용
	 * @param seqList
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "SalesListXdmUeleProc")
	public Map<String, Object> salesListXdmUeleProc(@RequestParam(value="chbox") List<String> seqList) {
		System.out.println("salesListXdmUeleProc: " + seqList);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if (seqList == null || (seqList != null && seqList.size() == 0)) {
			returnMap.put("rt", "fail");
		} else {
			int successCnt = service.listUelete(seqList);
			
			if (successCnt > 0) {
				returnMap.put("rt", "success");
			} else {
				returnMap.put("rt", "fail");
			}
		}

		return returnMap;
	}
	
	//////////////////////////////////////////////////////////////////////
	
	/**
	 * 전체 데이터 읽어오기 - 페이징 기능 들어감
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "SalesUsrList")
	public String salesUsrList(Model model, @ModelAttribute("vo") SalesVo vo) {
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(service.selectListCount(vo));
		
		if (vo.getTotalRows() > 0) {
			model.addAttribute("salesList", service.selectList(vo));
		}
		
		return path_user + "SalesUsrList";
	}
	
	/**
	 * 검색된 데이터 읽어오기 - 페이징 기능 들어감
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "SalesSearchUsrList")
	public String salesSearchUsrList(Model model, @ModelAttribute("vo") SalesVo vo, 
			@RequestParam("salesShValue") String salesShValue) {
		// addAttribute 하기 전에 미리 실행되야함(판매중인 게임만 검색)
		if (salesShValue != null && !salesShValue.equals("")) {
			vo.setShValue(salesShValue);
			vo.setShStateCd(Constants.SALES_CODE_ON_SALES);
			vo.setShOption(1);
		}
		
		vo.setParamsPaging(service.selectListCount(vo));
		
		if (vo.getTotalRows() > 0) {
			model.addAttribute("salesList", service.selectList(vo));
		}
		
		return path_user + "SalesUsrList";
	}
	
	/**
	 * 데이터 보기
	 * @return
	 */
	@RequestMapping(value = "SalesUsrDetail")
	public String salesUsrDetail(Model model, SalesDto salesDto, HttpSession httpSession) {
		// 판매자가 아닌 경우 조회수 증가
		if (!String.valueOf(httpSession.getAttribute("sessSeqUsr")).equals(salesDto.getMember_mSeq())) {
			service.plusHit(salesDto);
		}
		
		model.addAttribute("salesItem", service.selectOne(salesDto));
		
		return path_user + "SalesUsrDetail";
	}
	
	/**
	 * Ajax를 통한 중고 거래 등록 체크
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "SalesUsrInstCheckProc")
	public Map<String, Object> salesUsrInstCheckProc(SalesDto salesDto, HttpSession httpSession) {	
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		salesDto.setMember_mSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
		int cnt = service.insertCheck(salesDto);
		
		if (cnt == 0) {
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		}
		
		return returnMap;
	}
	
	/**
	 * 데이터 추가 폼
	 * @return
	 */
	@RequestMapping(value = "SalesUsrForm")
	public String salesUsrForm(Model model, SalesDto salesDto, HttpSession httpSession) {
		if (salesDto.getMsSeq() == null || salesDto.getMsSeq().equals("0") || salesDto.getMsSeq().equals("")) {
			// insert mode
			salesDto.setMember_mSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
			model.addAttribute("salesItem", salesDto);
		} else {
			// update mode
			model.addAttribute("salesItem", service.selectOne(salesDto));
		}
		
		return path_user + "SalesUsrForm";
	}
	
	/**
	 * 데이터 추가
	 * @return
	 */
	@RequestMapping(value = "SalesUsrInst")
	public String salesUsrInst(SalesDto salesDto, HttpSession httpSession) {
		salesDto.setMember_mSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
		service.insert(salesDto);
		
		return "redirect:SalesUsrList";
	}
	
	/**
	 * 입력한 데이터 수정하기
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@ResponseBody
	@RequestMapping(value = "SalesUsrUpdt")
	public Map<String, Object> salesUsrUpdt(SalesDto salesDto) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		SalesDto sDto = service.selectOne(salesDto);
		int soDtoCnt = service.orderCheckByMsSeq(salesDto);
		
		// 결제 완료거나, 구매중이면 수정 불가
		if (sDto.getMsStateCd() == Constants.SALES_CODE_SALES_COMPLETED || soDtoCnt > 0) {
			returnMap.put("rt", "fail");
		} else {
			service.update(salesDto);
			returnMap.put("rt", "success");
		}
		
		return returnMap;
	}
	
	/**
	 * Ajax를 통한 데이터 삭제하기
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "SalesUsrDele")
	public Map<String, Object> salesUsrDele(SalesDto salesDto) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		SalesDto sDto = service.selectOne(salesDto);
		int soDtoCnt = service.orderCheckByMsSeq(salesDto);
		
		// 결제 완료거나, 구매중이면 수정 불가
		if (sDto.getMsStateCd() == Constants.SALES_CODE_SALES_COMPLETED || soDtoCnt > 0) {
			returnMap.put("rt", "fail");
		} else {
			service.delete(salesDto);	
			returnMap.put("rt", "success");
		}
		
		return returnMap;
	}
	
	/**
	 * Toss 결제 폼
	 * @return
	 */
	@RequestMapping(value = "SalesUsrBuyForm")
	public String salesUsrBuyForm(Model model, SalesDto salesDto, MemberDto memberDto, HttpSession httpSession) {
		memberDto.setmSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
		model.addAttribute("memberItem", memberService.selectOne(memberDto));
		model.addAttribute("salesItem", service.selectOne(salesDto));
		
		return path_user + "SalesUsrBuyForm";
	}
	
	/**
	 * Ajax를 통한 Toss 결제 정보 DB 저장
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "SalesUsrBuyInst")
	public Map<String, Object> salesUsrBuyInst(Model model, SalesDto salesDto, 
			SalesOrderDto salesOrderDto, HttpSession httpSession) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		salesOrderDto.setMemberSales_msSeq(salesDto.getMsSeq());
		salesOrderDto.setoMember_mSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
		
		// 동일한 상품을 구매중 상태에서 20분이 지나도 구매완료 되지 않았다면 삭제한다.
		service.insert20minCheck(salesOrderDto);
		
		int cnt = service.insertOrderCheck(salesOrderDto);
		
		if (cnt == 0) { // 결제 중이 아니면
			int cntInsert = service.insertOrder(salesOrderDto);
			
			if (cntInsert == 0) {
				returnMap.put("rt", "fail");
			} else {
				model.addAttribute("salesOrderItem", salesOrderDto);
				returnMap.put("rt", salesOrderDto.getMsoSeq());
			}
		} else {
			returnMap.put("rt", "fail_other");
		}
		
		return returnMap;
	}
	
	/**
	 * Toss 결제 성공 UI
	 * @return
	 */
	@RequestMapping(value = "SalesUsrBuySuccess", method = RequestMethod.GET)
	public String salesUsrBuySuccess(Model model,
	        @RequestParam("paymentKey") String paymentKey,
	        @RequestParam("orderId") String orderId,
	        @RequestParam("amount") String amount) throws Exception {
        // DB에 저장된 결제 정보와 Toss로 결제하려는 정보가 일치하는지 확인한다.
        SalesOrderDto dto = new SalesOrderDto();
        dto.setMsoSeq(orderId);
        int check = service.orderCheck(dto);
        
        if (check == 0) {
        	model.addAttribute("message", "주문 실패하였습니다. 중고거래 게시판으로 이동합니다.");
        	return path_user + "SalesUsrBuyFail";
        }
        
        // Toss API에 결제 승인 요청
		JSONObject obj = new JSONObject();
		obj.put("orderId", orderId);
		obj.put("amount", amount);
		obj.put("paymentKey", paymentKey);

        // 토스페이먼츠 API는 시크릿 키를 사용자 ID로 사용하고, 비밀번호는 사용하지 않습니다.
        // 비밀번호가 없다는 것을 알리기 위해 시크릿 키 뒤에 콜론을 추가합니다.
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((tossSecretKey + ":").getBytes(StandardCharsets.UTF_8));
        String authorizations = "Basic " + new String(encodedBytes);

        // 결제를 승인하면 결제수단에서 금액이 차감돼요.
        URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", authorizations);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // 결제 승인 요청
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));

        // 결제 승인 결과 받음 (성공 200)
        int code = connection.getResponseCode();
        
        SalesOrderDto salesOrderDto = new SalesOrderDto();
    	salesOrderDto.setMsoSeq(orderId);
    	
        if (code == 200) {
        	// 결제 승인 성공 시 중고 거래 상태(구매 완료)/배송 상태(결제 완료) 변경
        	service.updateSuccess(salesOrderDto);
        	service.updateOrderSuccess(salesOrderDto);
        	
        	return path_user + "SalesUsrBuySuccess";
        } else {
        	int checkState = service.orderCheckSuccessState(dto);
        	
        	// 결제 성공한 주문인지 확인한다.
        	if (checkState > 0) {
        		// 실패 메세지 - ALREADY_PROCESSED_PAYMENT
            	model.addAttribute("message", "이미 구매완료 된 주문입니다.<br>에러코드: " + code + "<br><br>중고거래 게시판으로 이동합니다.");
            	return path_user + "SalesUsrBuyFail";
        	} else {
        		// 결제 승인 실패 시 임시 주문 정보 삭제
            	service.deleteOrder(salesOrderDto);
            	
            	// 실패 메세지
            	model.addAttribute("message", "주문 실패하였습니다.<br>에러코드: " + code + "<br><br>중고거래 게시판으로 이동합니다.");
            	return path_user + "SalesUsrBuyFail";
        	}
        }
    }
	
	/**
	 * Toss 결제 실패 UI
	 * @return
	 */
	@RequestMapping(value = "SalesUsrBuyFail", method = RequestMethod.GET)
	public String salesUsrBuyFail(Model model,
			@RequestParam("code") String code,
	        @RequestParam("message") String message,
	        @RequestParam("orderId") String orderId) {
		SalesOrderDto dto = new SalesOrderDto();
		dto.setMsoSeq(orderId);
		
		int checkState = service.orderCheckSuccessState(dto);
    	
    	// 결제 성공한 주문인지 확인한다.
    	if (checkState > 0) {
    		// 실패 메세지 - ALREADY_PROCESSED_PAYMENT
        	model.addAttribute("message", "이미 구매완료 된 주문입니다.<br>에러코드: " + code + "<br><br>중고거래 게시판으로 이동합니다.");

    	} else {
    		// 결제 승인 실패 시 임시 주문 정보 삭제
        	service.deleteOrder(dto);
        	
        	// 실패 메세지
        	model.addAttribute("message", "주문 실패하였습니다.<br>에러코드: " + code + "<br><br>중고거래 게시판으로 이동합니다.");
    	}
    	
		return path_user + "SalesUsrBuyFail";
	}
	
	/**
	 * 내 판매 정보 전체 데이터 읽어오기 - 페이징 기능 들어감
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "SalesUsrMySalesList")
	public String salesUsrMySalesList(Model model, @ModelAttribute("vo") SalesVo vo, HttpSession httpSession) {
		// addAttribute 하기 전에 미리 실행되야함
		vo.setMember_mSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
		vo.setParamsPaging(service.selectMySalesListCount(vo));
		
		if (vo.getTotalRows() > 0) {
			model.addAttribute("mySalesList", service.selectMySalesList(vo));
		}
		
		return path_user + "SalesUsrMySalesList";
	}
	
	/**
	 * 데이터 추가 폼
	 * @return
	 */
	@RequestMapping(value = "SalesUsrMySalesDeliForm")
	public String salesUsrMySalesDeliForm(Model model, SalesDto salesDto) {
		model.addAttribute("salesDeliItem", service.selectOrderOne(salesDto));
		
		return path_user + "SalesUsrMySalesDeliForm";
	}
	
	/**
	 * 입력한 데이터 수정하기
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "SalesUsrMySalesDeliUpdt")
	public String salesUsrMySalesDeliUpdt(Model model, SalesDto salesDto) {
		service.updateOrderDeli(salesDto);
		model.addAttribute("salesDeliItem", service.selectOrderOne(salesDto));
		
		return path_user + "SalesUsrMySalesDeliForm";
	}
	
	/**
	 * 입력한 데이터 수정하기
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "SalesUsrMySalesDeliComplateUpdt")
	public String salesUsrMySalesDeliComplateUpdt(Model model, SalesDto salesDto) {
		service.updateOrderDeliComplate(salesDto);
		model.addAttribute("salesDeliItem", service.selectOrderOne(salesDto));
		
		return path_user + "SalesUsrMySalesDeliForm";
	}
	
	/**
	 * 내 구매 정보 전체 데이터 읽어오기 - 페이징 기능 들어감
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "SalesUsrMyBuyList")
	public String salesUsrMyBuyList(Model model, @ModelAttribute("vo") SalesVo vo, HttpSession httpSession) {
		// addAttribute 하기 전에 미리 실행되야함
		vo.setoMember_mSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
		vo.setParamsPaging(service.selectMyBuyListCount(vo));
		
		if (vo.getTotalRows() > 0) {
			model.addAttribute("myBuyList", service.selectMyBuyList(vo));
		}
		
		return path_user + "SalesUsrMyBuyList";
	}
	
	/**
	 * 데이터 상세 보기
	 * @return
	 */
	@RequestMapping(value = "SalesUsrMyBuyDetail")
	public String salesUsrMyBuyDetail(Model model, SalesDto salesDto) {
		model.addAttribute("salesDeliItem", service.selectOrderOne(salesDto));
		
		return path_user + "SalesUsrMyBuyDetail";
	}

}
