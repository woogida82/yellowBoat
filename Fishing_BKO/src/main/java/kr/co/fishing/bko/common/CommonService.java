package kr.co.fishing.bko.common;

import java.util.List;
import java.util.Map;

import kr.co.fishing.bko.common.beans.CommonCodeBean;
import kr.co.fishing.bko.common.beans.CommonFileBean;



public interface CommonService {
	
	/**
     * 다운로드 파일 정보 취득
     * 
     * @param bean
     * @return
     * @throws Exception
     */
    CommonFileBean selectFileInfo(String downId, Map<String, String> paramMap) throws Exception;
    
    /**
     * 공통코드 리스트
     * 
     * @param bean
     * @return
     * @throws Exception
     */
    List<CommonCodeBean> selectCommonCodeList(String masterCd) throws Exception;
}
