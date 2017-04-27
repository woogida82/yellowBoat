package kr.co.fishing.bko.beans;

import java.util.List;

import kr.co.fishing.bko.common.beans.CommonBaseBean;

public class ReservationBean extends CommonBaseBean {
    private String reservId;
    private String shipId;
    private String reservDayId;
    private String statusCd;
    private String delYn;
    private String updateId;
    private String updateTime;
    private String createId;
    private String createTime;
    
    /* 추가 */
    private ShipInfoBean shipInfoBean; 
    private ReservationDayBean reservationDayBean;
    
    private List<ReservationDetailBean> reservationDetailBeans;
    
    private String reservDt;
    
    public String getReservId() {
        return reservId;
    }
    public void setReservId(String reservId) {
        this.reservId = reservId;
    }
    public String getShipId() {
        return shipId;
    }
    public void setShipId(String shipId) {
        this.shipId = shipId;
    }
    public String getReservDayId() {
        return reservDayId;
    }
    public void setReservDayId(String reservDayId) {
        this.reservDayId = reservDayId;
    }
    public String getStatusCd() {
        return statusCd;
    }
    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
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
    public ShipInfoBean getShipInfoBean() {
        return shipInfoBean;
    }
    public void setShipInfoBean(ShipInfoBean shipInfoBean) {
        this.shipInfoBean = shipInfoBean;
    }
    public ReservationDayBean getReservationDayBean() {
        return reservationDayBean;
    }
    public void setReservationDayBean(ReservationDayBean reservationDayBean) {
        this.reservationDayBean = reservationDayBean;
    }
    public String getReservDt() {
        return reservDt;
    }
    public void setReservDt(String reservDt) {
        this.reservDt = reservDt;
    }
    public List<ReservationDetailBean> getReservationDetailBeans() {
        return reservationDetailBeans;
    }
    public void setReservationDetailBeans(List<ReservationDetailBean> reservationDetailBeans) {
        this.reservationDetailBeans = reservationDetailBeans;
    }  
}
