<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/meta.jsp" %>
<%@ include file="/jsp/common/taglibs.jsp" %>
<%@ include file="/jsp/common/style.jsp" %>

<div class="content_box">
    <div class="util">
        <ul>
            <li class="list1"><a href="#"  onclick="ContentBody.returnHome();">Home</a></li>
            <li>예약관리</li>
            <li><a href="#"  onclick="ContentBody.reload();">예약관리</a></li>
        </ul>
    </div>
    <!--end util-->
    <div class="top_img">
        <h2>예약 정보</h2>
        <img src="/images/common/top_img1.png" />
    </div>
    <!--end top_img-->
    <div class="content">
        <h3>예약 정보</h3>
        <div class="table_write">
            <form action="" method="post" name="reservationDetailForm" id="reservationDetailForm" enctype="multipart/form-data">
                <input type="hidden" id="reservDtlId" name="reservDtlId" value="${reservDtlBean.reservDtlId}"/>
                <input type="hidden" id="shipId" name="shipId" value="${reservDtlBean.shipId}"/>
                
                <table width="100%">
                    <tr>
                        <td class="title">선박명</td>
                        <td colspan="3">
                            <input type="text" style="width:150px;" id="shipNm" name="shipNm" data-id="shipNm" value="${reservDtlBean.shipNm}" readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="title">예약일자</td>
                        <td colspan="3">
                            <input type="text" style="width:150px;" id="reservDt" name="reservDt" data-id="reservDt" value="${reservDtlBean.reservDt}" valid="{label:'예약일자', method:'required'}" readonly="readonly"/>
                        </td>
                    </tr>  
                    <tr>
                        <td class="title">예약자명</td>
                        <td colspan="3">
                            <input type="text" style="width:150px;" id="reservNm" name="reservNm" data-id="reservNm" value="${reservDtlBean.reservNm}" valid="{label:'예약자명', method:'required'}"/>
                        </td>
                    </tr>

                    <tr>
                        <td class="title">휴대폰번호</td>
                        <td colspan="3">
                            <input type="text" style="width:150px;" id="reservHpNo" name="reservHpNo" data-id="reservHpNo" value="${reservDtlBean.reservHpNo}" valid="{label:'휴대폰번호', method:'required', type:'tel'}"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="title">예약인원</td>
                        <td colspan="3">
                            대인 : <input type="text" style="width:150px;" id="reservAdultsNumber" name="reservAdultsNumber" data-id="reservAdultsNumber" value="${reservDtlBean.reservAdultsNumber}" valid="{label:'대인', method:'required', type:'number'}"/>
                            소인 : <input type="text" style="width:150px;" id="reservKidsNumber" name="reservKidsNumber" data-id="reservKidsNumber" value="${reservDtlBean.reservKidsNumber}" valid="{label:'소인', type:'number'}"/>
                        </td>
                    </tr>
                </table>                        
                <div class="btn_box">
                    <a href="#" id="updateReservDtlButton">수정</a>
                    <a href="#" id="cancelButton">취소</a>
                </div>
                <!--end btn_box-->
            </form>
        </div>
        <!--end table_write-->
    </div>
    <!--end content--> 
</div>
<%@ include file="/jsp/common/javascript.jsp" %>  
