package kr.co.fishing.bko.common.aop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.fishing.bko.common.beans.AdminBean;
import kr.co.fishing.bko.common.utils.CommonConstant.ERROR_CODE;
import kr.co.fishing.bko.common.utils.CommonConstant.SESSION_KEY;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
    
    /**
     * 로그인 체크
     * 
     * @return 로그인체크여부
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        AdminBean adminBean = (AdminBean) request.getSession().getAttribute(SESSION_KEY.ADMIN);
        
        if (adminBean == null) {
        	
        	if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
        		response.sendError(ERROR_CODE.SESSION_TIME_OUT);
        	} else {
        		response.sendRedirect("/bko/login");
        	}
            
            return false;
        }
        
        return true;
    }
}
