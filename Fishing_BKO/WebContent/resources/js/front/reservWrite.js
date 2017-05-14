var ReservWrite = {
    /* 초기 로딩시 실행 */
    init : function() {
        $("#regReservationButton").bind("click", function() { ReservWrite.regReservation(); });
        $("#cancelButton").bind("click", function() { ReservWrite.cancel(); });
    },
    
    /* 예약등록 */
    regReservation: function() {
        $("#reservWriteForm").onSubmit({
            url            : "/front/insertReservation",
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
                        ReservWrite.cancel();
                    }
                }else {
                    alert("실패하였습니다.");
                    ReservWrite.cancel();
                }
            }
        });        
    },
    
    deleteReservDtl : function(){
        $("#reservWriteForm").onSubmit({
            url            : "/front/deleteReservationDetail",
            confirmMessage : "예약을 삭제 하시겠습니까?",
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
                        ReservWrite.cancel();
                    }
                }else {
                    alert("실패하였습니다.");
                    ReservWrite.cancel();
                }
            }
        });         
    },
    
    /* 취소 */
    cancel : function(){
        var reservWriteForm = $("#reservWriteForm");
        var url = "/front/reservCalender";
        reservWriteForm.prop("action", url);
        reservWriteForm.submit();         
    }
};