package kr.co.fishing.bko.common.shipInfo;

import kr.co.fishing.bko.beans.ShipInfoBean;




public interface ShipInfoService {
    
    /**
     * 보트 정보 취득
     * 
     * @param bean
     * @return 관리자정보
     * @throws Exception
     */
    ShipInfoBean selectShipInfo(ShipInfoBean bean) throws Exception;
    
    /**
     * 보트 등록
     * 
     * @param bean
     * @return 관리자정보
     * @throws Exception
     */    
    int insertShipInfo(ShipInfoBean bean) throws Exception;
    
    /**
     * 보트 정보 수정
     * 
     * @param bean
     * @return 관리자정보
     * @throws Exception
     */   
    int updateShipInfo(ShipInfoBean paramBean) throws Exception;
    
    /**
     * 보트 삭제
     * 
     * @param bean
     * @return 관리자정보
     * @throws Exception
     */   
    int deleteShipInfo(ShipInfoBean paramBean) throws Exception;   
}
