package kr.co.fishing.bko.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import kr.co.fishing.bko.common.beans.AdminBean;
import kr.co.fishing.bko.common.utils.CommonConstant.AJAX_RESULT;
import kr.co.fishing.bko.common.utils.CommonConstant.SESSION_KEY;

@Controller
@RequestMapping("/bko")
public class LoginController {

    @Autowired
    private LoginService loginService;
    
    /**
     * 로그인화면 표시
     * 
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        
        ModelAndView mav = new ModelAndView();
        
        AdminBean adminBean = (AdminBean) request.getSession().getAttribute(SESSION_KEY.ADMIN);
        
        if (adminBean != null) {
            
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/bko/main");
            redirectView.setExposeModelAttributes(false);

            mav.setView(redirectView);
        } else {
            mav.setViewName("login/login");
        }
        
        return mav;
    }
    
    /**
     * 로그아웃
     * 
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        
        ModelAndView mav = new ModelAndView();
        
        request.getSession().invalidate();
        
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/bko/login");
        redirectView.setExposeModelAttributes(false);

        mav.setView(redirectView);
        
        return mav;
    }
    
    /**
     * 정보수정
     * 
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/userInfo")
    public String userInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        return "login/userInfo";
    }
    
    /**
     * 패스워드수정
     * 
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/changePasswd")
    public String changePasswd(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        return "login/changePasswd";
    }
    
    /**
     * 로그인처리
     * 
     * @param request
     * @param response
     * @param adminBean
     * @return 로그인처리결과
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/doLogin")
    public Map<String,Object> doLogin(HttpServletRequest request, HttpServletResponse response, 
            @ModelAttribute AdminBean adminBean) throws Exception {
        
        Map<String,Object> resultMap = new HashMap<String,Object>();
        
        try {
            
            // 관리자정보취득
            AdminBean resultBean = loginService.selectAdminInfo(adminBean);
            
            boolean isExists = false; 
            
            // 관리자가 존재 하는 경우
            if (resultBean != null && StringUtils.isNotEmpty(resultBean.getAdminId())) {
                // 세션등록
                request.getSession().setAttribute(SESSION_KEY.ADMIN, resultBean);
                isExists = true;
            }
            
            resultMap.put("result", AJAX_RESULT.OK);
            resultMap.put("isExists", isExists);
            
        } catch(Exception e) {
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }
        
        return resultMap;
    }
    
}
