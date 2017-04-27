<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/jsp/common/meta.jsp" %>
<%@ include file="/jsp/common/taglibs.jsp" %>
<%@ include file="/jsp/common/style.jsp" %>

<div class="content_box">
    <div class="util">
        <ul>
            <li class="list1"><a href="#"  onclick="ContentBody.returnHome();">Home</a></li>
            <li></li>
            <li><a href="#"  onclick="ContentBody.reload();"></a></li>
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
                        <col width="250px;" />
                        <col width="250px;" />
                        <col width="250px;" />
                    </colgroup>
                    <tr>
                        <th>번호</th>
                        <th>선박명</th>
                        <th>선주</th>
                        <th>예약확인</th>
                        <th>예약등록</th>
                    </tr>
                    <c:choose>
                        <c:when test="${!empty rows}">
                            <c:forEach items="${rows}" var="list" varStatus="status">
                                <tr>
                                    <td>${status.index +1}</td>
                                    <td>${list.shipNm }</td>
                                    <td>${list.userNm }</td>
                                    <td><a href="#" class="btn_modify" onClick="ReservMain.reservList('${list.shipId}');">예약확인</a></td>
                                    <td><a href="#" class="btn_delete" onClick="ReservMain.regReservation('${list.shipId}');">예약등록</a></td>
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
            </div>
        </form>
    </div>
</div>
<%@ include file="/jsp/common/javascript.jsp" %>