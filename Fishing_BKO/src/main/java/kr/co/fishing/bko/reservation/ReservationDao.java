package kr.co.fishing.bko.reservation;

import java.util.List;

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

    /**
     * 예약일 생성
     * 
     * @param ReservationDayBean
     * @return int
     * @throws Exception
     */      
    public int insertReservationDay(ReservationDayBean bean) throws Exception {
        return sqlSession.insert("reservation.insertReservationDay", bean);
    }

    /**
     * 예약 등록
     * 
     * @param ReservationBean
     * @return int
     * @throws Exception
     */     
    public int insertReservation(ReservationBean bean) throws Exception {
        return sqlSession.insert("reservation.insertReservation", bean);
    }

    /**
     * 예약 상세 등록
     * 
     * @param ReservationDetailBean
     * @return int
     * @throws Exception
     */ 
    public int insertReservationDetail(ReservationDetailBean bean) throws Exception {
        return sqlSession.insert("reservation.insertReservationDetail", bean);
    }

    /**
     * 예약상세 건수 조회
     * 
     * @param ReservationBean
     * @return int
     * @throws Exception
     */ 
    public int selectReservationDetailCnt(ReservationBean bean) throws Exception {
        return sqlSession.selectOne("reservation.selectReservationDetailCnt", bean);
    }

    /**
     * 예약상세 목록 조회
     * 
     * @param ReservationBean
     * @return List<ReservationDetailBean>
     * @throws Exception
     */ 
    public List<ReservationDetailBean> selectReservationDetailList(ReservationBean bean) throws Exception {
        return sqlSession.selectList("reservation.selectReservationDetailList", bean);
    }

    /**
     * 예약상세 조회
     * 
     * @param ReservationDetailBean
     * @return ReservationDetailBean
     * @throws Exception
     */     
    public ReservationDetailBean selectReservationDetail(ReservationDetailBean bean) throws Exception {
        return sqlSession.selectOne("reservation.selectReservationDetail", bean);
    }
    
    /**
     * 예약상세 상태 변경
     * 
     * @param bean
     * @return int
     * @throws Exception
     */
    public int updateReservDtlStatus(ReservationDetailBean bean) {
        return sqlSession.update("reservation.updateReservDtlStatus", bean);
    }
    
    /**
     * 예약상세 수정
     * 
     * @param bean
     * @return int
     * @throws Exception
     */
    public int updateReservationDetail(ReservationDetailBean bean) {
        return sqlSession.update("reservation.updateReservationDetail", bean);
    } 
    
    public List<ReservationDayBean> selectReservCalender(ReservationDayBean bean){
        return sqlSession.selectList("reservation.selectReservCalender", bean);
    }

    public int deleteReservationDetail(ReservationDetailBean bean) {
        // TODO Auto-generated method stub
        return sqlSession.update("reservation.deleteReservationDetail", bean);
    }
}
