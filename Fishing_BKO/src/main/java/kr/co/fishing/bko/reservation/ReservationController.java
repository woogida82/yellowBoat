package kr.co.fishing.bko.reservation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.fishing.bko.beans.ShipInfoBean;
import kr.co.fishing.bko.shipInfo.ShipInfoService;


@Controller
@RequestMapping("/bko/reservation")
public class ReservationController {
    @Autowired
    private ShipInfoService shipInfoService;       
    
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
}
