package kr.co.fishing.bko.common.shipInfo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.fishing.bko.beans.ShipInfoBean;

@Repository
public class ShipInfoDao {
	
    @Autowired
    private SqlSession sqlSession;
    
    /**
     * 관리자 정보 취득
     * 
     * @param bean
     * @return 관리자정보
     * @throws Exception
     */
    public ShipInfoBean selectShipInfo(ShipInfoBean bean) throws Exception {
        return sqlSession.selectOne("shipInfo.selectShipInfo", bean);
    }
    
    /**
     * 보트 등록
     * 
     * @param bean
     * @return 관리자정보
     * @throws Exception
     */    
    public int insertShipInfo(ShipInfoBean bean) throws Exception{
        return sqlSession.insert("shipInfo.insertShipInfo", bean);
    }
    
    /**
     * 보트 등록
     * 
     * @param bean
     * @return 관리자정보
     * @throws Exception
     */  
    public int updateShipInfo(ShipInfoBean bean) throws Exception{
        return sqlSession.insert("shipInfo.updateShipInfo", bean);
    }
    
    /**
     * 보트 등록
     * 
     * @param bean
     * @return 관리자정보
     * @throws Exception
     */  
    public int deleteShipInfo(ShipInfoBean bean) throws Exception{
        return sqlSession.insert("shipInfo.deleteShipInfo", bean);
    }    
}
