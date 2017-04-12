<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/jsp/common/meta.jsp" %>

<title>옐로우 보트</title>

<link rel="stylesheet" type="text/css" href="/css/common/index.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/common/reset.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/common/styles.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<script type="text/javascript" src="/js/jquery/LAB.min.js"></script>
<script type="text/javascript">
$LAB
.script("/js/jquery/jquery-1.11.2.min.js").wait()
.script("/js/jquery/jquery-migrate-1.2.1.min.js").wait()
.script("/js/jquery/jquery.cookie.js").wait()
.script("/js/login/login.js?ver=201503261151").wait(function(){
	$(function(){
        $('#userId').keypress(function(e){
            if(e.which == 13){
                login();
            }
        });
        
        $('#userPw').keypress(function(e){
            if(e.which == 13){
                 login();
            }
         });
        
        var loginId = $.cookie('ims_loginId');
        if(loginId){
            $("#userId").val(loginId);
            $('#idSave').attr("checked", true);
        }
        
        $("#loginBtn").click(function(){
            login();
        });
        
        $("#signUp").click(function(){
            signUp();
        });
        
    });
})

</script>

</head>
    <div class="login_box">
        <div class="login_top">
            <img src="/images/common/login_bg.jpg" alt="logo" />
        </div>
        <div class="login_form">
            <div class="title_bar">LOGIN</div>
                <div class="form_left">
                    <span class="title">아이디</span><input type="text" name="userId" id="userId" tabindex="1" maxlength="70" style="width:115px;"/>
                    <span class="title">비밀번호</span><input type="password" name="userPw" id="userPw" tabindex="2" maxlength="20" style="width:115px;"/>
                    <a href="#" id="loginBtn" class="login_bt">로그인</a>
                </div>
            <div class="form_right">
                <img src="/images/common/login_logo.png" alt="logo" />
            </div>
            <a href="#" id="signUp" class="title" >회원가입</a>
<!--             <input type="checkbox" id="idSave" /> -->
        </div>
    </div>    
</body>
</html>