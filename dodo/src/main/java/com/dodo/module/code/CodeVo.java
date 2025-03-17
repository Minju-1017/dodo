package com.dodo.module.code;

public class CodeVo {
	
	// Paging
	private int currPage = 1;			// 현재 페이지
	private int rowNumToShow = 5;		// 화면에 보여줄 데이터 줄 갯수
	private int pageNumToShow = 5;		// 화면에 보여줄 페이징 번호 갯수

	private int totalRows;				// 전체 데이터 갯수
	private int totalPages;				// 전체 페이지 번호
	private int startPage;				// 시작 페이지 번호
	private int endPage;				// 마지막 페이지 번호

	private int startRnumForMysql = 0;	// 쿼리 시작 row

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

}
