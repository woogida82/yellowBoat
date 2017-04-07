package kr.co.fishing.bko.common;

import java.util.List;
import java.util.Map;

import kr.co.fishing.bko.common.beans.CommonCodeBean;
import kr.co.fishing.bko.common.beans.CommonFileBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {
    
    @Autowired
    private CommonDao commonDao;
    
    /**
     * 다운로드 파일 정보 취득
     * 
     * @param bean
     * @return
     * @throws Exception
     */
    public CommonFileBean selectFileInfo(String downId, Map<String, String> paramMap) throws Exception {
    	return commonDao.selectFileInfo(downId, paramMap);
    }
    
    /**
     * 공통코드 리스트
     * 
     * @param bean
     * @return
     * @throws Exception
     */
    @Cacheable(value="commonCodeList", key="#masterCd")
    public List<CommonCodeBean> selectCommonCodeList(String masterCd) throws Exception {
        return commonDao.selectCommonCodeList(masterCd);
    }
}
