package kr.co.fishing.bko.common.beans;


public class CommonBaseBean {
    
    private AdminBean adminBean;
    
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
    
    /**
     * Paging
     * */
    private int totalCount;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount = 10;
    private int start;
    private int end;
    private int totalPages;    
    
    public AdminBean getAdminBean() {
        return adminBean;
    }
    public void setAdminBean(AdminBean adminBean) {
        this.adminBean = adminBean;
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
    
    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        totalPages = totalCount / pageSize;
        if (totalCount % pageSize > 0) totalPages++;
        end = page * pageSize >= totalCount ? totalCount : page * pageSize;
        start = (page - 1) * pageSize + 1;
        if (start > end) {
            start = 0;
            end = 0;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }    
}
