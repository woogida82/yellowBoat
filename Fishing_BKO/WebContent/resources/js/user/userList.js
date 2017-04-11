var UserList = {
    popupHtml : null,
    /* 초기 로딩시 실행 */
    init : function() {
        
    	UserList.popupHtml = $("#popupArea").html();
        $("#popupArea").remove();
        
        $("#newAdvBtn").click(function(){
        	UserList.makePopup();
        });
        
        $("#delBtn").click(function(){
            var userIds = ContentBody.$grid.jqGrid('getGridParam','selarrrow');
            
            if(userIds.length > 0) {
                
                if(confirm("삭제 하시겠습니까?")) {
                    $.ajax({
                        type : "POST",
                        dataType : "json",
                        url : "/bko/user/updateUserStatus",
                        data :{
                        	userIds : userIds,
                            status : 'Y'
                        },
                        success : function(data) {
                            
                            if (data.result == 'OK') {
                                ContentBody.$grid.setGridParam({postData : UserList.makeParam()}).trigger('reloadGrid');
                            }
                        }
                    });
                }
                
            }
        });

        /* 그리드 기본 */
        var colModel = [
            {
                label:"사용자ID",
                name : "adminId",
                index : "ADMIN_ID",
                key : true,
                align : "center",
                hidden : true,
                width : 10
            },
            {
                label:"아이디",
                name : "adminId",
                index : "ADMIN_ID",
                align : "center",
                formatter:function(idx, options, rowObject) {
                    link = "<a href=\"javascript:UserList.makePopup('"+rowObject.adminId+"')\" style='font-weight:bold;'>" + rowObject.adminId + "</a>";
                    return link;
                },
                width : 180
            },
            {
                label:"이름",
                name : "adminNm",
                index : "ADMIN_NM",
                align : "center",
                formatter:function(cellvalue, options, rowObject) {
                    return rowObject.adminNm;
                },
                width : 180
            },
            {
                label:"권한",
                name : "menuAuth",
                index : "MENU_AUTH",
                align : "center",
                formatter:function(cellvalue, options, rowObject) {
                    return rowObject.menuAuth;
                },
                width : 180
            },
            
            {
                label:"수정일시",
                name : "updateTime",
                index : "UPDATE_TIME",
                formatter:'date',
                formatoptions:{ srcformat: 'Y.m.d H:i:s', newformat: 'Y.m.d H:i:s'},
                align : "center",
                width : 150
            },
            {
                label:"수정자",
                name : "updateId",
                index : "UPDATE_ID",
                align : "center",
                width : 100
            },
            {
                label:"등록일시",
                name : "createTime",
                index : "CREATE_TIME",
                formatter:'date',
                formatoptions:{ srcformat: 'Y.m.d H:i:s', newformat: 'Y.m.d H:i:s'},
                align : "center",
                width : 150
            },
            {
                label:"등록자",
                name : "createId",
                index : "CREATE_ID",
                align : "center",
                width : 100
            }
        ];

        ContentBody.$grid.jqGrid($.extend(true, {}, $jqgridOpt, {
            colModel : colModel,
            url : "/bko/user/userList",
            pager: "#"+ContentBody.gridPagerID,
            multiselect: true
        }));
            
        ContentBody.$grid.jqGrid('navGrid', "#"+ContentBody.gridPagerID, {
            search:false, edit:false, add:false, del:false, refresh:false
        }).navButtonAdd("#"+ContentBody.gridPagerID, {
            caption: 'Excel 다운로드', 
            buttonicon: 'ui-icon-disk', 
            position: 'last',
            onClickButton: function(){
                $.excelDownload({
                    url : '/bko/user/userExcelDown',
                    data : UserList.makeParam()
                });
            }
        });
    },
    
    //팝업 영역
    makePopup:function(adminId) {
        
        var btns;
        
        if(adminId) {
            //btns = {삭제 : 'delete', 저장: 'submit', 취소: 'cancel'};
            btns = {저장: 'submit', 취소: 'cancel'};
        } else {
            btns = {저장: 'submit', 취소: 'cancel'};
        }
        
        var popup = {
                
            state0: {
                title: '사용자 등록/수정',
                html: UserList.popupHtml,
                buttons: btns,
                focus: 1,
                position: { width: 500 },
                submit:function(e,v,m,f){
                    
                    e.preventDefault();
                    
                    if(v=='submit'){

                        var url = "";
                        
                        if(adminId) {
                            url = "/bko/user/updateUser";
                        } else {
                            url = "/bko/user/insertUser";
                        }
                        
                        $("#popupForm").onSubmit({
                            url            : url,
                            validation     : true,             // validation 체크 유무
                            validmessage   : 'alertAll',       // 하나의 alert에 모든 에러 내용을 표시
                            confirmMessage : "저장 하시겠습니까?",
                            ajaxSubmit     : true,             // ajax로 통신할 경우
                            success        : function(data){   // ajax인 경우 success callback

                                if (data.result == 'OK') {
                                        
                                    if(adminId) {
                                        alert("수정되었습니다.");
                                        
                                    }else{
                                        alert("등록되었습니다.");
                                        
                                    }
                                    
                                    ContentBody.$grid.setGridParam({postData : UserList.makeParam()}).trigger('reloadGrid');
                                    $.prompt.close();
                                } else if (data.result == 'DUP') {
                                    alert("사용중인 아이디입니다.");
                                } else {
                                    alert("실패하였습니다.");
                                }
                                
                            }
                        });
                        
                    } else {
                        $.prompt.close();
                        
                    }
                    
                }
            }
        }
        
        var myPrompt = $.prompt(popup, {
            loaded: function(e){
                
                var popupObj = $(this);
                
                if (adminId) {
                    
                    popupObj.find("#adminId").attr("readonly", true);
                    
                    $.ajax({
                        type : "POST",
                        dataType : "json",
                        url : "/bko/user/selectUser",
                        data :{
                        	adminId : adminId
                        },
                        success : function(data) {
                            
                            if (data.result == 'OK') {
                                $.bindVal(data.planDrugInfo, 'popupForm');
                                UserList.popupInit(popupObj);
                            }
                        }
                    });
                    
                } else {
                    
                    $("#initPassDiv").hide();
                    
                	UserList.popupInit(popupObj);
                }
            }
        });
    },
    
    popupInit : function(popupObj) {
        
        popupObj.find(".checkbox-group, .radio-group").buttonset();
        popupObj.find("select.select").select2({minimumResultsForSearch: -1});
        popupObj.find("select.select-search").select2();
        popupObj.initvalidation();
        
        popupObj.find("#popupForm").slimScroll({height:'300px'});
    },
    
    /* 검색 */
    search : function() {
        if($("#searchWord").val() != "" && $("#searchWordType").val() == ""){
            alert("검색구분을 선택해주세요.");
        }else {
            ContentBody.$grid.setGridParam({postData : UserList.makeParam(), page: 1}).trigger('reloadGrid');
        }
    },
    
    initPass : function() {
        
        if (!confirm('패스워드를 초기화 하시겠습니까?')) {
            return;
        }
        
        $.ajax({
            type : "POST",
            dataType : "json",
            url : "/bko/user/updateInitPass",
            data :{
                adminId : $("#popupForm").find("#adminId").val()
            },
            success : function(data) {
                
                if (data.result == 'OK') {
                    alert("패스워드를 초기화 했습니다.");
                } else {
                    alert("실패하였습니다.");
                }
            }
        });
    },
    
    makeParam: function() {
        var searchWord = '';
        var searchWordS = '';
        var searchWordE = '';
        
        if ($("#searchWord")) {
            searchWord = $("#searchWord").val();
        }
        
        if ($("#searchWordS")) {
            searchWordS = $("#searchWordS").val();
        }
        
        if ($("#searchWordE")) {
            searchWordE = $("#searchWordE").val();
        }
        
        return {
            searchWordType : $("#searchWordType").val(),
            searchWord : searchWord,
            searchWordS : searchWordS,
            searchWordE : searchWordE,
        };
    }
};