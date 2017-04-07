package kr.co.fishing.bko.login;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.fishing.bko.common.beans.AdminBean;

@Repository
public class LoginDao {
	
    @Autowired
    private SqlSession sqlSession;
    
    /**
     * 관리자 정보 취득
     * 
     * @param bean
     * @return 관리자정보
     * @throws Exception
     */
    public AdminBean selectAdminInfo(AdminBean bean) throws Exception {
        return sqlSession.selectOne("admin.selectAdminInfo", bean);
    }
}
