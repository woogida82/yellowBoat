package kr.co.fishing.bko.common.tags;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import kr.co.fishing.bko.common.CommonService;
import kr.co.fishing.bko.common.beans.CommonCodeBean;
import kr.co.fishing.bko.common.utils.CommonUtil;
import kr.co.fishing.bko.common.utils.TagUtil;

import org.apache.commons.lang.StringUtils;

public class SelectTag extends TagSupport {
    
    private static final long serialVersionUID = 1L;
    
    private String boxName;
    private String masterCd;
    private List<CommonCodeBean> dataList;
    private String selected;
    private String selectDefault;
    private String selectDefaultName;
    private String include;
    private String makeDefault;
    private String result;
    
    @Override
    public int doStartTag() throws JspException {
        
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        
        try {
            if (StringUtils.isNotEmpty(masterCd) && (dataList == null || dataList.isEmpty())) {
                CommonService commonService = (CommonService) CommonUtil.getBean(request, "commonServiceImpl");
                dataList = commonService.selectCommonCodeList(masterCd);
            }
        } catch (Exception e) {
            throw new JspException(e);
        }
        
        boolean isMakeDefault = true;
        if (makeDefault != null && makeDefault.toLowerCase().equals("false")) {
            isMakeDefault = false;
        }
        
        result = TagUtil.makeSelectBox(boxName, dataList, selected, selectDefault, selectDefaultName, include, isMakeDefault);
        
        return SKIP_BODY;
    }
    
    @Override
    public int doEndTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.print(this.result);
        } catch (Exception e) {
            throw new JspTagException("I/O 예외 " + e.getMessage());
        } finally {
            this.boxName = null;
            this.masterCd = null;
            this.dataList = null;
            this.selected = null;
            this.selectDefault = null;
            this.selectDefaultName = null;
            this.include = null;
            this.makeDefault = null;
            this.result = null;
        }
        
        return EVAL_PAGE;
    }
    
    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }
    
    public void setMasterCd(String masterCd) {
        this.masterCd = masterCd;
    }
    
    public void setDataList(List<CommonCodeBean> dataList) {
        this.dataList = dataList;
    }
    
    public void setSelected(String selected) {
        this.selected = selected;
    }
    
    public void setSelectDefault(String selectDefault) {
        this.selectDefault = selectDefault;
    }
    
    public void setSelectDefaultName(String selectDefaultName) {
        this.selectDefaultName = selectDefaultName;
    }
    
    public void setInclude(String include) {
        this.include = include;
    }
    
    public void setMakeDefault(String makeDefault) {
        this.makeDefault = makeDefault;
    }
    
}
