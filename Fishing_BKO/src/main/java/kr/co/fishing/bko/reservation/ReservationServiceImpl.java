package kr.co.fishing.bko.reservation;

import java.util.List;

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
     * @param ReservationDetailBean
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

    /**
     * 예약 상세 건수
     * 
     * @param ReservationBean
     * @return int
     * @throws Exception
     */
    @Override
    public int selectReservationDetailCnt(ReservationBean bean) throws Exception {
        return reservationDao.selectReservationDetailCnt(bean);
    }

    /**
     * 예약 상세 리스트 조회
     * 
     * @param ReservationBean
     * @return List<ReservationDetailBean>
     * @throws Exception
     */
    @Override
    public List<ReservationDetailBean> selectReservationDetailList(ReservationBean bean) throws Exception {
        return reservationDao.selectReservationDetailList(bean);
    }

    /**
     * 예약 상세 조회
     * 
     * @param ReservationDetailBean
     * @return ReservationDetailBean
     * @throws Exception
     */
    @Override
    public ReservationDetailBean selectReservationDetail(ReservationDetailBean bean) throws Exception {
        ReservationDetailBean reservationDetailBean = reservationDao.selectReservationDetail(bean);
        return reservationDetailBean;
    }
    
    /**
     * 예약상세 상태 변경
     * 
     * @param ReservationDetailBean
     * @return int
     * @throws Exception
     */
    @Override
    public int updateReservDtlStatus(ReservationDetailBean bean) throws Exception {
        return reservationDao.updateReservDtlStatus(bean);
    }

    /**
     * 예약 수정
     * 
     * @param ReservationDetailBean
     * @return int
     * @throws Exception
     */    
    @Override
    public int updateReservation(ReservationDetailBean bean) throws Exception {
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
        result = reservationDao.updateReservationDetail(bean);
        
        return result;
    }
    
    /**
     * 예약 수정
     * 
     * @param ReservationDetailBean
     * @return int
     * @throws Exception
     */    
    @Override
    public int updateReservationDetail(ReservationDetailBean bean) throws Exception {
        int result = 0;
        //예약일 생성
        result = reservationDao.updateReservationDetail(bean);
        return result;
    }    

    @Override
    public List<ReservationDayBean> selectReservCalender(ReservationDayBean bean) throws Exception {
        return reservationDao.selectReservCalender(bean);
    }

    @Override
    public int deleteReservationDetail(ReservationDetailBean bean) throws Exception {
        int result = 0;
        //예약일 생성
        result = reservationDao.deleteReservationDetail(bean);
        return result;
    }
}
