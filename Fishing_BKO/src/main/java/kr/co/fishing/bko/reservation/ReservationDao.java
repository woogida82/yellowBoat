package kr.co.fishing.bko.reservation;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.fishing.bko.beans.ReservationBean;
import kr.co.fishing.bko.beans.ReservationDayBean;
import kr.co.fishing.bko.beans.ReservationDetailBean;

@Repository
public class ReservationDao {
    
    @Autowired
    private SqlSession sqlSession;    

    public int insertReservationDay(ReservationDayBean bean) {
        return sqlSession.insert("reservation.insertReservationDay", bean);
    }

    public int insertReservation(ReservationBean bean) {
        return sqlSession.insert("reservation.insertReservation", bean);
    }

    public int insertReservationDetail(ReservationDetailBean bean) {
        return sqlSession.insert("reservation.insertReservationDetail", bean);
    } 
}
