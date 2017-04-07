var FileList = {
    popupHtml : null,
    /* 초기 로딩시 실행 */
    init : function() {
        
        FileList.popupHtml = $("#popupArea").html();
        $("#popupArea").remove();
        
        $("#newAdvBtn").click(function(){
            FileList.makePopup();
        });
        
        $("#delBtn").click(function(){
            var idxs = ContentBody.$grid.jqGrid('getGridParam','selarrrow');
            
            if(idxs.length > 0) {
                
                if(confirm("삭제 하시겠습니까?")) {
                    $.ajax({
                        type : "POST",
                        dataType : "json",
                        url : "/bko/file/updateFileStatus",
                        data :{
                            idxs : idxs,
                            status : 'Y'
                        },
                        success : function(data) {
                            
                            if (data.result == 'OK') {
                                ContentBody.$grid.setGridParam({postData : FileList.makeParam()}).trigger('reloadGrid');
                            }
                        }
                    });
                }
                
            }
        });

        /* 그리드 기본 */
        var colModel = [
            {
                label:"IDX",
                name : "idx",
                index : "IDX",
                key : true,
                align : "center",
                hidden : true,
                width : 10
            },
            {
                label:"이벤트ID",
                name : "eventId",
                index : "EVENT_ID",
                align : "center",
                formatter:function(idx, options, rowObject) {
                    link = "<a href=\"javascript:FileList.makePopup('"+rowObject.idx+"')\" style='font-weight:bold;'>" + rowObject.eventId + "</a>";
                    return link;
                },
                width : 80
            },
            {
                label:"파일명",
                name : "orgFileNm",
                index : "ORG_FILE_NM",
                align : "center",
                formatter:function(cellvalue, options, rowObject) {
                    var link = "<a href=\"javascript:$.ajaxFileDownload({downId:'AGV', data:{idx:'" + rowObject.idx + "'}});\" style='font-weight:bold;'>" + rowObject.orgFileNm + "</a>";
                    return link;
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
                width : 110
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
                width : 110
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
            url : "/bko/file/fileList",
            pager: "#"+ContentBody.gridPagerID,
            multiselect: true
        }));
    },
    
    //팝업 영역
    makePopup:function(idx) {
        
        var btns;
        
        if(idx) {
            btns = {저장: 'submit', 취소: 'cancel'};
        } else {
            btns = {저장: 'submit', 취소: 'cancel'};
        }
        
        var popup = {
                
            state0: {
                title: 'DNA PDF 파일 등록',
                html: FileList.popupHtml,
                buttons: btns,
                focus: 1,
                position: { width: 500 },
                submit:function(e,v,m,f){
                    
                    e.preventDefault();
                    
                    if(v=='submit'){

                        var url = "";
                        
                        if(idx) {
                            url = "/bko/file/updateFile";
                        } else {
                            url = "/bko/file/insertFile";
                        }
                        
                        if ($("#uploadFile").val()) {
                            if (!/(\.jpg|\.png|\.raw)$/i.test($("#uploadFile").val().toLowerCase())) {
                                alert("이미지파일만 업로드 할 수 있습니다.");
                                return;
                            }                            
                        }
                        
                        $("#popupForm").onSubmit({
                            url            : url,
                            validation     : true,             // validation 체크 유무
                            validmessage   : 'alertAll',       // 하나의 alert에 모든 에러 내용을 표시
                            confirmMessage : "저장 하시겠습니까?",
                            ajaxSubmit     : true,             // ajax로 통신할 경우
                            success        : function(data){   // ajax인 경우 success callback

                                if (data.result == 'OK') {
                                        
                                    if (idx) {
                                        alert("수정되었습니다.");
                                    }else{
                                        alert("등록되었습니다.");
                                    }
                                    
                                    ContentBody.$grid.setGridParam({postData : FileList.makeParam()}).trigger('reloadGrid');
                                    
                                    $.prompt.close();
                                    
                                }else if (data.result == 'DUP') {
                                    alert("해당 제조번호로 등록된 데이터가 있습니다.\n다른 제조번호를 선택해 주세요.");
                                }else {
                                    $("#orgFileNm").val('');
                                    alert("실패하였습니다.");
                                }
                                
                            }
                        });
                        
                    } else if(v=='delete') {
                        
                        $("#popupForm").onSubmit({
                            url            : "/bko/file/deleteFile",
                            confirmMessage : "삭제 하시겠습니까?",   // 서브밋 전 확인 메세지
                            validation     : false,             // validation 체크 유무
                            validmessage   : 'alertAll',       // 하나의 alert에 모든 에러 내용을 표시
                            ajaxSubmit     : true,             // ajax로 통신할 경우
                            success        : function(data){   // ajax인 경우 success callback
                                if (data.result == 'OK') {
                                    alert("삭제되었습니다");
                                    location.reload();
                                }
                                 else {
                                    alert("실패하였습니다.");
                                }
                            }
                        });
                        
                        $.prompt.close();
                        
                    } else {
                        $.prompt.close();
                        
                    }
                    
                }
            }
        }
        
        var myPrompt = $.prompt(popup, {
            loaded: function(e){
                
                var popupObj = $(this);
                
                if (idx) {
                    
                    $.ajax({
                        type : "POST",
                        dataType : "json",
                        url : "/bko/file/selectFile",
                        data :{
                            idx : idx
                        },
                        success : function(data) {
                            
//                            $("#idxTr").show();
//                            $("#createTr").show();
//                            $("#updateTr").show();
                            
                            if (data.result == 'OK') {
                                $.bindVal(data.fileInfo, 'popupForm');
                                FileList.popupInit(popupObj);
                            }
                        }
                    });
                    
                } else {
                    
                    FileList.popupInit(popupObj);
                }
            }
        });
    },
    
    popupInit : function(popupObj) {
        
        popupObj.find(".checkbox-group, .radio-group").buttonset();
        popupObj.find("select.select").select2({minimumResultsForSearch: -1});
        popupObj.find("select.select-search").select2();
        popupObj.initvalidation();
        
        popupObj.find("#popupForm").slimScroll({height:'200px'});
    },
    
    /* 검색 */
    search : function() {
        if($("#searchWord").val() != "" && $("#searchWordType").val() == ""){
            alert("검색구분을 선택해주세요.");
        }else {
            ContentBody.$grid.setGridParam({postData : FileList.makeParam(), page: 1}).trigger('reloadGrid');
        }
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
    },
    
    fileChoice : function() {
        
        var filePath = $("#uploadFile").val();
        
        if (filePath) {
            var orgFileNm = filePath.replace(/^.*[\\\/]/, '');
            $("#orgFileNm").val(orgFileNm);
        }
    }
    
};