package kr.co.fishing.bko.reservation;

import java.util.List;

import kr.co.fishing.bko.beans.ReservationBean;
import kr.co.fishing.bko.beans.ReservationDayBean;
import kr.co.fishing.bko.beans.ReservationDetailBean;

public interface ReservationService {

    /**
     * 예약 등록
     * 
     * @param ReservationDetailBean
     * @return int
     * @throws Exception
     */    
    int insertReservation(ReservationDetailBean bean) throws Exception;
    
    /**
     * 예약상세 건수 조회
     * 
     * @param ReservationBean
     * @return int
     * @throws Exception
     */ 
    int selectReservationDetailCnt(ReservationBean bean) throws Exception; 
    
    /**
     * 예약상세 List 조회
     * 
     * @param ReservationBean
     * @return int
     * @throws Exception
     */ 
    List<ReservationDetailBean> selectReservationDetailList(ReservationBean bean) throws Exception;

    /**
     * 예약상세 조회
     * 
     * @param ReservationDetailBean
     * @return int
     * @throws Exception
     */ 
    ReservationDetailBean selectReservationDetail(ReservationDetailBean bean) throws Exception;

    /**
     * 예약상세 상태 변경
     * 
     * @param ReservationDetailBean
     * @return int
     * @throws Exception
     */     
    int updateReservDtlStatus(ReservationDetailBean bean) throws Exception;
    
    /**
     * 예약 수정
     * 
     * @param ReservationDetailBean
     * @return int
     * @throws Exception
     */    
    int updateReservation(ReservationDetailBean bean) throws Exception;
    
    List<ReservationDayBean> selectReservCalender(ReservationDayBean bean) throws Exception;

    int updateReservationDetail(ReservationDetailBean bean) throws Exception;

    int deleteReservationDetail(ReservationDetailBean bean) throws Exception;
}
