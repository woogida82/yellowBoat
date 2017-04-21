package kr.co.fishing.bko.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.fishing.bko.beans.MenuBean;
import kr.co.fishing.bko.common.beans.AdminBean;
import kr.co.fishing.bko.common.exception.BizException;
import kr.co.fishing.bko.common.utils.CommonConstant.AJAX_RESULT;

@Controller
@RequestMapping("/bko")
public class MainController {
    
    @Autowired
    private MainService mainService;

    /**
     * 로그인화면 표시
     * 
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/main")
    public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        return "layout/main";
    }
    
    @ResponseBody
    @RequestMapping("/selectInfo")
    public Map<String,Object> selectInfo(HttpServletRequest request, HttpServletResponse response, @ModelAttribute AdminBean bean) {
        
        Map<String,Object> resultMap = new HashMap<String,Object>();
        
        try {
            
            resultMap.put("selectInfo", mainService.selectInfo(bean));
            
            resultMap.put("result", AJAX_RESULT.OK);
        } catch(Exception e) {
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }
            
        return resultMap;
    }
            
    /**
     * 메뉴리스트 취득
     * 
     * @param request
     * @param response
     * @param menuBean
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getMenuList")
    public Map<String,Object> getMenuList(HttpServletRequest request, HttpServletResponse response, 
            @ModelAttribute MenuBean menuBean) throws Exception {

        Map<String,Object> resultMap = new HashMap<String,Object>();

        try {

            // 메뉴리스트 취득
            List<MenuBean> menuList = mainService.selectMenuList(menuBean);
            
            resultMap.put("menuList", menuList);
            resultMap.put("result", AJAX_RESULT.OK);

        } catch(Exception e) {
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }

        return resultMap;
    }
    
}
