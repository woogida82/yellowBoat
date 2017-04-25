<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/meta.jsp" %>
<%@ include file="/jsp/common/taglibs.jsp" %>
<%@ include file="/jsp/common/style.jsp" %>

<div class="content_box">
    <div class="util">
        <ul>
            <li class="list1"><a href="#"  onclick="ContentBody.returnHome();">Home</a></li>
            <li>회원/선박관리</li>
            <li><a href="#"  onclick="ContentBody.reload();">선박 정보</a></li>
        </ul>
    </div>
    <!--end util-->
    <div class="top_img">
        <h2>선박 정보 수정</h2>
        <img src="/images/common/top_img1.png" />
    </div>
    <!--end top_img-->
    <div class="content">
        <h3>선박 정보</h3>
        <div class="table_write">
            <form action="" method="post" name="shipInfoDetailForm" id="shipInfoDetailForm">
                <input type="hidden" id="shipId" name="shipId" value="${shipBean.shipId}"/>
                <input type="hidden" id="userId" name="userId" value="${shipBean.userId}"/>
                <input type="hidden" id="shipRegNo" name="shipRegNo" value="${shipBean.shipRegNo}"/>
                <table width="100%">
                    <tr>
                        <td class="title">선박 등록 번호</td>
                        <td colspan="3">
                            ${shipBean.shipRegNo}
                        </td>
                    </tr>                
                    <tr>
                        <td class="title">선박명</td>
                        <td colspan="3">
                            <input type="text" style="width:150px;" id="shipNm" name="shipNm" data-id='shipNm' value="${shipBean.shipNm}" valid="{label:'선박명', method:'required'}"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="title">선박 무게</td>
                        <td colspan="3">
                            <input type="text" style="width:150px;" id="shipWeight" name="shipWeight" data-id='shipWeight' value="${shipBean.shipWeight}" valid="{label:'선박 무게', type:'number'}"/> ton
                        </td>
                    </tr> 
                    <tr>
                        <td class="title">탑승정원</td>
                        <td colspan="3">
                            <input type="text" style="width:150px;" id="boardingQuota" name="boardingQuota" data-id='boardingQuota' value="${shipBean.boardingQuota}" valid="{label:'탑승정원', type:'number'}"/> 명
                        </td>
                    </tr>
                </table>                        
                <div class="btn_box">
                    <a href="#" id="updateInfoButton">수정</a>
                    <a href="#" id="deleteShipButton">삭제</a>
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
