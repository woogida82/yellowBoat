package kr.co.fishing.bko.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 현재 시간을 SimpleDateFormat으로 변형하여 날짜 스트링을 리턴한다.
     * @param dateFormat
     * @return
     */
    public static String getDate(String dateFormat) {
        return date2Str(new Date(), dateFormat);
    }
    
    /**
     * Date를 SimpleDateFormat으로 변형하여 날짜 스트링을 리턴한다.
     * @param date
     * @param dateFormat
     * @return
     */
    public static String date2Str(Date date, String dateFormat) {
        SimpleDateFormat myDateFormat = new SimpleDateFormat(dateFormat);
        return myDateFormat.format(date);
    }
    
    public static Date str2Date(String str, String pattern) {
        
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        
        Date date = null;
        
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return date;
    }
}
