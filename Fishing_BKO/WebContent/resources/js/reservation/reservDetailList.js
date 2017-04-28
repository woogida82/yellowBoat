var ReservDetailList = {
    /* 초기 로딩시 실행 */
    init : function() {
    },
    
    /* 예약 상세 */
    reservDetailView : function(reservDtlId){
        var reservDetailListForm = $("#reservDetailListForm");
        var url = "/bko/reservation/reservationDetailView";
        reservDetailListForm.prop("action", url);
        $("#reservDtlId").val(reservDtlId);
        reservDetailListForm.submit();          
    },
    
    /* 예약 상태 변경 */
    reservDtlStatus : function(reservDtlId, statusCd){
        $("#reservDtlId").val(reservDtlId);
        $("#statusCd").val(statusCd);
        
        var message = null;
        if(statusCd == '01'){
            message = "입금완료"; 
        }else{
            message = "입금취소";
        }
        
        $("#reservDetailListForm").onSubmit({
            url            : "/bko/reservation/reservDtlStatus",
            confirmMessage : message + " 하시겠습니까?",
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
                        alert(message + " 되었습니다.");
                        RegShipInfoView.cancel();
                    }
                }else {
                    alert("실패하였습니다.");
                    RegShipInfoView.cancel();
                }
            }
        });        
    },
    
    /* 예약등록 */
    regReservation : function(){
        var reservDetailListForm = $("#reservDetailListForm");
        var url = "/bko/reservation/regReservationView";
        reservDetailListForm.prop("action", url);
        reservDetailListForm.submit();           
    },  
    
    /* 검색 */
    search : function(page) {
        var reservDetailListForm = $("#reservDetailListForm");
        var url = "/bko/reservation/reservDetailList";
        reservDetailListForm.prop("action", url);
        $("#page").val(page);
        reservDetailListForm.submit();
    }    
};