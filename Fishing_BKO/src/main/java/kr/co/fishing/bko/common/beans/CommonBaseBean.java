package kr.co.fishing.bko.common.beans;


public class CommonBaseBean {
    
    private AdminBean adminBean;
    
    private int page = 1;
    
    /**
     * PAGING처리
     */
    private int startRow = 0;
    private int endRow = 0;
    private int rows = 0;
    
    /**
     * SORT처리
     */
    private String sortColumn;
    private String orderType;
    
    /**
     * command 
     */
    private String searchWordType;
    private String searchWord;
    private String searchWordS;
    private String searchWordE;
    
    public AdminBean getAdminBean() {
        return adminBean;
    }
    public void setAdminBean(AdminBean adminBean) {
        this.adminBean = adminBean;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getStartRow() {
        return startRow;
    }
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }
    public int getEndRow() {
        return endRow;
    }
    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }
    public int getRows() {
        return rows;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
    public String getSortColumn() {
        return sortColumn;
    }
    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }
    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public String getSearchWordType() {
        return searchWordType;
    }
    public void setSearchWordType(String searchWordType) {
        this.searchWordType = searchWordType;
    }
    public String getSearchWord() {
        return searchWord;
    }
    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }
    public String getSearchWordS() {
        return searchWordS;
    }
    public void setSearchWordS(String searchWordS) {
        this.searchWordS = searchWordS;
    }
    public String getSearchWordE() {
        return searchWordE;
    }
    public void setSearchWordE(String searchWordE) {
        this.searchWordE = searchWordE;
    }
	    
}
