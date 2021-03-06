var RegReservationView = {
    /* 초기 로딩시 실행 */
    init : function() {
        $("#reservDt").datepicker();
        $("#regReservationButton").bind("click", function() { RegReservationView.regReservation(); });
        $("#cancelButton").bind("click", function() { RegReservationView.cancel(); });
    },
    
    /* 예약등록 */
    regReservation: function() {
        $("#regReservationForm").onSubmit({
            url            : "/bko/reservation/insertReservation",
            confirmMessage : "예약을 등록 하시겠습니까?",
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
                        alert("예약이 등록 되었습니다.");
                        RegReservationView.cancel();
                    }
                }else {
                    alert("실패하였습니다.");
                    RegReservationView.cancel();
                }
            }
        });        
    },
    
    /* 취소 */
    cancel : function(){
        var regReservationForm = $("#regReservationForm");
        var url = "/bko/reservation/reservDetailList";
        regReservationForm.prop("action", url);
        regReservationForm.submit();         
    }
};