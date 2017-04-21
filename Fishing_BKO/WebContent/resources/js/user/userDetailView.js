var UserDetailView = {
    init : function(){
        $("#updateInfoButton").bind("click", function() { UserDetailView.updateUserInfo(); });
        $("#deleteUserButton").bind("click", function() { UserDetailView.deleteUser(); });
        $("#cancelButton").bind("click", function() { UserDetailView.cancel(); });
    },
    updateUserInfo:function(){
        var shipId = $("#shipId");
        var shipNm = $("#shipNm");
        
        if(shipId.val() != ""){
            if(shipNm.val() == ""){
                alert("선박 이름을 기입해 주시기 바랍니다. ");
                shipNm.focus();
                return false;
            }
        }
        
        $("#userDetailForm").onSubmit({
            url            : "/bko/user/updateUser",
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
                        UserDetailView.cancel();
                    }
                }else {
                    alert("실패하였습니다.");
                    UserDetailView.cancel();
                }
            }
        });          
    },
    deleteUser : function(){
        $("#userDetailForm").onSubmit({
            url            : "/bko/user/deleteUser",
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
                        UserDetailView.cancel();
                    }
                }else {
                    alert("실패하였습니다.");
                    UserDetailView.cancel();
                }
            }
        });          
    },
    cancel : function(){
        window.location.href = "/bko/user";
    }
}

