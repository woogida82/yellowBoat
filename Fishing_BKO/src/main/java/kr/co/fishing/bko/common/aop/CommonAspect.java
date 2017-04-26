package kr.co.fishing.bko.common.aop;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import kr.co.fishing.bko.common.beans.AdminBean;
import kr.co.fishing.bko.common.beans.CommonBaseBean;
import kr.co.fishing.bko.common.utils.CommonConstant.SESSION_KEY;

@Aspect
@Component
public class CommonAspect {

    /**
     * 로딩할 자바스크립트명을 설정 한다.
     * 
     * @param joinPoint
     * @param viewName
     */
    @AfterReturning(value = "execution(public String kr.co.fishing.bko..*Controller.*(..))", returning = "viewName")
    public void afterRetuning(JoinPoint joinPoint, String viewName) {
        ModelMap map = null;
        
        for (Object obj : joinPoint.getArgs()){ 
            if (obj instanceof ModelMap) {
                map = (ModelMap) obj;
                break;
            }
        }
        
        String jsName = "/js/" + viewName + ".js";
        String jsObjName = "";
        
        if (viewName.contains("/")) {
            jsObjName = viewName.substring(viewName.lastIndexOf("/") + 1);
        } else {
            jsObjName = viewName;
        }
        
        jsObjName = StringUtils.upperCase(jsObjName.substring(0, 1)) + jsObjName.substring(1);
        
        map.put("jsName", jsName);
        map.put("jsObjName", jsObjName);
    }
    
    /**
     * 관리자세션을 빈에 설정 한다.
     * 
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(public * kr.co.fishing.bko..*Controller.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        
        HttpServletRequest request = null;
        CommonBaseBean commonBean = null;
        
        for (Object obj : pjp.getArgs()){ 
            if (obj instanceof HttpServletRequest) {
                request = (HttpServletRequest) obj;
            } else if (obj instanceof CommonBaseBean) {
                commonBean = (CommonBaseBean) obj;
            } 
        }
        
        AdminBean adminBean = (AdminBean) request.getSession().getAttribute(SESSION_KEY.ADMIN);
                
        if (adminBean != null && commonBean != null) {
            commonBean.setAdminBean(adminBean);
            
            if (StringUtils.isNotEmpty(commonBean.getSearchWordS()) && StringUtils.isNotEmpty(commonBean.getSearchWordE()) && !commonBean.getSearchWordS().contains(":")) {
                commonBean.setSearchWordS(commonBean.getSearchWordS().replaceAll("\\.", ""));
                commonBean.setSearchWordE(commonBean.getSearchWordE().replaceAll("\\.", ""));
            }
        }
        
        return pjp.proceed();
    }
    
    /**
     * 페이징처리에 필요한 기본 설정을 한다.
     * 
     * @param pjp
     * @return
     * @throws Throwable
     */
//    @SuppressWarnings("unchecked")
//    @Around("execution(public java.util.Map<String,Object> kr.co.fishing.bko..*Controller.*Paging(..))")
//    public Object paginAround(ProceedingJoinPoint pjp) throws Throwable {
//        
//        CommonBaseBean commonBean = null;
//        Map<String,Object> resultMap = null;
//        
//        for (Object obj : pjp.getArgs()){ 
//            if (obj instanceof CommonBaseBean) {
//                commonBean = (CommonBaseBean) obj;
//            } 
//        }
//                
//        int rowCnt = 0;
//        if (commonBean != null) {
//            rowCnt = commonBean.getRows();
//            commonBean.setStartRow((rowCnt * commonBean.getPage()) - rowCnt);
//            commonBean.setEndRow(rowCnt);
//        }
//        
//        resultMap = (Map<String,Object>) pjp.proceed();
//        
//        if (commonBean != null) {
//            int totalCnt = 0;
//            
//            if (resultMap.get("records") != null) {
//                totalCnt = (int) resultMap.get("records");
//            }
//            
//            int total = (int) Math.ceil((double) totalCnt / rowCnt);
//            
//            resultMap.put("page", commonBean.getPage());
//            resultMap.put("total", total);
//        }
//        
//        return resultMap;
//    }
}
