package com.dodo.module.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dodo.common.DoDoUtil;
import com.dodo.module.code.CodeDto;
import com.dodo.module.code.CodeService;
import com.dodo.module.code.CodeVo;
import com.dodo.module.codegroup.CodeGroupDto;
import com.dodo.module.codegroup.CodeGroupService;
import com.dodo.module.codegroup.CodeGroupVo;
import com.dodo.module.game.GameDto;
import com.dodo.module.game.GameReviewDto;
import com.dodo.module.game.GameService;
import com.dodo.module.game.GameVo;
import com.dodo.module.member.MemberDto;
import com.dodo.module.member.MemberService;
import com.dodo.module.member.MemberVo;
import com.dodo.module.sales.SalesDto;
import com.dodo.module.sales.SalesService;
import com.dodo.module.sales.SalesVo;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/xdm/excel/")
public class ExcelController {
	
	@Autowired
	CodeService codeService;
	
	@Autowired
	GameService gameService;
	
	@Autowired
	CodeGroupService codeGroupService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	SalesService salesService;

	/**
	 * Apache POI 이용한 Excel Download
	 * @param dto
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "CodeXdmListExcelDownload")
    public ResponseEntity<byte[]> codeXdmListExcelDownload(@ModelAttribute("vo") CodeVo vo) throws IOException {		
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Code List");
		
		// 엑셀 헤더 생성
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("번호");
        headerRow.createCell(1).setCellValue("사용");
        headerRow.createCell(2).setCellValue("코드그룹 코드");
        headerRow.createCell(3).setCellValue("코드그룹 이름");
        headerRow.createCell(4).setCellValue("코드");
        headerRow.createCell(5).setCellValue("코드 이름");
        headerRow.createCell(6).setCellValue("코드 이름(영문)");
        headerRow.createCell(7).setCellValue("순서");
        headerRow.createCell(8).setCellValue("등록일");
        headerRow.createCell(9).setCellValue("수정일");
        
        // Data 읽기
        vo.setParamsPaging(codeService.selectListCount(vo));
        
        if (vo.getTotalRows() > 0) {
			List<CodeDto> list = codeService.selectList(vo);
	
	        // 엑셀 데이터 생성
			for (int i = 0; i < list.size(); i++) {
				CodeDto dto = list.get(i);
		        Row dataRow = sheet.createRow(i+1);
		        
		        dataRow.createCell(0).setCellValue(i+1);
		        dataRow.createCell(1).setCellValue(dto.getcUseNyStr());
		        dataRow.createCell(2).setCellValue(Integer.parseInt(dto.getCodeGroup_cgSeq()));
		        dataRow.createCell(3).setCellValue(dto.getCgName());
		        dataRow.createCell(4).setCellValue(Integer.parseInt(dto.getcSeq()));
		        dataRow.createCell(5).setCellValue(dto.getcName());
		        dataRow.createCell(6).setCellValue(dto.getcNameEng() == null ? "" : dto.getcNameEng());
		        dataRow.createCell(7).setCellValue(dto.getcSequence());
		        dataRow.createCell(8).setCellValue(dto.getcRegiDate() == null ? "" : dto.getcRegiDate());
		        dataRow.createCell(9).setCellValue(dto.getcUpdtDate() == null ? "" : dto.getcUpdtDate());
			}
        }
		
		// 엑셀 파일을 ByteArrayOutputStream에 작성
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        // HTTP 응답 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=CodeList.xlsx");
        headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }
    
    /**
     * Spring Boot 기본 제공 기능을 이용한 Excel Download
     * @param response
     * @param dto
     * @throws Exception
     */
    @RequestMapping(value = "CodeXdmListExcelDownloadBySB")
	public void codeXdmListExcelDownloadBySB(
			HttpServletResponse response, @ModelAttribute("vo") CodeVo vo) throws Exception {
    	// 원래는 xls이 아닌 csv로 해야함. xls로 하면 파일 손상 메세지가 뜸
	    response.setContentType("text/xls; charset=UTF-8"); 
	    response.setHeader("Content-Disposition", "attachment; filename = CodeList.xls");
	   
	    PrintWriter writer = response.getWriter();   
	    writer.write('\uFEFF');  // BOM 추가
	    writer.println("#, 사용, 코드그룹 코드, 코드그룹 이름, 코드, 코드 이름, 코드 이름(영문), 순서, 등록일, 수정일");

	    // Data 읽기
        vo.setParamsPaging(codeService.selectListCount(vo));
        
        if (vo.getTotalRows() > 0) {
        	List<CodeDto> list = codeService.selectList(vo);
        
        	int i = 1;
		    for (CodeDto dto : list) {
		        writer.printf("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s\n",
		        	i, 
		        	dto.getcUseNyStr(), 
		        	dto.getCodeGroup_cgSeq(), 
		        	dto.getCgName(),
		        	dto.getcSeq(),
		        	dto.getcName(),
		        	dto.getcNameEng(),
		        	dto.getcSequence(),
		        	dto.getcRegiDate(),
		        	dto.getcUpdtDate()
		        );
		        
		        i++;
		    }
        }
        
	    writer.flush();
	    writer.close();
	}
    
    /**
	 * Apache POI 이용한 Excel Read(Insert)
	 * @param dto
	 * @return
	 * @throws IOException
	 */
    @RequestMapping(value = "CodeXdmListExcelRead")
	public String codeXdmListExcelRead(CodeDto dto, @RequestParam("fUploadFiles") MultipartFile file) throws Exception { 
		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		
		for(int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
			CodeDto excel = new CodeDto();
		    
		    DataFormatter formatter = new DataFormatter();		        
		    XSSFRow row = worksheet.getRow(i);
		    	    	
		    String cName = formatter.formatCellValue(row.getCell(0));
		    String cNameEng = formatter.formatCellValue(row.getCell(1));
		    String cSequence = formatter.formatCellValue(row.getCell(2));
		    String cDescription = formatter.formatCellValue(row.getCell(3));
		    String cUseNy = formatter.formatCellValue(row.getCell(4));
		    String codeGroup_cgSeq = formatter.formatCellValue(row.getCell(5));
		
			if (cUseNy.equals("Y")) {
				cUseNy = "1";
			} else {
				cUseNy = "0";
			}
			
			excel.setcName(cName);
			excel.setcNameEng(cNameEng);
			excel.setcSequence(Integer.parseInt(cSequence));
			excel.setcDescription(cDescription);
			excel.setcUseNy(Integer.parseInt(cUseNy));
			excel.setCodeGroup_cgSeq(codeGroup_cgSeq);
	
		    codeService.insert(excel);
		} 
		
		return "redirect:/xdm/code/CodeXdmList";
	}
    
    /**
	 * Apache POI 이용한 Excel Download
	 * @param dto
	 * @return
     * @throws Exception 
	 */
	@RequestMapping(value = "GameXdmListExcelDownload")
    public ResponseEntity<byte[]> gameXdmListExcelDownload(@ModelAttribute("vo") GameVo vo) throws Exception {		
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Game List");
		
		// 엑셀 헤더 생성
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("번호");
        headerRow.createCell(1).setCellValue("고유번호");
        headerRow.createCell(2).setCellValue("게임명");
        headerRow.createCell(3).setCellValue("카테고리");
        headerRow.createCell(4).setCellValue("평점");
        headerRow.createCell(5).setCellValue("난이도");
        headerRow.createCell(6).setCellValue("공식판매사");
        headerRow.createCell(7).setCellValue("등록일");
        headerRow.createCell(8).setCellValue("수정일");
        
        // Data 읽기
        vo.setParamsPaging(gameService.selectListCount(vo));
        
        if (vo.getTotalRows() > 0) {
			List<GameDto> list = gameService.selectList(vo);
	
	        // 엑셀 데이터 생성
			for (int i = 0; i < list.size(); i++) {
				GameDto dto = list.get(i);
		        Row dataRow = sheet.createRow(i+1);
		        
		        dataRow.createCell(0).setCellValue(i+1);
		        dataRow.createCell(1).setCellValue(Integer.parseInt(dto.getgSeq()));
		        dataRow.createCell(2).setCellValue(dto.getgName());
		        dataRow.createCell(3).setCellValue(CodeService.selectOneCachedCode(String.valueOf(dto.getgCategoryCd())));
		        dataRow.createCell(4).setCellValue(dto.getGrScoreAvg());
		        dataRow.createCell(5).setCellValue(dto.getgLevel());
		        dataRow.createCell(6).setCellValue(CodeService.selectOneCachedCode(String.valueOf(dto.getgOfficialCd())));
		        dataRow.createCell(7).setCellValue(dto.getgRegiDate() == null ? "" : dto.getgRegiDate());
		        dataRow.createCell(8).setCellValue(dto.getgUpdtDate() == null ? "" : dto.getgUpdtDate());
			}
        }
		
		// 엑셀 파일을 ByteArrayOutputStream에 작성
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        // HTTP 응답 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=GameList.xlsx");
        headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }
    
    /**
	 * Apache POI 이용한 Excel Download
	 * @param dto
	 * @return
     * @throws Exception 
	 */
	@RequestMapping(value = "GameReviewXdmListExcelDownload")
    public ResponseEntity<byte[]> gameReviewXdmListExcelDownload(@ModelAttribute("vo") GameVo vo) throws Exception {		
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Game Review List");
		
		// 엑셀 헤더 생성
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("번호");
        headerRow.createCell(1).setCellValue("고유번호");
        headerRow.createCell(2).setCellValue("게임 고유번호");
        headerRow.createCell(3).setCellValue("게임명");
        headerRow.createCell(4).setCellValue("작성자 고유번호");
        headerRow.createCell(5).setCellValue("작성자 아이디");
        headerRow.createCell(6).setCellValue("작성자명");
        headerRow.createCell(7).setCellValue("평점");
        headerRow.createCell(8).setCellValue("등록일");
        
        // Data 읽기
        vo.setParamsPaging(gameService.selectReviewCount(vo));
        
        if (vo.getTotalRows() > 0) {
			List<GameReviewDto> list = gameService.selectReviewList(vo);
	
	        // 엑셀 데이터 생성
			for (int i = 0; i < list.size(); i++) {
				GameReviewDto dto = list.get(i);
		        Row dataRow = sheet.createRow(i+1);
		        
		        dataRow.createCell(0).setCellValue(i+1);
		        dataRow.createCell(1).setCellValue(Integer.parseInt(dto.getGrSeq()));
		        dataRow.createCell(2).setCellValue(Integer.parseInt(dto.getGame_gSeq()));
		        dataRow.createCell(3).setCellValue(dto.getgName());
		        dataRow.createCell(4).setCellValue(Integer.parseInt(dto.getMember_mSeq()));
		        dataRow.createCell(5).setCellValue(dto.getmId());
		        dataRow.createCell(6).setCellValue(dto.getmName());
		        dataRow.createCell(7).setCellValue(dto.getGrScore());
		        dataRow.createCell(8).setCellValue(dto.getGrRegiDate() == null ? "" : dto.getGrRegiDate());
			}
        }
		
		// 엑셀 파일을 ByteArrayOutputStream에 작성
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        // HTTP 응답 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=GameReviewList.xlsx");
        headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }
    
    /**
	 * Apache POI 이용한 Excel Download
	 * @param dto
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "CodeGroupXdmListExcelDownload")
    public ResponseEntity<byte[]> codeGroupXdmListExcelDownload(
    		@ModelAttribute("vo") CodeGroupVo vo) throws IOException {		
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Code Group List");
		
		// 엑셀 헤더 생성
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("번호");
        headerRow.createCell(1).setCellValue("사용");
        headerRow.createCell(2).setCellValue("코드그룹 코드");
        headerRow.createCell(3).setCellValue("코드그룹 이름");
        headerRow.createCell(4).setCellValue("코드그룹 이름(영문)");
        headerRow.createCell(5).setCellValue("갯수");
        headerRow.createCell(6).setCellValue("순서");
        headerRow.createCell(7).setCellValue("등록일");
        headerRow.createCell(8).setCellValue("수정일");
        
        // Data 읽기
        vo.setParamsPaging(codeGroupService.selectListCount(vo));
        
        if (vo.getTotalRows() > 0) {
			List<CodeGroupDto> list = codeGroupService.selectList(vo);
	
	        // 엑셀 데이터 생성
			for (int i = 0; i < list.size(); i++) {
				CodeGroupDto dto = list.get(i);
		        Row dataRow = sheet.createRow(i+1);
		        
		        dataRow.createCell(0).setCellValue(i+1);
		        dataRow.createCell(1).setCellValue(dto.getCgUseNyStr());
		        dataRow.createCell(2).setCellValue(Integer.parseInt(dto.getCgSeq()));
		        dataRow.createCell(3).setCellValue(dto.getCgName());
		        dataRow.createCell(4).setCellValue(dto.getCgNameEng());
		        dataRow.createCell(5).setCellValue(dto.getcCount());
		        dataRow.createCell(6).setCellValue(dto.getCgSequence());
		        dataRow.createCell(7).setCellValue(dto.getCgRegiDate() == null ? "" : dto.getCgRegiDate());
		        dataRow.createCell(8).setCellValue(dto.getCgUpdtDate() == null ? "" : dto.getCgUpdtDate());
			}
        }
		
		// 엑셀 파일을 ByteArrayOutputStream에 작성
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        // HTTP 응답 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=CodeGroupList.xlsx");
        headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }
    
    /**
	 * Apache POI 이용한 Excel Download
	 * @param dto
	 * @return
     * @throws Exception 
	 */
	@RequestMapping(value = "MemberXdmListExcelDownload")
    public ResponseEntity<byte[]> memberXdmListExcelDownload(
    		@ModelAttribute("vo") MemberVo vo) throws Exception {		
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Member List");
		
		// 엑셀 헤더 생성
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("번호");
        headerRow.createCell(1).setCellValue("탈퇴");
        headerRow.createCell(2).setCellValue("회원등급");
        headerRow.createCell(3).setCellValue("고유번호");
        headerRow.createCell(4).setCellValue("아이디");
        headerRow.createCell(5).setCellValue("이름");
        headerRow.createCell(6).setCellValue("생년월일");
        headerRow.createCell(7).setCellValue("등록일");
        headerRow.createCell(8).setCellValue("수정일");
        
        // Data 읽기
        vo.setParamsPaging(memberService.selectListCount(vo));
        
        if (vo.getTotalRows() > 0) {
			List<MemberDto> list = memberService.selectList(vo);
	
	        // 엑셀 데이터 생성
			for (int i = 0; i < list.size(); i++) {
				MemberDto dto = list.get(i);
		        Row dataRow = sheet.createRow(i+1);
		        
		        dataRow.createCell(0).setCellValue(i+1);
		        dataRow.createCell(1).setCellValue(dto.getmDelNyStr());
		        dataRow.createCell(2).setCellValue(CodeService.selectOneCachedCode(String.valueOf(dto.getmGradeCd())));
		        dataRow.createCell(3).setCellValue(Integer.parseInt(dto.getmSeq()));
		        dataRow.createCell(4).setCellValue(dto.getmId());
		        dataRow.createCell(5).setCellValue(dto.getmName());
		        dataRow.createCell(6).setCellValue(dto.getmBirth());
		        dataRow.createCell(7).setCellValue(dto.getmRegiDate() == null ? "" : dto.getmRegiDate());
		        dataRow.createCell(8).setCellValue(dto.getmUpdtDate() == null ? "" : dto.getmUpdtDate());
			}
        }
		
		// 엑셀 파일을 ByteArrayOutputStream에 작성
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        // HTTP 응답 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=MemberList.xlsx");
        headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }
    
    /**
	 * Apache POI 이용한 Excel Download
	 * @param dto
	 * @return
     * @throws Exception 
	 */
	@RequestMapping(value = "SalesXdmListExcelDownload")
    public ResponseEntity<byte[]> salesXdmListExcelDownload(@ModelAttribute("vo") SalesVo vo) throws Exception {		
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sales List");
		
		// 엑셀 헤더 생성
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("번호");
        headerRow.createCell(1).setCellValue("등록번호");
        headerRow.createCell(2).setCellValue("판매상태");
        headerRow.createCell(3).setCellValue("배송상태");
        headerRow.createCell(4).setCellValue("게임명");
        headerRow.createCell(5).setCellValue("판매가");
        headerRow.createCell(6).setCellValue("판매자");
        headerRow.createCell(7).setCellValue("구매자");
        headerRow.createCell(8).setCellValue("등록일");
        headerRow.createCell(9).setCellValue("구매일");
        
        // Data 읽기
        vo.setParamsPaging(salesService.selectXdmListCount(vo));
        
        if (vo.getTotalRows() > 0) {
			List<SalesDto> list = salesService.selectXdmList(vo);
	
	        // 엑셀 데이터 생성
			for (int i = 0; i < list.size(); i++) {
				SalesDto dto = list.get(i);
		        Row dataRow = sheet.createRow(i+1);
		        
		        dataRow.createCell(0).setCellValue(i+1);
		        dataRow.createCell(1).setCellValue(Integer.parseInt(dto.getMsSeq()));
		        dataRow.createCell(2).setCellValue(CodeService.selectOneCachedCode(String.valueOf(dto.getMsStateCd())));
		        dataRow.createCell(3).setCellValue(CodeService.selectOneCachedCode(String.valueOf(dto.getMsoDeliStateCd())));
		        dataRow.createCell(4).setCellValue(dto.getgName());
		        dataRow.createCell(5).setCellValue(DoDoUtil.formatNumberComma(dto.getMsPrice()));
		        dataRow.createCell(6).setCellValue(dto.getmName());
		        dataRow.createCell(7).setCellValue(dto.getOmName());
		        dataRow.createCell(8).setCellValue(dto.getMsRegiDate() == null ? "" : dto.getMsRegiDate());
		        dataRow.createCell(9).setCellValue(dto.getMsoRegiDate() == null ? "" : dto.getMsoRegiDate());
			}
        }
		
		// 엑셀 파일을 ByteArrayOutputStream에 작성
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        // HTTP 응답 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=SalesList.xlsx");
        headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }
    
}