<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <%@ include file="/jsp/common/meta.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>옐로우보트</title>

    <link href="/css/front/bootstrap.min.css" rel="stylesheet">
    <link href="/css/front/index.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="/js/front/bootstrap.min.js"></script>
    <!-- Owl Stylesheets -->
    <link rel="stylesheet" href="/css/front/owl.carousel.css">
    <link rel="stylesheet" href="/css/front/owl.theme.default.css">
    
	
</head>

<body>   
    
    <div class="container-fluid top_banner">
    	<div class="row top_banner_img">
        	<img src="/images/front/top_banner.jpg" alt="" class="img-responsive">
            <div class="btn_box">
            	<a href="#" class="reserve_woman">여성전용 예약바로가기</a>
                <a href="#" class="reserve_group">기업단체 전용 예약바로가기</a>
                <a href="#" class="top_banner_close"><img src="/images/front/top_banner_close.png" alt=""></a>
            </div>
        </div>
        <a href="#" class="top_banner_open"><img src="/images/front/top_banner_btn.png" alt="탑배너버튼"></a>
    </div>
    
    <!--End top_banner-->
   
    
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

    <div class="container-fluid">
        <div class="row">
			<img src="/images/front/main_visual.jpg" alt="" class="img-responsive center-block">
        </div>
    </div>
    <!--End main_visual-->
    
    
    <div class="main_content">
        <div class="container-fluid m_search_wrap">
            <div class="row m_search">
                <div class="col-md-2">
                    <h3>간편검색</h3>
                </div>
                <div class="col-md-10">
                    <span><select></select></span>
                    <span><select></select></span>
                    <span><select></select></span>
                    <a href="#">확인</a>
                </div>
            </div>
        </div>
        <!--End main_search-->
        
        <div class="container m_banner">
            <div class="row">
                <div class="col-md-12">
                    <h2>옐로우보트 주요정보</h2>
                </div>
                <div class="col-md-5">
                    <a href="#"><img src="/images/front/m_banner_easy.jpg" alt="쉬운배낚시" class="img-responsive center-block"></a>
                </div>
                <div class="col-md-7">
                    <a href="#"><img src="/images/front/m_banner_event.jpg" alt="이벤트" class="img-responsive center-block"></a>
                    <div class="row mt_30">
                        <div class="col-md-6 col-xs-6">
                            <a href="#"><img src="/images/front/m_banner_info.jpg" alt="날씨정보" class="img-responsive center-block"></a>
                        </div>
                        <div class="col-md-6 col-xs-6">
                            <a href="#"><img src="/images/front/m_banner_faq.jpg" alt="FAQ" class="img-responsive center-block"></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--End m_banner-->
        
        <div class="container-wide m_banner2">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-xs-6">
                        <a href="#"><img src="/images/front/m_banner_woman.png" alt="여.성.우.대 " class="img-responsive center-block"></a>
                    </div>
                    <div class="col-md-6 col-xs-6">
                        <a href="#"><img src="/images/front/m_banner_group.png" alt="기업, 단체 환영" class="img-responsive center-block"></a>
                    </div>
                </div>
            </div>
        </div>
        <!--End m_banner2-->
        
        <div class="container hot_spot">
            <div class="row">
                <div class="col-md-12">
                    <h2>옐로우보트 추천스팟</h2>
                </div>
                <div class="col-md-3">
                	<div class="hot_spot_list">
                        <div class="img_box"><a href="/front/reservCalender?shipId=4"><img src="/images/front/m_noimg.jpg" alt="" class="img-responsive center-block"></a></div>
                        <div class="txt_box">
                            <p class="name">옐로우보트</p>
                            <p class="type">우럭, 광어 배낚시</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                	<div class="hot_spot_list">
                        <div class="img_box"><a href="/front/reservCalender?shipId=4"><img src="/images/front/m_noimg.jpg" alt="" class="img-responsive center-block"></a></div>
                        <div class="txt_box">
                            <p class="name">옐로우보트</p>
                            <p class="type">우럭, 광어 배낚시</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                	<div class="hot_spot_list">
                        <div class="img_box"><a href="/front/reservCalender?shipId=4"><img src="/images/front/m_noimg.jpg" alt="" class="img-responsive center-block"></a></div>
                        <div class="txt_box">
                            <p class="name">옐로우보트</p>
                            <p class="type">우럭, 광어 배낚시</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                	<div class="hot_spot_list">
                        <div class="img_box"><a href="/front/reservCalender?shipId=4"><img src="/images/front/m_noimg.jpg" alt="" class="img-responsive center-block"></a></div>
                        <div class="txt_box">
                            <p class="name">옐로우보트</p>
                            <p class="type">우럭, 광어 배낚시</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                	<div class="hot_spot_list">
                        <div class="img_box"><a href="/front/reservCalender?shipId=4"><img src="/images/front/m_noimg.jpg" alt="" class="img-responsive center-block"></a></div>
                        <div class="txt_box">
                            <p class="name">옐로우보트</p>
                            <p class="type">우럭, 광어 배낚시</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                	<div class="hot_spot_list">
                        <div class="img_box"><a href="/front/reservCalender?shipId=4"><img src="/images/front/m_noimg.jpg" alt="" class="img-responsive center-block"></a></div>
                        <div class="txt_box">
                            <p class="name">옐로우보트</p>
                            <p class="type">우럭, 광어 배낚시</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                	<div class="hot_spot_list">
                        <div class="img_box"><a href="/front/reservCalender?shipId=4"><img src="/images/front/m_noimg.jpg" alt="" class="img-responsive center-block"></a></div>
                        <div class="txt_box">
                            <p class="name">옐로우보트</p>
                            <p class="type">우럭, 광어 배낚시</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                	<div class="hot_spot_list">
                        <div class="img_box"><a href="/front/reservCalender?shipId=4"><img src="/images/front/m_noimg.jpg" alt="" class="img-responsive center-block"></a></div>
                        <div class="txt_box">
                            <p class="name">옐로우보트</p>
                            <p class="type">우럭, 광어 배낚시</p>
                        </div>
                    </div>
                </div>
                <a href="#" class="more_btn">더보기</a>
            </div>
        </div>
        <!--End hot_spot-->
        
        <div class="container-fluid m_gallery">
            <div class="row">
                <div class="col-md-12">
                    <h2>조황갤러리</h2>
                </div>
                <div class="col-md-12">
                    <div class="owl-carousel owl-theme">
                        <div class="item m_gallery_list">
                            <div class="img_box">
                                <a href="#"><img src="/images/front/img_sample.jpg" alt="" class="img-responsive center-block"></a>
                            </div>
                            <div class="txt_box">
                                <p class="name">[서해]돌고래호</p>
                                <p class="type">[3월] 오천항 돌고래호 / 우럭 출조</p>
                            </div>
                        </div>
                        <div class="item m_gallery_list">
                            <div class="img_box">
                                <a href="#"><img src="/images/front/img_sample2.jpg" alt="" class="img-responsive center-block"></a>
                            </div>
                            <div class="txt_box">
                                <p class="name">[서해]돌고래호</p>
                                <p class="type">[3월] 오천항 돌고래호 / 우럭 출조</p>
                            </div>
                        </div>
                        <div class="item m_gallery_list">
                            <div class="img_box">
                                <a href="#"><img src="/images/front/img_sample.jpg" alt="" class="img-responsive center-block"></a>
                            </div>
                            <div class="txt_box">
                                <p class="name">[서해]돌고래호</p>
                                <p class="type">[3월] 오천항 돌고래호 / 우럭 출조</p>
                            </div>
                        </div>
                        <div class="item m_gallery_list">
                            <div class="img_box">
                                <a href="#"><img src="/images/front/img_sample2.jpg" alt="" class="img-responsive center-block"></a>
                            </div>
                            <div class="txt_box">
                                <p class="name">[서해]돌고래호</p>
                                <p class="type">[3월] 오천항 돌고래호 / 우럭 출조</p>
                            </div>
                        </div>
                        <div class="item m_gallery_list">
                            <div class="img_box">
                                <a href="#"><img src="/images/front/img_sample.jpg" alt="" class="img-responsive center-block"></a>
                            </div>
                            <div class="txt_box">
                                <p class="name">[서해]돌고래호</p>
                                <p class="type">[3월] 오천항 돌고래호 / 우럭 출조</p>
                            </div>
                        </div>
                        <div class="item m_gallery_list">
                            <div class="img_box">
                                <a href="#"><img src="/images/front/img_sample.jpg" alt="" class="img-responsive center-block"></a>
                            </div>
                            <div class="txt_box">
                                <p class="name">[서해]돌고래호</p>
                                <p class="type">[3월] 오천항 돌고래호 / 우럭 출조</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--End m_gallery-->
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <a href="#"><img src="/images/front/m_enjoy.jpg" alt="" class="img-responsive center-block"></a>
                </div>
            </div>
        </div>
        <!--End m_enjoy-->
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                	<div class="m_map">
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3202.2021904177636!2d126.3531233377584!3d36.621519474542666!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357a71feb2291c07%3A0x5669582d1141a83e!2z7Lap7LKt64Ko64-EIO2DnOyViOq1sCDrgqjrqbQg64u57JWU66asIDEtMjA!5e0!3m2!1sko!2skr!4v1492593305995" width="100%" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
                    </div>
                </div>
            </div>
        </div>
        <!--End m_map-->
        
        <div class="container-wide m_app">
        	<div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="txt_box">
                       		<p>Download Apps</p>
                            <p>스마트 폰으로 앱 이용이 가능합니다.</p>
                        </div>
                        <div class="btn_box">
                        	<a href="#"><img src="/images/front/app_google.png" alt="구글플레이"></a>
                            <a href="#"><img src="/images/front/app_apple.png" alt="앱스토어"></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--End m_app-->
        
    </div>
    <!--End main_content-->
	
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

	<!--탑배너-->
	<script type="text/javascript">
        $('.top_banner_close').click(function() {
            $('.top_banner_img').slideToggle('slow', function() {
            });
			$('.top_banner_open').show('slow', function() {
            });
        });
		$('.top_banner_open').click(function() {
            $('.top_banner_img').slideToggle('slow', function() {
            });
			$('.top_banner_open').hide('slow', function() {
            });
        });
    </script>
	
    <!--조황갤러리-->
	<script src="/js/front/owl.carousel.js"></script>
	<script>
        $(document).ready(function() {
            $('.owl-carousel').owlCarousel({
                loop: true,
                margin: 10,
				autoplay: true,
                responsiveClass: true,
                responsive: {
                    0: {
                        items: 1,
                        nav: true
                    },
                    600: {
                        items: 3,
                        nav: false
                    },
                    1000: {
                        items: 5,
                        nav: true,
                        loop: false,
                        margin: 40
                    }
                }
            })
        })
    </script>
    
    <!--탑버튼-->
    <script>
        function go_top(orix, oriy, desx, desy) {
            var Timer;
            if (document.body.scrollTop == 0) {
                var winHeight = document.documentElement.scrollTop;
            } else {
                var winHeight = document.body.scrollTop;
            }
            if (Timer) clearTimeout(Timer);
            startx = 0;
            starty = winHeight;
            if (!orix || orix < 0) orix = 0;
            if (!oriy || oriy < 0) oriy = 0;
            var speed = 7;
            if (!desx) desx = 0 + startx;
            if (!desy) desy = 0 + starty;
            desx += (orix - startx) / speed;
            if (desx < 0) desx = 0;
            desy += (oriy - starty) / speed;
            if (desy < 0) desy = 0;
            var posX = Math.ceil(desx);
            var posY = Math.ceil(desy);
            window.scrollTo(posX, posY);
            if ((Math.floor(Math.abs(startx - orix)) < 1) && (Math.floor(Math.abs(starty - oriy)) < 1)) {
                clearTimeout(Timer);
                window.scroll(orix, oriy);
            } else if (posX != orix || posY != oriy) {
                Timer = setTimeout("go_top(" + orix + "," + oriy + "," + desx + "," + desy + ")", 15);
            } else {
                clearTimeout(Timer);
            }
        }
    </script>
</body>

</html>