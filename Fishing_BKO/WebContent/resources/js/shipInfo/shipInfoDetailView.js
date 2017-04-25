var ShipInfoDetailView = {
    init : function(){
        $("#updateInfoButton").bind("click", function() { ShipInfoDetailView.updateShipInfo(); });
        $("#deleteShipButton").bind("click", function() { ShipInfoDetailView.deleteShip(); });
        $("#cancelButton").bind("click", function() { ShipInfoDetailView.cancel(); });
    },
    updateShipInfo:function(){
        $("#shipInfoDetailForm").onSubmit({
            url            : "/bko/shipInfo/updateShipInfo",
            confirmMessage : "수정하시겠습니까?",
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
                        alert("수정되었습니다.");
                        ShipInfoDetailView.cancel();
                    }
                }else {
                    alert("실패하였습니다.");
                    ShipInfoDetailView.cancel();
                }
            }
        });          
    },
    deleteShip : function(){
        $("#userDetailForm").onSubmit({
            url            : "/bko/shipInfo/deleteShipInfo",
            confirmMessage : "삭제하시겠습니까?",
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
                        alert("삭제되었습니다.");
                        ShipInfoDetailView.cancel();
                    }
                }else {
                    alert("실패하였습니다.");
                    ShipInfoDetailView.cancel();
                }
            }
        });          
    },
    cancel : function(){
        window.location.href = "/bko/shipInfo";
    }    
}

