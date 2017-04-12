var ContentBody = {
    contentType: null,
    $grid: null,        //그리드  
    gridID: "listGrid",     //그리드 ID   
    $gridPager: null,   //그리드 pager 
    gridPagerID: "listGridPager",  //그리드 ID pager 
    searchBoxHeight: null,

    /*초기 로딩시 실행*/
    init: function() {
        if ($(".bko-bodyContentInnerBox").data("content-type")) {
            this.contentType = $(".bko-bodyContentInnerBox").data("content-type");
        }
        
        /* 타입선택에 따라 로직 변경 */
        this.choiceContentType();

        /*content에 스크롤 여부에 따라 grid width, height resize bind 설정*/
        $(window).bind('resize', function() {
            ContentBody.reSize();
        });
        
        // 검색패널
        if ($(".bko-searchPanel").length > 0) {
            $(".bko-searchPanel").click(function(){
                ContentBody.searchBoxView();
            });
        }
        
        // 새로고침
        if ($(".bko-refresh").length > 0) {
            $(".bko-refresh").click(function(){
                $(this).attr("disabled", true);
                ContentBody.reload();
            });
        }
        
        // 새창열기
        if ($(".bko-newWindow").length > 0) {
            $(".bko-newWindow").click(function(){
                ContentBody.winOpen();
            });
        }
        
        // 검색영역 초기화
        if ($("#reset").length > 0) {
            $("#reset").click(function(){
                // textbox 초기화
                $(".bko-searchBox input[type=text]").val('');
                
                // selectbox 초기화
                $(".bko-searchBox select").each(function(){
                    $(this).find(">option:eq(0)").attr('selected', true).trigger('change');
                });
            });
        }
        
    },
    
    /*content영역에 나오는 타입 선택*/
    choiceContentType: function(){
        var html = "";
        
        //content에 해당 타입 그리기
        if (this.contentType=="grid1" || this.contentType=="grid3") {
            html = "<table id='"+this.gridID+"'></table><div id='"+this.gridPagerID+"'></div>";
        } else if(this.contentType=="grid2") {  
            html = "<table id='"+this.gridID+"'></table>";
        } else {
            html = "";
        }
        
        //content에 해당 타입 붙이기
        $(".bko-bodyContentInnerBox").prepend(html);
        
        if (this.contentType=="grid1" || this.contentType=="grid3") {
            $(".bko-bodyBottom").attr("style","overflow: hidden;");
            this.$grid = $("#" + this.gridID);
            this.$gridPager = $("#" + this.gridPagerID);
        } else if(this.contentType=="grid2") {
            $(".bko-bodyBottom").attr("style","overflow: auto;");
            this.$grid = $("#" + this.gridID);
        }
        
    },
    
    /*window resize*/
    reSize: function(){
        if(this.searchBoxHeight == "0px" || this.searchBoxHeight == "0"){
            $(".bko-searchBox").css("padding-top", "0px");
        }
        

        var headHeight = $(".bko-bodyHeader").height();

        if ($(".bko-funcBtnArea").length > 0) {
            headHeight += $(".bko-funcBtnArea").height();
        }

        if ($(".bko-searchBox:visible").length > 0) {
            headHeight += $(".bko-searchBox").height();
            headHeight += 16;
        } else {
            headHeight += 2;
        }
      
        var bodyHeight = $(window).height() - headHeight;
      
        //타입별 resize
        if (this.contentType == "grid1" || this.contentType == "grid3") {
            $(".bko-bodyContent").css("height", bodyHeight);
            $(".bko-bodyContentInnerBox").css("height", bodyHeight);
            
            var gridHeight = bodyHeight - $(".ui-jqgrid-hdiv").height() - $("#" + this.gridPagerID).height();
            
            if(this.contentType == "grid1") {
                this.$grid.setGridWidth($(window).width(), true);
            }
            
            this.$grid.jqGrid("setGridHeight", gridHeight);

        } else if(this.contentType == "grid2") {
            this.$grid.setGridWidth($(window).width(), true); 
            this.$grid.jqGrid("setGridHeight", bodyHeight - $(".ui-jqgrid-hdiv").height());
        }
        
        //공통
        $(".bko-bodyPanel").css("top","");  //패널이 고정이 되는 거 방지
        $(".bko-bodyPanel").css("left","");  //패널이 고정이 되는 거 방지
        
    },
    
    /*검색 영역*/
    searchBoxView: function() {
        
        $(".bko-searchBox").toggle( "slide",{direction: "up"}, 100, function(){
            ContentBody.reSize();
            
            if($(".bko-searchBox").is(':visible')){ 
                $("#bko-searchPanel").removeClass("bko-searchPanel_hover");
                $("#bko-searchPanel").addClass("bko-searchPanel");
            }else{
                $("#bko-searchPanel").removeClass("bko-searchPanel");
                $("#bko-searchPanel").addClass("bko-searchPanel_hover");
            }   
        });
    },
    
    /*검색 영역 리사이즈*/
    searchBoxResize: function(){
        var size = $(".bko-searchBox").height();
        
        if(!size || size == 0){
            size = "150";
        }
        
        this.searchBoxHeight = size;
        
        $(".bko-searchBox").css("height",size+"px");
        $(".bko-searchInnerBox").css("height",size+"px");
        //$(".bko-divTable").css("height",(parseInt(size)-20)+"px");
        $(".bko-tableBtnArea").css("height",(parseInt(size)-(parseInt(size)/2)-25)+"px");
        $(".bko-tableBtnArea").css("padding-top",(parseInt(size)/2)-25+"px" );
        
        $(".bko-searchBox").show("fast",function(){
            ContentBody.reSize();
        });
    
    },
    
    /*새창띄우기*/
    winOpen: function(){
        var openNewWindow = window.open("about:blank");
        openNewWindow.location.href = location.pathname;
    },
    
    /*새로고침*/
    reload: function(){
        if ($("object", parent.document).length > 0) {
            // 세션체크
            $.checkSession();
        }
        
        window.location.reload(true);
    },
    
    changeSearchArea : function(obj) {
        var searchType = obj.find(">option:selected").data('searchtype');
        
        var searchAreaObj = obj.closest(".search_check").find("#searchArea");
        
        searchAreaObj.find("div[id=searchRangeDiv]").remove();
        
        if (searchType == 'text') {
            
            searchAreaObj.find("input[name=searchWord]").show();
            
        } else if (searchType == 'date') {
            
            searchAreaObj.find("input[name=searchWord]").val('').hide();
            
            var html = '<div id="searchRangeDiv"><input type="text" class="text" id="searchWordS" name="searchWordS" placeholder="시작일" maxlength="10" style="width: 100px;" readonly/>';
            html += ' ~ <input type="text" class="text" id="searchWordE" name="searchWordE" placeholder="종료일" maxlength="10" style="width: 100px;" readonly/></div>';
            searchAreaObj.append(html);
            
            searchAreaObj.find("input[name=searchWordS],input[name=searchWordE]").datepicker();
            
            searchAreaObj.find("input[name=searchWordS]").datepicker("option", "beforeShow", function(input, inst){
                if(searchAreaObj.find("input[name=searchWordE]").val()){
                    var date = new Date(searchAreaObj.find("input[name=searchWordE]").val());
                    $(this).datepicker("option", "maxDate", date);
                }
            });
            
            searchAreaObj.find("input[name=searchWordE]").datepicker("option", "beforeShow", function(input, inst){
                if(searchAreaObj.find("input[name=searchWordS]").val()){
                    var date = new Date(searchAreaObj.find("input[name=searchWordS]").val());
                    $(this).datepicker("option", "minDate", date);
                }
            });
            
        } else if (searchType == 'datetime') {
            
            searchAreaObj.find("input[name=searchWord]").val('').hide();
            
            var html = '<div id="searchRangeDiv"><input type="text" class="text" id="searchWordS" name="searchWordS" placeholder="시작일시" maxlength="10" style="width: 150px;" readonly/>';
            html += ' ~ <input type="text" class="text" id="searchWordE" name="searchWordE" placeholder="종료일시" maxlength="10" style="width: 150px;" readonly/></div>';
            searchAreaObj.append(html);
            
            searchAreaObj.find("input[name=searchWordS],input[name=searchWordE]").datetimepicker();
            
            searchAreaObj.find("input[name=searchWordS]").datetimepicker("option", "beforeShow", function(input, inst){
                if(searchAreaObj.find("input[name=searchWordE]").val()){
                    var date = new Date(searchAreaObj.find("input[name=searchWordE]").val());
                    $(this).datepicker("option", "maxDate", date);
                }
            });
            
            searchAreaObj.find("input[name=searchWordE]").datetimepicker("option", "beforeShow", function(input, inst){
                if(searchAreaObj.find("input[name=searchWordS]").val()){
                    var date = new Date(searchAreaObj.find("input[name=searchWordS]").val());
                    $(this).datepicker("option", "minDate", date);
                }
            });
            
        }
    }
};