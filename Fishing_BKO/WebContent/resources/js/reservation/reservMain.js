var ShipInfoList = {
    /* 초기 로딩시 실행 */
    init : function() {
        $("#regShipInfoButton").bind("click", function() { ShipInfoList.regShipInfoView(); });        
    },
    /* 검색 */
    search : function(page) {
        var formShipInfoList = $("#shipInfoListForm");
        var url = "/bko/shipInfo";
        formShipInfoList.prop("action", url);
        $("#page").val(page);
        formShipInfoList.submit();
    },
    /* 선박 정보상세 화면으로 이동 */
    detailView : function(shipId){
        var formShipInfoList = $("#shipInfoListForm");
        var url = "/bko/shipInfo/shipInfoDetailView";
        formShipInfoList.prop("action", url);
        $("#shipId").val(shipId);
        formShipInfoList.submit();
    },
    /* 회원정보 상세 */
    userInfoView : function(userId){
        var formUserList = $("#shipInfoListForm");
        var url = "/bko/user/userDetailView";
        formUserList.prop("action", url);
        $("#userId").val(userId);
        formUserList.submit();
    },
    /* 선박등록 화면 이동 */
    regShipInfoView : function(){
        var formUserList = $("#shipInfoListForm");
        var url = "/bko/shipInfo/regShipInfoView";
        formUserList.prop("action", url);
        formUserList.submit();        
    }
};