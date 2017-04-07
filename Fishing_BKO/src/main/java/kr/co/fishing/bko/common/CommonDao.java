package kr.co.fishing.bko.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import kr.co.fishing.bko.common.beans.CommonCodeBean;
import kr.co.fishing.bko.common.beans.CommonFileBean;



@Repository
public class CommonDao {
	
    @Autowired
    private SqlSession sqlSession;
    
    /**
     * 공통코드 리스트
     * 
     * @param bean
     * @return
     * @throws Exception
     */
    @Cacheable(value="commonCodeList", key="#masterCd")
    public List<CommonCodeBean> selectCommonCodeList(String masterCd) throws Exception {
        return sqlSession.selectList("commonCode.selectCommonCodeList", masterCd);
    }
    
    /**
     * 다운로드 파일 정보 취득
     * 
     * @param bean
     * @return
     * @throws Exception
     */
    public CommonFileBean selectFileInfo(String downId, Map<String, String> paramMap) throws Exception {
        
        paramMap.put("downId", downId);
        
        return sqlSession.selectOne("file.selectFile", paramMap);
    }
}
