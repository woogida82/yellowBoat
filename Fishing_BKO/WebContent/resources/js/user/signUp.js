var SignUp = {
    init : function(){
        $("#signUpButton").bind("click", function() { SignUp.doSignUp(); });
        $("#cancelButton").bind("click", function() { SignUp.cancel(); });
    },
    doSignUp : function(){
        $("#signUpForm").onSubmit({
            url            : "/bko/user/insertUser",
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
                        SignUp.cancel();
                    }
                }else {
                    alert("실패하였습니다.");
                    SignUp.cancel();
                }
            }
        });        
    },
    cancel : function(){
        window.location.href = "/bko/login";
    }
}

