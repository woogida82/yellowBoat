var ShipInfoList = {
    /* 초기 로딩시 실행 */
    init : function() {
    },
    /* 검색 */
    search : function(page) {
        var formShipInfoList = $("#shipInfoListForm");
        var url = "/bko/user";
        formShipInfoList.prop("action", url);
        $("#page").val(page);
        formShipInfoList.submit();
    },
    /* 회원상세 화면으로 이동 */
    detailView : function(userId){
        var formShipInfoList = $("#shipInfoListForm");
        var url = "/bko/user/userDetailView";
        formShipInfoList.prop("action", url);
        $("#userId").val(userId);
        formShipInfoList.submit();
    }
};