//로그인 버튼 클릭 시 로그인 버튼 이미지 토글
function loginBtnImgChg(click) {
	if (click) { //클릭 시 
		$('#loginBtn').attr('src', '/images/login/ms_login_b_push.png?' + new Date().getTime());
		$('#loginBtn').attr('disabled', true);
	} 
	else {//노멀
		$('#loginBtn').attr('src', '/images/login/ms_login_b.png?' + new Date().getTime());
		$('#loginBtn').attr('disabled', false);
	}
}

function login() { 
//	loginBtnImgChg(true);

	var id = $('#userId').val();
	var pw = $('#userPw').val();

	if (!id) {
		alert('아이디를 입력해주세요.');
		$('#userId').focus();
	} else if (!pw) {
		alert('비밀번호를 입력해주세요.');
		$('#userPw').focus();
	} else {

//		if ($('#idSave').is(':checked')) {
//			$.cookie('ims_loginId', id, {
//				expires : 30
//			});
//		} else {
//			$.cookie('ims_loginId', "", {
//				expires : 30
//			});
//		}

		$.ajax({
			url : '/bko/doLogin',
			type : 'POST',
			data : {
			    userId : id,
			    userPw : pw
			},
			dataType : 'json',
			async : false,
			success : function(data, textStatus, xhr) {
				if (data.result == "OK") {

					if (!data.isExists) {
						alert("입력하신 정보를 다시 한번 확인해주세요.");
					} else {
						location.href = "/bko/main";
					}
				} else {
					alert("로그인에 실패 했습니다.");
				}
			}
		});
	}

//	loginBtnImgChg(false);
}

//회원가입 페이지 이동
function signUp(){
    location.href = "/bko/user/singUpView";
}
