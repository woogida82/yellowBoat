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

<div id="bko-workspace">
    <div class="bko-topspace">
        <div class="bko-blackGround" id="background1"></div>
        <div class="bko-topBar">
            <div id="bko-top-Side-Btn">
                <div class="bko-leftMenuLink" ><input type="image" src="/images/layout/btn_sidebar.png" id="bko-sidbarbtn" alt="메뉴"  title="메뉴"/></div>
                <div id="bko-topCI">
                    <div id="bko-topCIimg">
                        <img src="/images/layout/logo_new_S.png" style="width:100%;margin-left: 10px;" />
                    </div>
                </div>
                <div id="bko-divline"></div>
            </div>
            <div id="bko-infoBar">
                <div id="bko-leftNaviBtn"><input type="image" src="/images/layout/divNavi-left.png" class="bko-divNaviBtn" id="bko-slide-pevious" alt="앞으로" title="앞으로이동" /></div>
                <div id="bko-infoDisplay"><div id="bko-btnList" draggable="true" ondragstart="return dragStart(event)" ondragend="return dragEnd(event)"></div></div>
                <div id="bko-rightNaviBtn"><input type="image" src="/images/layout/divNavi-right.png" class="bko-divNaviBtn" id="bko-slide-next"  alt="뒤로" title="뒤로이동" /></div>
            </div>
            <div class="bko-Userinfo">
                <div class="bko-LoginUser">
                    <div class="bko-LoginUser" id="bko-LoginName"><b id="UserName">${SESSION_KEY_ADMIN.adminNm}</b></div>
                    <div class="bko-LoginUser" id="bko-LoginManu"><input type="image" src="/images/layout/down.png" alt="사용자정보관리" title="사용자정보관리" /></div>
                </div>
            </div>
        </div>
        <div class="bko-blackGround" id="background2"></div>
    </div>
    <div class="bko-bodyspace">
        <div id="bko-leftMenu">
            <div class="bko-leftMenuItem" id="bko-leftMenuList">
            </div><!-- bko-leftMenuItem -->     
        </div> <!-- bko-leftMenu -->
        
        <div id="bko-CententBox"></div>

    </div><!-- class=bko-bodyspace -->

</div>

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