<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/jsp/common/meta.jsp" %>
<%@ include file="/jsp/common/taglibs.jsp" %>
<%@ include file="/jsp/common/style.jsp" %>
<%@ include file="/jsp/common/javascript.jsp" %>

<title>회원 가입</title>
</head>
<body>
    <div class="header_wrap">
        <h1>
            <a href="#" onclick="Main.returnHome();"><img src="/images/common/logo.png" alt="대원낚시" /></a>
        </h1>
    </div>
    <div class="content_wrap">
        <div class="content_box">
            <div class="util">
                <ul>
                    <li class="list1">Home</li>
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
                                    <input type="text" style="width:150px;" id="userId" name="userId" data-id='userId' valid="{label:'아이디', method:'required'}"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="title">비밀번호</td>
                                <td colspan="3">
                                    <input type="password" style="width:150px;" id="userPw" name="userPw" data-id='userPw' valid="{label:'비밀번호', method:'required'}"/>
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
                                    <input type="text" style="width:80px;" id="zipCd" name="zipCd" data-id='zipCd'/>
                                </td>
                            </tr>
                            <tr>
                                <td class="title">주소</td>
                                <td colspan="3">
                                    <input type="text" style="width:100%;" id="addr" name="addr" data-id='addr'/>
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
                        
                        <table width="100%">
                            <tr>
                                <td class="title">선박 이름</td>
                                <td colspan="3">
                                    <input type="text" style="width:150px;" id="shipNm" name="shipInfoBean.shipNm" data-id='shipNm' valid="{label:'선박 이름', method:'required'}"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="title">선박 무게</td>
                                <td colspan="3">
                                    <input type="text" style="width:150px;" id="shipWeight" name="shipInfoBean.shipWeight" data-id='shipWeight' valid="{label:'선박 무게', type:'number'}"/>
                                </td>
                            </tr> 
                            <tr>
                                <td class="title">탑승정원</td>
                                <td colspan="3">
                                    <input type="text" style="width:150px;" id="boardingQuota" name="shipInfoBean.boardingQuota" data-id='boardingQuota' valid="{label:'탑승정원', type:'number'}"/>
                                </td>
                            </tr>
                        </table>                        
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
<script type="text/javascript">
    $LAB.script(function() {
        return [
                "/js/jquery/jquery-1.11.2.min.js",
                "/js/jquery/jquery-migrate-1.2.1.min.js",
                "/js/jquery/jquery-ui.min.js",
            ];
    }).wait()
    .script("/js/jquery/html5shiv.min.js")
    .script("/js/jquery/jquery.form.min.js")
    .script("/js/jquery/jquery.slimscroll.min.js").wait()
    .script("/js/jquery/jquery.select2.min.js").wait()
    .script("/js/jquery/jquery-impromptu.min.js").wait()
    .script(function() {
        return [
            "/js/common/control.js",
            "/js/common/validator.js",
            "/js/common/utils.js",
            "/js/common/fileUtils.js",
            "/js/common/dateUtils.js",
            "/js/common/common.js?20151224"
        ];
    }).wait()
    .script("/js/layout/menu.js")
    .script("/js/user/signUp.js?20170412")
    .wait(function(){
        $(function () {
			SignUp.init();
        });
    })
    .script("/js/common/script.js")
    ;
</script>           
</body>
</html>