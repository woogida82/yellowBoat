// valid type 에 따른 초기화
(function($) {
    $.validation = function(data, klass) {};
    
    $.extend($.validation, {
        settings : {
            isProcess : false,
            options   : null
        },
        // 입력할 패턴의 초기화
        setStyle : function($element, type) {
            // type :  '','eng'-영문 ,'numeric'-소숫점이하고정,'number'-숫자만, 'alphanum'-영문하고 숫자만
            // 영문만
            if (type == "eng") {
                jQuery($element).alpha();
                jQuery($element).css("ime-mode", "disabled");
            // 영문, 숫자
            } else if (type == "alphanum") {
                jQuery($element).alphanumber();
                jQuery($element).css("ime-mode", "disabled");
            // 숫자만
            } else if (type == "number") {
                jQuery($element).css("ime-mode", "disabled").number();
            // 숫자(천단위점, 소숫점)만
            } else if (type.match("numeric")) {
                var len = 0;
                if (type.replace("numeric","").length > 0) {
                    len = type.replace("numeric.","")*1;
                }
                // value에 들어 있는 데이터 초기화
                jQuery($element).comma(len);
                jQuery($element).numeric(len);
                jQuery($element).css("ime-mode", "disabled"); 
            } else if (type.match("double")) {
                jQuery($element).doubleType();
                jQuery($element).css("ime-mode", "disabled"); 
            } else if (type.match("jumin")) {
                jQuery($element).jumin();
                jQuery($element).css("ime-mode", "disabled"); 
            } else if (type.match("tel")) {
                jQuery($element).tel();
                jQuery($element).css("ime-mode", "disabled"); 
            } else if (type.match("lotto")) {
                jQuery($element).lotto();
                jQuery($element).css("ime-mode", "disabled"); 
            }
            
        },
        setEventPrevent : function(event) {
            if(event.keyCode && (event.keyCode < 48 || event.keyCode > 57) )
                event.preventDefault();
        },
        getValidObj : function($element) {
           var ret = null;
           try {
              ret = eval('(' + $element.attr('valid') + ')');
           } catch (e) {
              alert("["+$element.attr('name')+"] 의 valid 항목이 잘못 되었습니다.");
           }
           return ret;
        }
    });

  // 초기화 함수
    $.fn.initvalidation = function() {
        this.each(function() {
          // valid 의 설정에 따라 초기 타입 설정 (영문만, 숫자만, 길이제한, 패턴 등)
            $(this).find("input[valid]").each(function() {
              var valObject = $.validation.getValidObj($(this));
              if (valObject.type != undefined && valObject.type != null) {
                $.validation.setStyle($(this), valObject.type);
              }
            });
        });
    };
})(jQuery);


// input 제어 및 초기화에 사용할 함수
(function($) {
    
    // 숫자 및 영문자만 처리
    $.fn.jumin = function() {
        return this.each (function() {
            $(this).number({jumin:true, allow:"-"});
        });
    };
    
    // 숫자 및 영문자만 처리
    $.fn.tel = function() {
        return this.each (function() {
            $(this).number({tel:true, allow:"-"});
        });
    };
    
    // 숫자 및 영문자만 처리
    $.fn.alphanumber = function(p) { 
        p = $.extend({
            ichars: "!@#$%^&*()+=[]\\\';,/{}|\":<>?~-`._",
            nchars: "",
            allow : "-",
            comma : false,
            decim : 0,
            jumin : false,
            tel : false,
            doubleType : false
          }, p);    
        return this.each (
                function() {
                    if (p.nocaps) p.nchars += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                    if (p.allcaps) p.nchars += "abcdefghijklmnopqrstuvwxyz";
                    s = p.allow.split('');
                    for (var i=0;i<s.length;i++) if (p.ichars.indexOf(s[i]) != -1) s[i] = "\\" + s[i];
                    p.allow = s.join('|');

                    var reg = new RegExp(p.allow,'gi');
                    var ch = p.ichars + p.nchars;
                    ch = ch.replace(reg,'');

                    $(this).keypress (
                            function (e) {
                                    if (!e.charCode) {
                                      k = String.fromCharCode(e.which);
                                    }   else {
                                      k = String.fromCharCode(e.charCode);
                                    }

                                    if (ch.indexOf(k) != -1) e.preventDefault();
                                    if (e.ctrlKey&&k=='v') e.preventDefault();
                            }
                    );
                    
                    $(this).blur(function(){$(this).val($(this).val().replace(/[ㄱ-ㅎㅏ-ㅣ가-힣]/g, ''));});

                    $(this).keyup(
                        function (e) {
                            
                            if (e.keyCode && ((e.keyCode >= 33 && e.keyCode <= 40) || e.keyCode == 45 || e.keyCode == 46 || e.keyCode == 8 || e.keyCode == 9)) {
                                return;
                            }
                                
                            if (!e.charCode) {
                              if (p.comma) {
                                  $(this).comma(p.decim);
                              } else if (p.jumin) {
                                  
                                  var jumin = $(this).val().replace('-', '');
                                  
                                  if (jumin.length > 13) {
                                      jumin = jumin.substring(0, 13);
                                  }
                                  
                                  if (jumin.length > 6) {
                                      $(this).val(jumin.substring(0, 6) + '-' + jumin.substring(6));
                                  }
                              } else if (p.tel) {
                                  
                                  var tel = $(this).val().replace(/-/g, '');
                                    
                                  if (tel.length > 2) {
                                      if (tel.substring(0, 2) == '02') {
                                          
                                          if (tel.length > 10) {
                                              tel = tel.substring(0, 10);
                                          }
                                          
                                          if (tel.length >= 3 && tel.length < 6) {
                                              $(this).val(tel.substring(0, 2) + '-' + tel.substring(2));
                                          } else if (tel.length >= 6 && tel.length < 10) {
                                              $(this).val(tel.substring(0, 2) + '-' + tel.substring(2, 5) + '-' + tel.substring(5));
                                          } else if (tel.length >= 10) {
                                              $(this).val(tel.substring(0, 2) + '-' + tel.substring(2, 6) + '-' + tel.substring(6));
                                          } else {
                                              $(this).val(tel);
                                          }
                                          
                                      } else {
                                          
                                          if (tel.length > 11) {
                                              tel = tel.substring(0, 11);
                                          }
                                          
                                          if (tel.length >= 4 && tel.length < 7) {
                                              $(this).val(tel.substring(0, 3) + '-' + tel.substring(3));
                                          } else if (tel.length >= 7 && tel.length < 11) {
                                              $(this).val(tel.substring(0, 3) + '-' + tel.substring(3, 6) + '-' + tel.substring(6));
                                          } else if (tel.length >= 11) {
                                              $(this).val(tel.substring(0, 3) + '-' + tel.substring(3, 7) + '-' + tel.substring(7));
                                          } else {
                                              $(this).val(tel);
                                          }
                                          
                                      }
                                  } else {
                                      $(this).val(tel);
                                  }
                              }
                              
                              $(this).val($(this).val().replace(/[ㄱ-ㅎㅏ-ㅣ가-힣]/g, ''));
                            }
                        }
                    );

                    // 마우스 오른쪽 방지
                    $(this).bind('contextmenu',function () {return false;});
                }
            );
    };

  // 숫자만 처리
    $.fn.number = function(p) {
        var az = "abcdefghijklmnopqrstuvwxyz";
        az += az.toUpperCase();
        
        var allow = '';
        if (p && p.allow) {
            allow = p.allow;
        }
        
        p = $.extend({nchars:az, allow:allow}, p);
        return this.each (function() {
                $(this).alphanumber(p);
        });
    };

  // 영문자만 처리
    $.fn.alpha = function(p) {
        var nm = "1234567890";
        p = $.extend({nchars:nm}, p);
        return this.each (function() {
                $(this).alphanumber(p);
        });
    };  

  // 숫자에 소숫점 및 천단위 점 표시
  $.fn.numeric = function(len, p) {
    p = $.extend({allow:".", comma:true, decim:len}, p);
    return this.each (function() {
      $(this).number(p);
    });
  };
  
  //숫자에 소숫점 및 천단위 점 표시
  $.fn.doubleType = function(len, p) {
    p = $.extend({allow:".-", doubleType:true}, p);
    return this.each (function() {
      $(this).number(p);
    });
  };

  // 천단위 및 소숫점 표시
  $.fn.comma = function(len) {
      var regx = new RegExp(/(-?\d+)(\d{3})/);
      var str = $(this).val().replace(/,/gi,''); // 콤마 제거
      var bExists = str.indexOf(".");
      var strArr = null;
      var spcheck= false;
      if (str.indexOf(".") > -1) {
        spcheck= true;
          strArr = str.split('.');
      } else {
        strArr = new Array();
        strArr[0] = str;
        strArr[1] = "";
      }

      // 정규식으로 숫자만 추린다.
      if (strArr[0].length > 0 && strArr[0].substring(0,1) == "-") {
        strArr[0] = "-"+strArr[0].replace(/[^0-9]/g,"");
      } else {
        strArr[0] = strArr[0].replace(/[^0-9]/g,"");
      }
      strArr[1] = strArr[1].replace(/[^0-9]/g,"");

      if (bExists > -1) {
        // 소수 부분의 반올림 및 자릿수에 맞추어 0 셋팅 작업
        if (len > 0) {
          if (strArr[1].length < len) {
            len = strArr[1].length;
          }
          if (len > 0) {
            strArr[1] = "0."+strArr[1];
            str = ""+Math.round(strArr[1]*Math.pow(10, len));
            // 앞으로 0 채우기
            if (str.length > 0 && str.length < len) {
              var zz = len-str.length;
              for (z=0;z<zz;z++) {
                str = "0"+str;
              }
            }
          } else {
            // 재사용변수 이므로 초기화 한다.
            str = "";
          }
          strArr[1] = str;
        } else {
          spcheck= false;
          strArr[0] = ""+((strArr[0]*1)+Math.round(("0."+strArr[1])*1));
          strArr[1] = "";
        }
      }

      while(regx.test(strArr[0])){
        strArr[0] = strArr[0].replace(regx,"$1,$2");  
      }
      
      if (strArr[1] != "") {
        $(this).val(strArr[0]+"."+strArr[1]);
      } else {
        if (spcheck) {
          $(this).val(strArr[0]+".");
        } else {
          $(this).val(strArr[0]);
        }
      }
  };

})(jQuery);


// submit 처리
(function($) {
    $.onSubmit = function(data, klass) {};
    
    // ajax용 
    $.fn.onCheck = function(options)    {
        var result = null;
        try {
            options.data.put("method", options.method);
            $.ajax({
                url: options.url,
                async : options.async,
                type: options.type,
                timeout: 1000,
                data: options.data.toMap(),
                error: function(){
                    alert('죄송합니다. 시스템의 오류가 발생하였습니다.\n\n문제가 계속 발생할 경우 관리자에 문의하시기 바랍니다.');
                },
                success: function(data){
                    result = trim(data);
                }
            });
        } catch(e) {
            alert("errCode : " + e.number + "\n" + "errMessage : " + e.message);
        }
        
        return result;
    };
    
    // submit action
    $.fn.onSubmit = function(options) {
        try {
                // msgType default 정의
                var msgType = "alert";
                
                if (options.validmessage != undefined) {
                    msgType = options.validmessage; 
                }
                
                // 두번 클릭 방지
                if (this.keepProcess) {
                    alert("processing.....");
                    return false;
                }
                
                // 더블클릭 허용하는 경우 (true 방지, false 허용)
                if (options.keepProcess != null)
                    this.keepProcess = options.keepProcess;
                
                validator = new $.validator(msgType);
                
                if (options.validation != undefined && options.validation) {
                    if (!validator.validCheck(this)) {
                        return false;
                    }
                }
                
                if (options.confirmMessage != null && !confirm(options.confirmMessage)) {
                    return false;
                }
        
                if (options.callpost != null) {
                    
                    var ret = options.callpost();
                    
                    if (ret != undefined && !ret) {
                        return false;
                    }
                }
                
                if (options.target != null) {
                    $(this).attr({
                            action : options.url,
                            target : options.target
                        });
                } else {
                    $(this).attr({
                        action : options.url,
                        target : "_self"
                    });
                }
                
                if (options.ajaxSubmit != undefined && options.ajaxSubmit) {
                        
                    if ($(this).attr("enctype") == 'multipart/form-data') {
                    
                        // 세션체크
                        $.checkSession();
                        
                        var iframeId = 'iframe' + (new Date().getTime());
                        
                        $form = $(this).copyForm({
                            action : options.url,
                            method : 'post'
                        });
                        $form.attr("action", options.url);
                        $form.attr("method", "post");
                        $form.attr("enctype", "multipart/form-data");
                        $form.attr("target", iframeId);
                        
                        $(this).find("input[type=file]").each(function(){
                            var oldElement = $(this);
                            
                            var newElement = oldElement.clone();
                            oldElement.before(newElement);
                            $form.append(oldElement);
                        });
                        
                        $iframe = $("<iframe>", {
                            src  : "about:blank", 
                            name : iframeId,
                            style: "display:none;"
                        });
                        $iframe.appendTo('body');
                        
                        $iframe.on("load", function (e) {
                            
                            var data = $.parseJSON($iframe.contents().find("body").text());

                            options.success(data);
                            
                            $(this).remove();
                        });
                        
                        $form.appendTo('body');
                        $form.submit();
                        $form.remove();
                        
                    } else {
                        
                        var async = true;
                        if (options.async != undefined) {
                            async = options.async;
                        }
                        
                        $(this).ajaxForm({
                            async : async,
                            cache : false,
                            dataType : "json",
                            iframe : false,
                            success : function(data) {
                                
                                options.success(data);
                            }
                        }).submit();
                        
                        $(this).ajaxFormUnbind();
                    }
                    
                } else {
                    $(this).submit();
                }
                
                
            } catch (e){
                //$.onSubmit.keepProcess = false;
                return false;
            }
        return true;
    };
    
})(jQuery);