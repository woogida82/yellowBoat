package kr.co.fishing.bko.common.exception;

import java.util.List;


public class BizException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private List<String> errList;

    public BizException() {
        super();
    }
    
    public BizException(Throwable t) {
        super(t);
    }

    public BizException(String msg, Throwable t) {
        super(msg, t);
    }
    
    public BizException(String msg) {
        super(msg);
    }
    
    public BizException(List<String> errList) {
        super();
        this.errList = errList;
    }
    
    public BizException(Throwable t, List<String> errList) {
        super(t);
        this.errList = errList;
    }
    
    public BizException(String msg, List<String> errList) {
        super(msg);
        this.errList = errList;
    }

    public List<String> getErrList() {
        return errList;
    }

    public void setErrList(List<String> errList) {
        this.errList = errList;
    }
} 
