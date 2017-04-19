var UserList = {
    /* 초기 로딩시 실행 */
    init : function() {
        /* 그리드 기본 */
        var colModel = [
            {
                label:"사용자ID",
                name : "userId",
                index : "USER_ID",
                key : true,
                align : "center",
                hidden : true,
                width : 10
            },
            {
                label:"아이디",
                name : "userId",
                index : "USER_ID",
                align : "center",
                formatter:function(idx, options, rowObject) {
                    link = "<a href=\"javascript:UserList.makePopup('"+rowObject.userId+"')\" style='font-weight:bold;'>" + rowObject.userId + "</a>";
                    return link;
                },
                width : 180
            },
            {
                label:"이름",
                name : "userNm",
                index : "USER_NM",
                align : "center",
                formatter:function(cellvalue, options, rowObject) {
                    return rowObject.userNm;
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
            
//        ContentBody.$grid.jqGrid('navGrid', "#"+ContentBody.gridPagerID, {
//            search:false, edit:false, add:false, del:false, refresh:false
//        });
    }
};