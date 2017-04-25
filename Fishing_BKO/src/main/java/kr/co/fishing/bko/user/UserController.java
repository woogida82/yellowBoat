package kr.co.fishing.bko.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.mysql.jdbc.StringUtils;

import kr.co.fishing.bko.common.beans.AdminBean;
import kr.co.fishing.bko.common.beans.CommonBaseBean;
import kr.co.fishing.bko.common.utils.CommonConstant.AJAX_RESULT;
import kr.co.fishing.bko.common.utils.CommonConstant.SESSION_KEY;


@Controller
@RequestMapping("/bko/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("")
    public String user(HttpServletRequest request, HttpServletResponse response, @ModelAttribute AdminBean bean, ModelMap model) throws Exception {
        try {
            int resultCnt = userService.selectUserListCnt(bean);
            bean.setTotalCount(resultCnt);            
            
            List<AdminBean> resultList = userService.selectUserList(bean);
            
            model.addAttribute("bean", bean);
            model.addAttribute("rows", resultList);
            model.addAttribute("records", resultCnt);
        } catch(Exception e) {
            e.printStackTrace();
        }        
        return "user/userList";
    }
        
    /**
     * 회원상세화면
     * 
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */     
    @RequestMapping("/userDetailView")
    public String userDetailView(HttpServletRequest request, HttpServletResponse response, @ModelAttribute AdminBean bean, ModelMap model) throws Exception {
        AdminBean userBean = new AdminBean();
        userBean = userService.selectUser(bean);
        
        model.addAttribute("userBean", userBean);
        return "user/userDetailView";
    }   
    
    /**
     * 회원상세화면
     * 
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */     
    @RequestMapping("/userInfoChangeView")
    public String userInfoChangeView(HttpServletRequest request, HttpServletResponse response, @ModelAttribute AdminBean bean, ModelMap model) throws Exception {
        AdminBean userBean = new AdminBean();
        
        bean.setUserId(bean.getAdminBean().getUserId());
        userBean = userService.selectUser(bean);
        
        model.addAttribute("userBean", userBean);
        return "user/userInfoChangeView";
    }     
    
    
    
    /**
     * 회원가입화면
     * 
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */    
    @RequestMapping("/singUp")
    public ModelAndView singUp(HttpServletRequest request, HttpServletResponse response, @ModelAttribute CommonBaseBean bean, ModelMap model) throws Exception {
        ModelAndView mav = new ModelAndView();
        
        AdminBean adminBean = (AdminBean) request.getSession().getAttribute(SESSION_KEY.ADMIN);
        
        if (adminBean != null) {
            
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/bko/main");
            redirectView.setExposeModelAttributes(false);

            mav.setView(redirectView);
        } else {
            mav.addObject("jsName", "/js/user/signUp.js");
            mav.addObject("jsObjName", "SignUp");
            mav.setViewName("user/signUp");
        }
        return mav;
    } 
    
    @ResponseBody
    @RequestMapping("/selectUser")
    public Map<String,Object> selectUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute AdminBean bean) throws Exception {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        
        try {
            
            resultMap.put("planDrugInfo", userService.selectUser(bean));
            resultMap.put("result", AJAX_RESULT.OK);
            
        } catch(Exception e) {
            
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }
        return resultMap;
    }

    
    @ResponseBody
    @RequestMapping("/insertUser")
    public Map<String,Object> insertPlanDrug(HttpServletRequest request, HttpServletResponse response, @ModelAttribute AdminBean bean) throws Exception {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        
        try {

            //아이디 중복 체크
            int resultCnt = userService.selectUserAdminId(bean);
            if(resultCnt > 0){
                resultMap.put("result", AJAX_RESULT.DUP); //중복
            } else {
                userService.insertUser(bean);
                resultMap.put("result", AJAX_RESULT.OK);
            }
        } catch(Exception e) {
            
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }
        return resultMap;
    }
    
    @ResponseBody
    @RequestMapping("/updateUser")
    public Map<String,Object> updateUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute AdminBean bean) throws Exception {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {
            if(("CUST").equals(bean.getAdminBean().getUserCd())){
                int chkPw = userService.checkPassWord(bean);
                if(chkPw > 0){
                    String newUserPw1 = null;
                    String newUserPw2 = null;
                    newUserPw1 = bean.getNewUserPw1();
                    newUserPw2 = bean.getNewUserPw2();
                    if(!StringUtils.isNullOrEmpty(newUserPw1)&&!StringUtils.isNullOrEmpty(newUserPw2)){
                        if((newUserPw1).equals(newUserPw2)){
                            userService.updateUser(bean);
                            AdminBean paramBean = new AdminBean();
                            paramBean.setUserId(bean.getUserId());
                            paramBean.setUserPw(newUserPw1);
                            paramBean.setAdminBean((AdminBean) request.getSession().getAttribute(SESSION_KEY.ADMIN));
                            userService.updateInitPass(paramBean);
                            resultMap.put("result", AJAX_RESULT.OK);                            
                        }else{
                            resultMap.put("result", AJAX_RESULT.NPW); //새 비밀번호 오류 
                        }
                    }else if(StringUtils.isNullOrEmpty(newUserPw1)&&StringUtils.isNullOrEmpty(newUserPw2)){
                        userService.updateUser(bean);
                        resultMap.put("result", AJAX_RESULT.OK);                             
                    }else{
                        resultMap.put("result", AJAX_RESULT.NPW); //새 비밀번호 오류                         
                    }
                }else{
                    resultMap.put("result", AJAX_RESULT.PW); //기본 비밀번호 오류                    
                }
            }else{
                userService.updateUser(bean);
                resultMap.put("result", AJAX_RESULT.OK);            
            }            
        } catch(Exception e) {
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }
        return resultMap;
    }
    
    @ResponseBody
    @RequestMapping("/updateUserStatus")
    public Map<String,Object> updateUserStatus(HttpServletRequest request, HttpServletResponse response, 
                                              @RequestParam(value="userIds[]") String[] userIds, 
                                              @RequestParam(value="status") String status) throws Exception {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        
        try {
            AdminBean adminBean = (AdminBean) request.getSession().getAttribute(SESSION_KEY.ADMIN);
            AdminBean bean = new AdminBean();
            
            if(userIds != null && userIds.length > 0) {
                bean.setIdxs(userIds);
                bean.setStatus(status);
                bean.setAdminBean(adminBean);
            }
            
            userService.updateUsetDelStatus(bean);
            resultMap.put("result", AJAX_RESULT.OK);
            
        } catch(Exception e) {
            
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }
        
        return resultMap;
    }
    
    @ResponseBody
    @RequestMapping("/updateInitPass")
    public Map<String,Object> updateInitPass(HttpServletRequest request, HttpServletResponse response, 
            @ModelAttribute AdminBean bean) throws Exception {
        
        Map<String,Object> resultMap = new HashMap<String,Object>();
        
        try {
            userService.updateInitPass(bean);
            resultMap.put("result", AJAX_RESULT.OK);
        } catch(Exception e) {
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }
        
        return resultMap;
    }
    
    @ResponseBody
    @RequestMapping("/deleteUser")
    public Map<String,Object> deleteUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute AdminBean bean) throws Exception {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        
        try {
            
            if(("CUST").equals(bean.getAdminBean().getUserCd())){
                int chkPw = userService.checkPassWord(bean);
                if(chkPw > 0){
                    userService.deleteUser(bean);
                    resultMap.put("result", AJAX_RESULT.OK);                        
                }else{
                    resultMap.put("result", AJAX_RESULT.PW); //기존 비밀번호 오류                    
                }
            }else{
                userService.deleteUser(bean);
                resultMap.put("result", AJAX_RESULT.OK);                
            }
        } catch(Exception e) {
            
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }
        
        return resultMap;
    }    
}
