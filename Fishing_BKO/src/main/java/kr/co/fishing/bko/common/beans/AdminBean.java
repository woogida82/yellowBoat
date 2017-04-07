package kr.co.fishing.bko.common.beans;

import java.util.ArrayList;
import java.util.List;

public class AdminBean extends CommonBaseBean {
    
    private String adminId;
    private String adminPw;
    private String adminNm;//이름 
    private String menuAuth;//메뉴권한
    private String dept;//부서
    private String team;//팀
    private String post;//직위
    private String duty;//직책
    private String delYn;
    
    private String updateId;
    private String updateTime;
    private String createId;
    private String createTime;

    private String newPw;
    private List<String> menuAuthList = new ArrayList<String>();
    private List<String> dataAuthList = new ArrayList<String>();
    
    private String[] idxs; //멀티 IDX
    private String status; //상태
    
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminPw() {
		return adminPw;
	}
	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}
	public String getAdminNm() {
		return adminNm;
	}
	public void setAdminNm(String adminNm) {
		this.adminNm = adminNm;
	}
    public String getMenuAuth() {
		return menuAuth;
	}
	public void setMenuAuth(String menuAuth) {
		this.menuAuth = menuAuth;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getNewPw() {
        return newPw;
    }
    public void setNewPw(String newPw) {
        this.newPw = newPw;
    }
    public List<String> getMenuAuthList() {
        return menuAuthList;
    }
    public void setMenuAuthList(List<String> menuAuthList) {
        this.menuAuthList = menuAuthList;
    }
    public List<String> getDataAuthList() {
        return dataAuthList;
    }
    public void setDataAuthList(List<String> dataAuthList) {
        this.dataAuthList = dataAuthList;
    }
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
    public String[] getIdxs() {
        return idxs;
    }
    public void setIdxs(String[] idxs) {
        this.idxs = idxs;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
}
