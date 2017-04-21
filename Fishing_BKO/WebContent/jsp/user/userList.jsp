<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/meta.jsp" %>
<%@ include file="/jsp/common/taglibs.jsp" %>
<%@ include file="/jsp/common/style.jsp" %>

<div class="content_box">
    <div class="util">
        <ul>
            <li class="list1"><a href="#"  onclick="ContentBody.returnHome();">Home</a></li>
            <li>회원관리</li>
            <li><a href="#"  onclick="ContentBody.reload();">전체회원</a></li>
        </ul>
    </div>
    <!--end util-->
    <div class="top_img">
        <h2>회원관리</h2>
        <img src="/images/common/top_img1.png" />
    </div>
    <!--end top_img-->
    <div class="content">
        <h3>회원목록</h3>
        <form action="/bko/user/userDetailView" id="userListForm" name="userListForm">
            <input type="hidden" id="userId" name="userId"/>
            <div class="search_area">
                <ul>
                    <li>
                        <select style="width:120px;" id="searchWordType" name="searchWordType">
                            <option value="userId" data-searchType="text">아이디</option>
                            <option value="userNm" data-searchType="text">이름</option>
                        </select>
                    </li>
                    <li><input type="text" style="width:150px;" id= "searchWord" name="searchWord" onkeydown="javascript:if (event.keyCode == 13) UserList.search();"/></li>
                    <li><a href="#" onclick="UserList.search();">검색</a></li>
                </ul>
            </div>   
            <div class="table_box">     
                <div class="bko-bodyContent">
                    <div class="bko-bodyContentInnerBox" data-content-type="grid1"></div>
                </div>
            </div>
        </form>
    </div>
</div>
<%@ include file="/jsp/common/javascript.jsp" %>