<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/jsp/common/meta.jsp" %>
<%@ include file="/jsp/common/taglibs.jsp" %>
<%@ include file="/jsp/common/style.jsp" %>

<title>FISHING</title>
<!-- 필수 //-->
<link rel="stylesheet" type="text/css" href="/css/layout/main.css" />
<link rel="stylesheet" type="text/css" href="/css/layout/dropdown.css" />

<style type="text/css">

.bko-topspace .bko-topBar {
    background-color : lightskyblue;
}

</style>
</head>

<body>

<!-- <div id="bko-workspace"> -->
<!--     <div class="bko-topspace"> -->
<!--         <div class="bko-blackGround" id="background1"></div> -->
<!--         <div class="bko-topBar"> -->
<!--             <div id="bko-top-Side-Btn"> -->
<!--                 <div class="bko-leftMenuLink" ><input type="image" src="/images/layout/btn_sidebar.png" id="bko-sidbarbtn" alt="메뉴"  title="메뉴"/></div> -->
<!--                 <div id="bko-topCI"> -->
<!--                     <div id="bko-topCIimg"> -->
<!--                         <img src="/images/layout/logo_new_S.png" style="width:100%;margin-left: 10px;" /> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div id="bko-divline"></div> -->
<!--             </div> -->
<!--             <div id="bko-infoBar"> -->
<!--                 <div id="bko-leftNaviBtn"><input type="image" src="/images/layout/divNavi-left.png" class="bko-divNaviBtn" id="bko-slide-pevious" alt="앞으로" title="앞으로이동" /></div> -->
<!--                 <div id="bko-infoDisplay"><div id="bko-btnList" draggable="true" ondragstart="return dragStart(event)" ondragend="return dragEnd(event)"></div></div> -->
<!--                 <div id="bko-rightNaviBtn"><input type="image" src="/images/layout/divNavi-right.png" class="bko-divNaviBtn" id="bko-slide-next"  alt="뒤로" title="뒤로이동" /></div> -->
<!--             </div> -->
<!--             <div class="bko-Userinfo"> -->
<!--                 <div class="bko-LoginUser"> -->
<%--                     <div class="bko-LoginUser" id="bko-LoginName"><b id="UserName">${SESSION_KEY_ADMIN.adminNm}</b></div> --%>
<!--                     <div class="bko-LoginUser" id="bko-LoginManu"><input type="image" src="/images/layout/down.png" alt="사용자정보관리" title="사용자정보관리" /></div> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="bko-blackGround" id="background2"></div> -->
<!--     </div> -->
<!--     <div class="bko-bodyspace"> -->
<!--         <div id="bko-leftMenu"> -->
<!--             <div class="bko-leftMenuItem" id="bko-leftMenuList"> -->
<!--             </div>bko-leftMenuItem      -->
<!--         </div> bko-leftMenu -->
        
<!--         <div id="bko-CententBox"></div> -->

<!--     </div>class=bko-bodyspace -->

<!-- </div> -->
    <div class="header_wrap">
        <h1>
            <a href="#"><img src="images/logo.png" alt="대원낚시" /></a>
        </h1>
    </div>
    
    <div class="content_wrap">
        <div class="sidebar" id="bko-leftMenuList">
            <div class="profile">
                <img src="images/photo.png" />
                <p class="title">최종관리자님</p>
                <p>좋은 하루 되세요!</p>
                <p class="date">Today 2017.04.01</p>
            </div>
            <!--end profile-->
            <div class="gnb_box">
                <div id='cssmenu'>
<!--                     <ul> -->
<!--                         <li class='has-sub'><a href='#'><span class="list1"><i class="material-icons">settings</i> 기초자료관리</span></a> -->
<!--                             <ul> -->
<!--                                 <li><a href='#'><span>출조형태</span></a></li> -->
<!--                                 <li><a href='#'><span>지역구분</span></a></li> -->
<!--                             </ul> -->
<!--                         </li> -->
<!--                         <li class='has-sub'><a href='#'><span class="list2"><i class="material-icons">person</i> 회원관리</span></a> -->
<!--                             <ul> -->
<!--                                 <li><a href='#'><span>전체회원</span></a></li> -->
<!--                             </ul> -->
<!--                         </li> -->
<!--                         <li class='has-sub'><a href='#'><span class="list3"><i class="material-icons">receipt</i> 게시판관리</span></a> -->
<!--                             <ul> -->
<!--                                 <li><a href='#'><span>낚신 뉴스</span></a></li> -->
<!--                                 <li><a href='#'><span>낚신 조황정보</span></a></li> -->
<!--                                 <li><a href='#'><span>낚신 공지사항</span></a></li> -->
<!--                                 <li><a href='#'><span>낚신 이벤트</span></a></li> -->
<!--                                 <li><a href='#'><span>낚신 베스트갤러리</span></a></li> -->
<!--                                 <li><a href='#'><span>낚신 갤러리</span></a></li> -->
<!--                                 <li><a href='#'><span>낚신 쪽지</span></a></li> -->
<!--                                 <li><a href='#'><span>낚신 예약</span></a></li> -->
<!--                                 <li><a href='#'><span>낚신 추천배</span></a></li> -->
<!--                                 <li><a href='#'><span>낚신 특별할인</span></a></li> -->
<!--                                 <li><a href='#'><span>낚신 자주하는질문</span></a></li> -->
<!--                                 <li><a href='#'><span>낚신 환불규정</span></a></li> -->
<!--                             </ul> -->
<!--                         </li> -->
<!--                         <li><a href='sub_schedule_view.html'><span class="list3"><i class="material-icons">event_available</i> 일정관리</span></a> -->
<!--                         </li> -->
<!--                         <li><a href='#'><span class="list3"><i class="material-icons">close</i> 로그아웃</span></a> -->
<!--                         </li> -->
<!--                     </ul> -->
                </div>
            </div>
            <!--end gnb_box-->
        </div>
        <!--end sidebar-->
        
        <div class="content_box">
            <div class="util">
                <ul>
                    <li class="list1">Home</li>
                    <li>기초자료관리</li>
                    <li>출조형태</li>
                </ul>
            </div>
            <!--end util-->

            <div class="top_img">
                <h2>기초자료관리</h2>
                <img src="images/top_img1.png" />
            </div>
            <!--end top_img-->

            <div class="content">
<!--                 <h3>출조형태</h3> -->

<!--                 <div class="search_area"> -->
<!--                     <ul> -->
<!--                         <li><select style="width:120px;">제목</select></li> -->
<!--                         <li><input type="text" style="width:150px;" /></li> -->
<!--                         <li><a href="">검색</a></li> -->
<!--                     </ul> -->
<!--                 </div> -->
<!--                 end search_area -->
                
<!--                 <div class="type_select"> -->
<!--                     <span><i class="material-icons">search</i> 분류선택</span> -->
<!--                     <span><select></select></span> -->
<!--                     <a href="#">검색</a> -->
<!--                 </div> -->
<!--                 end type_select -->

<!--                 <div class="table_box">  -->
<!--                     <table width="100%" class="t_body"> -->
<!--                         <colgroup> -->
<!--                             <col width="10%" span="4" /> -->
<!--                             <col width="*" /> -->
<!--                             <col width="20%" /> -->
<!--                         </colgroup> -->
<!--                         <tr> -->
<!--                             <th>번호</th> -->
<!--                             <th>섬네일</th> -->
<!--                             <th>대분류</th> -->
<!--                             <th>소분류</th> -->
<!--                             <th>분류명</th> -->
<!--                             <th>관리</th> -->
<!--                         </tr> -->
<!--                         <tr> -->
<!--                             <td>1</td> -->
<!--                             <td><img src="images/thumb.jpg" alt="" /></td> -->
<!--                             <td>배낚시</td> -->
<!--                             <td>테마</td> -->
<!--                             <td>유료낚시</td> -->
<!--                             <td><a href="#" class="btn_modify">수정</a><a href="#" class="btn_delete">삭제</a></td> -->
<!--                         </tr> -->
<!--                         <tr> -->
<!--                             <td>1</td> -->
<!--                             <td><img src="images/thumb.jpg" alt="" /></td> -->
<!--                             <td>배낚시</td> -->
<!--                             <td>테마</td> -->
<!--                             <td>유료낚시</td> -->
<!--                             <td><a href="#" class="btn_modify">수정</a><a href="#" class="btn_delete">삭제</a></td> -->
<!--                         </tr> -->
<!--                         <tr> -->
<!--                             <td>1</td> -->
<!--                             <td><img src="images/thumb.jpg" alt="" /></td> -->
<!--                             <td>배낚시</td> -->
<!--                             <td>테마</td> -->
<!--                             <td>유료낚시</td> -->
<!--                             <td><a href="#" class="btn_modify">수정</a><a href="#" class="btn_delete">삭제</a></td> -->
<!--                         </tr> -->
<!--                         <tr> -->
<!--                             <td>1</td> -->
<!--                             <td><img src="images/thumb.jpg" alt="" /></td> -->
<!--                             <td>배낚시</td> -->
<!--                             <td>테마</td> -->
<!--                             <td>유료낚시</td> -->
<!--                             <td><a href="#" class="btn_modify">수정</a><a href="#" class="btn_delete">삭제</a></td> -->
<!--                         </tr> -->
<!--                         <tr> -->
<!--                             <td>1</td> -->
<!--                             <td><img src="images/thumb.jpg" alt="" /></td> -->
<!--                             <td>배낚시</td> -->
<!--                             <td>테마</td> -->
<!--                             <td>유료낚시</td> -->
<!--                             <td><a href="#" class="btn_modify">수정</a><a href="#" class="btn_delete">삭제</a></td> -->
<!--                         </tr> -->
<!--                     </table> -->
<!--                     <div class="pagination"> -->
<!--                         <a href="#" class="prev2"></a> -->
<!--                         <a href="#" class="prev"></a> -->
<!--                         <strong>1</strong> -->
<!--                         <a href="#">2</a> -->
<!--                         <a href="#">3</a> -->
<!--                         <a href="#">4</a> -->
<!--                         <a href="#">5</a> -->
<!--                         <a href="#">6</a> -->
<!--                         <a href="#">7</a> -->
<!--                         <a href="#" class="next"></a> -->
<!--                         <a href="#" class="next2"></a> -->
<!--                     </div> -->
<!--                     end pagination -->
<!--                     <div class="btn_box"> -->
<!--                         <a href="sub_write.html">등록</a> -->
<!--                     </div> -->
<!--                     end btn_box -->
<!--                 </div> -->
<!--                 end table_box -->
            </div>
            <!--end content-->
        </div>
        <!--end content_box-->
    </div>
    <!--end content_wrap-->
            




<script type="text/javascript" src="/js/jquery/LAB.min.js"></script>
<script type="text/javascript">
    $LAB.script(function() {
        return [
                "/js/jquery/jquery-1.11.2.min.js",
                "/js/jquery/jquery-migrate-1.2.1.min.js",
                "/js/jquery/jquery-ui.min.js",
            ];
    }).wait()
    .script("/js/jquery/html5shiv.min.js")
    .script("/js/jquery/jquery.form.min.js")
    .script("/js/jquery/jquery.slimscroll.min.js").wait()
    .script("/js/jquery/jquery.select2.min.js").wait()
    .script("/js/jquery/jquery-impromptu.min.js").wait()
    .script(function() {
        return [
            "/js/common/control.js",
            "/js/common/validator.js",
            "/js/common/utils.js",
            "/js/common/fileUtils.js",
            "/js/common/dateUtils.js",
            "/js/common/common.js?20151224"
        ];
    }).wait()
    .script("/js/layout/menu.js")
    .script("/js/layout/main.js?20160609")
    .wait(function(){
        $(function () {
            Main.init();
        });
    })
    .script("/js/common/script.js")
    ;
</script>
</body>
</html>