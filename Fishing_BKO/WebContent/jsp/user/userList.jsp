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
        <form action="" id="userListForm" name="userListForm">
            <input type="hidden" id="userId" name="userId"/>
            <input type="hidden" id="page" name="page"  value="${bean.page}"/>
            <div class="search_area">
                <ul>
                    <li>
                        <select style="width:120px;" id="searchWordType" name="searchWordType">
                            <option value="userId" data-searchType="text">아이디</option>
                            <option value="userNm" data-searchType="text">이름</option>
                        </select>
                    </li>
                    <li><input type="text" style="width:150px;" id= "searchWord" name="searchWord" onkeydown="javascript:if (event.keyCode == 13) UserList.search('1');"/></li>
                    <li><a href="#" onclick="UserList.search('1');">검색</a></li>
                </ul>
            </div>   
            <div class="table_box">     
                <table class="t_body">
                    <colgroup>
                        <col width="50px;"/>
                        <col width="250px;" />
                        <col width="250px" />
                        <col width="100px;" />
                        <col width="170px;" />
                        <col width="100px;" />
                        <col width="170px;" />
                        <col width="100px;" />
                    </colgroup>
                    <tr>
                        <th>번호</th>
                        <th>아이디</th>
                        <th>이름</th>
                        <th>권한</th>
                        <th>수정일시</th>
                        <th>수정자</th>
                        <th>등록일시</th>
                        <th>등록자</th>
                    </tr>
                    <c:choose>
                        <c:when test="${!empty rows}">
                            <c:forEach items="${rows}" var="list" varStatus="status">
                                <tr>
                                    <td>${status.index +1}</td>
                                    <td>
                                        <a onclick="javascript:UserList.detailView('${list.userId }');">
                                            ${list.userId }
                                        </a>
                                    </td>
                                    <td>
                                        <a onclick="javascript:UserList.detailView('${list.userId }');">
                                            ${list.userNm }
                                        </a>
                                    </td>
                                    <td>${list.menuAuth }</td>
                                    <td>${list.updateTime }</td>
                                    <td>${list.updateId }</td>
                                    <td>${list.createTime }</td>
                                    <td>${list.createId }</td>
                                </tr>                                
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="8">
                                    조회된 결과가 없습니다.
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </table> 
                <div class="pagination">
                    <cpg:paginator maxLinks="10" currPage="${bean.page}" totalPages="${bean.totalPages}" uri="UserList"/>
                </div>                
            </div>
        </form>
    </div>
</div>
<%@ include file="/jsp/common/javascript.jsp" %>