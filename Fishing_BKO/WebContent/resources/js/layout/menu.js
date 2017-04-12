var Menu = {
    menusList: null,
    $leftMenuList: null,
    debug: false,
    
    makeMenu: function() {
        
        var oThis = this;
        
//        var appendHtml = "";
        
//        appendHtml += "<div id='dropdown-1' class='dropdown'>";
//        appendHtml += "<ul class='dropdown-menu'>";
//        appendHtml += "        <li><a href='javascript://' onClick='Main.changeInfo();'title='정보수정'>정보수정</a></li>";
//        appendHtml += "        <li><a href='javascript://' onClick='Main.changePW();'title='비밀번호변경'>비밀번호변경</a></li>";
//        appendHtml += "        <li><a href='/bko/logout' title='로그아웃'>로그아웃</a></li>";
//        appendHtml += "</ul>";
//        appendHtml += "</div>"; 
//        
//        $(".bko-bodyspace").append(appendHtml);
        
        oThis.$leftMenuList = $("#bko-leftMenuList");
        
        $.ajax({
            type : "POST",
            dataType : "json",
            url : "/bko/getMenuList",
            async: false,
            data: {},
            beforeSend: function() {},
            success : function(data, textStatus, xhr) {
                
                if (data.result == "OK") {
                    
                    if (data.menuList) {
                        oThis.menusList = data.menuList;
                        var str = '';
                        var setClass = '';
                        var captionName = '';
                        
                        for (var i = 0; i < data.menuList.length; i++) {
                            
                            var menu = data.menuList[i];
                            if (menu.subId == 0) {
                                if(menu.lastYn == 'Y'){
                                    if(menu.directYn == 'Y'){
                                        str += "<li class='has-sub'><a href='#'><span class='list3' onClick=\"Main.openClientDirect('"+menu.uri+"');\"><i class='material-icons'>"+menu.material+"</i>"+ menu.menuNm +"</span></a></li>"
                                    }else{
                                        str += "<li class='has-sub'><a href='#'><span class='list3' onClick=\"Main.openClient('"+menu.uri+"');\"><i class='material-icons'>"+menu.material+"</i>"+ menu.menuNm +"</span></a></li>"
                                    }
                                }else{
                                    str += "<li class='has-sub'><a href='#'><span class='list3'><i class='material-icons'>"+menu.material+"</i>"+ menu.menuNm +"</span></a><ul>"
                                }
                            }else{
                                if(menu.lastYn == 'Y'){
                                    str += "<li><a href='#'><span onClick=\"Main.openClient('"+menu.uri+"');\">"+ menu.menuNm +"</span></a></li></ul></li>";
                                }else{
                                    str += "<li><a href='#'><span onClick=\"Main.openClient('"+menu.uri+"');\">"+ menu.menuNm +"</span></a></li>";
                                }
                            }
                        }
                        oThis.$leftMenuList.html(str);
                    }
                    else {
                        oThis.$leftMenuList.html("<div class=\"bko-leftMenuTitle\">메뉴정보없음:000</div>");
                    }
                    
                } else {
                    oThis.$leftMenuList.html("<div class=\"bko-leftMenuTitle\">메뉴정보없음:001</div>");
                }
            },
            error : function(xhr, ajaxOptions, thrownError) {
                oThis.$leftMenuList.html("<div class=\"bko-leftMenuTitle\">메뉴정보없음:003</div>");
            }
        });
        
    }
};