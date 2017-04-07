package kr.co.fishing.bko.beans;

import org.springframework.web.multipart.MultipartFile;

import kr.co.fishing.bko.common.beans.CommonBaseBean;


public class FileBean extends CommonBaseBean {

    private String idx; //IDX
    private String phoneNo; //휴대폰번호
    private String fileNm; //파일명
    private String filePath; //파일패스
    private String month; //대상년월

    private String delYn; //삭제여부
    private String updateId;
    private String updateTime;
    private String createId;
    private String createTime;
    
    private String eventId;
    private String orgFilePath;
    private String orgFileNm;
    private String thumFilePath;
    private String thumFileSizeCd;

    private MultipartFile uploadFile;
    
    
    private String[] idxs; //멀티 IDX
    private String status; //상태
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
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
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
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
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getEventId() {
        return eventId;
    }
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    public String getOrgFilePath() {
        return orgFilePath;
    }
    public void setOrgFilePath(String orgFilePath) {
        this.orgFilePath = orgFilePath;
    }
    public String getOrgFileNm() {
        return orgFileNm;
    }
    public void setOrgFileNm(String orgFileNm) {
        this.orgFileNm = orgFileNm;
    }
    public String getThumFilePath() {
        return thumFilePath;
    }
    public void setThumFilePath(String thumFilePath) {
        this.thumFilePath = thumFilePath;
    }
    public String getThumFileSizeCd() {
        return thumFileSizeCd;
    }
    public void setThumFileSizeCd(String thumFileSizeCd) {
        this.thumFileSizeCd = thumFileSizeCd;
    }
    
}
