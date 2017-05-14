package kr.co.fishing.bko.front;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.fishing.bko.beans.ReservationBean;
import kr.co.fishing.bko.beans.ReservationDayBean;
import kr.co.fishing.bko.beans.ReservationDetailBean;
import kr.co.fishing.bko.beans.ShipInfoBean;
import kr.co.fishing.bko.common.utils.CommonConstant.AJAX_RESULT;
import kr.co.fishing.bko.reservation.ReservationService;

@Controller
@RequestMapping("/front")
public class FrontController {
    
    private final Log logger = LogFactory.getLog(getClass());
    
    @Autowired
    private ReservationService reservationService;    
    
    @RequestMapping("")
    public String front(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        try {
        } catch(Exception e) {
            e.printStackTrace();
        }        
        return "front/main";
    }
    
    @RequestMapping("/reservCalender")
    public String reservCalender(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ReservationDayBean bean, ModelMap model) throws Exception {
        try {
            model.addAttribute("bean", bean);
        } catch(Exception e) {
            e.printStackTrace();
        }        
        return "front/reservCalender";
    }
    
    @ResponseBody
    @RequestMapping("/getReservCalendar")    
    public Map<String, Object> getReservCalendar(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ReservationDayBean bean, ModelMap model) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        try{        
            Calendar cal = Calendar.getInstance();
            
            String strYear = bean.getYear();
            String strMonth = bean.getMonth();            
            
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
             
            if(!StringUtils.isEmpty(strYear)&&!StringUtils.isEmpty(strMonth))
            {
                year = Integer.parseInt(strYear);
                month = Integer.parseInt(strMonth)-1;
                bean.setReservDt(strYear+'.'+strMonth);
            }else{
                bean.setReservDt(String.valueOf(year)+'.'+String.valueOf(month+1));
                strYear = String.valueOf(year);
                strMonth = String.valueOf(month+1);
            }
            
            //년도/월 셋팅
            cal.set(year, month, 1);  
            
            int startDay = cal.getMinimum(java.util.Calendar.DATE);
            int endDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
            int start = cal.get(java.util.Calendar.DAY_OF_WEEK);            
            
            List<ReservationDayBean> resultList = reservationService.selectReservCalender(bean);
            
            
            resultMap.put("rows", resultList);
            
            resultMap.put("year", strYear);
            resultMap.put("month", strMonth);
            resultMap.put("startDay", startDay);
            resultMap.put("endDay", endDay);
            resultMap.put("start", start);
            
            resultMap.put("result", AJAX_RESULT.OK);
        }catch (Exception e) {
            logger.error("## EXCEPTION MESSAGE : " + e.getMessage());
            logger.error("## PRINT STACK TRACE : ", e);
        }
        return resultMap;         
    }
    
    
    @RequestMapping("/reservView")
    public String reservView(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ReservationDetailBean bean, ModelMap model) throws Exception {
        try {
            ReservationDetailBean reservDtlBean = new ReservationDetailBean();
            reservDtlBean = reservationService.selectReservationDetail(bean);            
            model.addAttribute("bean", bean);
            model.addAttribute("dtlBean", reservDtlBean);
        } catch(Exception e) {
            e.printStackTrace();
        }        
        return "front/reservView";
    }
    
    @RequestMapping("/reservWrite")
    public String reservWrite(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ReservationDetailBean bean, ModelMap model) throws Exception {
        try {
            model.addAttribute("bean", bean);
        } catch(Exception e) {
            e.printStackTrace();
        }        
        return "front/reservWrite";
    }   
    
    @ResponseBody
    @RequestMapping("/insertReservation")
    public Map<String,Object> insertReservation(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ReservationDetailBean bean) throws Exception {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {  
            bean.setReservDt(bean.getYear()+"."+bean.getMonth()+"."+bean.getDay());
            ReservationBean reservationBean = new ReservationBean(); 
            reservationBean.setShipId(bean.getShipId());
            bean.setReservationBean(reservationBean);
            reservationService.insertReservation(bean);
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
            reservationService.updateReservationDetail(bean);
            resultMap.put("result", AJAX_RESULT.OK);
        } catch(Exception e) {
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }        
        return resultMap;
    }  
    
    @ResponseBody
    @RequestMapping("/deleteReservationDetail")
    public Map<String,Object> deleteReservationDetail(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ReservationDetailBean bean) throws Exception {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {  
            reservationService.deleteReservationDetail(bean);
            resultMap.put("result", AJAX_RESULT.OK);
        } catch(Exception e) {
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }        
        return resultMap;
    }     
    
}
