var Main = {
    check : null,
    LoadContentsList: null,
    popupHtml : null,
    passHtml : null,
    
    init: function() {
        
//        Main.popupHtml = $("#popupArea").html();
//        $("#popupArea").remove();
//        Main.passHtml = $("#popupArea2").html();
//        $("#popupArea2").remove();
        
//        var oThis = this;
        /*
         * dropdown : 현재 활성화된 dropdown Menu 확인용
         * divPositionIdx : slide 횟수 확인용
         * LoadContentsList : Load 된 Client Box ID List
         */
//        this.check = {"dropdown" : "", "divPositionIdx" : 0};
//        this.LoadContentsList = new Array();
        
        // 메뉴 구성
        Menu.makeMenu();
                
//        this.setBindClick();
        
//        /*
//         * 화면 초기화
//         */     
//        $("#bko-infoBar").css('width', $(window).width() - 333);
//        $("#bko-infoDisplay").css('width', $(window).width() - 351);
//        $('.bko-bodyspace').css('height' , $(window).height() - 34);
//        $('#bko-CententBox').css('height' , $(window).height() - 34);
//        $('#bko-CententBox').css('width' , $(window).width());    
//        $('#bko-leftMenu').slimScroll({
//            width: '210px',
//            height: 'auto'
//        }); // 좌측 메뉴 스크롤 기능 추가   
                
//        this.showLeftMenu();
        
//        $(window).resize(function() {
//            
//            $("#bko-infoBar").css('width',  $(window).width() - 333);
//            $("#bko-infoDisplay").css('width',  $(window).width() - 351);
//
//            $('.bko-bodyspace').css('height' ,  $(window).height() - 34);
//            $('#bko-CententBox').css('height' ,  $(window).height() - 34);  
//            if ($('#bko-leftMenu').css("display") == "block") { //좌측 메뉴 활성화 체크
//                $('#bko-CententBox').css('width' ,  $(window).width() - $('#bko-leftMenu').width());    
//            } else {
//                $('#bko-CententBox').css('width' , $(window).width());
//            }
//            
//            //좌측 메뉴 스크롤 size 조절
//            $('#bko-leftMenu').slimScroll({
//                width: '210px',
//                height: 'auto'
//            });
//            
//            if ($(".bko-ClientBox").length > 0) { // 활성화 된 컨텐츠 있는지 확인
//                $('.bko-ClientBox').css('height' , $('#bko-CententBox').css('height')); 
//            }
//            // 상단 버튼 갯수 많을 경우 네비게이션 버튼 활성화  
//            var minCount = parseInt($("#bko-infoDisplay").width() / 162); 
//            if (minCount < $(".bko-cCloseBtn").length) {
//                $(".bko-divNaviBtn").css("display", "block");   
//            } else {
//                $(".bko-divNaviBtn").css("display", "none");
//            }
//        });
        
        // 뒤로가기 막기
        (function(storedHash){
            window.location.href += "#";
            setTimeout(function(){
                window.location.href += "1";
            }, 50); 
            
            window.setInterval(function () {
                if (window.location.hash != storedHash) {
                    window.location.hash = storedHash;
                }
            },100);
        })(window.location.hash);
        
    },
    
    setBindClick: function(){
        
        var oThis = this;
        
        $("#bko-sidbarbtn").bind("click", function() { return oThis.showLeftMenu(); });
        $("#bko-slide-pevious").bind("click", function() { return oThis.divSlide("pevious"); });
        $("#bko-slide-next").bind("click", function() { return oThis.divSlide("next"); });
        $("#bko-LoginManu > input").bind("click", function() { return oThis.dropdownMenu('bko-LoginManu','dropdown-1'); });
        
        // dropdown menu 다른영역 클릭시 자동 닫기
        $("*").mouseup( function (e) {
            if (oThis.check['dropdown'] != "") {
                var dropmenu = $(oThis.check['dropdown']);
                if (dropmenu.has(e.target).length == 0) {
                    $(oThis.check['dropdown']).hide("slide", { direction: "up" }, 100, function() {
                        $(oThis.check['dropdown']).css("display", "none");
                        oThis.check['dropdown'] = "";
                    });     
                }
            }
        });
    },
    /*
     * 컨텐츠 Loding List 작성 (실행된 Contents ID 목록 만들기)
     */
    makeContentsList: function() {
        
        var oThis = this;
        
        oThis.LoadContentsList = [];
        
            if ($(".bko-cCloseBtn").length > 0) {
                
                $(".bko-ClientBox:not(object)").each(function(i) {

                    var selectID = $(this).attr("id");

                    oThis.LoadContentsList.push([selectID]);

                });
                
            }

    },      
    /*
     * 활성화된 Contents Box top button으로 슬라이딩 처리
     * winID : Contents Box ID
     */
    activeTopButton: function(winID) {
            
            var othis = this;
        
            var minCount = parseInt($("#bko-infoDisplay").width() / 162);  // 기본 상태에서 button 몇개 까지 표시 가능한지 계산 
            var SelectBtnIdx = -1;
            // button이 몇번째에 있는지 위치 확인 (inArray 사용 할 수 없어 수동 처리)
            for (var i=0; i < this.LoadContentsList.length; i++ ) {
                if (this.LoadContentsList[i] == winID) {
                    SelectBtnIdx = i;
                    break;
                }
            }

            SelectBtnIdx += 1;
            
            var goIdx = 0;
            var moveIdx = "";
            
            if (SelectBtnIdx <= minCount) { // 기본상태 보다 작으면 처음으로 slide 처리
                
                setTimeout(function(){
                    moveIdx = "0px";
                    $("#bko-btnList").animate({left: goIdx} , 50, function() {
                        othis.check['divPositionIdx'] = 0;  
                    });
                }, 50);
                
            } else {
                
                goIdx = SelectBtnIdx - minCount;
                
                if ((goIdx - this.check['divPositionIdx']) > 0) { // 현재 화면에서 우측으로 이동 필요
                    
                    setTimeout(function(){
                        moveIdx = goIdx * 162;
                        moveIdx = "-"+moveIdx.toString()+"px";
                        $("#bko-btnList").css("left", "0px");
                        $("#bko-btnList").animate({left: moveIdx} , 50, function(){
                            othis.check['divPositionIdx'] += goIdx; // 현재 포지션에서 우측 이동 횟수 만큼 더하기                     
                        });
                    }, 50);
                    
                } else if ((goIdx - this.check['divPositionIdx']) < 0) { // 현재 화면에서 좌측으로 이동 필요
                    
                    setTimeout(function(){
                        //moveIdx = othis.check['divPositionIdx'] - goIdx;
                        //moveIdx = moveIdx * 162;
                        moveIdx = goIdx * 162;
                        moveIdx = "-"+moveIdx.toString()+"px";
                        $("#bko-btnList").css("left", "0px");
                        $("#bko-btnList").animate({left: moveIdx} , 50, function() {
                            othis.check['divPositionIdx'] = goIdx;  // 현재 포지션에서 계산된 횟수로 지정          
                        });     
                    }, 50);
                    
                }
            }   
            
    },      
    /*
     * 메뉴 실행 function
     * winID : Contents Box ID, CURL : Contents URL, MenuName: 메뉴명
     */
    openClient: function(winID, CURL, MenuName) {
        
        // 세션체크
        $.checkSession();
        
        var objName = "";
        var btnName = "";
        var AppendHtml = "";        
        
        if (winID == "") return false;
        if (CURL == "") return false;
        if (MenuName == "") return false;
        
        if ($(".bko-ClientBox").length > 0) {
            $(".bko-ClientBox").css("z-indx", "10");
            $(".bko-ClientBox").css("visibility", "hidden");
        }

        if ($("#"+winID).length > 0) {
                
                objName = "#obj"+winID;
                btnName = "#btn"+ winID;
                $("#"+winID).css("z-indx","20");
                $("#"+winID).css("visibility","visible");
                $(objName).css("visibility","visible");  // visibility : visible/hidden
                
                $(objName).css("height",  $('#bko-CententBox').css('height'));  
                
                if ($(".bko-infoBtnA").length > 0) { // 활성화된 버튼 모두 비활성화
                    $(".bko-infoBtnA").removeClass('bko-infoBtnA').addClass('bko-infoBtn');
                }
                $(btnName).removeClass('bko-infoBtn').addClass('bko-infoBtnA');
                
                this.activeTopButton(winID);
            
        } else {
            
            AppendHtml = "<div class='bko-ClientBox' id='"+winID+"'><object class='bko-ClientBox' id='obj"+winID+"' type='text/html'></object></div>";              
            $('#bko-CententBox').prepend(AppendHtml);
            objName = "#obj"+winID;
            $(objName).css("height",  $('#bko-CententBox').css('height'));      
                        
            document.getElementById("obj"+winID).data = CURL;
            $("#"+winID).css("z-indx","20");
            $("#"+winID).css("visibility","visible");
            $(objName).css("visibility ","visible");  // visibility : visible/hidden
            //$(objName).load(CURL);
            $(objName).focus();
            
            if ($(".bko-infoBtnA").length > 0) { // 활성화된 버튼 모두 비활성화
                $(".bko-infoBtnA").removeClass('bko-infoBtnA').addClass('bko-infoBtn');
            }
            AppendHtml = "<div class='bko-infoBtnA' id='btn"+winID+"' title='"+MenuName+"'><dd><dl class='bko-infoBtn-left'></dl><dl class='bko-infoBtn-center1' onClick=\"Main.cententActive('"+winID+"');\">";
            AppendHtml += MenuName;
            AppendHtml += "</dl><dl class='bko-infoBtn-center2'>";
            AppendHtml += "<input type='image' src='/images/layout/closef.png' onClick=\"Main.cententClose('"+winID+"');\" class='bko-cCloseBtn' title='닫기' />";
            AppendHtml += "</dl><dl class='bko-infoBtn-right'></dl></dd></div>";
            $("#bko-btnList ").css("width", 165 * ($(".bko-cCloseBtn").length + 1));
            $('#bko-btnList').prepend(AppendHtml);
            
            // 상단 버튼 갯수 많을 경우 네비게이션 버튼 활성화
            var minCount = parseInt($("#bko-infoDisplay").width() / 162); 
            if (minCount < $(".bko-cCloseBtn").length) {
                $(".bko-divNaviBtn").css("display", "block");           
            } else {
                $(".bko-divNaviBtn").css("display", "none");
            }
            
            this.makeContentsList(); //  making Client Box List(onLoad)
            
            $("#bko-btnList").animate({left: "0px"} , "fast"); // 첫 Slide 화면으로 이동 
            this.check['divPositionIdx'] = 0;               
        }       
        
        if (screen.width < 1300) {
            $(".bko-ClientBox > object").css("overflow", "scroll");
        }
    },  
    /*
     * Left slide Menu Action (좌측 슬라이드 메뉴 활성화/비활성화)
     */
    showLeftMenu: function() {
        
            if ($('#bko-leftMenu').css("display") == "none") {
                
                $('#bko-leftMenu').show("slide", { direction: "left" }, 100, function(){
                }); //좌측 메뉴 활성화
                $("#bko-CententBox").animate({ 
                    "left": "211px",
                    "width": "-=210px"
                    }, 10, function(){
                        $("#bko-sidbarbtn").attr('id','bko-sidbarbtnA');
                    }); // 컨텐츠 영역 크기 변경
                
                
                
            } else if ($('#bko-leftMenu').css("display") == "block") {
                
                $('#bko-leftMenu').hide("slide", { direction: "left" }, 100, function(){

                        }); // 컨텐츠 영역 크기 변경
                $('#bko-leftMenu').css("display", "none");
                $("#bko-CententBox").animate({ 
                    "left": "1px",
                    "width": "+=210px"
                    }, 10, function(){
                        $("#bko-sidbarbtnA").attr('id','bko-sidbarbtn');
                }); // 좌측 메뉴 넣기
                
            } // ($('#bko-leftMenu').css("display") == "none")
            
    },
    /*
     * 상단 메뉴 버튼을 이용하여 컨텐츠 활성화
     * winID : Contents Box ID
     */
    cententActive: function(winID) {
        
        if ($("#"+winID).length > 0) {
            
            $(".bko-ClientBox").css("z-index", "10"); //모든 활성화창 hidden 처리 // visibility : visible/hidden
            $(".bko-ClientBox").css("visibility", "hidden"); //모든 활성화창 hidden 처리 // visibility : visible/hidden
            $("#"+winID).css("z-index","20"); // 해당 컨텐츠창만 show
            $("#"+winID).css("visibility","visible"); // 해당 컨텐츠창만 show
            $("#obj"+winID).css("visibility","visible"); // 해당 컨텐츠창 object도 show
            $("#obj"+winID).css("height",  $('#bko-CententBox').css('height')); 
            
            if ($(".bko-infoBtnA").length > 0) { // 활성화된 버튼 모두 비활성화
                $(".bko-infoBtnA").removeClass('bko-infoBtnA').addClass('bko-infoBtn');
            }
            $("#btn"+ winID).removeClass('bko-infoBtn').addClass('bko-infoBtnA');       
        }
            
    },
    /*
     * 상단 메뉴 버튼을 이용하여 컨텐츠 창 닫기
     * winID : Contents Box ID
     */
    cententClose: function(winID) {
        
        $("#btn"+ winID).remove();
        $("#"+ winID).remove();
        
        if ($(".bko-ClientBox").length > 0) {
            
            $(".bko-ClientBox").css("z-index", "10"); //모든 활성화창 hidden 처리   // visibility : visible/hidden
            $(".bko-ClientBox").css("visibility", "hidden"); //모든 활성화창 hidden 처리   // visibility : visible/hidden
            $(".bko-ClientBox:first").css("z-index","20"); // 첫번째 컨텐츠창만 show
            $(".bko-ClientBox:first").css("visibility","visible"); // 첫번째 컨텐츠창만 show
            
            var tmpId = $(".bko-ClientBox:first").attr('id');
//          $("#obj"+ tmpId).css("display", "block");
            $("#obj"+ tmpId).css("visibility", "visible");
            
            if ($(".bko-infoBtnA").length > 0) { // 활성화된 버튼 모두 비활성화
                $(".bko-infoBtnA").removeClass('bko-infoBtnA').addClass('bko-infoBtn');
            }               
            $("#btn"+ tmpId).removeClass('bko-infoBtn').addClass('bko-infoBtnA');
            $("#bko-btnList ").css("width", 165 * ($(".bko-cCloseBtn").length + 1));    
            
            // 상단 버튼 갯수 많을 경우 네비게이션 버튼 활성화  
            var minCount = parseInt($("#bko-infoDisplay").width() / 162); 
            if (minCount < $(".bko-cCloseBtn").length) {                
                $(".bko-divNaviBtn").css("display", "block"); // div slider Navi 버튼 활성화                 
            } else {
                $(".bko-divNaviBtn").css("display", "none"); // div slider Navi 버튼 비활성화
            }
            $("#bko-btnList").animate({left: 0} , "fast"); 
            this.check['divPositionIdx'] = 0;
            
            this.makeContentsList();
            
        }
            
    },
    /*
     * 상단 버튼 List 이동 
     * btnAction : Next => 오른쪽으로 이동,  pevious => 왼쪽으로 이동
     */
    divSlide: function(btnAction) {
        
        if (btnAction == "next") {
            var maxCount = $(".bko-cCloseBtn").length - parseInt($("#bko-infoDisplay").width() / 162);
            if (maxCount > this.check['divPositionIdx'] ) {
                $("#bko-btnList").animate({left:"-=162px"} , "fast");
                this.check['divPositionIdx']++;
            }
        } else if (btnAction == "pevious") {
            if (this.check['divPositionIdx'] != 0) {
                $("#bko-btnList").animate({left:"+=162px"} , "fast");
                this.check['divPositionIdx']--;
            }
        }       
            
    },
    /*
     * DropDown Menu 활성화 
     * pId : ID, MenuID : 메뉴 ID
     */
    dropdownMenu: function(pId, MenuId) {
        
        var oThis = this;
        
        pId = "#"+pId;
        MenuId = "#"+MenuId;
        
        var p = $(pId).position(); 
        
        if (!((oThis.check['dropdown'] == MenuId) || (oThis.check['dropdown'] == ""))) {
            
            $(oThis.check['dropdown']).hide("slide", { direction: "up" }, 100, function() {
                $(oThis.check['dropdown']).css("display", "none");
                oThis.check['dropdown'] = "";
            });     
            
        }
        if ($(MenuId).css("display") == "none" ) {
            $(MenuId).css({
                "position": "absolute",
                "top": p.top + 30,
                "left": p.left - $(MenuId).width() + 20
            });
            $(MenuId).show("slide", { direction: "up" }, 100 , function() {
                $(MenuId).css("display", "block");
                oThis.check['dropdown'] = MenuId;
            });
        } else {
            $(MenuId).hide("slide", { direction: "up" }, 100, function() {
                $(MenuId).css("display", "none");
                oThis.check['dropdown'] = "";
            });
        }
            
    },
    changeInfo: function() {
        
        var oThis = this;
        
        var popup = {
            state0: {
                title: '정보수정',
                html: Main.popupHtml,
                buttons: {완료: 'submit', 취소: 'cancel'},
                focus: 1,
                position: { width: 300 },
                submit:function(e,v,m,f){
                    
                    e.preventDefault();
                    
                    if(v=='submit'){
                        
                        $("#popupForm").onSubmit({
                            url            : "/bko/updateInfo",
//                                confirmMessage : "등록하시겠습니까?",   // 서브밋 전 확인 메세지
                            validation     : true,             // validation 체크 유무
                            validmessage   : 'alertAll',       // 하나의 alert에 모든 에러 내용을 표시
                            ajaxSubmit     : true,             // ajax로 통신할 경우
                            success        : function(data){   // ajax인 경우 success callback
                                if (data.result == 'OK') {
                                    alert("수정되었습니다.");
                                    location.reload();
                                }else {
                                    alert("실패하였습니다.");
                                }
                                $.prompt.close();
                            }
                        });
                    } else {
                        $.prompt.close();
                    }
                }
            }
        };
        
        var myPrompt = $.prompt(popup, {
            loaded: function(e){
                
                var popupObj = $(this);
                
                $.ajax({
                    type : "POST",
                    dataType : "json",
                    url : "/bko/selectInfo",
                    success : function(data) {
                        if (data.result == 'OK') {
                            $.bindVal(data.selectInfo, 'popupForm');
                            
                            popupObj.find(".checkbox-group, .radio-group").buttonset();
                            popupObj.find("select.select").select2({minimumResultsForSearch: -1});
                            popupObj.find("select.select-search").select2();
                            popupObj.initvalidation();
                        }
                    }
                });
            }
        });
        
        $(oThis.check['dropdown']).hide("slide", { direction: "up" }, 100, function() {
                $(oThis.check['dropdown']).css("display", "none");
                oThis.check['dropdown'] = "";
        });
    },
    
    
    changePW: function() {
        
        var oThis = this;
        
        var popup = {
            state0: {
                title: '비밀번호변경',
                html: Main.passHtml,
                buttons: {완료: 'submit', 취소: 'cancel'},
                focus: 1,
                position: { width: 300 },
                submit:function(e,v,m,f){
                    
                    e.preventDefault();
                    
                    if(v=='submit'){
                        
                        if($("#newPw").val() != $("#newPw2").val()) {
                            alert("새비밀번호가 동일하지 않습니다.");
                            return;
                        }
                        
                        $("#passForm").onSubmit({
                            url            : "/bko/updatePass",
                            validation     : true,             // validation 체크 유무
                            validmessage   : 'alertAll',       // 하나의 alert에 모든 에러 내용을 표시
                            ajaxSubmit     : true,             // ajax로 통신할 경우
                            success        : function(data){   // ajax인 경우 success callback
                                if (data.result == 'OK') {
                                    
                                    if(data.errList) {
                                        var errMsg = '';
                                        
                                        for (var i = 0; i < data.errList.length; i++) {
                                            
                                            if (i > 0) {
                                                errMsg += "\n";
                                            }
                                            errMsg += data.errList[i];
                                        }
                                        
                                        alert(errMsg);
                                        
                                    } else {
                                        alert("수정되었습니다.");
                                        $.prompt.close();
                                    }
                                }else {
                                    alert("실패하였습니다.");
                                }
                            }
                        });
                    } else {
                        $.prompt.close();
                    }
                }
            }
        };
        
        var myPrompt = $.prompt(popup, {
            loaded: function(e){
                $(this).initvalidation();
            }
        });
        
        $(oThis.check['dropdown']).hide("slide", { direction: "up" }, 100, function() {
                $(oThis.check['dropdown']).css("display", "none");
                oThis.check['dropdown'] = "";
        });
    }
};