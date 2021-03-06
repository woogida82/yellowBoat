var UserInfoChangeView = {
    init : function(){
        $("#updateInfoButton").bind("click", function() { UserInfoChangeView.updateUserInfo(); });
        $("#deleteUserButton").bind("click", function() { UserInfoChangeView.deleteUser(); });
        $("#cancelButton").bind("click", function() { UserInfoChangeView.cancel(); });
    },
    updateUserInfo:function(){
        var newUserPw1 = $("#newUserPw1").val();
        var newUserPw2 = $("#newUserPw2").val();
        
        
        if((newUserPw1 == newUserPw2)||(newUserPw1 == "" && newUserPw2 == "")){
            UserInfoChangeView.doUpdateUserInfo();
        }else{
            alert("새 비밀번호가 일치 하지 않습니다.");
            return false;            
        }
    },
    doUpdateUserInfo:function(){
        $("#userDetailForm").onSubmit({
            url            : "/bko/user/updateUser",
            confirmMessage : "개인 정보를 수정하시겠습니까?",
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
                        UserInfoChangeView.cancel();
                    }
                }else if (data.result == 'PW') {
                    alert("기존 비밀번호를 확인하시기 바랍니다.");
                }else if (data.result == 'NPW') {
                    alert("새 비밀번호를 확인하시기 바랍니다.");
                }else{
                    alert("실패하였습니다.");
                    UserInfoChangeView.cancel();
                }
            }
        });         
    },
    deleteUser : function(){
        
        $("#userDetailForm").onSubmit({
            url            : "/bko/user/deleteUser",
            confirmMessage : "탈퇴 하시겠습니까?",
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
                        alert("탈퇴 되었습니다.");
                        UserInfoChangeView.logOut();
                    }
                }else if (data.result == 'PW') {
                    alert("기존 비밀번호를 확인하시기 바랍니다.");
//                    UserInfoChangeView.cancel();
                }else{
                    alert("실패하였습니다.");
                    UserInfoChangeView.cancel();                    
                }
            }
        }); 
    },
    cancel : function(){
//        window.location.href = "/bko/user";
        ContentBody.returnHome();
    },
    logOut : function(){
        ContentBody.logOut();
    },
    getPostNo: function() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipCd').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('addr').value = fullAddr;

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('addrDetail').focus();
            }
        }).open();
    }    
}

