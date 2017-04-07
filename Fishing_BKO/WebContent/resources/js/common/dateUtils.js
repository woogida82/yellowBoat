(function($) {
    
    $.format = function(format, dateStr, weekName){
        
        if (!format) {
            format = 'yyyy-MM-dd';
        }
        
        var date = $.strToDate(dateStr);
        
        if (!weekName) {
            weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
        }
        
        return format.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
            switch ($1) {
                case "yyyy": return date.getFullYear();
                case "yy": return date.getFullYear() % 1000 % 100;
                case "MM": return $.zeroPad(date.getMonth() + 1);
                case "dd": return $.zeroPad(date.getDate());
                case "E": return weekName[date.getDay()];
                case "HH": return $.zeroPad(date.getHours());
                case "hh": return $.zeroPad((h = date.getHours() % 12) ? h : 12);
                case "mm": return $.zeroPad(date.getMinutes());
                case "ss": return $.zeroPad(date.getSeconds());
                case "a/p": return date.getHours() < 12 ? "오전" : "오후";
                default: return $1;
            }
        });
    };
    
    /* 요일구하기 : dateStr 형식 yyyy.mm.dd*/
    $.getWeekday = function(dateStr){
        
        var date = $.strToDate(dateStr);
        var weekName = ["일", "월", "화", "수", "목", "금", "토"];

        return $.format('E', dateStr, weekName);
    };
    
    $.getToday = function(format){
        return $.format(format, $.strToDate());
    }
    
    $.dateAdd = function(dateStr, addDay, format){
        
        if(!addDay){
            addDay = 0;
        }

        var date;
        
        if (!dateStr) {
            date = new Date();
        } else {
            date = $.strToDate(dateStr);
        }
        
        date.setDate(date.getDate() + parseInt(addDay, 10));
        
        return $.format(format, date);
    };
    
    $.dateDiff = function(date1Str, date2Str) {
        
        var date1 = $.strToDate(date1Str);
        var date2 = $.strToDate(date2Str);
     
        // getTimeメソッドで経過ミリ秒を取得し、２つの日付の差を求める
        var msDiff = date2.getTime() - date1.getTime();
     
        // 求めた差分（ミリ秒）を日付へ変換します（経過ミリ秒÷(1000ミリ秒×60秒×60分×24時間)。端数切り捨て）
        var daysDiff = Math.floor(msDiff / (1000 * 60 * 60 *24));
     
        // 差分へ1日分加算して返却します
        return ++daysDiff;
    };
    
    $.strToDate = function(dateStr){
        
        if (!dateStr) {
            return new Date();
        }
        
        if (typeof dateStr == "number") {
            dateStr = dateStr.toString();
        } else if (typeof dateStr == "string") {
            dateStr = dateStr.split(".").join("");
        } else if (typeof dateStr == "object") {
            return dateStr;
        } 
        
        var yy = parseInt(dateStr.substr(0, 4));
        var mm = parseInt(dateStr.substr(4, 2));
        var dd = parseInt(dateStr.substr(6, 2));
        
        return new Date(yy, mm - 1, dd); 
    };
    
    $.strToDateTime = function(dateStr){
        
        if (!dateStr) {
            return new Date();
        }
        
        if (typeof dateStr == "number") {
            dateStr = dateStr.toString();
        } else if (typeof dateStr == "string") {
            dateStr = dateStr.split(/[\.\s:]/).join("");
        } else if (typeof dateStr == "object") {
            return dateStr;
        } 
        
        var yy = parseInt(dateStr.substr(0, 4));
        var mm = parseInt(dateStr.substr(4, 2));
        var dd = parseInt(dateStr.substr(6, 2));
        var h = parseInt(dateStr.substr(8, 2));
        var m = parseInt(dateStr.substr(10, 2));
        var s = 0;
        
        if (dateStr.length > 12) {
            parseInt(dateStr.substr(12, 2));
        }
        
        return new Date(yy, mm - 1, dd, h, m, s); 
    };
    
    $.getAge = function(dateStr){
        var birthday = $.strToDate(dateStr);
        var today = new Date();
        var years = today.getFullYear() - birthday.getFullYear();

        // Reset birthday to the current year.
        birthday.setFullYear(today.getFullYear());

        // If the user's birthday has not occurred yet this year, subtract 1.
        if (today < birthday) {
            years--;
        }
    };
    
})(jQuery);