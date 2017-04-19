package kr.co.fishing.bko.common.shipInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.fishing.bko.beans.ShipInfoBean;

@Service
public class ShipInfoServiceImpl implements ShipInfoService {
    
    @Autowired
    private ShipInfoDao shipInfoDao;
    
    /**
     * 관리자 정보 취득
     * 
     * @param bean
     * @return 관리자정보
     * @throws Exception
     */
    @Override
    public ShipInfoBean selectShipInfo(ShipInfoBean bean) throws Exception {
        ShipInfoBean shipInfoBean = shipInfoDao.selectShipInfo(bean);
        return shipInfoBean;
    }

    /**
     * 보트 등록
     * 
     * @param bean
     * @return 관리자정보
     * @throws Exception
     */
    public int insertShipInfo(ShipInfoBean bean) throws Exception{
        return shipInfoDao.insertShipInfo(bean);
    }    
}
