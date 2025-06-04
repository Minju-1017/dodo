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

import com.dodo.module.code.CodeDto;
import com.dodo.module.code.CodeService;
import com.dodo.module.code.CodeVo;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/xdm/excel/")
public class ExcelController {
	
	@Autowired
	CodeService codeService;

	/**
	 * Apache POI 이용한 Excel Download
	 * @param dto
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "CodeXdmListExcelDownload")
    public ResponseEntity<byte[]> codeXdmListExcelDownload(
    		@ModelAttribute("vo") CodeVo vo) throws IOException {		
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
        
        // Code Data 읽기
        vo.setParamsPaging(codeService.selectListCount(vo));
        
        if (vo.getTotalRows() > 0) {
			List<CodeDto> codeList = codeService.selectList(vo);
	
	        // 엑셀 데이터 생성
			for (int i = 0; i < codeList.size(); i++) {
				CodeDto codeDto = codeList.get(i);
		        Row dataRow = sheet.createRow(i+1);
		        
		        dataRow.createCell(0).setCellValue(i+1);
		        dataRow.createCell(1).setCellValue(codeDto.getcUseNyStr());
		        dataRow.createCell(2).setCellValue(Integer.parseInt(codeDto.getCodeGroup_cgSeq()));
		        dataRow.createCell(3).setCellValue(codeDto.getCgName());
		        dataRow.createCell(4).setCellValue(Integer.parseInt(codeDto.getcSeq()));
		        dataRow.createCell(5).setCellValue(codeDto.getcName());
		        dataRow.createCell(6).setCellValue(codeDto.getcNameEng() == null ? "" : codeDto.getcNameEng());
		        dataRow.createCell(7).setCellValue(codeDto.getcSequence());
		        dataRow.createCell(8).setCellValue(codeDto.getcRegiDate());
		        dataRow.createCell(9).setCellValue(codeDto.getcUpdtDate() == null ? "" : codeDto.getcUpdtDate());
			}
        }
		
		// 엑셀 파일을 ByteArrayOutputStream에 작성
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        // HTTP 응답 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=codelist.xlsx");
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
	    response.setHeader("Content-Disposition", "attachment; filename = codelist.xls");
	   
	    PrintWriter writer = response.getWriter();   
	    writer.write('\uFEFF');  // BOM 추가
	    writer.println("#, 사용, 코드그룹 코드, 코드그룹 이름, 코드, 코드 이름, 코드 이름(영문), 순서, 등록일, 수정일");

	    // Code Data 읽기
        vo.setParamsPaging(codeService.selectListCount(vo));
        
        if (vo.getTotalRows() > 0) {
        	List<CodeDto> codeList = codeService.selectList(vo);
        
        	int i = 1;
		    for (CodeDto codeDto : codeList) {
		        writer.printf("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s\n",
		        	i, 
		        	codeDto.getcUseNyStr(), 
		        	codeDto.getCodeGroup_cgSeq(), 
		        	codeDto.getCgName(),
		        	codeDto.getcSeq(),
		        	codeDto.getcName(),
		        	codeDto.getcNameEng(),
		        	codeDto.getcSequence(),
		        	codeDto.getcRegiDate(),
		        	codeDto.getcUpdtDate()
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
	public String readExcel(CodeDto dto, @RequestParam("fUploadFiles") MultipartFile file) throws Exception { 
    	System.out.println("$$$$$$$$$$$$$$" + file);
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
}