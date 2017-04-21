package kr.co.fishing.bko.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.fishing.bko.common.beans.AdminBean;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao adminDao;
    
    public int selectUserListCnt(AdminBean bean) throws Exception{
    	return adminDao.selectUserListCnt(bean);
    }
    
    public int selectUserAdminId(AdminBean bean) throws Exception{
    	return adminDao.selectUserAdminId(bean);
    }
    
    public List<AdminBean> selectUserList(AdminBean bean) throws Exception{
    	return adminDao.selectUserList(bean);
    }
  
    public AdminBean selectUser(AdminBean bean) throws Exception{
    	return adminDao.selectUser(bean);
    }
    
    public int insertUser(AdminBean bean) throws Exception{
    	return adminDao.insertUser(bean);
    }
    
    public int updateUser(AdminBean bean) throws Exception{
    	return adminDao.updateUser(bean);
    }
    
    public int updateUsetDelStatus(AdminBean bean) throws Exception{
    	return adminDao.updateUsetDelStatus(bean);
    }
    
    public int updateInitPass(AdminBean bean) throws Exception{
        return adminDao.updateInitPass(bean);
    }
    
    public int deleteUser(AdminBean bean) throws Exception{
        return adminDao.deleteUser(bean);
    }    
}
