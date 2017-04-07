package kr.co.fishing.bko.common.utils;

public class CommonConstant {
    
	/** 세션키 */
    public interface SESSION_KEY {
        /** 관리자 */
        public final String ADMIN = "SESSION_KEY_ADMIN";
    }
    
    /** 에러코드 */
    public interface ERROR_CODE {
        /** 세션타임아웃 */
        public final int SESSION_TIME_OUT = 999;
        
        /** 시스템에러가 발생했습니다. 시스템관리자에게 문의 하세요. */
        public final String MSG_0001E = "MSG_0001E";
        /** 기존 비밀번호와 일치하지 않습니다. */
        public final String MSG_0002E = "MSG_0002E";
        
        /** 엑셀파일에 대한 항목 정의가 없습니다. */
        public final String EXCEL_MSG_0001W = "EXCEL_MSG_0001W";
        /** 숫자만 입력할수 있습니다. */
        public final String EXCEL_MSG_0002E = "EXCEL_MSG_0002E";
        /** 숫자({0}자리)만 입력할수 있습니다. */
        public final String EXCEL_MSG_0003E = "EXCEL_MSG_0003E";
        /** 숫자({0}~{1}자리)만 입력할수 있습니다. */
        public final String EXCEL_MSG_0004E = "EXCEL_MSG_0004E";
        /** 문자({0}자리)만 입력할수 있습니다. */
        public final String EXCEL_MSG_0005E = "EXCEL_MSG_0005E";
        /** 문자({0}~{1}자리)만 입력할수 있습니다. */
        public final String EXCEL_MSG_0006E = "EXCEL_MSG_0006E";
        /** 이메일 형식이 올바르지 않습니다. */
        public final String EXCEL_MSG_0007E = "EXCEL_MSG_0007E";
        /** 휴대폰번호 형식이 올바르지 않습니다. */
        public final String EXCEL_MSG_0008E = "EXCEL_MSG_0008E";
        /** 전화번호 형식이 올바르지 않습니다. */
        public final String EXCEL_MSG_0009E = "EXCEL_MSG_0009E";
        /** 우편번호 형식이 올바르지 않습니다. */
        public final String EXCEL_MSG_0010E = "EXCEL_MSG_0010E";
        /** 코드값을 확인해 주세요. */
        public final String EXCEL_MSG_0011E = "EXCEL_MSG_0011E";


        
    }
    
    /** 사용여부 */
    public interface USE {
        /** 사용 */
        public final String Y = "Y";
        
        /** 미사용 */
        public final String N = "N";
    }
    
    /** AJAX결과 */
    public interface AJAX_RESULT {
        /** 정상 */
        public final String OK = "OK";
        
        /** 에러 */
        public final String NG = "NG";
        
        /** 중복 */
        public final String DUP = "DUP";
    }
    
    /** 파일구분 */
    public interface FILE_TYPE {
        /** 웹영역 */
        public final String WEB = "0";
        
        /** 일반파일영역 */
        public final String NORMAL = "1";
    }
    
    /** 사용여부 */
    public interface EXCELUP_STATUS {
        /** 정상 */
        public final String OK = "1";
        
        /** 에러 */
        public final String NG = "2";
    }
}
