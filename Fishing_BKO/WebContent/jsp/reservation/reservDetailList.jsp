<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/jsp/common/meta.jsp" %>
<%@ include file="/jsp/common/taglibs.jsp" %>
<%@ include file="/jsp/common/style.jsp" %>

<div class="content_box">
    <div class="util">
        <ul>
            <li class="list1"><a href="#"  onclick="ContentBody.returnHome();">Home</a></li>
            <li>예약관리</li>
            <li><a href="#" onclick="ContentBody.reload();">예약관리</a></li>
        </ul>
    </div>
    <!--end util-->
    <div class="top_img">
        <h2>예약상세 목록</h2>
        <img src="/images/common/top_img1.png" />
    </div>
    <!--end top_img-->
    <div class="content">
        <h3>예약상세 목록</h3>
        <form action="" id="reservDetailListForm" name="reservDetailListForm">
            <input type="hidden" id="shipId" name="shipId" value="${bean.shipId }"/>
            <input type="hidden" id="page" name="page"  value="${bean.page}"/>
            <input type="hidden" id="reservDtlId" name="reservDtlId" />
            <input type="hidden" id="statusCd" name="statusCd" />
            <div class="search_area">
                <ul>
                    <li>
                        <select style="width:120px;" id="searchWordType" name="searchWordType">
                            <option value="reservNm" data-searchType="text">예약자</option>
                        </select>
                    </li>
                    <li><input type="text" style="width:150px;" id= "searchWord" name="searchWord" onkeydown="javascript:if (event.keyCode == 13) ReservDetailList.search('1');"/></li>
                    <li><a href="#" onclick="ReservDetailList.search('1');">검색</a></li>
                </ul>
            </div>            
            <div class="table_box">     
                <table class="t_body">
                    <colgroup>
                        <col width="50px;" />
                        <col width="200px;" />
                        <col width="150px;" />
                        <col width="200px;" />
                        <col width="150px;" />
                        <col width="100px;" />
                        <col width="100px;" />
                        <col width="100px;" />
                        <col width="100px;" />                                                                        
                    </colgroup>
                    <tr>
                        <th>번호</th>
                        <th>선박명</th>
                        <th>예약일</th>
                        <th>예약자</th>
                        <th>휴대폰 번호</th>
                        <th>예약상태</th>
                        <th>예약구분</th>
                        <th>예약(입금)완료</th>
                        <th>예약취소</th>
                    </tr>
                    <c:choose>
                        <c:when test="${!empty resultList}">
                            <c:forEach items="${resultList}" var="list" varStatus="status">
                                <tr>
                                    <td>${status.index +1}</td>
                                    <td><a href="#" onClick="ReservDetailList.reservDetailView('${list.reservDtlId}');">${list.shipNm }</a></td>
                                    <td><a href="#" onClick="ReservDetailList.reservDetailView('${list.reservDtlId}');">${list.reservDt }</a></td>
                                    <td><a href="#" onClick="ReservDetailList.reservDetailView('${list.reservDtlId}');">${list.reservNm }</a></td>
                                    <td>${list.reservHpNo }</td>
                                    <td>${list.reservCd }</td>
                                    <td>${list.statusCd }</td>
                                    <td><a href="#" class="btn_list" onClick="ReservDetailList.ReservDtlStatus('${list.reservDtlId}', '01');">예약완료</a></td>
                                    <td><a href="#" class="btn_reg" onClick="ReservDetailList.ReservDtlStatus('${list.reservDtlId}', '09');">예약취소</a></td>
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
                    <cpg:paginator maxLinks="10" currPage="${bean.page}" totalPages="${bean.totalPages}" uri="ReservDetailList"/>
                </div> 
                <!--end pagination--> 
                
                <div class="btn_box">
	               <a href="#" id="regReservationButton">등록</a>
                </div>
                <!--end btn_box-->                  
            </div>
        </form>
    </div>
</div>
<%@ include file="/jsp/common/javascript.jsp" %>