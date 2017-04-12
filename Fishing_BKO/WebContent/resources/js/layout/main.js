var Main = {
    check : null,
    LoadContentsList: null,
    popupHtml : null,
    passHtml : null,
    
    init: function() {
        var signUpYn = $("#signUpYn").val();
        var toDay = "Today "+$.getToday('yyyy.MM.dd');
        var pDate = $("p.date");
        var pUserNm = $("p.title");
        
        pDate.empty();
        pDate.text(toDay);
            
        if(signUpYn != "Y"){
            // 메뉴 구성
            Menu.makeMenu();            
        }else{
            pUserNm.empty();
            pUserNm.text("고객 님");
        }
    },
    //페이지 직접이동
    openClientDirect: function(CURL) {
        location.href = CURL;
    }    
};