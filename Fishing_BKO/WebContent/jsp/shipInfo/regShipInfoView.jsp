<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/meta.jsp" %>
<%@ include file="/jsp/common/taglibs.jsp" %>
<%@ include file="/jsp/common/style.jsp" %>

<div class="content_box">
    <div class="util">
        <ul>
            <li class="list1"><a href="#"  onclick="ContentBody.returnHome();">Home</a></li>
            <li>회원/선박관리</li>
            <li><a href="#"  onclick="ContentBody.reload();">선박 등록</a></li>
        </ul>
    </div>
    <!--end util-->
    <div class="top_img">
        <h2>선박 등록</h2>
        <img src="/images/common/top_img1.png" />
    </div>
    <!--end top_img-->
    <div class="content">
        <h3>선박 정보</h3>
        <div class="table_write">
            <form action="" method="post" name="regShipInfoForm" id="regShipInfoForm">
                <input type="hidden" id="userId" name="userId" value="${userId}" />
                <table width="100%">
                    <tr>
                        <td class="title">선박 등록 번호</td>
                        <td colspan="3">
                            <input type="text" style="width:150px;" id="shipRegNo" name="shipRegNo" data-id="shipRegNo" valid="{label:'선박 등록 번호', method:'required'}"/>
                        </td>
                    </tr>                
                    <tr>
                        <td class="title">선박명</td>
                        <td colspan="3">
                            <input type="text" style="width:150px;" id="shipNm" name="shipNm" data-id="shipNm" valid="{label:'선박명', method:'required'}"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="title">선박 무게</td>
                        <td colspan="3">
                            <input type="text" style="width:150px;" id="shipWeight" name="shipWeight" data-id="shipWeight" valid="{label:'선박 무게', type:'number'}"/> ton
                        </td>
                    </tr> 
                    <tr>
                        <td class="title">탑승정원</td>
                        <td colspan="3">
                            <input type="text" style="width:150px;" id="boardingQuota" name="boardingQuota" data-id="boardingQuota" valid="{label:'탑승정원', type:'number'}"/> 명
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="title">선박 Main 사진</td>
                        <td colspan="3">
                            <div style="float: left; margin-right: 1px;">
                                <input type="text" style="width: 200px;" class="text" id="orgMainImgNm" name="orgMainImgNm" data-id="orgMainImgNm" readonly="readonly" valid="{label:'파일', method:'required'}">
                            </div>
                            <div style="float: left; margin-right: 1px; padding: 0px 6px 0px 6px;" class="fileUpload bko-gray bko-middle">
                                <span>업로드</span>
                                <input type="file" id="uploadFileMain" name="uploadFileMain" class="upload" onchange="RegShipInfoView.fileChoice('uploadFileMain', 'orgMainImgNm', 'holderMain');"/>
                            </div>
                        </td>
                    </tr>    
                    <tr>
                        <td class="title">미리보기</td>
                        <td colspan="3" class="file_photo">
<!--                             <div id="holderMain"></div> -->
                            <img src="#" id="holderMain"/>
                        </td>
                    </tr> 
                    
                    <tr>
                        <td class="title">선박 전면 사진</td>
                        <td colspan="3">
                            <div style="float: left; margin-right: 1px;">
                                <input type="text" style="width: 200px;" class="text" id="org01ImgNm" name="org01ImgNm" data-id="org01ImgNm" readonly="readonly" valid="{label:'파일', method:'required'}">
                            </div>
                            <div style="float: left; margin-right: 1px; padding: 0px 6px 0px 6px;" class="fileUpload bko-gray bko-middle">
                                <span>업로드</span>
                                <input type="file" id="uploadFile01" name="uploadFile01" class="upload" onchange="RegShipInfoView.fileChoice('uploadFile01', 'org01ImgNm', 'holder01');"/>
                            </div>
                        </td>
                    </tr>    
                    <tr>
                        <td class="title">미리보기</td>
                        <td colspan="3" class="file_photo">
                            <img src="#" id="holder01"/>
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="title">선박 후면 사진</td>
                        <td colspan="3">
                            <div style="float: left; margin-right: 1px;">
                                <input type="text" style="width: 200px;" class="text" id="org05ImgNm" name="org05ImgNm" data-id="org05ImgNm" readonly="readonly" valid="{label:'파일', method:'required'}">
                            </div>
                            <div style="float: left; margin-right: 1px; padding: 0px 6px 0px 6px;" class="fileUpload bko-gray bko-middle">
                                <span>업로드</span>
                                <input type="file" id="uploadFile05" name="uploadFile05" class="upload" onchange="RegShipInfoView.fileChoice('uploadFile05', 'org05ImgNm', 'holder05');"/>
                            </div>
                        </td>
                    </tr>    
                    <tr>
                        <td class="title">미리보기</td>
                        <td colspan="3" class="file_photo">
                            <img src="#" id="holder05"/>
                        </td>
                    </tr>                                                       
                    
                </table>                        
                <div class="btn_box">
                    <a href="#" id="regShipInfoButton">등록</a>
                    <a href="#" id="cancelButton">취소</a>
                </div>
                <!--end btn_box-->
            </form>
        </div>
        <!--end table_write-->
    </div>
    <!--end content--> 
</div>
<%@ include file="/jsp/common/javascript.jsp" %>  
