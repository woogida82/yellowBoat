package kr.co.fishing.bko.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.fishing.bko.beans.ReservationBean;
import kr.co.fishing.bko.beans.ReservationDayBean;
import kr.co.fishing.bko.beans.ReservationDetailBean;

@Service
public class ReservationServiceImpl implements ReservationService {
    
    @Autowired
    private ReservationDao reservationDao;
    
    
    /**
     * 예약 등록
     * 
     * @param bean
     * @return int
     * @throws Exception
     */  
    @Override
    public int insertReservation(ReservationDetailBean bean) throws Exception {
        int result = 0;
        //예약일 생성
        ReservationDayBean paramBean1 = new ReservationDayBean();
        paramBean1.setReservDt(bean.getReservDt());
        paramBean1.setAdminBean(bean.getAdminBean());
        result =reservationDao.insertReservationDay(paramBean1);
        
        ReservationBean paramBean2 = new ReservationBean();
        paramBean2 = bean.getReservationBean();
        paramBean2.setReservDayId(paramBean1.getReservDayId());
        paramBean2.setAdminBean(bean.getAdminBean());
        result =reservationDao.insertReservation(paramBean2);
        
        bean.setReservId(paramBean2.getReservId());
        result = reservationDao.insertReservationDetail(bean);
        
        return result;
    }
}
