package com.farmai.DTO;

public class Pager {
    private int totalRows;		//전체 행수  >> DB에서 가져와야함
    private int totalPageNo;	//전체 페이지 수
    private int totalGroupNo;	//전체 그룹 수 >> 페이지수가 10개이고, 그룹당 페이지 수가 5개면 그룹수는2개이다
    private int startPageNo;	//그룹의 시작 페이스 번호 (이전)12345(다음) 에서 '1'를 뜻함
    private int endPageNo;		//그룹의 끝 페이지 번호 (이전)12345(다음) 에서 '5'를 뜻함
    private int pageNo;			//현재 페이지 번호 >>사용자가 원하는 페이지 넘버
    private int pagesPerGroup;	//그룹당 페이지 수 (이전)12345(다음) 에서 이전과 다음의 수
    private int groupNo;		//현재 그룹 번호 >>페이지수가 10개이고, 그룹당 페이지 수가 5개면 그룹수는2개이며, 12345가 그룹 1임
    private int rowsPerPage;	//페이지당 행 수
    private int startRowNo;		//페이지의 시작 행 번호 >>1페이지라면 1페이지의 첫 행 번호
    private int endRowNo;		//페이지의 마지막 행 번호
    private String tableName;

    public Pager(int rowsPerPage, int pagesPerGroup, int totalRows, int pageNo, String tableName) {
        this.rowsPerPage = rowsPerPage;
        this.pagesPerGroup = pagesPerGroup;
        this.totalRows = totalRows;
        this.pageNo = pageNo;
        this.tableName = tableName;
        totalPageNo = totalRows / rowsPerPage;
        if(totalRows % rowsPerPage != 0) totalPageNo++;

        /* totalPageNo = (int)Math.ceil((double)totalRows / rowsPerPage); */

        totalGroupNo = totalPageNo / pagesPerGroup;
        if(totalPageNo % pagesPerGroup != 0) totalGroupNo++;

        groupNo = (pageNo - 1) / pagesPerGroup + 1;

        startPageNo = (groupNo-1) * pagesPerGroup + 1;

        endPageNo = startPageNo + pagesPerGroup - 1;
        if(groupNo == totalGroupNo) endPageNo = totalPageNo;

        startRowNo = (pageNo - 1) * rowsPerPage + 1;
        endRowNo = pageNo * rowsPerPage;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalPageNo() {
        return totalPageNo;
    }

    public int getTotalGroupNo() {
        return totalGroupNo;
    }

    public int getStartPageNo() {
        return startPageNo;
    }

    public int getEndPageNo() {
        return endPageNo;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPagesPerGroup() {
        return pagesPerGroup;
    }

    public int getGroupNo() {
        return groupNo;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public int getStartRowNo() {
        return startRowNo;
    }

    public int getEndRowNo() {
        return endRowNo;
    }

    public String getTableName() {
        return tableName;
    }


    @Override
    public String toString() {
        return "Pager{" +
                "totalRows=" + totalRows +
                ", totalPageNo=" + totalPageNo +
                ", totalGroupNo=" + totalGroupNo +
                ", startPageNo=" + startPageNo +
                ", endPageNo=" + endPageNo +
                ", pageNo=" + pageNo +
                ", pagesPerGroup=" + pagesPerGroup +
                ", groupNo=" + groupNo +
                ", rowsPerPage=" + rowsPerPage +
                ", startRowNo=" + startRowNo +
                ", endRowNo=" + endRowNo +
                ", tableName='" + tableName + '\'' +
                '}';
    }
}
