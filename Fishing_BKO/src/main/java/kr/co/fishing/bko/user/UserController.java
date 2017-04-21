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

import kr.co.fishing.bko.beans.ShipInfoBean;
import kr.co.fishing.bko.common.beans.AdminBean;
import kr.co.fishing.bko.common.beans.CommonBaseBean;
import kr.co.fishing.bko.common.shipInfo.ShipInfoService;
import kr.co.fishing.bko.common.utils.CommonConstant.AJAX_RESULT;
import kr.co.fishing.bko.common.utils.CommonConstant.SESSION_KEY;


@Controller
@RequestMapping("/bko/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ShipInfoService shipInfoService;    
    
    @RequestMapping("")
    public String user(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        return "user/userList";
    }
    
    @ResponseBody
    @RequestMapping("/userList")
    public Map<String,Object> userListPaging(HttpServletRequest request, HttpServletResponse response, @ModelAttribute AdminBean bean) throws Exception {
        
        Map<String,Object> resultMap = new HashMap<String,Object>();
        
        try {
            
            int resultCnt = userService.selectUserListCnt(bean);
            
            if(resultCnt > 0){
                List<AdminBean> resultList = userService.selectUserList(bean);
                resultMap.put("rows", resultList);
                resultMap.put("records", resultCnt);
            }
            
            resultMap.put("result", AJAX_RESULT.OK);
            
        } catch(Exception e) {
            
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }
        
        return resultMap;
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
        AdminBean resultUserBean = new AdminBean();
        ShipInfoBean resultShipBean = new ShipInfoBean();
        resultUserBean = userService.selectUser(bean);
        ShipInfoBean paramBean = new ShipInfoBean();
        paramBean.setUserId(bean.getUserId());
        resultShipBean = shipInfoService.selectShipInfo(paramBean);
        
//        model.addAttribute("userId", bean.getUserId());
        model.addAttribute("userBean", resultUserBean);
        model.addAttribute("shipBean", resultShipBean);
        return "user/userDetailView";
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
//            
            if(resultCnt > 0){
                resultMap.put("result", AJAX_RESULT.DUP); //중복
            } else {
                userService.insertUser(bean);
                ShipInfoBean shipInfoBean = new ShipInfoBean();
                shipInfoBean = bean.getShipInfoBean();
                shipInfoBean.setUserId(bean.getUserId());
                shipInfoBean.setShipCd("00");
                shipInfoBean.setStatusCd("00");
                shipInfoBean.setAdminBean(bean.getAdminBean());
                shipInfoService.insertShipInfo(shipInfoBean);
                
                resultMap.put("result", AJAX_RESULT.OK);
            }
//            
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
        	userService.updateUser(bean);
            ShipInfoBean paramBean = new ShipInfoBean();
            paramBean = bean.getShipInfoBean();        	
        	if(!paramBean.getShipId().isEmpty()){
            	paramBean.setShipCd("00");
            	paramBean.setStatusCd("00");        	
            	paramBean.setAdminBean(bean.getAdminBean());
            	shipInfoService.updateShipInfo(paramBean);
        	}
            resultMap.put("result", AJAX_RESULT.OK);
            
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
            bean.setUserPw(bean.getUserId()+"1234");
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
            userService.deleteUser(bean);
            
            ShipInfoBean paramBean = new ShipInfoBean();
            paramBean = bean.getShipInfoBean();
            if(!paramBean.getShipId().isEmpty()){
                paramBean.setAdminBean(bean.getAdminBean());
                shipInfoService.deleteShipInfo(paramBean);   
            }
            resultMap.put("result", AJAX_RESULT.OK);
        } catch(Exception e) {
            
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }
        
        return resultMap;
    }    
}
