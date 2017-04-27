var ReservMain = {
    /* 초기 로딩시 실행 */
    init : function() {
    },
    
    /* 예약리스트 */
    reservList : function(shipId){
        var reservMainForm = $("#reservMainForm");
        var url = "/bko/reservation/reservList";
        reservMainForm.prop("action", url);
        $("#shipId").val(shipId);
        reservMainForm.submit();        
    },
    
    /* 예약등록 */
    regReservation : function(shipId){
        var reservMainForm = $("#reservMainForm");
        var url = "/bko/reservation/regReservationView";
        reservMainForm.prop("action", url);
        $("#shipId").val(shipId);
        reservMainForm.submit();        
    }
};