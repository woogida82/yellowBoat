(function($) {
    
    $.fn.getInputType = function () {
        return this[0].tagName.toString().toLowerCase() === "input" ? $(this[0]).prop("type").toLowerCase() : this[0].tagName.toLowerCase();
    };
    
    $.fn.copyForm = function(options) {
        $form = $("<form>", {
            'action' : options.url,
            'method' : options.method
        });
        
        $(this).find("input[name], select[name], textarea[name]").each(function(){
            
            var sType = $(this).getInputType();
            
            if (sType == "text" || sType == "textarea" || sType == "hidden" || sType == "email"){
                
                $hidden = $("<input>", {
                    type   : "hidden",
                    name   : $(this).attr("name")
                });
                
                if ($(this).attr("valid")) {
                    $hidden.attr("valid", $(this).attr("valid"));
                }
                
                $hidden.val($(this).val()).appendTo($form);
            } else if (sType == "radio" || sType == "checkbox") {
                
                if ($(this).is(":checked")) {
                    
                    $hidden = $("<input>", {
                        type   : "hidden",
                        name   : $(this).attr("name")
                    });
                    
                    if ($(this).attr("valid")) {
                        $hidden.attr("valid", $(this).attr("valid"));
                    }
                    
                    $hidden.val($(this).val()).appendTo($form);
                }
            } else if (sType == "select") {
                
                var name = $(this).attr("name");
                
                if ($(this).find(":selected").length == 0) {
                 
                    if ($(this).attr("valid")) {
                        $hidden = $("<input>", {
                            type   : "hidden",
                            name   : name,
                            valid  : $(this).attr("valid")
                        });
                        
                        $hidden.appendTo($form);
                    }
                } else {
                    $(this).find(":selected").each(function(){
                        
                        $hidden = $("<input>", {
                            type   : "hidden",
                            name   : name
                        });
                        
                        $hidden.val($(this).val()).appendTo($form);
                    });
                }
            }
            
        });
        
        // data 추가
        if (options.data != undefined) {
            for (var key in options.data) {
                
                $hidden = $("<input>", {
                    type   : "hidden",
                    name   : key, 
                    value  : options.data[key]
                });
                
                $hidden.appendTo($form);
            }
        }
        
        return $form;
    },
    
    $.popup = function(options) {
        
        var target = options.target?options.target:"NewPopup";
        
        try{
            
            $form = $("<form>", {
                'action' : options.url,
                'target' : target,
                'method' : 'POST'
            });
            
            // data 추가
            if (options.data != undefined) {
                for (var key in options.data) {
                    
                    $hidden = $("<input>", {
                        type   : "hidden",
                        name   : key, 
                        value  : options.data[key]
                    });
                    
                    $hidden.appendTo($form);
                }
            }
            
            $form.appendTo('body');
            
            if (options.width == undefined) {
                options.width = 800;
            }
        
            if (options.height == undefined) {
                options.height = 600;
            }
            
            var popWidth = options.width;
            var popHeight = options.height;
            
            //scrollbar display 여부.
            var scrollable = (options.scrollable == undefined || options.scrollable == "" || options.scrollable == 'Y') ? "yes" : "no";
            var resizable = (options.resizable == undefined || options.resizable == "" || options.resizable == 'Y') ? "yes" : "no";
            var fullscreen = options.fullscreen == 'Y' ? "yes" : "no";
            
            if (fullscreen == 'yes') {
                popWidth = screen.width;
                popHeight = screen.height;
            }
            
            var x = (screen.availWidth - popWidth) / 2;
            var y = (screen.availHeight - popHeight) / 2;
            var opt =   "left=" + x + "px," + 
                        "top=" + y + "px," + 
                        "width=" + popWidth + "," + 
                        "height=" + popHeight + "," + 
                        "toolbar=no," +
                        "location=no," +
                        "directories=no," +
                        "status=no," +
                        "menubar=no," +
                        "resizable=" + resizable + "," +
                        "fullscreen=" + fullscreen + "," +
                        "scrollbars=" + scrollable;
            
            var popupObj = window.open("", target, opt);
            
            $form.submit().remove();
            
            popupObj.focus();
        } catch (e) {
            return false;
        }
    };
    
    $.fn.popup = function(options) {
        
        var target = options.target!=undefined?
                        options.target:"NewPopup";
        
        try{
            
            $form = $(this).copyForm();
            
            $form.appendTo('body');
            
            if (options.width == undefined) {
                options.width = 800;
            }
        
            if (options.height == undefined) {
                options.height = 600;
            }
            
            //scrollbar display 여부.
            var scrollable = (options.scrollable == undefined || options.scrollable == "" || options.scrollable == 'Y') ? "yes" :"no";
            
            var x = (screen.availWidth - options.width) / 2;
            var y = (screen.availHeight - options.height) / 2;
            var opt =   "left=" + x + "px," + 
                        "top=" + y + "px," + 
                        "width=" + options.width + "," + 
                        "height=" + options.height + "," + 
                        "toolbar=no," +
                        "location=no," +
                        "directories=no," +
                        "status=no," +
                        "menubar=no," +
                        "resizable=yes," +
                        "scrollbars=" + scrollable;
            
            var popupObj = window.open("", target, opt);
            
            $form.submit().remove();
            
            popupObj.focus();
        } catch (e) {
            return false;
        }
    };
    
    /* 2자리보다 적을 경우 앞에 0을 붙여 자리수를 맞춘다. 날짜(월,일)에 사용*/
    $.zeroPad = function(d, digits){
        
        if (digits == undefined) {
            digits = 2;
        }
        
        var zero = '';
        d = d.toString();

        if (d.length < digits) {
            for (var i = 0; i < digits - d.length; i++) {
                zero += '0';
            }
        }
        
        return zero + d;
    };
    
    /* 특수문자 처리 */
    $.escapeTagName = function(name){
        var tmpName = name.split("[").join("\\[");
        tmpName = tmpName.split("]").join("\\]");
        tmpName = tmpName.split(".").join("\\.");
        tmpName = tmpName.split("/").join("\\/");
        return tmpName;
    };
    
    /* 금액 콤마 표시*/
    $.setComma = function(n){
        if (!n) {
            return '';
        }
        
        var reg = /(^[+-]?\d+)(\d{3})/;
        n += '';
        while (reg.test(n)){
            n = n.replace(reg, '$1' + ',' + '$2');
        }
  
        return n;
    };
    
    $.removeComma = function(n){
        
        if (n) {
            n = n.split(",").join("");
        }
  
        return n;
    };
    
    // IFRAME용
    $.checkSession = function(){
        
        // 세션체크
        $.ajax({
            url: '/bko/common/checkSession',
            type: 'POST',
            dataType: 'json',
            async: false,
            success: function(data, textStatus, xhr) {} 
        });
        
    };
    
    $.ajaxError = function(xhr, status, err){
        
        if (xhr.status == 999) {
            alert("세션이 만료 되었습니다.");
            
            var loginUrl = "/bko/login";
            
            var isObj = false;
            $("object", parent.document).each(function(){
                if ($(this).attr("data") == location.pathname) {
                    isObj = true;
                    return false;
                }
            });
            
            // Object인 경우 (콘텐츠Body인 경우)
            if (isObj) {
                parent.top.location.href = loginUrl;
            }
            // 팝업인 경우
            else if (opener) {
                opener.parent.top.location.href = loginUrl;
                window.close();
            }
            // 상기 외
            else {
                top.location.href = loginUrl;
            }
        } else {
            alert("예외가 발생했습니다. 관리자에게 문의하세요.");
        }
        
    };
    
 
    $.bindVal= function(data, htmlId){
        
        var targetObj;
        
        if (typeof htmlId == 'string') {
            targetObj = $("#" + htmlId + " [data-id]");
        } else {
            targetObj = htmlId.find("[data-id]");
        }
        
        targetObj.each(function() {
            
            var dataId = $(this).data("id");
            var val = '';
                      
            if (/(.+)\[([0-9]+)\]\.(.+)/i.test(dataId)) {
                try {
                    val = data[RegExp.$1][RegExp.$2][RegExp.$3];
                } catch(e) {
                    
                }
            } else {
                val = data[dataId];
            }
                
            if (val) {
                var sType = $(this).getInputType();
                
                if (sType == "text" || sType == "textarea" || sType == "hidden") {
                    $(this).val(val);
                    
                    if ($(this).attr("onkeyup")) {
                        $.ajaxSetup({async:false});
                        $(this).trigger("keyup");
                        $.ajaxSetup({async:true});
                    }
                    
                } else if (sType == "select") {
                    
                    if (val) {
                        var vals = val.split(",");
                        
                        for (var i = 0; i < vals.length; i++) {
                            $(this).find("option[value='" + vals[i] +"']").attr("selected", true);
                        }
                    }
                    
                    if ($(this).attr("onchange")) {
                        $.ajaxSetup({async:false});
                        $(this).trigger("change");
                        $.ajaxSetup({async:true});
                    }
                } else if (sType == "checkbox") {
                    if ($(this).val() == val) {
                        $.ajaxSetup({async:false});
                        $(this).trigger("click");
                        $.ajaxSetup({async:true});
                    }
                } else if (sType == "ul") {
                    
                    if ($(this).attr("class") == "radio-group" && $(this).find("input[value=" + val + "]:radio").length > 0) {
                        var radioObj = $(this).find("input[value=" + val + "]:radio");
                        
                        radioObj.attr("checked", true);
                        
                        if (radioObj.attr("onclick")) {
                            $.ajaxSetup({async:false});
                            radioObj.trigger("click");
                            $.ajaxSetup({async:true});
                        }
                    }
                    
                    if ($(this).attr("class") == "checkbox-group" && $(this).find("input:checkbox").length > 0) {
                        var vals = val.split(",");
                        var checkFlg = false;
                        
                        if (vals.length > 0) {
                            $(this).find("input:checkbox").each(function(){
                                $(this).attr("checked", false);
                            });
                        }
                        
                        for (var i = 0; i < vals.length; i++) {
                            if ($(this).find("input[value=" + vals[i] + "]:checkbox").length > 0) {
                                var chkObj = $(this).find("input[value=" + vals[i] + "]:checkbox");
                                
                                chkObj.attr("checked", true);
                                
                                if (chkObj.attr("onclick")) {
                                    $.ajaxSetup({async:false});
                                    chkObj.trigger("click");
                                    $.ajaxSetup({async:true});
                                }
                            }
                        }
                    }
                    
                } else if (sType == "img") {
                    $(this).attr("src", cdnDomainName + val);
                } else {
                    $(this).html(val);
                }
            }
        });
        
    };
    
    /**
     * 한글포함 문자열 길이를 구한다
     */
    $.getTextBytes = function (str) {
        var len = 0;
        for (var i = 0; i < str.length; i++) {
            if (escape(str.charAt(i)).length == 6) {
                len++;
            }
            len++;
        }
        return len;
    };
    
    $.popupImg = function(imgPath){
        var img = new Image();
        img.src = imgPath; 
        img.onload = function() {
            
            var popupWidth = this.width;
            if (popupWidth > 600) {
                popupWidth = 600;
            }

            var html = '<div style="position: relative;">';
            html += '<img src="'+imgPath+'" style="width:' + popupWidth + 'px;">';
            html += '</div>';

            var popup = {
                state0: {
                    title: '미리보기',
                    html: html,
                    buttons: {},
                    focus: 1,
                    position: {width : popupWidth + 20},
                    submit:function(e,v,m,f){

                    }
                }
            }
            var myPrompt = $.prompt(popup, {
                loaded: function(e){
                }
            });
        }
    };
    
    /**
     * null값을 빈문자열로 변경한다.
     */
    $.convStr = function (str) {
        if (!str) {
            return '';
        }
        
        return str;
    };
})(jQuery);