<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/jsp/common/meta.jsp" %>

<title>비밀번호 변경</title>

<link rel="stylesheet" type="text/css" href="/css/layout/common.css" />
<link rel="stylesheet" type="text/css" href="/css/layout/base.css" />
<link rel="stylesheet" type="text/css" href="/css/layout/popup.css" />

</head>

<body>
<div class="bko-popupbaseLayer" style="width:400px; height:230px;">
    <div class="popTitle">비밀번호 변경</div>
    <p class="btnClose">
        <input type="image" src="/images/layout/closef.png" alt="닫기" id="btnClose" title="닫기"/>
    </p>
    <div class="tns_popupInner">
        <dl class="basic_dl">
            <dt class="dt06">기존 비밀번호</dt>
            <dd><input type="password" class="text size04" id="oldPswd"  maxlength=24/></dd>
        </dl>
        <dl class="basic_dl">
            <dt class="dt06">변경 할 비밀번호</dt>
            <dd><input type="password" class="text size04" id="newPswd1"  maxlength=24/></dd>
        </dl>
        <dl class="basic_dl">
            <dt class="dt06">변경 할  비밀번호 확인</dt>
            <dd><input type="password" class="text size04" id="newPswd2" maxlength=24/></dd>
        </dl>
        <p class='pw_note'>(영문 대/소,숫자,특수문자 조합 6~24자)특수문자는 `~`,`!`,`@`,`#`,`$`,`*`,`(`,`)`,`=`,`_`,`.`,`_` 사용가능.</p>
    </div><!--//tns_popupInner-->
    <div class="btnArea">
    	<button class="bko-Button bko-gray " id="btnSave">적용</button></div>
    	
    <!--//btnArea-->
</div><!--//bko-popupbaseLayer-->

<%@ include file="/jsp/common/javascript.jsp" %>

</body>
</html>