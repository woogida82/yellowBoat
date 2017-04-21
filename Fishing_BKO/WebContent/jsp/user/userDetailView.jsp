<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/meta.jsp" %>
<%@ include file="/jsp/common/taglibs.jsp" %>
<%@ include file="/jsp/common/style.jsp" %>

<div class="content_box">
    <div class="util">
        <ul>
            <li class="list1"><a href="#"  onclick="ContentBody.returnHome();">Home</a></li>
            <li>회원관리</li>
            <li><a href="#"  onclick="ContentBody.reload();">회원 정보</a></li>
        </ul>
    </div>
    <!--end util-->
    <div class="top_img">
        <h2>회원 정보</h2>
        <img src="/images/common/top_img1.png" />
    </div>
    <!--end top_img-->
    <div class="content">
        <h3>회원 정보</h3>
        <div class="table_write">
            <form action="" method="post" name="userDetailForm" id="userDetailForm">
                <input type="hidden" id="userId" name="userId" value="${userBean.userId}"/>
                <input type="hidden" id="shipId" name="shipInfoBean.shipId" value="${shipBean.shipId}"/>
                <input type="hidden" id="shipRegNo" name="shipInfoBean.shipRegNo" value="${shipBean.shipRegNo}"/>
                <table width="100%">
                    <tr>
                        <td class="title">아이디</td>
                        <td colspan="3">
                            ${userBean.userId}
                        </td>
                    </tr>
                    <tr>
                        <td class="title">이름</td>
                        <td colspan="3">
                            <input type="text" style="width:150px;" id="userNm" name="userNm" data-id='userNm' value="${userBean.userNm}" valid="{label:'이름', method:'required'}"/>
                        </td>
                    </tr> 
                    <tr>
                        <td class="title">우편번호</td>
                        <td colspan="3">
                            <input type="text" style="width:80px;" id="zipCd" name="zipCd" data-id='zipCd' value="${userBean.zipCd}" valid="{label:'우편번호', method:'required'}" readonly="readonly"/>
                            <a href="#" class="btn_postPop" onclick="javascript:UserDetailView.getPostNo();">우편 번호</a>
                        </td>
                    </tr>
                    <tr>
                        <td class="title">주소</td>
                        <td colspan="3">
                            <input type="text" style="width:100%;" id="addr" name="addr" data-id='addr' value="${userBean.addr}" valid="{label:'주소', method:'required'}"/>
                        </td>
                    </tr> 
                    <tr>
                        <td class="title">상세 주소</td>
                        <td colspan="3">
                            <input type="text" style="width:340px;" id="addrDetail" name="addrDetail" data-id='addrDetail' value="${userBean.addrDetail}" valid="{label:'상세주소', method:'required'}"/>
                        </td>
                    </tr>                     
                    <tr>
                        <td class="title">휴대폰번호</td>
                        <td colspan="3">
                            <input type="text" style="width:150px;" id="hpNo" name="hpNo" data-id='hpNo' value="${userBean.hpNo}" valid="{label:'휴대폰번호', type:'tel'}"/>
                        </td>
                    </tr> 
                    <tr>
                        <td class="title">은행명</td>
                        <td colspan="3">
                            <input type="text" style="width:150px;" id="bankCd" name="bankCd" data-id='bankCd' value="${userBean.bankCd}"/>
                        </td>
                    </tr>                                         
                    <tr>
                        <td class="title">계좌번호</td>
                        <td colspan="3">
                            <input type="text" style="width:150px;" id="acntNo" name="acntNo" data-id='acntNo' value="${userBean.acntNo}"/>
                        </td>
                    </tr> 
                    <tr>
                        <td class="title">예금주</td>
                        <td colspan="3">
                            <input type="text" style="width:150px;" id="acntNm" name="acntNm" data-id='acntNm' value="${userBean.acntNm}"/>
                        </td>
                    </tr> 
                </table>
                
                <table width="100%">
                    <tr>
                        <td class="title">선박 등록 번호</td>
                        <td colspan="3">
                            ${shipBean.shipRegNo}
                        </td>
                    </tr>                
                    <tr>
                        <td class="title">선박 이름</td>
                        <td colspan="3">
                            <input type="text" style="width:150px;" id="shipNm" name="shipInfoBean.shipNm" data-id='shipNm' value="${shipBean.shipNm}"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="title">선박 무게</td>
                        <td colspan="3">
                            <input type="text" style="width:150px;" id="shipWeight" name="shipInfoBean.shipWeight" data-id='shipWeight' value="${shipBean.shipWeight}" valid="{label:'선박 무게', type:'number'}"/>
                        </td>
                    </tr> 
                    <tr>
                        <td class="title">탑승정원</td>
                        <td colspan="3">
                            <input type="text" style="width:150px;" id="boardingQuota" name="shipInfoBean.boardingQuota" data-id='boardingQuota' value="${shipBean.boardingQuota}" valid="{label:'탑승정원', type:'number'}"/>
                        </td>
                    </tr>
                </table>                        
                <div class="btn_box">
                    <a href="#" id="updateInfoButton">수정</a>
                    <a href="#" id="deleteUserButton">삭제</a>
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
