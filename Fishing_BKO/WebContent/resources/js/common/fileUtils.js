(function($) {
    
    /**
     * 이미지파일을 업로드 할 시에 사용한다.
     */
    $.ajaxFileUpload = function(options) {
        
        // 세션체크
        $.checkSession();
        
        var iframeId = 'iframe' + (new Date().getTime());
        
        $form = $("<form>", {
            action  : '/bko/common/ajaxFileUpload',
            method  : 'POST',
            enctype : 'multipart/form-data',
            target  : iframeId
        });
        
        var oldElement = $("#" + options.fileId);
        
//        if (!oldElement.val().match(/(?:gif|jpg|png|bmp)$/)) {
//            alert("이미지 파일만 업로드 가능합니다.");
//            return;
//        }
        
        var newElement = oldElement.clone();
        oldElement.before(newElement);
        oldElement.attr("name", "upload");
        $form.append(oldElement);
        
        $hidden = $("<input>", {
            type   : "hidden",
            name   : "saveId",
            value  : options.saveId
        });
        
        $hidden.appendTo($form);
        
        $hidden = $("<input>", {
            type   : "hidden",
            name   : "resizeSize",
            value  : options.resizeSize
        });
        
        $hidden.appendTo($form);
        
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
        
        $iframe = $("<iframe>", {
            src  : "about:blank", 
            name : iframeId,
            style: "display:none;"
        });
        $iframe.appendTo('body');
        
        $iframe.on("load", function (e) {
            
            var data = $.parseJSON($iframe.contents().find("body").text());
            
            $(this).remove();
            
            options.success(data);
        });
        
        $form.submit().remove();
    };
    
    $.ajaxFileDownload = function(options) {
            
        // 세션체크
        $.checkSession();
        
        var iframeId = 'iframe' + (new Date().getTime());
        
        $form = $("<form>", {
            action  : '/bko/common/ajaxFileDownload',
            method  : 'POST',
            target  : iframeId
        });
        
        $hidden = $("<input>", {
            type   : "hidden",
            name   : "downId",
            value  : options.downId
        });
        
        $hidden.appendTo($form);
        
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
        
        $iframe = $("<iframe>", {
            src  : "about:blank", 
            name : iframeId,
            style: "display:none;"
        });
        $iframe.appendTo('body');
        
        $iframe.on("load", function () {                
            $(this).remove();
        });
        
        $form.submit().remove();
    };
    
    $.ajaxExcelUpload = function(options) {
        
        // 세션체크
        $.checkSession();
        
        if (options.workId == undefined || options.workId == '' 
            || options.fileId == undefined || options.fileId == '') {
            return;
        }
        
        var oldElement = $("#" + options.fileId);
        var fileName = oldElement.val();
        var fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        if (!fileName) {
            alert("업로드파일을 선택 해 주세요");
            return;
        }
        
        if (fileExt != "xls" && fileExt != "xlsx") {
            alert('엑셀파일만 등록 가능합니다.');
            return;
        }
        
        if (!confirm("업로드 하시겠습니까?")) {
            return;
        }
        
        var iframeId = 'iframe' + (new Date().getTime());
        
        $form = $("<form>", {
            action : "/bko/common/excelupload/", 
            method : "post",
            enctype: "multipart/form-data",
            target : iframeId
        });
        
        $hidden = $("<input>", {
            type   : "hidden",
            name   : "workId", 
            value  : options.workId
        });
        
        $hidden.appendTo($form);
        
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
        
        // 파일 추가
        var newElement = oldElement.clone();
        oldElement.before(newElement);
        oldElement.attr("name", "upload");
        $form.append(oldElement);
        $form.appendTo('body');
          
        $iframe = $("<iframe>", {
            src  : "about:blank", 
            name : iframeId,
            style: "display:none;"
        });
        $iframe.appendTo('body');
        
        $iframe.on("load", function (e) {
            
            var data = $.parseJSON($iframe.contents().find("body").text());
            
            if (data.result == "OK") {
                
                if (options.success) {
                    options.success(data);
                }
            } else {
                
                if (data.processId) {
                    if (confirm("데이터형식이 맞지 않습니다.\n에러리스트를 다운로드 받으시겠습니까?")) {
                        
                        $.excelDownload({
                            url : '/bko/common/excelErrList',
                            workId : options.workId,
                            data : {processId : data.processId}
                        });
                    }
                } else {
                    if (data.message) {
                        alert(data.message);
                    }
                }
                
            }
            
            $(this).remove();
        });
        
        $form.submit().remove();
    };
    
    $.excelDownload = function(options) {
            
        // 세션체크
        $.checkSession();
        
        if (!options.url) {
            return;
        }
        
        var iframeId = 'iframe' + (new Date().getTime());
        
        $iframe = $("<iframe>", {
            src  : "about:blank", 
            name : iframeId,
            style: "display:none;"
        });
        $iframe.appendTo('body');
        
        $iframe.on("load", function () {                
            $(this).remove();
        });
        
        $excelform = $("<form>", {
            action : options.url, 
            method : "post",
            target : iframeId
        });
        
        // data 추가
        if (options.data != undefined) {
            for (var key in options.data) {
                
                $hidden = $("<input>", {
                    type   : "hidden",
                    name   : key, 
                    value  : options.data[key]
                });
                
                $hidden.appendTo($excelform);
                
            }
        }
        
        $excelform.appendTo('body');
        
        $excelform.submit();
        
        $excelform.remove();
    };
    
    $.fn.excelDownload = function(options) {
        
        // 세션체크
        $.checkSession();
        
        $excelform = $(this).copyForm();
        
        var iframeId = 'iframe' + (new Date().getTime());
        
        $iframe = $("<iframe>", {
            src  : "about:blank", 
            name : iframeId,
            style: "display:none;"
        });
        $iframe.appendTo('body');
        
        $iframe.on("load", function () {                
            $(this).remove();
        });
        
        // data 추가
        if (options.data != undefined) {
            for (var key in options.data) {
                
                $hidden = $("<input>", {
                    type   : "hidden",
                    name   : key, 
                    value  : options.data[key]
                });
                
                $hidden.appendTo($excelform);
                
            }
        }
        
        $excelform.appendTo('body');
        
        $excelform.submit();
        
        $excelform.remove();
    };
})(jQuery);