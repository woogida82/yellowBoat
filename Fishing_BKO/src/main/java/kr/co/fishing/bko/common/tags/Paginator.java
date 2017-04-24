package kr.co.fishing.bko.common.tags;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.Writer;

public class Paginator extends SimpleTagSupport {
    private String uri;
    private int currPage;
    private int totalPages;
    private int maxLinks = 10;

    private Writer getWriter() {
        return getJspContext().getOut();
    }

    @Override
    public void doTag() throws JspException {
        Writer out = getWriter();

        boolean lastPage = (currPage == totalPages);
        int pgStart = Math.max(currPage - maxLinks / 2, 1);
        int pgEnd = pgStart + maxLinks;
        if (pgEnd > totalPages + 1) {
            int diff = pgEnd - totalPages;
            pgStart -= diff - 1;
            if (pgStart < 1)
                pgStart = 1;
            pgEnd = totalPages + 1;
        }

        try {
            if (currPage > 1)
                out.write(constructLink(currPage - 1, null, "prev"));
            for (int i = pgStart; i < pgEnd; i++) {
                out.write(constructLink(i));
            }
            if (!lastPage && totalPages > 0)
                out.write(constructLink(currPage + 1, null, "next"));
        } catch (java.io.IOException ex) {
            throw new JspException("Error in Paginator tag", ex);
        }
    }

    private String constructLink(int page) {
        return constructLink(page, String.valueOf(page), null);
    }

    private String constructLink(int page, String text, String className) {
        StringBuilder link = new StringBuilder("<a href='#'");
        if (className != null) {
            link.append(" class=\"");
            link.append(className);
            link.append("\"");
        }  
        
        link.append(" onclick=\"");
        link.append("javascript:");
        link.append(uri);
        link.append(".search('");
        link.append(page);
        link.append("');\"");
        link.append(">");
        if (text != null) {
            if (page == currPage) {
                link.append("<strong>");
                link.append(text);
                link.append("</strong>");
            }else{
                link.append(text);
            }
        }        
        link.append("</a>");        
        return link.toString();
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setMaxLinks(int maxLinks) {
        this.maxLinks = maxLinks;
    }
}