package kr.co.fishing.bko.main;

import java.util.List;

import kr.co.fishing.bko.beans.MenuBean;
import kr.co.fishing.bko.common.beans.AdminBean;



public interface MainService {
    
    /**
     * 메뉴리스트취득
     * 
     * @param bean
     * @return
     * @throws Exception
     */
    List<MenuBean> selectMenuList(MenuBean bean) throws Exception;
    
    AdminBean selectInfo(AdminBean bean) throws Exception;
}
