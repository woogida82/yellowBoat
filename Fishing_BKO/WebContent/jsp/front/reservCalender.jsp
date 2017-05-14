<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <%@ include file="/jsp/common/meta.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>옐로우보트</title>

    <link href="/css/front/bootstrap.min.css" rel="stylesheet">
    <link href="/css/front/index.css" rel="stylesheet">
    <!-- Owl Stylesheets -->
    <link rel="stylesheet" href="/css/front/owl.carousel.css">
    <link rel="stylesheet" href="/css/front/owl.theme.default.css">
    
	
</head>

<body>   
    <!--Start Header-->
    <nav class="navbar navbar-default">
        <div class="container header_box">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
                <a class="navbar-brand" href="#"><img src="/images/front/logo.png" alt="옐로우보트"></a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="#">조황정보<span class="sr-only">(current)</span></a></li>
                    <li><a href="#">예약현황</a></li>
                    <li><a href="#">고객센터</a></li>
                    <li><a href="#">이벤트</a></li>
                </ul>
                <div class="login_box">
                    <a href="#">로그인</a>
                    <a href="#">회원가입</a>
                </div>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
    <!--End Header-->

    <!--Start 서브컨텐츠-->
    <div class="container-fluid sub_top top_bg2">
    </div>

    <div class="container sub_content">
        <div class="row title_box2">
        	<div class="col-md-12"><h3>예약현황</h3></div>
        </div>
        <!--Start 예약표-->
        <div class="schedule_box col-md-12">
            <p id="calTitle" class="date">
                <a class="date_prev" href="#"></a>
                2016년 2월
                <a class="date_next" href="#"></a>
            </p>
            <table class="schedule">
                <colgroup>
                    <col width="14%" span="7" />
                </colgroup>
                <thead>
                    <tr>
                        <td class="sunday">일요일</td>
                        <td>월요일</td>
                        <td>화요일</td>
                        <td>수요일</td>
                        <td>목요일</td>
                        <td>금요일</td>
                        <td>토요일</td>
                    </tr>
                </thead>
                <tbody id="calendarTab">
                </tbody>
            </table>
        </div>
        <!--end schedule_box-->
    </div><!--End 서브컨텐츠-->

    <div class="container-wide m_footer">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <p>옐로우보트 | 대표 : 박상록 | 충청남도 태안군 남면 당암리 | 사업자등록번호 : 316-90-43448<br> 전화 : 010-2033-7470 | 대표전화 : 064-744-1176 | 이메일 : mjyang@naver.com<br> © COPYRIGHT YELLOW BOAT. ALL RIGHT RESERVED.</p>
                    <div class="top_btn" onclick="go_top();" style="cursor:pointer">
                        <img src="/images/front/top_bt.png" alt="맨위로" />
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--End Footer-->
    <form id="reservCalenderForm" name="reservCalenderForm" action="#">
        <input type="hidden" id="shipId" name="shipId" value="${bean.shipId}" />
        <input type="hidden" id="reservId" name="reservId"/>
        <input type="hidden" id="reservDtlId" name="reservDtlId"/>
        
        <input type="hidden" id="year" name="year" value="${bean.year}"/>
        <input type="hidden" id="month" name="month" value="${bean.month}"/>
        <input type="hidden" id="day" name="day"/>
    </form>

    <!--탑버튼-->
    <%@ include file="/jsp/common/javascript.jsp" %>  
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="/js/front/bootstrap.min.js"></script>
    <script>
        function go_top(orix,oriy,desx,desy) {
            var Timer; 
            if (document.body.scrollTop == 0) {
                 var winHeight = document.documentElement.scrollTop;
             } else {
                 var winHeight = document.body.scrollTop;
             }
             if(Timer) clearTimeout(Timer);
             startx = 0;
             starty = winHeight;
             if(!orix || orix < 0) orix = 0;
             if(!oriy || oriy < 0) oriy = 0;
             var speed = 7;
             if(!desx) desx = 0 + startx;
             if(!desy) desy = 0 + starty;
             desx += (orix - startx) / speed;
             if (desx < 0) desx = 0;
             desy += (oriy - starty) / speed;
             if (desy < 0) desy = 0;
             var posX = Math.ceil(desx);
             var posY = Math.ceil(desy);
             window.scrollTo(posX, posY);
             if((Math.floor(Math.abs(startx - orix)) < 1) && (Math.floor(Math.abs(starty - oriy)) < 1)){
                 clearTimeout(Timer);
                 window.scroll(orix,oriy);
             }else if(posX != orix || posY != oriy){
                 Timer = setTimeout("go_top("+orix+","+oriy+","+desx+","+desy+")",15);
             }else{
                 clearTimeout(Timer);
             }
        }
    </script>
</body>

</html>