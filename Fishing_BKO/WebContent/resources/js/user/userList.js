var UserList = {
    /* 초기 로딩시 실행 */
    init : function() {
    },
    /* 검색 */
    search : function(page) {
        var formUserList = $("#userListForm");
        var url = "/bko/user";
        formUserList.prop("action", url);
        $("#page").val(page);
        formUserList.submit();
    },
    /* 회원상세 화면으로 이동 */
    detailView : function(userId){
        var formUserList = $("#userListForm");
        var url = "/bko/user/userDetailView";
        formUserList.prop("action", url);
        $("#userId").val(userId);
        formUserList.submit();
    }
};