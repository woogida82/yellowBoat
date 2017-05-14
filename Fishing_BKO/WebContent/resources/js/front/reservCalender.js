var ReservCalender = {
    /* 초기 로딩시 실행 */
    init : function() {
        ReservCalender.reservCalendar('', '');
    },
    
    reservCalendar : function(year, month){
        $("#year").val(year);
        $("#month").val(month);
        
        $("#reservCalenderForm").onSubmit({
            url            : "/front/getReservCalendar",
            validation     : true,             // validation 체크 유무
            validmessage   : 'alertAll',       // 하나의 alert에 모든 에러 내용을 표시
            ajaxSubmit     : true,             // ajax로 통신할 경우
            success        : function(data){   // ajax인 경우 success callback
                if (data.result == 'OK') {
                    ReservCalender.reservCalendarCallBack(data);
                }
            }
        });        
        
    },
    
    reservCalendarCallBack : function(data){
        var result = data.result;
        if(result == "OK"){
            var year = data.year;
            var month = data.month;
            var innerHtmlCal = ""; 
            var innerHtmlTab = "";  
            var newLine = 0;
            var startDay = data.startDay;
            var endDay = data.endDay;
            var start = data.start;
            var dList = data.rows; 
            
            $("#year").val(year);
            $("#month").val(month);            
            
            $("#calTitle").empty();
            innerHtmlCal = "<a class='date_prev' href='#' onclick='ReservCalender.moveCalendarPrev();'></a>";
            innerHtmlCal = innerHtmlCal + year+"년 "+month+"월";
            innerHtmlCal = innerHtmlCal + "<a class='date_next' href='#' onclick='ReservCalender.moveCalendarNext();'></a>";
            $("#calTitle").html(innerHtmlCal);
            
            var calendarTable = $("#calendarTab");
            calendarTable.empty();
            
            innerHtmlTab = innerHtmlTab + "<tr>";
            
            for(var index = 1; index < start ; index++ )
            {
                innerHtmlTab = innerHtmlTab +"<td >&nbsp;</td>";
                newLine = newLine + 1;
            }            
            
//            for(var index = 1; index <= endDay; index++)
//            {
//                
//                innerHtmlTab = innerHtmlTab +"<td>";
//                innerHtmlTab = innerHtmlTab + index;
//                innerHtmlTab = innerHtmlTab +"</td>";
//                newLine = newLine + 1;
//                if(newLine == 7)
//                {
//                    innerHtmlTab = innerHtmlTab +"</tr>";
//                    if(index <= endDay)
//                    {
//                        innerHtmlTab = innerHtmlTab +"<tr>";
//                    }
//                    newLine = 0;
//                }                
//            }
            
            for(var i = 0; i< dList.length; i++)
            {
                innerHtmlTab = innerHtmlTab + "<td>";
                innerHtmlTab = innerHtmlTab + "<div class='sd_inner'>";
                innerHtmlTab = innerHtmlTab + "<p class='date_num'>";
                innerHtmlTab = innerHtmlTab + dList[i].day;
                innerHtmlTab = innerHtmlTab + "</p>";
                
                var dList2 = dList[i].reservationDetailBeans;
                
                if(dList2.length > 0){
                    innerHtmlTab = innerHtmlTab + "<ul>";
                    for(var k = 0; k< dList2.length; k++){
                        if(k < 2){
                            innerHtmlTab = innerHtmlTab + "<li><a href='#' onclick=ReservCalender.reservView('"+dList2[k].reservDtlId+"','"+dList[i].day+"');>";
                            innerHtmlTab = innerHtmlTab + dList2[k].reservNm + "님(" + dList2[k].reservAdultsNumber + "분)&nbsp;예약";
                            innerHtmlTab = innerHtmlTab + "</a></li>";
                        }else if(k == 2){
                            innerHtmlTab = innerHtmlTab + "<li><a href='#'>...</a></li>";
                        }
                    }
                    innerHtmlTab = innerHtmlTab + "</ul>";
                }
                
                if(dList[i].reservationBean != null){
                    if(dList[i].reservationBean.statusCd == '00' || dList[i].reservationBean.statusCd == ''){
                        innerHtmlTab = innerHtmlTab + "<a href='#' class='reserve_btn' onclick=ReservCalender.reservWrite('"+dList[i].day+"'); >예약하기</a>";
                    }else{
                        innerHtmlTab = innerHtmlTab + "<a class='reserve_finish'>예약마감</a>";
                    }
                }else{
                    innerHtmlTab = innerHtmlTab + "<a href='#' class='reserve_btn' onclick=ReservCalender.reservWrite('"+dList[i].day+"'); >예약하기</a>";
                }
                
                innerHtmlTab = innerHtmlTab + "</div>";
                innerHtmlTab = innerHtmlTab + "</td>";
                
                
                
                newLine = newLine + 1;
                if(newLine == 7)
                {
                    innerHtmlTab = innerHtmlTab + "</tr>";
                    if(i <= dList.length)
                    {
                        innerHtmlTab = innerHtmlTab + "<tr>";                    
                    }
                    newLine = 0;
                }                
            }            
            
            while(newLine > 0 && newLine < 7)
            {
                innerHtmlTab = innerHtmlTab +"<td >&nbsp;</td>";
                newLine++;
            }            
            
            innerHtmlTab = innerHtmlTab + "</tr>";
            calendarTable.html(innerHtmlTab);
        }
    },
    
    reservWrite : function(day){
        var reservCalenderForm = $("#reservCalenderForm");
        var url = "/front/reservWrite";
        reservCalenderForm.prop("action", url);
        $("#day").val(day);
        reservCalenderForm.submit();  
    },
    
    reservView : function(reservDtlId, day){
        var reservCalenderForm = $("#reservCalenderForm");
        var url = "/front/reservView";
        reservCalenderForm.prop("action", url);
        $("#reservDtlId").val(reservDtlId);
        $("#day").val(day);
        reservCalenderForm.submit();          
    },

    moveCalendarPrev : function(){
        var year = $("#year").val();
        var month = $("#month").val(); 
        
        if(month == '1'){
            year = Number(year) - 1; 
            year = year.toString();
            month = '12';
        }else{
            month = Number(month) - 1;
            month = month.toString();
        }

        ReservCalender.reservCalendar(year, month);
    },
    
    moveCalendarNext : function(){
        var year = $("#year").val();
        var month = $("#month").val();
        
        if(month == '12'){
            year = Number(year) + 1;
            year = year.toString();
            month = '1';
        }else{
            month = Number(month) + 1;
            month = month.toString();
        }
        ReservCalender.reservCalendar(year, month);
    }
};