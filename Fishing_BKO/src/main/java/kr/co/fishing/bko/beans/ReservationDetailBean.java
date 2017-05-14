package kr.co.fishing.bko.beans;

import kr.co.fishing.bko.common.beans.CommonBaseBean;

public class ReservationDetailBean extends CommonBaseBean {
    private String reservDtlId;
    private String reservId;
    private String reservNm;
    private String reservHpNo;
    private String reservPw;
    private String reservAdultsNumber;
    private String reservKidsNumber;
    private String reservCd;
    private String deposit;
    private String statusCd;
    private String reservContens;
    private String delYn;
    private String updateId;
    private String updateTime;
    private String createId;
    private String createTime;
    
    /* 추가 */
    private ReservationBean reservationBean;
    
    private String reservDt; //예약일
    private String shipNm; //선박명
    private String shipId; //선박ID
    
    private String year;
    private String month;
    private String day;    

    public String getReservDtlId() {
        return reservDtlId;
    }
    public void setReservDtlId(String reservDtlId) {
        this.reservDtlId = reservDtlId;
    }
    public String getReservId() {
        return reservId;
    }
    public void setReservId(String reservId) {
        this.reservId = reservId;
    }
    public String getReservNm() {
        return reservNm;
    }
    public void setReservNm(String reservNm) {
        this.reservNm = reservNm;
    }
    public String getReservHpNo() {
        return reservHpNo;
    }
    public void setReservHpNo(String reservHpNo) {
        this.reservHpNo = reservHpNo;
    }
    public String getReservAdultsNumber() {
        return reservAdultsNumber;
    }
    public void setReservAdultsNumber(String reservAdultsNumber) {
        this.reservAdultsNumber = reservAdultsNumber;
    }
    public String getReservKidsNumber() {
        return reservKidsNumber;
    }
    public void setReservKidsNumber(String reservKidsNumber) {
        this.reservKidsNumber = reservKidsNumber;
    }
    public String getReservCd() {
        return reservCd;
    }
    public void setReservCd(String reservCd) {
        this.reservCd = reservCd;
    }
    public String getDeposit() {
        return deposit;
    }
    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }
    public String getStatusCd() {
        return statusCd;
    }
    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }
    public String getReservContens() {
        return reservContens;
    }
    public void setReservContens(String reservContens) {
        this.reservContens = reservContens;
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
    public ReservationBean getReservationBean() {
        return reservationBean;
    }
    public void setReservationBean(ReservationBean reservationBean) {
        this.reservationBean = reservationBean;
    }
    public String getReservDt() {
        return reservDt;
    }
    public void setReservDt(String reservDt) {
        this.reservDt = reservDt;
    }
    public String getReservPw() {
        return reservPw;
    }
    public void setReservPw(String reservPw) {
        this.reservPw = reservPw;
    }
    public String getShipNm() {
        return shipNm;
    }
    public void setShipNm(String shipNm) {
        this.shipNm = shipNm;
    }
    public String getShipId() {
        return shipId;
    }
    public void setShipId(String shipId) {
        this.shipId = shipId;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
}
