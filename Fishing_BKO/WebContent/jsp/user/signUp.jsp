<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/jsp/common/meta.jsp" %>
<%@ include file="/jsp/common/taglibs.jsp" %>
<%@ include file="/jsp/common/style.jsp" %>

<title>회원 가입</title>
</head>
<body>
    <div class="header_wrap">
        <h1>
            <a href="#" onclick="SignUp.returnHome();"><img src="/images/common/logo.png" alt="대원낚시" /></a>
        </h1>
    </div>
    <div class="content_wrap">
        <div class="content_box">
            <div class="util">
                <ul>
                    <li class="list1"><a href="#"  onclick="SignUp.returnHome();">Home</a></li>
                    <li><a href="#"  onclick="ContentBody.reload();">회원 가입</a></li>
                </ul>
            </div>
            <!--end util-->
            <div class="top_img">
                <h2>회원 가입</h2>
                <img src="/images/common/top_img1.png" />
            </div>
            <!--end top_img-->
            <div class="content">
                <h3>회원 가입</h3>
                <div class="table_write">
                    <form action="" method="post" name="signUpForm" id="signUpForm">
                        <table width="100%">
                            <tr>
                                <td class="title">아이디</td>
                                <td colspan="3">
                                    <input type="text" style="width:150px;" id="userId" name="userId" data-id='userId' valid="{label:'아이디', method:'required', type:'alphanum'}"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="title">비밀번호</td>
                                <td colspan="3">
                                    <input type="password" style="width:150px;" id="userPw" name="userPw" data-id='userPw' valid="{label:'비밀번호', method:'required', type:'alphanum'}"/>
        	                   </td>
                            </tr> 
                            <tr>
                                <td class="title">비밀번호 확인</td>
                                <td colspan="3">
                                    <input type="password" style="width:150px;" id="userPw2" name="userPw2" data-id='userPw2' valid="{label:'비밀번호 확인', method:'required', type:'alphanum'}"/>
                               </td>
                            </tr>                             
                            <tr>
                                <td class="title">이름</td>
                                <td colspan="3">
                                    <input type="text" style="width:150px;" id="userNm" name="userNm" data-id='userNm' valid="{label:'이름', method:'required'}"/>
                                </td>
                            </tr> 
                            <tr>
                                <td class="title">우편번호</td>
                                <td colspan="3">
                                    <input type="text" style="width:80px;" id="zipCd" name="zipCd" data-id='zipCd' valid="{label:'우편번호', method:'required'}" readonly="readonly"/>
                                    <a href="#" class="btn_postPop" onclick="javascript:SignUp.getPostNo();">우편 번호</a>
                                </td>
                            </tr>
                            <tr>
                                <td class="title">주소</td>
                                <td colspan="3">
                                    <input type="text" style="width:340px;" id="addr" name="addr" data-id='addr' valid="{label:'주소', method:'required'}"/>
                                </td>
                            </tr> 
                            <tr>
                                <td class="title">상세 주소</td>
                                <td colspan="3">
                                    <input type="text" style="width:340px;" id="addrDetail" name="addrDetail" data-id='addrDetail' valid="{label:'상세주소', method:'required'}"/>
                                </td>
                            </tr>                             
                            <tr>
                                <td class="title">휴대폰번호</td>
                                <td colspan="3">
                                    <input type="text" style="width:150px;" id="hpNo" name="hpNo" data-id='hpNo' valid="{label:'휴대폰번호', type:'tel'}"/>
                                </td>
                            </tr> 
                            <tr>
                                <td class="title">은행명</td>
                                <td colspan="3">
                                    <input type="text" style="width:150px;" id="bankCd" name="bankCd" data-id='bankCd'/>
                                </td>
                            </tr>                                         
                            <tr>
                                <td class="title">계좌번호</td>
                                <td colspan="3">
                                    <input type="text" style="width:150px;" id="acntNo" name="acntNo" data-id='acntNo'/>
                                </td>
                            </tr> 
                            <tr>
                                <td class="title">예금주</td>
                                <td colspan="3">
                                    <input type="text" style="width:150px;" id="acntNm" name="acntNm" data-id='acntNm'/>
                                </td>
                            </tr> 
                        </table>
                        
<!--                         <table width="100%"> -->
<!--                             <tr> -->
<!--                                 <td class="title">선박 등록 번호</td> -->
<!--                                 <td colspan="3"> -->
<!--                                     <input type="text" style="width:150px;" id="shipRegNo" name="shipInfoBean.shipRegNo" data-id='shipRegNo' valid="{label:'선박 등록 번호', method:'required'}"/> -->
<!--                                 </td> -->
<!--                             </tr>                         -->
<!--                             <tr> -->
<!--                                 <td class="title">선박 이름</td> -->
<!--                                 <td colspan="3"> -->
<!--                                     <input type="text" style="width:150px;" id="shipNm" name="shipInfoBean.shipNm" data-id='shipNm' valid="{label:'선박 이름', method:'required'}"/> -->
<!--                                 </td> -->
<!--                             </tr> -->
<!--                             <tr> -->
<!--                                 <td class="title">선박 무게</td> -->
<!--                                 <td colspan="3"> -->
<!--                                     <input type="text" style="width:150px;" id="shipWeight" name="shipInfoBean.shipWeight" data-id='shipWeight' valid="{label:'선박 무게', type:'number'}"/> -->
<!--                                 </td> -->
<!--                             </tr>  -->
<!--                             <tr> -->
<!--                                 <td class="title">탑승정원</td> -->
<!--                                 <td colspan="3"> -->
<!--                                     <input type="text" style="width:150px;" id="boardingQuota" name="shipInfoBean.boardingQuota" data-id='boardingQuota' valid="{label:'탑승정원', type:'number'}"/> -->
<!--                                 </td> -->
<!--                             </tr> -->
<!--                         </table>                         -->
                        <div class="btn_box">
                            <a href="#" id="signUpButton">등록</a>
                            <a href="#" id="cancelButton">취소</a>
                        </div>
                        <!--end btn_box-->
                    </form>
                </div>
                <!--end table_write-->
            </div>
            <!--end content--> 
        </div>
        <!--end content_box--> 
    </div>
    <!--end content_wrap-->
    
    <div class="footer_wrap">
        <p>Copyright YellowBoat. All right reserved.</p>
    </div>
    <!--end footer_wrap-->  
    <%@ include file="/jsp/common/javascript.jsp" %>  
</body>
</html>