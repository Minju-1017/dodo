package com.dodo.module;

import java.util.Date;

/**
 * 쿼리의 WHERE 구문에 들어가는 조건들은 모두 Vo에 들어간다고 보면 된다.
 * seq는 1개 포함 시키기(Dto에 들어가는 변수명과 동일하게)
 * 관례적으로 Vo에 들어가는 변수들은 변수명 앞에 sh를 붙인다.
 */
public class BaseVo {
	
	// <-- Paging
	private int currPage = 1;			// 현재 페이지
	private int rowNumToShow = 5;		// 화면에 보여줄 데이터 줄 갯수
	private int pageNumToShow = 5;		// 화면에 보여줄 페이징 번호 갯수

	private int totalRows;				// 전체 데이터 갯수
	private int totalPages;				// 전체 페이지 번호
	private int startPage;				// 시작 페이지 번호
	private int endPage;				// 마지막 페이지 번호

	private int startRnumForMysql = 0;	// 쿼리 시작 row
	// -->
	
	// <-- Search
	private Integer shUseNy = 1;		// null 값을 받아야 되는 경우가 있어서 int 대신 Integer 사용
	private Integer shDelNy = 0;		// null 값을 받아야 되는 경우가 있어서 int 대신 Integer 사용
	private Integer shOptionDate = 2;	// null 값을 받아야 되는 경우가 있어서 int 대신 Integer 사용
	private String shDateStart;
	private String shDateEnd;
	private Integer shOption;			// null 값을 받아야 되는 경우가 있어서 int 대신 Integer 사용
	private String shValue;
	// -->
	
	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getRowNumToShow() {
		return rowNumToShow;
	}

	public void setRowNumToShow(int rowNumToShow) {
		this.rowNumToShow = rowNumToShow;
	}

	public int getPageNumToShow() {
		return pageNumToShow;
	}

	public void setPageNumToShow(int pageNumToShow) {
		this.pageNumToShow = pageNumToShow;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartRnumForMysql() {
		return startRnumForMysql;
	}

	public void setStartRnumForMysql(int startRnumForMysql) {
		this.startRnumForMysql = startRnumForMysql;
	}
	
	public void setParamsPaging(int totalRows) {
		// 총 개수 설정
		this.totalRows = totalRows;
		
		// 총 페이지 수 설정
		if (totalRows == 0) {
			totalPages = 1;
		} else {
			totalPages = totalRows / rowNumToShow;
			
			if (totalRows % rowNumToShow > 0) {
				totalPages = totalPages + 1;
			}
		}

		// 화면에 보여줄 현재 페이지 설정
		if (totalPages < currPage) {
			currPage = totalPages;
		}
		
		// 화면에 보여줄 시작 페이지 설정
		startPage = ((currPage - 1) / pageNumToShow) * pageNumToShow + 1;

		// 화면에 보여줄 시작 페이지 설정
		endPage = startPage + pageNumToShow - 1;

		if (endPage > totalPages) {
			endPage = totalPages;
		}
		
		// DB에서 가져올 데이터의 시작 row
		if (currPage == 1) {
			startRnumForMysql = 0;
		} else {
			startRnumForMysql = (rowNumToShow * (currPage - 1));
		}
		
		System.out.println("currPage:" + currPage);
		System.out.println("totalRows:" + totalRows);
		System.out.println("rowNumToShow:" + rowNumToShow);
		System.out.println("totalPages:" + totalPages);
		System.out.println("startPage:" + startPage);
		System.out.println("endPage:" + endPage);
		System.out.println("startRnumForMysql: " + startRnumForMysql);
	}

	public Integer getShUseNy() {
		return shUseNy;
	}

	public void setShUseNy(Integer shUseNy) {
		this.shUseNy = shUseNy;
	}

	public Integer getShDelNy() {
		return shDelNy;
	}

	public void setShDelNy(Integer shDelNy) {
		this.shDelNy = shDelNy;
	}

	public Integer getShOptionDate() {
		return shOptionDate;
	}

	public void setShOptionDate(Integer shOptionDate) {
		this.shOptionDate = shOptionDate;
	}

	public String getShDateStart() {
		return shDateStart;
	}
	
	public String getShDateStartDB() {
		return shDateStart + " 00:00:00";
	}

	public void setShDateStart(String shDateStart) {
		this.shDateStart = shDateStart;
	}

	public String getShDateEnd() {
		return shDateEnd;
	}
	
	public String getShDateEndDB() {
		return shDateEnd + " 23:59:59";
	}

	public void setShDateEnd(String shDateEnd) {
		this.shDateEnd = shDateEnd;
	}

	public Integer getShOption() {
		return shOption;
	}

	public void setShOption(Integer shOption) {
		this.shOption = shOption;
	}

	public String getShValue() {
		return shValue;
	}

	public void setShValue(String shValue) {
		this.shValue = shValue;
	}

}
