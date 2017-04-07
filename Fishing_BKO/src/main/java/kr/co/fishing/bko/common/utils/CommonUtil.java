package kr.co.fishing.bko.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

public class CommonUtil {
	
	/**
	 * 스프링의 bean 오브젝트를 취득한다.
	 * 
	 * @param request
	 * @param beanId
	 * @return
	 * @throws Exception
	 */
	public static Object getBean(HttpServletRequest request, String beanId) throws Exception {
        
        // DispatcherServlet => ContextLoadListender 의 참조관계를 갖고 있으므로 
        // DispatcherServlet 먼저 확인후 ContextLoaderListener 확인한다.
        // DispatcherServlet으로 로딩된 context를 가져 온다.
        WebApplicationContext webApplicationContext = RequestContextUtils.getWebApplicationContext(request);
        
        if (!webApplicationContext.containsBean(beanId)) {
        	// ContextLoaderListener으로 로딩된 context를 가져 온다.
            ServletContext servletContext = request.getSession().getServletContext();
            webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        }
        
        return webApplicationContext.getBean(beanId);
    }
	
	/**
     * 리스트를 구분자로 연결한다.
     * 
     * @param list 대상 리스트 
     * @param separator 구분문자
     * @return 검색결과
     */
    public static String join(List<String> list, String separator) {
        
        StringBuffer result = new StringBuffer();
        
        if (list != null) {
            
            for (int i = 0; i < list.size(); i++) {
                
                String str = list.get(i);
                
                if (i > 0) {
                    result.append(separator);
                }
                
                result.append(str);
            }
            
        }
        
        return result.toString();
    }
    
    /**
     * 백분율을 계산 한다.
     * 
     * @return 계산결과
     */
    public static String rate(String target, String total, Integer digits) {
        
        String result = "";
        
        BigDecimal calcResult = null;
        
        try {
            BigDecimal targetCnt = new BigDecimal(target);
            BigDecimal totalCnt = new BigDecimal(total);
            calcResult = targetCnt.multiply(new BigDecimal(100)).divide(totalCnt, digits.intValue(), BigDecimal.ROUND_HALF_UP);
        } catch(Exception e) {
            calcResult = new BigDecimal(0);
        }
        
        if (digits == 0) {
            result = String.valueOf(calcResult.intValue());
        } else {
            result = String.valueOf(calcResult.doubleValue());
        }
        
        return result;
    }
    
    /** 
     * 금액 형식으로 변환
     * 
     * @param value
     * @return 변환결과
     */
    public static String num2String(int value){
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("#,##0");
        return df.format(value);
    }
    
    /** 
     * 금액 형식으로 변환
     * 
     * @param value
     * @return 변환결과
     */
    public static String num2String(long value){
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("#,##0");
        return df.format(value);
    }
    
    /** 
     * 금액 형식으로 변환
     * 
     * @param value
     * @return 변환결과
     */
    public static String num2String(String value){
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("#,##0");
        return df.format(value);
    }
}
