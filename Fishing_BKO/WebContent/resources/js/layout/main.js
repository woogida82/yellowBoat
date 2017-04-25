var URL = {ADMN:"/bko/user",CUST:"/bko/shipInfo"};
var userCd = $("#userCd").val();
var url;

var Main = {
    check : null,
    LoadContentsList: null,
    popupHtml : null,
    passHtml : null,
    init: function() {
        var toDay = "Today "+$.getToday('yyyy.MM.dd');
        var pDate = $("p.date");
        var sidebar = $("div.sidebar");
        var userCd = $("#hiddenUserCd").val();
        var url = "";
        // 메뉴 구성
        Menu.makeMenu();  
        pDate.empty();
        pDate.text(toDay);
        if(userCd == "ADMN"){
            url = URL.ADMN;
        }else{
            url = URL.CUST;
        }
        
        Main.openClient(url);
    },
    //페이지 직접이동
    openClientDirect: function(CURL) {
        location.href = CURL;
    },
    /*
     * 메뉴 실행 function
     * winID : Contents Box ID, CURL : Contents URL, MenuName: 메뉴명
     */
    openClient: function(CURL) {
        $.checkSession();// 세션체크
        var objName = "";
        var btnName = "";
        var AppendHtml = "";    
        var CententBox = $('#bko-CententBox');
        CententBox.empty();
        
        if (CURL == "") return false;
        
        AppendHtml = "<object class='bko-ClientBox' id='objContent' type='text/html'></object>";              
        CententBox.prepend(AppendHtml); 
        document.getElementById("objContent").data = CURL;
    },
    /* 홈으로 */
    returnHome: function(){
        window.location.href = "/bko/main";
    },
    /* log out */
    logOut : function(){
        window.location.href = "/bko/logout";
    }
};