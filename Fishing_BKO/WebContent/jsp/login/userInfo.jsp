<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/jsp/common/meta.jsp" %>

<title>사용자 관리</title>

<link rel="stylesheet" type="text/css" href="/css/layout/common.css" />
<link rel="stylesheet" type="text/css" href="/css/layout/base.css" />
<link rel="stylesheet" type="text/css" href="/css/layout/popup.css" />

</head>
<body>
<div class="bko-popupbaseLayer" style="width:560px; height:310px;">
		 <div class="popTitle">사용자정보 등록</div>
		<p class="btnClose">
			<input type="image" src="/images/layout/closef.png" alt="닫기" id="btnClose"  title="닫기"/>
		</p>
	<div class="tns_popupInner">
			<dl class="basic_dl btnHide">
	            <dt>사용자 등급</dt>
	            <dd>
	            <select id="Gubun" class="select02" onchange="userInfo.GubunChange();">
	            <option value=""></option>
	            </select>
	            </dd>
           </dl>
			<dl class="basic_dl" id="workplace_hide" style='display:none;!important;'>
            	<dt>소속</dt>
            	<dd>
                	<select id="SoSuk"  class="select02"><option value=''></option></select>
                	<select id="SoSuk2"  class="select02" disabled><option value=''></option></select>
                	<select id="SoSuk3"  class="select02" disabled><option value=''></option></select>
            	</dd>
        	</dl>
        <dl class="basic_dl">
            <dt >아이디</dt>
            <dd>
                <input type="text"  id="member_id" class="text size06" disabled maxlength=12/>&nbsp;&nbsp;
                <input type="hidden" value="Y" id="isChkMemberId">
                <button class="bko-Button bko-gray btnHide"  id="btn_ChkMemberId">중복체크</button> 
                <span class="mbyte v_middle btnHide" >(영문 대/소, 숫자 조합 6~ 24자)</span>
                <button class="bko-Button bko-gray btnHide"   id="btn_PasswordReset" >비밀번호초기화</button>
                <input type="hidden" id="member_pw"/>
                <!-- <p class="note_txt btnHide" id="note_txt"><strong>비밀번호는 아이디에 1234가 붙어서 자동 생성됩니다.</strong><br />예)아이디가 AAA11C인 경우 비번은 AAA11C1234 </p> -->
            </dd>
        </dl>
        <dl class="basic_dl">
            <dt>사용자명</dt>
            <dd><input type="text" id="member_nm" class="text size06" maxlength=10/><span class="mbyte">(20 byte 이내)</span></dd> 
        </dl>
         <dl class="basic_dl">
            <dt>휴대폰번호</dt>
            <dd>
                <select id="mobileNo1">
                    <option value=''>선택</option>
                </select> -
                <input type="text" onkeydown="checkNum(this);" onkeyup="checkNum(this);" maxlength=4 id="mobileNo2" class="text size02" /> -
                <input type="text" onkeydown="checkNum(this);" onkeyup="checkNum(this);" maxlength=4 id="mobileNo3" class="text size02" />
                <button class="bko-Button bko-gray" id="btn_ChkHpNo">중복체크</button>
                <input type="hidden" value="N" id="isChkHpNo">
                <input type="hidden" value="" id="hmobileNo">
            </dd>
        </dl>
        <dl class="basic_dl">
            <dt>연락처</dt>
            <dd>
                <select id="telNo1"></select> - 
                <input type="text" onkeydown="userInfo.CurrencyChk(this);" onkeyup="userInfo.CurrencyChk(this);" maxlength=4 id="telNo2" class="text size02" />  -
                <input type="text" onkeydown="userInfo.CurrencyChk(this);" onkeyup="userInfo.CurrencyChk(this);" maxlength=4 id="telNo3" class="text size02" />
            </dd>
        </dl>
        <dl class="basic_dl">
            <dt >이메일</dt>
            <dd><input type="text" id="member_email" class="text size04" maxlength=50/> <span class="mbyte">(50 byte 이내)</span></dd>
        </dl>
        <dl class="basic_dl btnHide">
            <dt >사번</dt>
            <dd>
                <input type="text" maxlength=24 id="member_empl_no" class="text size04" />&nbsp;&nbsp;
                <button class="bko-Button bko-gray" id="btn_ChkEmpNo">중복체크</button> <span class="mbyte">(관리자, 배차자 필수)</span>
                <input type="hidden" value="Y" id="isChkEmpNo">
                <input type="hidden" value="" id="hEmpNo">
                
            </dd>
        </dl>
        <dl class="basic_dl btnHide">
            <dt>사용 여부</dt>
            <dd class="dd05"><input type="checkbox" class="checkbox" id="use_yn" checked /></dd>
            <dt class="dt05">최근 접속 일시</dt>
            <dd><input type="text" id="login_dt" class="text size04" disabled/></dd>
        </dl>
        <dl class="basic_dl btnHide">
            <dt class="h_72">메모</dt>
            <dd class="h_auto02"><textarea rows="4" cols="73" maxlength=100 id="contants" class="textarea03"></textarea></dd>
        </dl>
    </div>
    <div class="btnArea">
        <button class="bko-Button bko-gray" id="btn_Save" >등록</button>
        <button class="bko-Button bko-gray" title="닫기" onclick="userInfo.Close();">닫기</button>
    </div>
</div>

<%@ include file="/jsp/common/javascript.jsp" %>

</body>
</html>