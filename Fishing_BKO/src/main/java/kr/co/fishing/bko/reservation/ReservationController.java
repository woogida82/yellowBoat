package kr.co.fishing.bko.reservation;

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

import kr.co.fishing.bko.beans.ReservationBean;
import kr.co.fishing.bko.beans.ReservationDetailBean;
import kr.co.fishing.bko.beans.ShipInfoBean;
import kr.co.fishing.bko.common.utils.CommonConstant.AJAX_RESULT;
import kr.co.fishing.bko.shipInfo.ShipInfoService;


@Controller
@RequestMapping("/bko/reservation")
public class ReservationController {
    @Autowired
    private ShipInfoService shipInfoService;     
    
    @Autowired
    private ReservationService reservationService;
    
    @RequestMapping("")
    public String reservMain(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ShipInfoBean bean, ModelMap model) throws Exception {
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
        
        return "reservation/reservMain";
    }
    
    @RequestMapping("/regReservationView")
    public String regReservationView(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ReservationBean bean, ModelMap model) throws Exception {
        try {        
            ShipInfoBean paramBean = new ShipInfoBean();
            ShipInfoBean resultBean = new ShipInfoBean();
            
            paramBean.setShipId(bean.getShipId());
            resultBean = shipInfoService.selectShipInfo(paramBean);
            
            model.addAttribute("shipBean", resultBean);
            model.addAttribute("bean", bean);
        } catch(Exception e) {
            e.printStackTrace();
        }        
        return "reservation/regReservationView";
    }
    
    @RequestMapping("/reservList")
    public String reservList(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ReservationBean bean, ModelMap model) throws Exception {
        
        return "reservation/reservList";
    }    
    
    @ResponseBody
    @RequestMapping("/insertReservation")
    public Map<String,Object> insertReservation(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ReservationDetailBean bean) throws Exception {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {  
            
            reservationService.insertReservation(bean);
            resultMap.put("result", AJAX_RESULT.OK);
        } catch(Exception e) {
            
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }        
        return resultMap;
    }    
    
    
    
    
}
