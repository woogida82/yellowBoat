package kr.co.fishing.bko.reservation;

import kr.co.fishing.bko.beans.ReservationDetailBean;

public interface ReservationService {

    /**
     * 예약 등록
     * 
     * @param bean
     * @return int
     * @throws Exception
     */    
    int insertReservation(ReservationDetailBean bean) throws Exception;
}
