package kr.co.fishing.bko.main;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import kr.co.fishing.bko.beans.MenuBean;
import kr.co.fishing.bko.common.beans.AdminBean;

@Repository
public class MainDao {
	
    @Autowired
    private SqlSession sqlSession;
    
    /**
     * 메뉴리스트취득
     * 
     * @param bean
     * @return
     * @throws Exception
     */
    @Cacheable(value="menuList", key="#bean.adminBean.menuAuth")
    public List<MenuBean> selectMenuList(MenuBean bean) throws Exception {
        return sqlSession.selectList("main.selectMenu", bean);
    }
    
    public AdminBean selectInfo(AdminBean bean) throws Exception{
        return sqlSession.selectOne("main.selectInfo", bean);
    }
    
    public int updateInfo(AdminBean bean) throws Exception{
        return sqlSession.update("main.updateInfo", bean);
    }
    
    public int matchPass(AdminBean bean) throws Exception{
        return sqlSession.selectOne("main.matchPass", bean);
    }
    
    public int updatePass(AdminBean bean) throws Exception{
        return sqlSession.update("main.updatePass", bean);
    }
}
