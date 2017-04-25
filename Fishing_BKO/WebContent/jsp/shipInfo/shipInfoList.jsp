<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/jsp/common/meta.jsp" %>
<%@ include file="/jsp/common/taglibs.jsp" %>
<%@ include file="/jsp/common/style.jsp" %>

<div class="content_box">
    <div class="util">
        <ul>
            <li class="list1"><a href="#"  onclick="ContentBody.returnHome();">Home</a></li>
            <li>회원/선박관리</li>
            <li><a href="#"  onclick="ContentBody.reload();">전체선박</a></li>
        </ul>
    </div>
    <!--end util-->
    <div class="top_img">
        <h2>선박관리</h2>
        <img src="/images/common/top_img1.png" />
    </div>
    <!--end top_img-->
    <div class="content">
        <h3>선박 목록</h3>
        <form action="" id="shipInfoListForm" name="shipInfoListForm">
            <input type="hidden" id="userId" name="userId" />
            <input type="hidden" id="shipId" name="shipId" />
            <input type="hidden" id="page" name="page"  value="${bean.page}"/>
            <div class="search_area">
                <ul>
                    <li>
                        <select style="width:120px;" id="searchWordType" name="searchWordType">
                            <c:if test="${bean.adminBean.userCd eq 'ADMN' }">
                                <option value="userId" data-searchType="text">아이디</option>
                            </c:if>
                            <option value="shipNm" data-searchType="text">선박명</option>
                        </select>
                    </li>
                    <li><input type="text" style="width:150px;" id= "searchWord" name="searchWord" onkeydown="javascript:if (event.keyCode == 13) ShipInfoList.search('1');"/></li>
                    <li><a href="#" onclick="ShipInfoList.search('1');">검색</a></li>
                </ul>
            </div>   
            <div class="table_box">     
                <table class="t_body">
                    <colgroup>
                        <col width="50px;"/>
                        <col width="250px;" />
                        <col width="100px" />
                        <col width="100px" />
                        <col width="150px;" />
                        <col width="170px;" />
                        <col width="100px;" />
                        <col width="170px;" />
                        <col width="100px;" />
                    </colgroup>
                    <tr>
                        <th>번호</th>
                        <th>선박명</th>
                        <th>선박무게</th>
                        <th>탑승정원</th>
                        <th>선박소유자</th>
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
                                        <a onclick="javascript:ShipInfoList.detailView('${list.shipId }');">
                                            ${list.shipNm }
                                        </a>
                                    </td>
                                    <td>${list.shipWeight } ton</td>
                                    <td>${list.boardingQuota } 명</td>
                                    <td>
                                        <c:if test="${bean.adminBean.userCd eq 'CUST' }">
                                            ${list.userNm }
                                        </c:if>
                                        <c:if test="${bean.adminBean.userCd eq 'ADMN' }">
                                            <a onclick="javascript:ShipInfoList.userInfoView('${list.userId }');">
                                                ${list.userNm }
                                            </a>
                                        </c:if>
                                    </td>
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
                    <cpg:paginator maxLinks="10" currPage="${bean.page}" totalPages="${bean.totalPages}" uri="ShipInfoList"/>
                </div> 
                <!--end pagination-->  
                             
                <div class="btn_box">
                    <c:if test="${bean.adminBean.userCd eq 'CUST' }">
                        <a href="#" id="regShipInfoButton">등록</a>
                    </c:if>
                </div>
                <!--end btn_box-->                
            </div>
        </form>
    </div>
</div>
<%@ include file="/jsp/common/javascript.jsp" %>