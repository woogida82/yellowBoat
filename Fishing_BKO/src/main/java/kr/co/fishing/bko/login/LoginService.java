package kr.co.fishing.bko.login;

import kr.co.fishing.bko.common.beans.AdminBean;




public interface LoginService {
    
    /**
     * 관리자 정보 취득
     * 
     * @param bean
     * @return 관리자정보
     * @throws Exception
     */
    AdminBean selectAdminInfo(AdminBean bean) throws Exception;
    
}
