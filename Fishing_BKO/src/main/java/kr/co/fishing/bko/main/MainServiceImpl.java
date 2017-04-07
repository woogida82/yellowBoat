package kr.co.fishing.bko.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.fishing.bko.beans.MenuBean;
import kr.co.fishing.bko.common.beans.AdminBean;
import kr.co.fishing.bko.common.exception.BizException;
import kr.co.fishing.bko.common.utils.ContextUtil;
import kr.co.fishing.bko.common.utils.CommonConstant.ERROR_CODE;

@Service
public class MainServiceImpl implements MainService {
    
    @Autowired
    private MainDao mainDao;
    
    @Autowired
    private ContextUtil contextUtil;
    
    /**
     * 메뉴리스트취득
     * 
     * @param bean
     * @return
     * @throws Exception
     */
    @Override
    public List<MenuBean> selectMenuList(MenuBean bean) throws Exception {
    	return mainDao.selectMenuList(bean);
    }
    
    public AdminBean selectInfo(AdminBean bean) throws Exception{
        return mainDao.selectInfo(bean);
    }
    
    public int updateInfo(AdminBean bean) throws Exception{
        return mainDao.updateInfo(bean);
    }
    
    public void updatePass(AdminBean bean) throws Exception{
        List<String> errList = new ArrayList<String>();
        
        if(mainDao.matchPass(bean) == 0) {
            // 에러처리
            errList.add(contextUtil.getMessage(ERROR_CODE.MSG_0002E));
            throw new BizException(errList);
        } else {
            mainDao.updatePass(bean);
        }
        
        
    }
}
