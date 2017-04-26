var RegShipInfoView = {
    init : function(){
        $("#regShipInfoButton").bind("click", function() { RegShipInfoView.regShipInfo(); });
        $("#cancelButton").bind("click", function() { RegShipInfoView.cancel(); });
        $(".file_photo>img").hide();
    },
    /* 선박등록 */
    regShipInfo:function(){
        $("#regShipInfoForm").onSubmit({
            url            : "/bko/shipInfo/insertShipInfo",
            confirmMessage : "등록 하시겠습니까?",
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
                        alert("등록 되었습니다.");
                        RegShipInfoView.cancel();
                    }
                }else {
                    alert("실패하였습니다.");
                    RegShipInfoView.cancel();
                }
            }
        });        
    },
    /* cancel */
    cancel : function(){
        window.location.href = "/bko/shipInfo";
    },
    
    fileChoice : function(name1, name2, name3) {
        var filePath = $("#"+name1).val();
        var ext = filePath.split('.').pop().toLowerCase();
        var file = null;
        var img = null;
        var holder = $("#"+name3); 
//      holder = document.getElementById(name3);               
        if($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
            alert('이미지 파일이 아닙니다! (gif, png, jpg, jpeg 만 업로드 가능)');
            $("#"+name1).val("");
            $("#"+name2).val("");
            holder.hide();
            return false;
        }
        
        if (filePath) {
            var orgFileNm = filePath.replace(/^.*[\\\/]/, '');
            $("#"+name2).val(orgFileNm);
        }
        file = $("#"+name1)[0].files[0];
        blobURL = window.URL.createObjectURL(file);
        holder.prop('src', blobURL);
        holder.show();
    }    
}

 
