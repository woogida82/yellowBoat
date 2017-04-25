package kr.co.fishing.bko.user;

import java.util.List;

import kr.co.fishing.bko.common.beans.AdminBean;

public interface UserService {
    
    int selectUserListCnt(AdminBean bean) throws Exception;
    
    int selectUserAdminId(AdminBean bean) throws Exception;
    
    List<AdminBean> selectUserList(AdminBean bean) throws Exception;
  
    AdminBean selectUser(AdminBean bean) throws Exception;
    
    int insertUser(AdminBean bean) throws Exception;
    
    int updateUser(AdminBean bean) throws Exception;
    
    int updateUsetDelStatus(AdminBean bean) throws Exception;
    
    int updateInitPass(AdminBean bean) throws Exception;

    int deleteUser(AdminBean bean) throws Exception;

    int checkPassWord(AdminBean bean) throws Exception;
}
