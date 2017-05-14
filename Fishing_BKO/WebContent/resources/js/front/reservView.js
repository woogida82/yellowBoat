var ReservView = {
    /* 초기 로딩시 실행 */
    init : function() {
        $("#updateReservDtlButton").bind("click", function() { ReservView.updateReservation(); });
        $("#cancelButton").bind("click", function() { ReservView.cancel(); });
        $("#deleteReservDtlButton").bind("click", function(){ReservView.deleteReservDtl();});
   },
    
    /* 예약수정 */
    updateReservation: function() {
        $("#reservViewForm").onSubmit({
            url            : "/front/updateReservation",
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
                        ReservView.cancel();
                    }
                }else {
                    alert("실패하였습니다.");
                    ReservView.cancel();
                }
            }
        });        
    },
    
    deleteReservDtl : function(){
        $("#reservViewForm").onSubmit({
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
                        alert("예약이 삭제 되었습니다.");
                        ReservView.cancel();
                    }
                }else {
                    alert("실패하였습니다.");
                    ReservView.cancel();
                }
            }
        });         
    },    
    
    /* 취소 */
    cancel : function(){
        var reservViewForm = $("#reservViewForm");
        var url = "/front/reservCalender";
        reservViewForm.prop("action", url);
        reservViewForm.submit();         
    }
};