package kr.co.fishing.bko.shipInfo;

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
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.fishing.bko.beans.ShipInfoBean;
import kr.co.fishing.bko.common.utils.CommonConstant.AJAX_RESULT;


@Controller
@RequestMapping("/bko/shipInfo")
public class ShipInfoController {
    
    
    @Autowired
    private ShipInfoService shipInfoService;    
    
    @RequestMapping("")
    public String shipInfo(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ShipInfoBean bean, ModelMap model) throws Exception {
        try {
            int resultCnt = shipInfoService.selectShipInfoListCnt(bean);
            bean.setTotalCount(resultCnt);
            
            List<ShipInfoBean> resultList = shipInfoService.selectShipInfoList(bean);
            
            model.addAttribute("bean", bean);
            model.addAttribute("rows", resultList);
            model.addAttribute("records", resultCnt);            
        } catch(Exception e) {
            e.printStackTrace();
        }        
        
        return "shipInfo/shipInfoList";
    }
        
    
    @ResponseBody
    @RequestMapping("/insertShipInfo")
    public Map<String,Object> insertPlanDrug(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ShipInfoBean bean) throws Exception {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        
        try {
                bean.setShipCd("00");
                bean.setStatusCd("00");
                shipInfoService.insertShipInfo(bean);
                resultMap.put("result", AJAX_RESULT.OK);
        } catch(Exception e) {
            
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }
        return resultMap;
    }
    
    @ResponseBody
    @RequestMapping("/updateShipInfo")
    public Map<String,Object> updateShipInfo(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ShipInfoBean bean) throws Exception {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        
        try {
    	    bean.setShipCd("00");
    	    bean.setStatusCd("00");        	
        	shipInfoService.updateShipInfo(bean);
            resultMap.put("result", AJAX_RESULT.OK);
            
        } catch(Exception e) {
            
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }
        
        return resultMap;
    }
    
    @ResponseBody
    @RequestMapping("/deleteShipInfo")
    public Map<String,Object> deleteShipInfo(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ShipInfoBean bean) throws Exception {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {
            shipInfoService.deleteShipInfo(bean);   
            resultMap.put("result", AJAX_RESULT.OK);
        } catch(Exception e) {
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }
        
        return resultMap;
    }    
}