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
                            
                            setClass = ' class=';
                            captionName = '';
                            
                            if (menu.subId == 0) {
                                setClass += '"bko-leftMenuTitle" ';
                                captionName = menu.menuNm;
                                
                            } else {
                                setClass += '"bko-leftMenuContext" id="m' + menu.menuIdx+'" ';
                                setClass += 'onClick="Main.openClient(\'w' + menu.menuIdx + '\' ,\'' + menu.uri + '\' ,\'' + menu.menuNm + '\');" ';
                                setClass += '"alt="' + menu.menuNm + '" title="' + menu.menuNm + '" ';
                                captionName += menu.menuNm;
                                
                            }
                            str += "<div "+setClass+">"+captionName+"</div> ";
                            if (oThis.debug) {  console.log("setClass ===> "+setClass);};
                            if (oThis.debug) {  console.log("captionName ===> "+captionName);};
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