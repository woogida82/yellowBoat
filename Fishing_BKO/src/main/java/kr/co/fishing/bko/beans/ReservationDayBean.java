package kr.co.fishing.bko.beans;

import java.util.List;

import kr.co.fishing.bko.common.beans.CommonBaseBean;

public class ReservationDayBean extends CommonBaseBean {
    private String reservDayId;
    private String year;
    private String month;
    private String day;
    private String delYn;
    private String updateId;
    private String updateTime;
    private String createId;
    private String createTime;
    
    /* 추가 */
    private List<ReservationBean> reservationBeans;
    private ReservationBean reservationBean;
    private List<ReservationDetailBean> reservationDetailBeans;
    
    private String reservDt;
    private String shipId;

    public String getReservDayId() {
        return reservDayId;
    }
    public void setReservDayId(String reservDayId) {
        this.reservDayId = reservDayId;
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
    public List<ReservationBean> getReservationBeans() {
        return reservationBeans;
    }
    public void setReservationBeans(List<ReservationBean> reservationBeans) {
        this.reservationBeans = reservationBeans;
    }
    public String getReservDt() {
        return reservDt;
    }
    public void setReservDt(String reservDt) {
        this.reservDt = reservDt;
    }
    public ReservationBean getReservationBean() {
        return reservationBean;
    }
    public void setReservationBean(ReservationBean reservationBean) {
        this.reservationBean = reservationBean;
    }
    public List<ReservationDetailBean> getReservationDetailBeans() {
        return reservationDetailBeans;
    }
    public void setReservationDetailBeans(List<ReservationDetailBean> reservationDetailBeans) {
        this.reservationDetailBeans = reservationDetailBeans;
    }
    public String getShipId() {
        return shipId;
    }
    public void setShipId(String shipId) {
        this.shipId = shipId;
    }   
}
