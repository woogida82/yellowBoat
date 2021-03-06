<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/jsp/common/meta.jsp" %>
<%@ include file="/jsp/common/taglibs.jsp" %>
<%@ include file="/jsp/common/style.jsp" %>

<div class="content_box">
    <div class="util">
        <ul>
            <li class="list1"><a href="#"  onclick="ContentBody.returnHome();">Home</a></li>
            <li>예약관리</li>
            <li><a href="#"  onclick="ContentBody.reload();">예약관리</a></li>
        </ul>
    </div>
    <!--end util-->
    <div class="top_img">
        <h2>예약관리</h2>
        <img src="/images/common/top_img1.png" />
    </div>
    <!--end top_img-->
    <div class="content">
        <h3>선박 목록</h3>
        <form action="" id="reservMainForm" name="reservMainForm">
            <input type="hidden" id="shipId" name="shipId" />
            <input type="hidden" id="page" name="page"  value="${bean.page}"/>
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
                                    <td><a href="#" class="btn_list" onClick="ReservMain.reservList('${list.shipId}');">예약확인</a></td>
                                    <td><a href="#" class="btn_reg" onClick="ReservMain.regReservation('${list.shipId}');">예약등록</a></td>
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