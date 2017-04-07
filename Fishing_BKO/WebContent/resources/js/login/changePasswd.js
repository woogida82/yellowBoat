/**
 * 암호변경 POPup : ChangePasswd.js
 */
var changePasswd = {
		debug: null,  // debug 모드용
		
		init: function() {
			
			var oThis = this;
			
			this.debug = true;

			if (oThis.debug) console.log("**************  TnsFormPOP Init begin ************************");
			
			this.SetBindClick();

			
			if (oThis.debug) console.log("**************  TnsFormPOP Init end   ************************");		
			
//			TNSPopupLibChild.Init();
		},
		SetBindClick: function(){
			var oThis = this;
			
			$("#btnCancel").bind("click", function() { oThis.Close(); });
			$("#btnSave").bind("click", function() { oThis.RegData(); });
			$("#btnClose").bind("click", function() { oThis.Close(); });
		},
		RegData: function(){
			var oThis = this;
			var oldPswd = $("#oldPswd").val();
			var newPswd1 = $("#newPswd1").val();
			var newPswd2 = $("#newPswd2").val();

			if(oldPswd.Trim()==""){
			AlertMsg("기존 비밀번호를 입력해주세요.","oldPswd");
			return;
			}
			
			if(newPswd1.Trim()==""){
			AlertMsg("변경 할 비밀번호를 입력해주세요.","newPswd1");
			return;
			}
			if (oldPswd == newPswd1){
			AlertMsg("변경 할 비밀번호를 기존 비밀번호와 다르게 입력해주세요.","newPswd1");
			return;
			} 
			if(String(newPswd1).isPswd()==false){
			AlertMsg("비밀번호는 영 대/소, 숫자, 특수문자로 6~24자로 입력하여야합니다.","newPswd1");
			return;
			}
			if(newPswd2.Trim()==""){
			AlertMsg("변경 할 비밀번호 확인란을 입력해주세요.","newPswd2");
			return;
			}
			if (newPswd1!=newPswd2){
			AlertMsg("변경 할 비밀번호 확인와 일치하지 않습니다.", "newPswd2");
			return;
			} 
			
			$.ajax({
				type : "POST",
				dataType : "text",
				url : "/html/UserInfo/ChangePasswdBL.jsp",
				data: GetDataPostData(38,[oldPswd , newPswd1], "BASICINFOMATION_0045"),
	   			beforeSend: function() {
				},
				success : function(data) {
					var parseData = GetJSON(data);
					
					try {
						if(parseData.res=="ok"){
							AlertMsg("비밀번호변경을 완료하였습니다.");
							oThis.Close();
							return;
						}else if(parseData.res=="e"){
							AlertMsg("기존비밀번호를 확인해주세요.");
							return;
						}else{
							AlertMsg("입력 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
							return;
						}
					}catch (e) {
						AlertMsg(e);
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
					AlertMsg("입력 중 오류가 발생했습니다2. 잠시 후 다시 시도해주세요.");
				},
				complete: function() {
				}
			});
		},
		Close: function() {
			TNSPopupLibChild.Close();
		}
	};