var ReservationDetailView = {
    /* 초기 로딩시 실행 */
    init : function() {
        $("#reservDt").datepicker();
        $("#updateReservDtlButton").bind("click", function() { ReservationDetailView.updateReservation(); });
        $("#cancelButton").bind("click", function() { ReservationDetailView.cancel(); });
    },
    
    /* 예약수정 */
    updateReservation: function() {
        $("#reservationDetailForm").onSubmit({
            url            : "/bko/reservation/updateReservation",
            confirmMessage : "예약수정을 등록 하시겠습니까?",
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
                        alert("예약이 수정 되었습니다.");
                        ReservationDetailView.cancel();
                    }
                }else {
                    alert("실패하였습니다.");
                    ReservationDetailView.cancel();
                }
            }
        });        
    },
    
    /* 취소 */
    cancel : function(){
        var reservationDetailForm = $("#reservationDetailForm");
        var url = "/bko/reservation/reservDetailList";
        reservationDetailForm.prop("action", url);
        reservationDetailForm.submit();         
    }
};