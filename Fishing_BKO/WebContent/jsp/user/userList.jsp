<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/jsp/common/meta.jsp" %>
<%@ include file="/jsp/common/taglibs.jsp" %>
<%@ include file="/jsp/common/style.jsp" %>

<title>사용자 관리</title>


</head>
<body>
    
    <div class="bko-wrapper"> 
        <div class="bko-bodyWap">
            <div class="bko-bodyHeader">
                <div class="bko-bodyHeaderWrap">
                    <div class="bko-titleArea">
                        <span id="bko-title">사용자 관리</span>
                    </div>
                    <div class="bko-iconArea">
                        <input type="image" id="bko-searchPanel" class="bko-searchPanel" src="/images/content/limpid.png" alt="검색패널" title="검색패널"/>
                        <input type="image" id="bko-refresh" class="bko-refresh" src="/images/content/limpid.png" alt="새로고침" title="새로고침" />
                        <input type="image" id="bko-newWindow" class="bko-newWindow" src="/images/content/limpid.png" alt="새창열기" title="새창열기" />
                    </div>
                </div>
            </div>

            <div class="bko-bodyBottom">
                <!--bko-searchBox start  -->
                <div class="bko-searchBox" style="height:50px;">
                    <form id="searchFrm">
	                    <div class="bko-searchInnerBox" >
                             <div class="search_check">
                                <span>
                                    <select id="searchWordType" name="searchWordType" class='select' onchange="javascript:ContentBody.changeSearchArea($(this));">
                                            <option value="userId" data-searchType="text">아이디</option>
                                            <option value="userNm" data-searchType="text">이름</option>
                                    </select>
                                </span>
                                <span id="searchArea"><input type="text"  class="text" id="searchWord" name="searchWord" placeholder="검색어 입력" onkeydown="javascript:if (event.keyCode == 13) UserList.search();" maxlength="30" style="width: 200px;"/></span>
                            </div>
                       </div>
                   </form>
                    <!--☆버튼영역-->
                    <div class="bko-tableBtnArea" style="margin-bottom:0px;">
                        <button class="bko-gray bko-xlarge" title="검색" onclick="UserList.search();">검색</button>
                        <button class="bko-gray bko-xlarge" title="초기화" id="reset">초기화</button>
                    </div><!--//bko-tableBtnArea-->
                </div>
                <!--bko-searchBox end  -->

                <div class="bko-funcBtnArea">
                    <dl class="bko-funcBtnInnerBox">
                        <dd class="bko-funcBtn">
                            <button class="bko-gray bko-middle" title="삭제" id="delBtn" style="float: right;">삭제</button>
                            <button class="bko-gray bko-middle" title="등록" id="newAdvBtn" style="float: right;">등록</button>
                        </dd>
                        <dd class="bko-excelBtn">
<!--                             <input type="image" id="bko-excel" src="/images/content/btn_down_xls.gif" alt="엑셀다운로드" title="엑셀다운로드"/> -->
                        </dd>
                    </dl>
                </div>
                
                <div class="bko-bodyContent">
                    <div class="bko-bodyContentInnerBox" data-content-type="grid1"></div>
                </div>
               
            </div>
        </div>
    </div>

<div id="popupArea" style="display: none;">
    <form action="" method="post" name="popupForm" id="popupForm">
        <table cellpadding="5" cellspacing="2" border="0">
            <tr>
                <td class="td1">아이디</td>
                <td class="td2">
                    <span>
                        <input type="text" style="float: left;" class="text" id="userId" name="userId" data-id='userId' maxlength="30" valid="{label:'아이디', method:'required'}"/>
                        
                        <div id="initPassDiv" style="float: left; margin-left: 2px;">
                            <input type="button" style="padding: 3px 6px 3px 6px;" class="bko-gray bko-middle" value="패스워드 초기화" onclick="javascript:UserList.initPass();" />
                        </div>
                        <span style="color:red;display: inline-block;">※초기패스워드 : 아이디 + 1234</span>
                    </span>
                </td>
            </tr>
            <tr>
                <td class="td1">이름</td>
                <td class="td2">
                    <span><input type="text"  class="text" id="userNm" name="userNm" data-id='userNm' maxlength="50" valid="{label:'이름', method:'required'}"/></span>
                </td>
            </tr>
            <tr>
                <td class="td1">메뉴권한</td>
                <td class="td2">
                    <span><com:makeCheck boxName="menuAuth" masterCd="MENU_AUTH" selected="" include="data-id='menuAuth' data-role='none' class='checkbox-group'" subInclude="valid=\"{label:'메뉴권한', method:'required'}\""/></span>
                </td>
            </tr>
        </table>    
    </form>
</div>

<%@ include file="/jsp/common/javascript.jsp" %>

</body>
</html>