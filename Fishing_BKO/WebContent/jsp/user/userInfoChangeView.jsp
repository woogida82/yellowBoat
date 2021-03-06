<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/meta.jsp" %>
<%@ include file="/jsp/common/taglibs.jsp" %>
<%@ include file="/jsp/common/style.jsp" %>

<div class="content_box">
    <div class="util">
        <ul>
            <li class="list1"><a href="#"  onclick="ContentBody.returnHome();">Home</a></li>
            <li>회원/선박관리</li>
            <li><a href="#"  onclick="ContentBody.reload();">개인정보 수정</a></li>
        </ul>
    </div>
    <!--end util-->
    <div class="top_img">
        <h2>개인정보 수정</h2>
        <img src="/images/common/top_img1.png" />
    </div>
    <!--end top_img-->
    <div class="content">
        <h3>개인 정보</h3>
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
                        <td class="title">기존 비밀번호</td>
                        <td colspan="3">
                            <input type="password" style="width:150px;" id="userPw" name="userPw" data-id='userPw' valid="{label:'비밀번호', method:'required', type:'alphanum'}"/>
                        </td>
                    </tr>  
                    <tr>
                        <td class="title">새 비밀번호</td>
                        <td colspan="3">
                            <input type="password" style="width:150px;" id="newUserPw1" name="newUserPw1" data-id='newUserPw1' valid="{label:'비밀번호', type:'alphanum'}"/>
                        </td>
                    </tr> 
                    <tr>
                        <td class="title">새 비밀번호 확인</td>
                        <td colspan="3">
                            <input type="password" style="width:150px;" id="newUserPw2" name="newUserPw2" data-id='newUserPw2' valid="{label:'비밀번호', type:'alphanum'}"/>
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
                            <a href="#" class="btn_postPop" onclick="javascript:UserInfoChangeView.getPostNo();">우편 번호</a>
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
                <div class="btn_box">
                    <a href="#" id="updateInfoButton">수정</a>
                    <a href="#" id="deleteUserButton">탈퇴</a>
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
