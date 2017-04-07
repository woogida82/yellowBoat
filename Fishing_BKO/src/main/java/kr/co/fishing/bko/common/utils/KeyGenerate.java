/**
@ 20131122 comnaute

결과를 BASE64 코드로 리턴 하므로 시간을 기반으로 하는 경우 1000분의 1초까지 사용하면 
대략적으로(통상 request를 순차적으로 처리하므로) 사용할 수 있을 것으로 판단됨. 

숫자로 구성된 코드를 2Byte 씩 끊어서 ASCII 코드로 전환후 BASE64로 전환하여 사용 가능한 코드 생성

BASE64    
현재 웹에서 특별한 처리없이 어느나라나 사용할 수 있는 ASCII 코드는 총 65개 
(ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=)

이중에 = 을 제외하면 64개를 사용할 수 있으며 "="는 padding 문자로 사용한다.
64개의 문자를 표현하기 위해서 6bit가 필요하고 
이를 다시 화면에 뿌리기 위해서는 ASCII의 8bit의 코드를 사용해야 하므로
ASCII 3글자 단위로 BASE64 4글자가 만들어 지는 구조가 된다. 
(이렇게 딱 맞게 구성이 안되는 경우를 위해 "="을 padding으로 사용-실제로 변환시에 무시됨) 


BASE64는 4Byte 단위로 증가 하므로 코드의 길이는 4*n 형태로 증가하는 값을 갖게 된다. 

넘겨 받은 NUMBER 를 2Byte씩 끊어서 아스키코드로 변환후 이를 다시 base64로 전환하여 코드 생성한다.
변환후 웹으로 보낼때 문제가 발생할 수 있는 "=" 이 포함되지 않게 하기 위해 코드의 길이를 제한 한다.

DATEFORMAT                              NUMBER          HEX     BASE64
------------------------------------------------------------------------ 
HHmmss                                          6                           3               4
yyMMddHHmmss                        12                      6               8
yyyyMMddHHmmssXXXX      18                      9               12

XXXX : SSS를 받아서 6진수로 변환한 값

위의 예시를 보면 넘어오는 숫자코드의 길이는 6*n 으로 구성되고 
반환하는 코드의 길이는 4*n 으로 구성된다.

결과적으로 BASE64를 사용하므로 62, 63 이 포함되는 경우 / + 같은 기호가 표시 되므로
날짜, 시간은 59까지 표시하지만 1/1000 초단위 표시는 62, 63을 포함하게 되므로 해당 값에 
대해서만 6진수로 변환하여 처리한다.

4*n 형태 이외의 길이의 코드를 사용하려면 "=" 를 제거하고 사용해도 무방하다.
*/
package kr.co.fishing.bko.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.codec.binary.Base64;

/**
* 넘어온 숫자를 기준으로 코드 생성 
* @author ComnautE
* @version 1.0
* @date 2013. 11. 22.
* @classinfo dev.comnaute.KeyGenerate.java
*/
public class KeyGenerate {
    /*public static void main(String args[]) throws Exception {
        System.out.println("================================================");
        System.out.println("숫자여부");
        System.out.println("checkNumber('131122213311') : " + checkNumber("131122213311"));
        System.out.println("checkNumber('13112221331A') : " + checkNumber("13112221331A"));
        System.out.println("================================================");
        System.out.println("길이가 6의 배수가 아니면 앞에 0으로 채움");
        System.out.println("setPadZero('123456') : " + setPadZero("123456"));
        System.out.println("setPadZero('1234') : " + setPadZero("1234"));
        System.out.println("setPadZero('1234567') : " + setPadZero("1234567"));
        System.out.println("================================================");
        System.out.println("2Byte씩 끊어서 ASCII 전환");
        System.out.println("getAscii('131122213311') : " + getAscii("131122213311"));
        System.out.println("================================================");
        System.out.println("BASE64");
        System.out.println("BASE64 전환 : " + getEnStrkey(getAscii("131122213311")));
        System.out.println("BASE64 복원 : " + getDeAscii(getEnStrkey(getAscii("131122213311"))));
        System.out.println("BASE64 복원에러여부 : " + getEnStrkey("&!@#$12312"));
        System.out.println("================================================");
        System.out.println("시스템시간을 가져와서 padZero 후 변환하여 결과 보기");
        System.out.println("BASE64 전환 : " + getEnStrkey(getAscii(setPadZero(getDateNumber("yyyyMMddHHmmssSSS")))));
        System.out.println("================================================");
        System.out.println("62 : " + getEnStrkey(getAscii(setLeftPadding("62",6,"0"),false)));
        System.out.println("63 : " + getEnStrkey(getAscii(setLeftPadding("63",6,"0"),false)));
        System.out.println("================================================");
        System.out.println("999를 6진수로 : " + getDigitNum(999));
        System.out.println("getLongNumber : " + getLongNumber());
        System.out.println("================================================");
        System.out.println("BASE64 전환 시간 : " + getLongNumber());
        System.out.println("BASE64 전환 아스키 : " + getAscii(getLongNumber()));
        System.out.println("BASE64 전환 : " + getEnStrkey(getAscii(getLongNumber())));
        System.out.println("================================================");
        System.out.println("SN : " + getSplitSep(getEnStrkey(getAscii(getLongNumber())),4,"-"));
        System.out.println("================================================");
        System.out.println("BASE64 복원 아스키 : " + getDeStrkey("KysMHwwVCw0U".getBytes()));
        System.out.println("BASE64 복원 시간 : " + getDeAscii(getDeStrkey("KysMHwwVCw0U".getBytes())));
        System.out.println("BASE64 복원 초1/1000 : " + getDigitNum(Integer.parseInt(getDeAscii(getDeStrkey("KysMHwwVCw0U".getBytes())).substring(14,18)),6,10));
        System.out.println(Integer.parseInt(getDeAscii(getDeStrkey("KysMHwwVCw0U".getBytes())).substring(14,18)));
        System.out.println("================================================");
        System.out.println("최종테스트");
        System.out.println("짧은키 : " + getShotKey());
        System.out.println("길은키 : " + getLongKey());
        System.out.println("생성키 : " + getSerialKey());
        System.out.println("================================================");
        System.out.println("* 키는 영문 대소문자와 숫자로 이루어져 있으며 단일 시스템에서 중복 발생하지 않는다.");
        System.out.println("================================================");
    }*/

    /**
     * 짧은키구하기
     */
    public static String getShotKey() {
        return getEnStrkey(getAscii(setPadZero(getShotNumber())));
    }
        
    /**
     * 긴키구하기
     */
    public static String getLongKey() {
        return getEnStrkey(getAscii(setPadZero(getLongNumber())));
    }
    
    /**
     * 발급번호구하기
     */
    public static String getSerialKey() {
        return getSplitSep(getEnStrkey(getAscii(setPadZero(getLongNumber()))), 4, "-");
    }
    
    /**
     * 숫자로만 구성된 입력값을 정해진 규칙으로 아스키코드로 변환
     */
    public static String getAscii(String num) {
        return getAscii(num, true);
    }
    
    /**
     * 2Byte씩 잘라서 ASCII 변환
     */
    public static String getAscii(String num, boolean rewind) {
        String ret = "";
        int len = 0;
        char[] arr = null;
        if (null != num) {
            len = num.length()/2;
            arr = new char[len];
            for (int i=0;i<len;i++) {
                /* 배열에 넣을때 역순으로 넣는다. */
                if (rewind) {
                    arr[len-i-1] = (char) Integer.parseInt(num.substring(i*2,(i+1)*2));
                } else {
                    arr[i] = (char) Integer.parseInt(num.substring(i*2,(i+1)*2));
                }
            }
            ret = new  String(arr, 0, arr.length);
        }
        return ret;
    }
    
    /**
     * 아스키코드를 넘겨 받았던 숫자로만 구성된 입력값으로 변환
     */
    public static String getDeAscii(String str) {
        return getDeAscii(str, true);
    }
    
    /**
     * getAscii에서 변환된 String의 역변환
     */
    public static String getDeAscii(String str, boolean rewind) {
        String ret = "";
        int len = 0;
        char[] arr = null;
        if (null != str) {
            char[] tmp = str.toCharArray();
            len = str.length();
            arr = new char[len];
            for (int i=0;i<len;i++) {
                /* 배열에 넣을때 역순으로 넣는다. */
                if (rewind) {
                    arr[i] = tmp[len-i-1];
                } else {
                    arr[i] = tmp[i];
                }
            }
            
            for (int i=0;i<len;i++) {
                ret += setLeftPadding(Integer.toString((int)arr[i]),2,"0");
            }
        }
        return ret;
    }
    
    public static String getRandomKey(int length) {
        int l = (int)(length / 4);
        return getEnStrkey(getRandomKey(l * 3));
    }
    
    /**
     * 구한 결과를 일정길이로 자르기
     */
    public static String getSplitSep(String str, int len, String sep) {
        String ret = "";
        if (null != str && str.length()/len > 0) {
            while (str.length() > len) {
                if (ret.length()>0) {
                    ret += sep;
                }
                ret += str.substring(0, len);
                str  = str.substring(len);
            }
            
            if (str.length() > 0) {
                if (ret.length()>0) {
                    ret += sep;
                }
                ret += str;
            }
        } else {
            ret = str;
        }
        return ret;
    }

    /**
     * 키를 구하기 위해 BASE64 Encoding 
     */
    public static String getEnStrkey(String str) {
        byte[] ret = null;
        if (null != str && str.length() > 0) {
            ret = Base64.encodeBase64(str.getBytes());
        }
        return new String(ret);
    }
    
    /**
     * 키를 풀어서 데이터 BASE64 Decoding 
     */
    public static String getDeStrkey(byte[] str) {
        byte[] ret = null;
    
        if (null != str) {
            ret = Base64.decodeBase64(str);
        }
    
        return new String(ret);
    }
    
    /**
     * @ 날짜 (yyMMddHHmmss)구하기
     */
    public static String getShotNumber() {
        return getDateNumber("yyMMddHHmmss");
    }
    
    /**
     * @ 날짜 (yyyyMMddHHmmssXXXX)구하기
     */
    public static String getLongNumber() {
        String num = getDateNumber("yyyyMMddHHmmss");
        String tmp = getDateNumber("SSS");
        tmp = getDigitNum(Integer.parseInt(tmp));
        num = num + tmp;
        return num;
    }
    
    /**
     * @ 자리수 맞추어 주기 위해 6자리로 채움.
     */
    public static String setPadZero(String num) {
        if (null != num && (num.length())%6 > 0) {
            num = setLeftPadding(num,num.length()+6-(num.length()%6),"0");
        }
        return num;
    }
    
    /**
     * @ XXXX구하기 위한 진수변환 및 자리채움
     */
    public static String getDigitNum(int num) {
        // 길이가 4자리여야 한다.
        return setLeftPadding(getDigitNum(num, 10, 6), 4, "0");
    }
    
    /**
     * @ 10진수를 X(X<10)진수로 변환 (10진수를 X로 나눈 몫을 계속 나눠 단계별로 떨어지는 나머지를 역순으로 적으면 됨)
     */
    public static String getDigitNum(int num, int srcDigit, int tgtDigit) {
        String ret = "";
        int cul = 0;
        if (srcDigit < 10) {
            String tmp = Integer.toString(num);
            num = 0;
            for (int i=0;i<tmp.length();i++) {
                num += Integer.parseInt(tmp.substring(i, i+1))*(int)Math.pow(srcDigit, tmp.length()-i-1);
            }
        }
        if (tgtDigit > 1 && tgtDigit < 10) {
            ret = Integer.toString(num%tgtDigit)+ret;
            cul = num/tgtDigit;
            while (cul > 0) {
                ret = Integer.toString(cul%tgtDigit)+ret;
                cul = cul/tgtDigit;
            }
        } else {
            ret = Integer.toString(num);
        }
        return ret;
    }
    
    /**
     * 필요한 길이가 안되는 경우 입력한 문자로 채움 
     */
    public static String setLeftPadding(String str, int length, String padStr) {
        String ret = "";
        int lenStr = str.length();
        if(lenStr < length) {
            char strResult[] = new char[length];
            char strArr[] = str.toCharArray();
            for(int i=0;i<length;i++) {
                if(i < (length-lenStr)) {
                        strResult[i] = padStr.charAt(0);
                } else {
                    strResult[i] = strArr[i - (length - lenStr)];
                }
            }
            ret = new String(strResult);
        } else {
            ret = str;
        }
        return ret;
    
    }
    
    /**
     * 시스템에서 날짜를 구해온다.
     */
    public static String getDateNumber(String ptn) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat (ptn, Locale.KOREA);
        Date dt_src  = new Date ();
        String ret = mSimpleDateFormat.format(dt_src);
        return ret;
    }

}
