package kr.co.fishing.bko.login;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.fishing.bko.common.beans.AdminBean;

@Service
public class LoginServiceImpl implements LoginService {
    
    @Autowired
    private LoginDao adminDao;
    
    /**
     * 관리자 정보 취득
     * 
     * @param bean
     * @return 관리자정보
     * @throws Exception
     */
    @Override
    public AdminBean selectAdminInfo(AdminBean bean) throws Exception {
        AdminBean adminBean = adminDao.selectAdminInfo(bean);
        
        String menuAuth = adminBean.getMenuAuth();
        
        // 메뉴권한을 리스트로 변경
        if (StringUtils.isNotEmpty(menuAuth)) {
            adminBean.setMenuAuthList(Arrays.asList(menuAuth.split(",")));
        }
        
        return adminBean;
    }
}
