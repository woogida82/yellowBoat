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
    
    /**
     * 예약 메인
     * 
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */     
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
    
    /**
     * 예약 상세
     * 
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */    
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
    
    /**
     * 예약 상세 목록
     * 
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */    
    @RequestMapping("/reservDetailList")
    public String reservDetailList(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ReservationBean bean, ModelMap model) throws Exception {
        try {        
            int resultCnt = reservationService.selectReservationDetailCnt(bean); 
            bean.setTotalCount(resultCnt);
            
            List<ReservationDetailBean> resultList = reservationService.selectReservationDetailList(bean);
            
            ShipInfoBean shipBean = new ShipInfoBean();
            ShipInfoBean paramBean = new ShipInfoBean();
            paramBean.setShipId(bean.getShipId());
            shipBean = shipInfoService.selectShipInfo(paramBean);
            
            model.addAttribute("bean", bean);
            model.addAttribute("shipBean", shipBean);
            model.addAttribute("resultList", resultList);
            model.addAttribute("records", resultCnt); 
        } catch(Exception e) {
            e.printStackTrace();
        }        
        
        return "reservation/reservDetailList";
    }    
    
    @RequestMapping("/reservationDetailView")
    public String reservationDetailView(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ReservationDetailBean bean, ModelMap model) throws Exception {
        try {   
            ReservationDetailBean reservDtlBean = new ReservationDetailBean();
            reservDtlBean = reservationService.selectReservationDetail(bean);
            
            ShipInfoBean shipBean = new ShipInfoBean();
            ShipInfoBean paramBean = new ShipInfoBean();
            paramBean.setShipId(reservDtlBean.getShipId());
            shipBean = shipInfoService.selectShipInfo(paramBean);            
            
            model.addAttribute("reservDtlBean", reservDtlBean);  
            model.addAttribute("shipBean", shipBean);
            model.addAttribute("bean", bean); 
        } catch(Exception e) {
            e.printStackTrace();
        }         
        return "reservation/reservationDetailView";        
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
    
    @ResponseBody
    @RequestMapping("/reservDtlStatus")
    public Map<String,Object> reservDtlStatus(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ReservationDetailBean bean) throws Exception {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {  
            
            reservationService.updateReservDtlStatus(bean);
            resultMap.put("result", AJAX_RESULT.OK);
        } catch(Exception e) {
            
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }        
        return resultMap;
    }
    
    @ResponseBody
    @RequestMapping("/updateReservation")
    public Map<String,Object> updateReservation(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ReservationDetailBean bean) throws Exception {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {  
            reservationService.updateReservation(bean);
            resultMap.put("result", AJAX_RESULT.OK);
        } catch(Exception e) {
            
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }        
        return resultMap;
    }    
}
