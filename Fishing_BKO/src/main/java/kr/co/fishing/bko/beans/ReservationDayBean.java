package kr.co.fishing.bko.beans;

import java.util.List;

import kr.co.fishing.bko.common.beans.CommonBaseBean;

public class ReservationDayBean extends CommonBaseBean {
    private String reserv_Day_Id;
    private String year;
    private String month;
    private String day;
    private String delYn;
    private String updateId;
    private String updateTime;
    private String createId;
    private String createTime;
    
    private List<ReservationBean> reservationBeans;
    
    public String getReserv_Day_Id() {
        return reserv_Day_Id;
    }
    public void setReserv_Day_Id(String reserv_Day_Id) {
        this.reserv_Day_Id = reserv_Day_Id;
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
}
