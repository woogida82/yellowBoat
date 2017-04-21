package kr.co.fishing.bko.user;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.fishing.bko.common.beans.AdminBean;

@Repository
public class UserDao {
	
    @Autowired
    private SqlSession sqlSession;

    public int selectUserListCnt(AdminBean bean) throws Exception{
        return sqlSession.selectOne("user.selectUserListCnt", bean);
    }
    
    public List<AdminBean> selectUserList(AdminBean bean) throws Exception{
        return sqlSession.selectList("user.selectUserList", bean);
    }
    
    public AdminBean selectUser(AdminBean bean) throws Exception{
        return sqlSession.selectOne("user.selectUser", bean);
    }
    
    public int insertUser(AdminBean bean) throws Exception{
        return sqlSession.insert("user.insertUser", bean);
    }
    
    public int updateUser(AdminBean bean) throws Exception{
        return sqlSession.update("user.updateUser", bean);
    }
    
    public int selectUserAdminId(AdminBean bean) throws Exception{
        return sqlSession.selectOne("user.selectUserAdminId", bean);
    }
    
    public int updateUsetDelStatus(AdminBean bean) throws Exception{
        return sqlSession.update("user.updateUsetDelStatus", bean);
    }
    
    public int updateInitPass(AdminBean bean) throws Exception{
        return sqlSession.update("user.updateInitPass", bean);
    }

    public int deleteUser(AdminBean bean) {
        return sqlSession.update("user.deleteUser", bean);
    }
}
