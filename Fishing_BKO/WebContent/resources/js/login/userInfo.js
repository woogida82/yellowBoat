var userInfo= {
		isOrganize : null,
		pageType : null,
		fromMenu : null,
		init: function(){
			var oThis = this;
//			this.pageType = Request.getParameter("pageType");
//			this.rowid =Request.getParameter("rowid");
//			this.fromMenu =Request.getParameter("fromMenu");
//			this.Main =Request.getParameter("Main");
			this.SetBindClick();
			this.Authority();
			//사용자 정보 가져오기 
			if(this.pageType == "U"){
				$('#member_id').prop('readonly', true);
				$("#workplace_hide").show();	
				oThis.GetUserDetail();			
			}
			else{
				
//				GetLbsCommonNew("Gubun","00011", "COMMON_NM" , "사용등급 선택",function(){});
//				//국번 가져오기
//				GetLbsCommonNew("mobileNo1","00013","COMMON_NM", "선택",function(){}); 
//				//지역번호 가져오기
//				GetLbsCommonNew("telNo1","00012", "COMMON_NM", "선택",function(){});
				$("#btn_ChkMemberId").show();
				$("#divText").show();
			}
			
			$("#Gubun").change(function(){
			});
			$("#SoSuk").change(function(){
			});
			$("#SoSuk2").change(function(){
			});
			
			//등록/수정버튼
			$("#btn_Save").bind("click", function() {
			});
			$("#btnClose").bind("click", function() { oThis.Close(); });
			
			TNSPopupLibChild.Init();
		},
		
		GetUserDetail:function(){
			var oThis = this;
			
			$.ajax({
				type : "POST",
				dataType : "text",
				url : "/command_select.jsp",
				data: GetDataPostData(37, [oThis.rowid,oThis.fromMenu], "BASICINFOMATION_0029"),
				beforeSend: function() {
				},
				success : function(HttpRequest) {
					var parseData = GetJSON(HttpRequest);
					var parseRow = parseData.rows;
					
					if (parseRow.length > 0){
						//사업자등급 가져오기
						GetLbsCommonNew("Gubun", "00011", "COMMON_NM", "사용등급 선택", function(){
							$("#Gubun").val(parseRow[0].MEMBER_GRADE);
							switch ($("#Gubun").val()){
							  case 'SM' :  //슈퍼관리자
							  case 'SU' :  //관리자
							  case 'A1' :  //통합배차자
							  case 'A2' :  //일반배차자
								  GetCommonWorkNewY("SoSuk", Global.CorpCD, "WORKPLACE_NM", "사업장 선택",function(){
										$("#SoSuk").val(parseRow[0].WORKPLACE_CD);
									});
									break;
							  case 'SP' :  //화주사
								  $("#SoSuk2").removeAttr('disabled');
								  GetCommonWorkNewY("SoSuk", Global.CorpCD, "WORKPLACE_NM", "사업장 선택",function(){
										$("#SoSuk").val(parseRow[0].WORKPLACE_CD);
										GetOrganizeCd1("SoSuk2", "10", $("#SoSuk").val(), "ORGANIZE_NM1", "화주사 선택",function(){
											$("#SoSuk2").val(parseRow[0].ORGANIZE_CD1);
											$("#SoSuk2").select2({	allowClear: true	,minimumInputLength: 2}).select2("val", parseRow[0].ORGANIZE_CD1);
										});
									});
									break;
							  case 'CO' :  //협력사
								  $("#SoSuk2").removeAttr('disabled');
								  GetCommonWorkNewY("SoSuk", Global.CorpCD, "WORKPLACE_NM", "사업장 선택",function(){
										$("#SoSuk").val(parseRow[0].WORKPLACE_CD);
										GetOrganizeCd1("SoSuk2", "15", $("#SoSuk").val(), "ORGANIZE_NM1", "화주사 선택",function(){
											$("#SoSuk2").val(parseRow[0].ORGANIZE_CD1);
											$("#SoSuk2").select2({	allowClear: true	,minimumInputLength: 2}).select2("val", parseRow[0].ORGANIZE_CD1);
										});
									});
									break;
							  case 'MA'   : 
							  $("#SoSuk2").removeAttr('disabled');
							  $("#SoSuk3").removeAttr('disabled');
							  GetCommonWorkNewY("SoSuk", Global.CorpCD, "WORKPLACE_NM", "사업장 선택",function(){
									$("#SoSuk").val(parseRow[0].WORKPLACE_CD);
									GetOrganizeCd1("SoSuk2", "10", $("#SoSuk").val(), "ORGANIZE_NM1", "화주사 선택",function(){
										$("#SoSuk2").val(parseRow[0].ORGANIZE_CD1);
										$("#SoSuk2").select2({	allowClear: true	,minimumInputLength: 2}).select2("val", parseRow[0].ORGANIZE_CD1);
										GetOrganizeCd2("SoSuk3", "20", $("#SoSuk").val(), $("#SoSuk2").val(), "ORGANIZE_NM2", "수요가 선택", function(){
											$("#SoSuk3").val(parseRow[0].ORGANIZE_CD2);
											$("#SoSuk3").select2({	allowClear: true	,minimumInputLength: 2}).select2("val", parseRow[0].ORGANIZE_CD2);
										});
									});
								});
								break;
							}
						}); 

						var arrMobile = parseRow[0].MEMBER_MOBILE.split("-");
						//국번 가져오기
						GetLbsCommonNew("mobileNo1","00013", "COMMON_NM" , "선택",function(){
							$("#mobileNo1").val(arrMobile[0]); 
						}); 
						$("#mobileNo2").val(arrMobile[1]); 
						$("#mobileNo3").val(arrMobile[2]); 

						var arrTel = parseRow[0].MEMBER_TEL.split("-");
						//지역번호 가져오기
						GetLbsCommonNew("telNo1","00012", "COMMON_NM" , "선택",function(){
							$("#telNo1").val(arrTel[0]); 
						}); 
						$("#telNo2").val(arrTel[1]); 
						$("#telNo3").val(arrTel[2]); 

						
						$("#member_id").val(parseRow[0].MEMBER_ID.trim()); //  사용자 아이디
						$("#member_empl_no").val(parseRow[0].EMPL_NO.trim()); //사용자 사번
						$("#hEmpNo").val(parseRow[0].EMPL_NO.trim()); //  사용자 사번
						$("#member_nm").val(parseRow[0].MEMBER_NM.trim()); //이름
						$("#member_email").val(parseRow[0].MEMBER_EMAIL.trim());//이메일
						$("#hmobileNo").val(parseRow[0].MEMBER_MOBILE.trim());
						
						if(parseRow[0].USE_YN == "Y")
							use_yn = true ;
						else
							use_yn = false ;
						
						$("input:checkbox[id='use_yn']").attr("checked", use_yn);
						$("#login_dt").val(parseRow[0].LOG_DT.trim());
						$("#contants").val(parseRow[0].MEMBER_MEMO.replace(/(<|<\;)br\s*\/*(>|>\;)/gi,  "\r\n"));
						
						$("#use_dt").html("등록 : "+parseRow[0].INS_MEMBER_ID+" | "+parseRow[0].INS_DT +"</br>"+"수정 : " + parseRow[0].UPDATE_MEMBER_ID + "|" +parseRow[0].UPDATE_DT);
					}
					oThis.SetSelect();
				}
			});	
			if(this.fromMenu=="Y"){
				$("#Gubun").attr('disabled',true);
				$("#SoSuk").attr('disabled',true);
				$("#SoSuk").attr('disabled',true);
				$("#member_id").attr('readonly',true);
				$("#member_empl_no").attr('readonly',true);
				$("#use_yn").attr('disabled',true);
				$("#contants").attr('readonly',true);
				$("#btn_PasswordReset").hide();
				$("#btn_ChkEmpNo").hide();
			}else{
				$("#btn_PasswordReset").show();
			}
				
		},
		SetBindClick: function(){
			var oThis = this;
			// 버튼 체크 1
			if(oThis.pageType == "U"){
				
				$(".popTitle").html("정보수정");
				
				document.getElementById("btn_Save").innerText = "수정";/* 익스, 크롬, 사파리, 오페라용 */  
				document.getElementById("btn_Save").textContent = "수정";/* 파이어폭스, 크롬, 사파리, 오페라용 */  
				
				$("#isChkMemberId").val("Y");
				$("#isChkHpNo").val("Y");
				$("#isChkTelNo").val("Y");
				$("#isChkEmpNo").val("Y");
			}
			// 중복체크 루틴 1
			// 아이디 중복 체크
			$( "#member_id" ).focus(function() {
				$("#isChkMemberId").val("N");
			});
			// 핸드폰번호 중복체크	
			$( "#mobileNo1" ).focus(function() {
				$("#isChkHpNo").val("N");
			});
			$( "#mobileNo2" ).focus(function() {
				$("#isChkHpNo").val("N");
			});
			$( "#mobileNo3" ).focus(function() {
				$("#isChkHpNo").val("N");
			});
			
			// 사번중복체크	
			$( "#member_empl_no" ).focus(function(){$("#isChkEmpNo").val("N");	});
			$('#btn_ChkMemberId').click(function(){
				var chkKey = $("#member_id").val().trim();
				oThis.ChkDuplicate("isChkMemberId",chkKey,"BASICINFOMATION_0035","사용 할 수 있는 아이디입니다.","이미 등록된 아이디입니다.");
		 	});
			//사용자 등록 비밀번호 초기화 
			$('#btn_PasswordReset').click(function(){oThis.initPassword();});
			$('#btn_ChkHpNo').click(function(){
				var chkKey = $("#mobileNo1").val().trim()+'-'+$("#mobileNo2").val().trim()+'-'+$("#mobileNo3").val().trim();
				oThis.ChkDuplicate("isChkHpNo",chkKey,"BASICINFOMATION_0032","등록 할 수 있는 핸드폰번호입니다.","이미 등록된 핸드폰 번호입니다.");
		 	});
			$('#btn_ChkEmpNo').click(function(){
				var chkKey = $("#member_empl_no").val().trim();
				oThis.ChkDuplicate("isChkEmpNo",chkKey,"BASICINFOMATION_0034","등록 할 수 있는 사번입니다.","이미 등록된 사번입니다.");
		 	});
		},
		
		/*핸드폰 번호 중복 체크*/
		ChkDuplicate: function(setId,chkKey,phosNo,yesMsg,noMsg) {
			
			$.ajax({
				type : "POST",
				dataType : "text",
				url : "/command_select.jsp",
				data: GetDataPostData(37, [chkKey], phosNo),
				beforeSend: function() {
				},
				success : function(HttpRequest) {
					var parseData = GetJSON(HttpRequest);

					if ( parseData.rows[0].IS_DUP != "Y"){
						$("#"+setId).val("Y"); //중복됨으로 No
						AlertMsg(yesMsg);
					}
					else{
						$("#"+setId).val("N"); //중복됨으로 No
						AlertMsg(noMsg);
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
					AlertMsg("중복여부를 체크하는 중에 오류가 발생했습니다.");
				}
			});	
			
		},
		/*핸드폰 번호 중복 체크*/
		ChkOrganizeInfo: function(workPlaceCd,organizeCd1,organizeCd2,callback) {
			var oThis = this;
			$.ajax({
				type : "POST",
				dataType : "text",
				url : "/command_select.jsp",
				data: GetDataPostData(37, [workPlaceCd,organizeCd1,organizeCd2], "BASICINFOMATION_0040"),
				beforeSend: function() {
				},
				success : function(HttpRequest) {
					var parseData = GetJSON(HttpRequest);
					oThis.isOrganize = parseData.rows[0].IS_EXIST ;
					if (typeof(callback) == "function") callback();
				},
				error : function(xhr, ajaxOptions, thrownError) {
					AlertMsg("조직 맵핑정보를 체크하는 중에 오류가 발생했습니다.");
					if (typeof(callback) == "function") callback();
				}
			});
		},
		
		SetSelect: function(){
			//소속 가져오기 
			var oThis = this;
			if(oThis.member_grade == "SM" || oThis.member_grade == "A1" || oThis.member_grade == "A2"){
				
				GetCommonWorkCode("SoSuk",Global.CorpCD,"",function(){
					$("#SoSuk").val(oThis.workplace_cd);
					
				});
			} 
		},
		
		/*사용자관리 신규 등록 팝업 취소버튼 클릭 시*/
		Close: function(){
			TNSPopupLibChild.Close();
		},
		/*차량수정*/
		ModifyData: function(){
			var oThis = this;

			var    memberId       = $("#member_id").val(); 
			var    memberGrade    = $("#Gubun").val();
			var    workPlaceCd    = $("#SoSuk").val();
			var    organizeCd1    = $("#SoSuk2").val();
			var    organizeCd2    = $("#SoSuk3").val();
			var    memberNm       = $("#member_nm").val();
			var    memberTel1     = $("#telNo1").val();
			var    memberTel2     = $("#telNo2").val();
			var    memberTel3     = $("#telNo3").val();
			var    memberTel      = memberTel1+"-"+memberTel2+"-"+memberTel3;
			var    memberMobile1  = $("#mobileNo1").val();
			var    memberMobile2  = $("#mobileNo2").val();
			var    memberMobile3  = $("#mobileNo3").val();
			var    memberMobile   = memberMobile1+"-"+memberMobile2+"-"+memberMobile3;
			var    memberEmail    = $("#member_email").val();
			var    emplNo         = $("#member_empl_no").val();
			var    useYn        = "N";
			if ($("#use_yn").is(":checked"))
				useYn = "Y";
			var    memberMemo     = $("#contants").val();
			memberMemo = String(memberMemo).ResvChange();

			if (memberGrade.trim()==""){
				AlertMsg("사용자 등급을 입력하세요.");
				return;
			}
				
			if (workPlaceCd.trim()==""){
				AlertMsg("사업장을 입력하세요.");
				return;
			}

			switch (memberGrade) {
			  case 'SP' :  //화주사
					if (organizeCd1.trim()==""){
						AlertMsg("화주사를 입력하세요.");
						return;
					}
					break;
			  case 'CO' :  //협력사
					if (organizeCd1.trim()==""){
						AlertMsg("협력사를 입력하세요.");
						return;
					}
					break;
			  case 'MA'   : 
					if (organizeCd1.trim()==""){
						AlertMsg("화주사를 입력하세요.");
						return;
					}
					if (organizeCd2.trim()==""){
						AlertMsg("수요가를 입력하세요.");
						return;
					}
					break;
			}
							
			if (memberNm.trim()==""){
				AlertMsg("사용자명을 입력해주세요.","memberNm");
				return;
			}
				
			if (String(memberMobile1).Trim()==""||String(memberMobile2).Trim()==""||String(memberMobile3).Trim()==""){
				AlertMsg("휴대폰번호를 입력해주세요.");
				return;
			}
			
			if ( (String(memberTel1).Trim()!=""&&String(memberTel2).Trim()!=""&&String(memberTel3).Trim()!="")
					||(String(memberTel1).Trim()==""&&String(memberTel2).Trim()==""&&String(memberTel3).Trim()=="")){
			}
			else{
				AlertMsg("연락처를 입력해주세요.");
				return;
			}
			
			if (memberGrade == 'SU'||memberGrade == 'A1'||memberGrade == 'A2'){
				if (emplNo.trim()==""){
					AlertMsg("사번을 입력해주세요.");
					return;
				}
			}
			if (emplNo.trim()=="")
				$("#isChkEmpNo").val("Y");

			// 중복체크 루틴 1
			// 아이디 중복 체크
			/*---
			if ($("#isChkMemberId").val() != "Y"){
				AlertMsg("사용자 아이디  중복 체크를 한 후에 처리하세요..");
				return;
			}
			----*/
			if ($("#hmobileNo").val()==memberMobile)
				$("#isChkHpNo").val("Y");
			
			if ($("#isChkHpNo").val() != "Y"){
				AlertMsg("휴대폰번호를 중복체크 한 후에 처리해주세요.");
				return;
			}
			
			if ($("#hEmpNo").val()==emplNo.trim())
				$("#isChkEmpNo").val("Y"); 
				
			if ($("#isChkEmpNo").val() != "Y"){
				AlertMsg("사번을 중복체크 한 후에 처리해주세요.");
				return;
			}
			LayerProgressBar.Make();
			$.ajax({
				type : "POST",
				dataType : "text",
				url : "/command_update.jsp",
				data: GetDataPostData(38, [  
				                             memberId    ,memberGrade , workPlaceCd ,organizeCd1 , organizeCd2 
				                            ,memberNm    ,memberTel   ,memberMobile,memberEmail ,emplNo      
				                            ,useYn       ,memberMemo  
				               			], "BASICINFOMATION_0030"),
	   			beforeSend: function() {
				},
				success : function(data) {
					var parseData = GetJSON(data);
					
					try {
						if(parseData.res=="ok"){
							LayerProgressBar.HideAfterShow("정보수정을 완료하였습니다.",
									function(){
								var parentCallBack = TNSPopupLibChild.Callback();
								if (parentCallBack) {
									parentCallBack();
								}
								oThis.Close();
							});
							
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
		/*password 초기화*/
		initPassword: function(){
			var oThis = this;
			var memberId = $("#member_id").val(); 
			var memberPw = "";

			if (memberId.trim()==""){
				AlertMsg("아이디를 입력해주세요.");
				return;
			}

			memberPw = memberId+"1234";
			
			$.ajax({
				type : "POST",
				dataType : "text",
				url : "/command_update_pswd.jsp",
				data: GetDataPostData(38, [ memberId , memberPw ], "BASICINFOMATION_0036"),
	   			beforeSend: function() {
				},
				success : function(data) {
					var parseData = GetJSON(data);
					try {
						if(parseData.res=="ok"){

							var parentCallBack = TNSPopupLibChild.Callback();
							if (parentCallBack) {
								parentCallBack();
							}
							
							userInfo.Close();
						
						}else{
							AlertMsg("비밀번호 초기화 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
							return;
						}
					}catch (e) {
						AlertMsg(e);
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
					AlertMsg("비밀번호 초기화 중 오류가 발생했습니다2. 잠시 후 다시 시도해주세요.");
				},
				complete: function() {
				}
			});
		},
		
		//사용자 등록 
		RegData: function(){
			var oThis = this;
			var  memberId  = $("#member_id").val(); 
			var  memberGrade = $("#Gubun").val();
			var  workPlaceCd = $("#SoSuk").val();
			var  organizeCd1 = $("#SoSuk2").val();
			var  organizeCd2  = $("#SoSuk3").val();
			var  memberNm = $("#member_nm").val();
			var  memberTel1 = $("#telNo1").val();
			var  memberTel2 = $("#telNo2").val();
			var  memberTel3  = $("#telNo3").val();
			var  memberTel  = memberTel1+"-"+memberTel2+"-"+memberTel3;
			var  memberMobile1 = $("#mobileNo1").val();
			var  memberMobile2 = $("#mobileNo2").val();
			var  memberMobile3 = $("#mobileNo3").val();
			var  memberMobile = memberMobile1+"-"+memberMobile2+"-"+memberMobile3;
			var  memberEmail = $("#member_email").val();
			var  emplNo  = $("#member_empl_no").val();
			var  useYn = "N";

			if ($("#use_yn").is(":checked"))
				useYn = "Y";
			var memberMemo = $("#contants").val();
			memberMemo = String(memberMemo).ResvChange();
			var memberPw = "";

			if (memberGrade.trim()==""){
				AlertMsg("사용자 등급을 선택해주세요.");
				return;
			}
				
			if (workPlaceCd.trim()==""){
				AlertMsg("사업장을 선택해주세요.");
				return;
			}

			switch (memberGrade) {
			  case 'SP' :  //화주사
					if (organizeCd1.trim()==""){
						AlertMsg("화주사를 선택해주세요.");
						return;
					}
					break;
			  case 'CO' :  //협력사
					if (organizeCd1.trim()==""){
						AlertMsg("협력사를 선택해주세요.");
						return;
					}
					break;
			  case 'MA'   : 
					if (organizeCd1.trim()==""){
						AlertMsg("화주사를 선택해주세요.");
						return;
					}
					if (organizeCd2.trim()==""){
						AlertMsg("수요가를 선택해주세요.");
						return;
					}
					break;
			}
			
			if (memberId.trim()==""){
				AlertMsg("아이디를 입력해주세요.");
				return;
			}
			else{
				if(!memberId.isEng()){
					AlertMsg("영문 대/소,숫자를 조합하여 아이디를 생성해주세요.");
				}
			}
			
			// 아이디 중복 체크
			if ($("#isChkMemberId").val() != "Y"){
				AlertMsg("아이디를  중복체크 한 후에 처리해주세요.");
				return;
			}
				
			if (memberNm.trim()==""){
				AlertMsg("사용자명을 입력해주세요.");
				return;
			}
			
			if (memberMobile1.trim()==""||memberMobile2.trim()==""||memberMobile3.trim()==""){
				
				AlertMsg("휴대폰번호를 입력해주세요.");
				return;
			}
			else{
				if(!memberMobile.isTel()){
					AlertMsg("휴대폰번호가 올바르지 않습니다."); 
					return;
				}
			}
			
			if ($("#isChkHpNo").val() != "Y"){
				AlertMsg("휴대폰번호를 중복체크 한 후에 처리해주세요.");
				return;
			}
				
			if ( (memberTel1.trim()!=""&&memberTel2.trim()!=""&&memberTel3.trim()!="") || (memberTel1.trim()==""&&memberTel2.trim()==""&&memberTel3.trim()=="")){
				
			}
			else{
				AlertMsg("연락처를 입력해주세요.");
				return;
			}
			
			if(memberEmail != "" && !memberEmail.isEMail()){
				
				AlertMsg("이메일을 올바르게 입력해주세요."); 
				return;
			}
			
			if (memberGrade == 'SU'||memberGrade == 'A1'||memberGrade == 'A2'){
				if (emplNo.trim()==""){
					AlertMsg("사번을 입력해주세요.");
					return;
				}
			}
			
			if (emplNo.trim()=="")
				$("#isChkEmpNo").val("Y");
		
			if ($("#isChkEmpNo").val() != "Y"){
				AlertMsg("사번을 중복체크 한 후에 처리해주세요.");
				return;
			}
			
			memberPw = memberId.trim() + "1234" ;
			LayerProgressBar.Make();
			$.ajax({
				type : "POST",
				dataType : "text",
				url : "/html/BasicInformation/userInfoBL.jsp",
				data: GetDataPostData(38, [
				                             memberId    ,memberGrade , workPlaceCd ,organizeCd1 , organizeCd2 
					                        ,memberNm    ,memberTel   ,memberMobile,memberEmail ,emplNo      
					                        ,useYn       ,memberMemo, memberPw   
				               			], "BASICINFOMATION_0031"),
	   			beforeSend: function() {
				},
				success : function(data) {
					var parseData = GetJSON(data);
					try {
						if(parseData.res=="ok"){
							LayerProgressBar.HideAfterShow("사용자 등록이 완료되었습니다.",
									function(){
										var parentCallBack = TNSPopupLibChild.Callback();
										if (parentCallBack) {
											parentCallBack();
										}
									userInfo.Close();
							});
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
		
		/*구분 변경시 소속 select box  disabled*/
		GubunChange:function(){ 
			$("#workplace_hide").show();			
			if($("#Gubun").val() == "SP"){ //화주사
				$("#SoSuk2").removeAttr('disabled');
				$("#SoSuk3").attr('disabled','disabled');
			}
			else if($("#Gubun").val() =="CO"){ //협력사
				
				$("#SoSuk2").removeAttr('disabled');
				$("#SoSuk3").attr('disabled','disabled');
			}
			else if($("#Gubun").val()  == "MA"){//수요가
				$("#SoSuk2").removeAttr('disabled');
				$("#SoSuk3").removeAttr('disabled');
			}
			else if($("#Gubun").val()  == "SM" || $("#Gubun").val()  == "A1" ||  $("#Gubun").val()  == "A2"){//관리자, 배차자(일반, 통합)
				$("#SoSuk2").attr('disabled','disabled');
				$("#SoSuk3").attr('disabled','disabled');
			}
		},
		
		// 숫자만 입력가능
		CurrencyChk: function(el) {

			if (el.tagName == "INPUT") {
				var currentValue = el.value;
				if(!currentValue.isNum()){
					currentValue = currentValue.replace(/[^0-9]/g, "");
					el.value = currentValue;
				}
			}
		},
		
		Authority:function(){
//			if(Global.MemberGrade == "SM" || Global.MemberGrade == "SU"){
//				//$("#btn_Save").show();
//				//$("#btn_ChkMemberId").show();
//				//$("#btn_PasswordReset").show();
//				//$("#note_txt").show();
//				//$("#btn_ChkEmpNo").show();
//			}
//			else{
//			///	$("#btn_Save").hide();
//				//$("#btn_ChkMemberId").hide();
//				//$("#btn_PasswordReset").hide();
//			//	$("#note_txt").hide();
//				//$("#btn_ChkEmpNo").hide();
//			}
		}
};