package kr.co.fishing.bko.beans;

import kr.co.fishing.bko.common.beans.CommonBaseBean;

public class MenuBean extends CommonBaseBean {
    
    private String menuIdx;
    private String menuId;
    private String subId;
    private String menuNm;
    private String uri;
    private String useYn;
    private String material;
    private String lastYn;
    private String directYn;
    
	public String getMenuIdx() {
		return menuIdx;
	}
	public void setMenuIdx(String menuIdx) {
		this.menuIdx = menuIdx;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getSubId() {
		return subId;
	}
	public void setSubId(String subId) {
		this.subId = subId;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public String getLastYn() {
        return lastYn;
    }
    public void setLastYn(String lastYn) {
        this.lastYn = lastYn;
    }
    public String getDirectYn() {
        return directYn;
    }
    public void setDirectYn(String directYn) {
        this.directYn = directYn;
    }
    
}
