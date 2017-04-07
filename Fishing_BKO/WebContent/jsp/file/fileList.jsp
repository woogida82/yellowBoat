<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/jsp/common/meta.jsp" %>
<%@ include file="/jsp/common/taglibs.jsp" %>
<%@ include file="/jsp/common/style.jsp" %>

<title>AGV 로그 데이터</title>


</head>
<body>
    
    <div class="bko-wrapper">
        <div class="bko-bodyWap">
            <div class="bko-bodyHeader">
                <div class="bko-bodyHeaderWrap">
                    <div class="bko-titleArea">
                        <span id="bko-title">AGV 로그 데이터</span>
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
                                            <option value="eventId" data-searchType="text">이벤트ID</option>
                                            <option value="orgFileNm" data-searchType="text">파일명</option>
                                            <option value="createId" data-searchType="text">등록자</option>
                                            <option value="updateId" data-searchType="text">수정자</option>
                                    </select>
                                </span>
                                <span id="searchArea"><input type="text"  class="text" id="searchWord" name="searchWord" placeholder="검색어 입력" onkeydown="javascript:if (event.keyCode == 13) SerialNoList.search();" maxlength="30" style="width: 200px;"/></span>
                            </div>
                       </div>
                   </form>
                    <!--☆버튼영역-->
                    <div class="bko-tableBtnArea" style="margin-bottom:0px;">
                        <button class="bko-gray bko-xlarge" title="검색" onclick="SerialNoList.search();">검색</button>
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
    <form action="" method="post" name="popupForm" id="popupForm" enctype="multipart/form-data">
        <input type="hidden" data-id="idx" id="idx" name="idx">
        <table cellpadding="5" cellspacing="2" border="0">
            <tr id="idxTr" style="display: none;">
                <td class="td1">IDX</td>
                <td class="td2">
                    <span data-id="idx"></span>
                </td>
            </tr>
            <tr>
                <td class="td1">이벤트ID</td>
                <td class="td2">
                    <input type="text" style="width: 200px;" class="text" id="eventId" name="eventId" data-id="eventId" valid="{label:'이벤트ID', method:'required', type:'number'}">
                </td>
            </tr>
            <tr>
                <td class="td1">파일 업로드</td>
                <td class="td2">
                    <div style="float: left; margin-right: 1px;">
                        <input type="text" style="width: 200px;" class="text" id="orgFileNm" data-id="orgFileNm" readonly="readonly" valid="{label:'파일', method:'required'}">
                    </div>
                    
                    <div style="float: left; margin-right: 1px; padding: 0px 6px 0px 6px;" class="fileUpload bko-gray bko-middle">
                        <span>업로드</span>
                        <input type="file" id="uploadFile" name="uploadFile" class="upload" onchange="FileList.fileChoice();"/>
                    </div>
                </td>
            </tr>
            <tr id="createTr" style="display: none;">
                <td class="td1">등록일시 / 등록자</td>
                <td class="td2">
                    <span data-id="createTime"></span> / 
                    <span data-id="createId"></span>
                </td>
            </tr>
            <tr id="updateTr" style="display: none;">
                <td class="td1">수정일시 / 수정자</td>
                <td class="td2">
                    <span data-id="updateTime"></span> / 
                    <span data-id="updateId"></span>
                </td>
            </tr>
        </table>
    </form>
</div>

<%@ include file="/jsp/common/javascript.jsp" %>

</body>
</html>