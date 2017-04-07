(function ($) {
    $.validator = function(t) {
        this.form = null;
      this.error = {
        result: false,
        type: t,
        msg: null,
        msgAll: '',
        resAll: true
      };
    };
    $.extend($.validator, {
        prototype: {
            validCheck: function (f) {
                  var ele = f.find(':input');
                // 메시지 초기화
                $('.es_error').remove();
                var validationCheck = true;
                ele.each(function (index) {
                    //$(this).val($(this).val());
                    validationCheck = validator.valid(this);
                    return validationCheck;
                });
                // 한번에 메시지 표시하기
                if (!this.error.resAll) {
                  if (this.error.msgAll != "") {
                    alert(this.error.msgAll);
                  }
                  return this.error.resAll;
                } else {
                  return validationCheck;
                }
            },
            valid: function (ele) {
                //var validator = this;
                var pattern = /({.+?}$)/;
                var cvalue = $(ele).attr('valid');
                var cls = null;
                if (cvalue != null) {
                    cls = cvalue.match(pattern);
                }
                if (cls != null) {
                    cls = cls[0];
                    try {
                        var orule = eval('(' + cls + ')');
                    } catch (e) {
                        this.error.result = false;
                        // Exception 에 해당하므로 바로 화면에 뿌리도록 한다.
                        this.error.type = 'alert';
                        this.error.msg = '[' + $(ele).attr('name') + '] 필드내의 valid 안에 표현식이 잘못된것 같습니다\n다시 확인해 주세요.';
                        this.invalidProcess(ele, orule, '');
                        return false;
                    }
                    var subrule = this.getSubRule(orule);
                    if (subrule != undefined) {
                      for (var z=0;z < subrule.length; z++) {
                        if (subrule[z].indexOf(":") > -1) {
                          subvalue = subrule[z].substring(subrule[z].indexOf(":")+1);
                          subkey   = subrule[z].substring(0,subrule[z].indexOf(":"));
                        } else {
                          subvalue = null;
                          subkey   = subrule[z];
                        }
                        var mtdname = 'val' + $.validator.ucfirst(subkey);
                        var existMethod = this.hasMethod(mtdname);
                        if (existMethod == undefined) {
                            this.error.result = false;
                            this.error.msg = 'valid 속성 안에 존재하지 않는 메소드를 호출하였습니다 [' + subkey + ']';
                        } else {
                            validator[mtdname](ele, orule, subkey);
                        }
                        if (this.error.result) {
                            this.invalidProcess(ele, orule, subkey);
                            if (this.error.type == "alertAll" || this.error.type == "printAll") {
                              this.error.resAll = false;
                              break;
                            } else {
                              return false;
                            }
                        }
                     }
                   }
                }
                return true;
            },

            getSubRule: function (orule) {
                if (orule.method != null) {
                   var subvalue = orule.method.replace(/(^\s*)|(\s*$)/gi, "");
                   var tmp = subvalue.split(",");
                   for (var z=0;z<tmp.length;z++) {
                     tmp[z] = tmp[z].replace(/(^\s*)|(\s*$)/gi, "");
                   }
                   return tmp;
                }
            },

            getSubValue: function (subrule, subkey) {
                var subvalue = null;
                for (var z=0;z < subrule.length; z++) {
                  if (subrule[z].indexOf(subkey) == 0) {
                    if (subrule[z].indexOf(":") > -1) {
                      subvalue = subrule[z].substring(subrule[z].indexOf(":")+1).replace(/(^\s*)|(\s*$)/gi,"");
                    } else {
                      subvalue = true;
                    }
                    break;                    
                  }
                }
                if (subvalue == null) {
                  // key 가 없음 (처리할 필요 없음)
                  return false;
                } else {
                  return subvalue;
                }
            },

            hasMethod: function (methodname) {
                return this && this[methodname] && this[methodname] instanceof Function;
            },
            invalidProcess: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), "focusid");
                if (this.error.type == 'printAll') {
                    var ease = "easeInOutExpo";
                    var parent = $(ele.parentNode);
                    var originBG = $(ele).css("background-color");
                    var hitBG = "#F0E68C";
                    
                    $(ele)
                        .stop()
                        .animate({backgroundColor: hitBG}, 100, ease)
                        .animate({backgroundColor: originBG}, 300, ease)
                        .animate({backgroundColor: hitBG}, 200, ease)
                        .animate({backgroundColor: originBG}, 300, ease)
                        .animate({backgroundColor: hitBG}, 300, ease)
                        .animate({backgroundColor: originBG}, 300, ease);
                    
                    parent.append("<div class='es_error'>" + this.error.msg + "</div>");
                    
                    if( parent.find('div.es_error').length > 0 ) parent.find('div.es_error').stop().hide().slideDown(ease).delay(3000).slideUp(ease, function(){$(this).remove();});
                } else if (this.error.type == 'print') {
                    var ease = "easeInOutExpo";
                    var parent = $(ele.parentNode);
                    var originBG = $(ele).css("background-color");
                    var hitBG = "#F0E68C";
                    
                    $(ele)
                        .stop()
                        .animate({backgroundColor: hitBG}, 100, ease)
                        .animate({backgroundColor: originBG}, 300, ease)
                        .animate({backgroundColor: hitBG}, 200, ease)
                        .animate({backgroundColor: originBG}, 300, ease)
                        .animate({backgroundColor: hitBG}, 300, ease)
                        .animate({backgroundColor: originBG}, 300, ease);
                        
                    parent.append("<div class='es_error'>" + this.error.msg + "</div>");
                    
                    if( parent.find('div.es_error').length > 0 ) parent.find('div.es_error').stop().hide().slideDown(ease).delay(3000).slideUp(ease, function(){$(this).remove();});
                    if (!subvalue) {
                        $(ele).focus();
                    } else {
                        $('#' + subvalue).focus();
                    }
                } else if (this.error.type == 'alertAll') {
                    this.error.msgAll += "\n"+this.error.msg;
                } else {
                    alert(this.error.msg);
                    if (!subvalue) {
                        $(ele).focus();
                    } else {
                        $('#' + subvalue).focus();
                    }
                }
                this.initError();
            },
            initError: function () {
                this.error.result = false;
                this.error.msg = null;
            },
            valRequired: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                // key 만 있어도 체크 하는 경우
                if (subvalue == false)  return;
                if ($(ele).attr('type') == 'checkbox' || $(ele).attr('type') == 'radio') {
                    if ($("input[name=" + $(ele).attr("name") + "]:checked").length == 0) {
                        this.error.result = true;
                        this.error.msg = '[' + orule.label + '] 필수 항목입니다.';
                    }
                } else {
                    if ($(ele).val()==null || $(ele).val() == '') {
                        this.error.result = true;
                        this.error.msg = '[' + orule.label + '] 필수 항목입니다.';
                    }
                }
            },
            valEqual: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                // 반드시 subvalue 가 필요한 경우 boolean 으로 넘어오는 것은 통과
                if (subvalue == false || subvalue == true)  return;
                if ($(ele).val() != $('#'+subvalue).val()) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] 값이 일치하지 않습니다.';
                }
            },
            valNotequal: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                // 반드시 subvalue 가 필요한 경우 boolean 으로 넘어오는 것은 통과
                if (subvalue == false || subvalue == true)  return;
                if ($(ele).val() == $('#'+subvalue).val()) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] 값이 일치합니다. 새로운 값을 입력해 주세요.';
                }
            },
            valMinlength: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                if (subvalue == false || subvalue == true)  return;
                if ($(ele).val().length < subvalue*1) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] 최소 ' + subvalue + '자 이상 입력하세요.';
                }
            },
            valMaxlength: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                if (subvalue == false || subvalue == true)  return;
                if ($(ele).val().length > subvalue*1) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] 최대 ' + subvalue + '자 이하로 입력하세요.';
                }
            },
            valEmail: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                if (subvalue == false)  return;
                var pattern = /([0-9a-zA-Z_-]+)@([0-9a-zA-Z_-]+)\.([0-9a-zA-Z_-]+)/;
                if (!pattern.test($(ele).val())) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] 이메일 형식을 확인해 주세요.';
                }
            },
            valHangul: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                if (subvalue == false)  return;
                var pattern = /[^가-힣]/;
                if (pattern.test($(ele).val())) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] 한글만 입력 가능합니다.';
                }
            },
            valHangul2: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                if (subvalue == false)  return;
                var pattern = /[^가-힣ㄱ-ㅎㅏ-ㅣ]/;
                if (pattern.test($(ele).val())) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] 한글만 입력 가능합니다.';
                }
            },
            valNospace: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                if (subvalue == false)  return;
                var pattern = /(\s)/g;
                if (pattern.test($(ele).val())) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] 공백문자는 입력할 수 없습니다.';
                }
            },
            valNumber: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                if (subvalue == false)  return;
                var pattern = /[^0-9]/;
                if (pattern.test($(ele).val())) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] 숫자만 입력할 수 있습니다.';
                }
            },
            valNumeric: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                if (subvalue == false)  return;
                var pattern = /(-?\d+)(\d{3})(\.?\d*)/;
                if (pattern.test($(ele).val())) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] 숫자만 입력할 수 있습니다.';
                }
            },
            valDouble: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                if (subvalue == false)  return;
                var pattern = /^[-]?\d+(?:[.]\d+)?$/;
                if (!pattern.test($(ele).val())) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] 더블형 숫자만 입력할 수 있습니다.';
                }
            },
            valAlpha: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                if (subvalue == false)  return;
                var pattern = /[^a-z]/i;
                if (pattern.test($(ele).val())) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] 영문자만 입력할 수 있습니다.';
                }
            },
            valAlphanumber: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                if (subvalue == false)  return;
                var pattern = /[^a-z0-9]/i;
                if (pattern.test($(ele).val())) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] 영문자와 숫자만 입력할 수 있습니다.';
                }
            },
            valAlphanumber_: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                if (subvalue == false)  return;
                var pattern = /[^a-z0-9_]/i;
                if (pattern.test($(ele).val())) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] 영문자, 숫자, \'-\' 만 입력할 수 있습니다.';
                }
            },
            valPhonenumber: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                if (subvalue == false)  return;
                var pattern = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/;
                if (!pattern.test($(ele).val())) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] 전화번호를 확인해 주세요.';
                }
            },
            valHangulalphanumber: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                if (subvalue == false)  return;
                var pattern = /[^가-힣a-z0-9]/i;
                if (pattern.test($(ele).val())) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] 한글, 영문, 숫자만 입력할 수 있습니다.';
                }
            },
            valJumin: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                if (subvalue == false)  return;
                var value = $(ele).val().replace(/[^\d]/g, '');
                var pattern = /\d{13}/;
                if (!pattern.test(value)) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] 주민번호 13자리를 입력해 주세요.';
                } else {
                    var sum_1 = 0;
                    var sum_2 = 0;
                    var at = 0;
                    var juminno = value;
                    sum_1 = (juminno.charAt(0) * 2) + (juminno.charAt(1) * 3) + (juminno.charAt(2) * 4) + (juminno.charAt(3) * 5) + (juminno.charAt(4) * 6) + (juminno.charAt(5) * 7) + (juminno.charAt(6) * 8) + (juminno.charAt(7) * 9) + (juminno.charAt(8) * 2) + (juminno.charAt(9) * 3) + (juminno.charAt(10) * 4) + (juminno.charAt(11) * 5);
                    sum_2 = sum_1 % 11;
                    if (sum_2 == 0) at = 10;
                    else {
                        if (sum_2 == 1) at = 11;
                        else at = sum_2;
                    }
                    att = 11 - at;
                    if (juminno.charAt(12) != att || juminno.substr(2, 2) < '01' || juminno.substr(2, 2) > '12' || juminno.substr(4, 2) < '01' || juminno.substr(4, 2) > '31' || juminno.charAt(6) > 4) {
                        this.error.result = true;
                        this.error.msg = '[' + orule.label + '] 올바른 주민등록번호가 아닙니다.';
                    }
                }
            },
            valTaxno: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                if (subvalue == false)  return;
                var value = $(ele).val().replace(/[^\d]/g, '');
                var sum = 0;
                var getlist = new Array(10);
                var chkvalue = new Array('1', '3', '7', '1', '3', '7', '1', '3', '5');
                for (var i = 0; i < 10; i++) {
                    getlist[i] = value.substring(i, i + 1);
                }
                for (var i = 0; i < 9; i++) {
                    sum += getlist[i] * chkvalue[i];
                }
                sum = sum + parseInt((getlist[8] * 5) / 10);
                sidliy = sum % 10;
                sidchk = 0;
                if (sidliy != 0) {
                    sidchk = 10 - sidliy;
                } else {
                    sidchk = 0;
                } if (sidchk != getlist[9]) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] 잘못된 사업자등록번호입니다.';
                }
            },
            valGroupcheck: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                if (subvalue == false)  return;
                var flag = false;
                ele = document.getElementsByName(ele.name);
                for (var i = 0; i < ele.length; i++)
                    if (ele[i].checked) flag = true;
                if (!flag) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] 최소한 하나이상 체크하셔야 합니다.';
                }
            },
            valZipcode: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                if (subvalue == false)  return;
                var pattern = /^\d{3}-?\d{3}($)/;
                if (!pattern.test($(ele).val())) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] 우편번호가 잘못된것 같습니다.';
                }
            },
            valUniq: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                // 반드시 subvalue 가 필요한 경우
                if (subvalue == false || subvalue == true)  return;
                var data = {};
                data[$(ele).attr('name')] = encodeURIComponent($(ele).val());
                $.ajax({
                    type: 'post',
                    async: false,
                    url: subvalue,
                    data: data,
                    success: function (data) {
                        if (data.result != '0') {
                            validator.error.result = true;
                            validator.error.type = 'print';
                            validator.error.msg  = '이미 사용중입니다.';
                        }
                    }
                });
            },
            valIp: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                if (subvalue == false)  return;
                
                var validateIp = function(ip) {
                    var pattern4 = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
                    
                    if (pattern4.test(ip))
                        return true;
                    
                    var pattern6 = /(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]).){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]).){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))/;
                    return pattern6.test(ip);
                };
                
                if (!validateIp($(ele).val())) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] IP형식을 확인해 주세요.';
                }
            },
            valMac: function (ele, orule, subkey) {
                var subvalue = this.getSubValue(this.getSubRule(orule), subkey);
                if (subvalue == false)  return;
                
                var expresison = /^([0-9A-F]{2}[:-]?){5}([0-9A-F]{2})$/;
                
                if (!expresison.test($(ele).val())) {
                    this.error.result = true;
                    this.error.msg = '[' + orule.label + '] MAC형식을 확인해 주세요.';
                }
            }
           
        },
        setResult: function () {
            var obj = {};
            obj.result = true;
            obj.msg = msg;
        },
        bothTrim: function (value) {
            var pattern = /(^\s*)|(\s*$)/g;
            return value.replace(pattern, '');
        },
        ucfirst: function (value) {
            str = value.toString();
            var parts = str.match(/(\w)(\w*)/);
            try {
            str = parts[1].toUpperCase() + parts[2].toLowerCase();
            } catch(e) {
                console.log(e);
            }
            return str;
        }
    });
})(jQuery);