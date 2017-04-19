<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/jsp/common/meta.jsp" %>
<%@ include file="/jsp/common/taglibs.jsp" %>
<%@ include file="/jsp/common/style.jsp" %>
<%@ include file="/jsp/common/javascript.jsp" %>

<title>옐로우 보트</title>
</head>

<body>
    <div class="header_wrap">
        <h1>
            <a href="#" onclick="Main.returnHome();"><img src="/images/common/logo.png" alt="대원낚시" /></a>
        </h1>
    </div>
    <div class="content_wrap">
        <div class="sidebar">
            <div class="profile">
                <img src="/images/common/photo.png" />
                
                <p class="title">${SESSION_KEY_ADMIN.userNm} 님</p>
                
                <p>좋은 하루 되세요!</p>
                <p class="date"></p>
            </div>
            <!--end profile-->
            <div class="gnb_box">
                <div id='cssmenu'>
                    <ul id='bko-leftMenuList'>
                    </ul>
                </div>
            </div>
            <!--end gnb_box-->
        </div>
        <!--end sidebar-->
        
        <div id="bko-CententBox"></div>
        
    </div>
    <!--end content_wrap-->
    
    <div class="footer_wrap">
        <p>Copyright YellowBoat. All right reserved.</p>
    </div>
    <!--end footer_wrap-->    
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
    .script("/js/layout/main.js?20170412")
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