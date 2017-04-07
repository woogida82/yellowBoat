package kr.co.fishing.bko.common.tags;

import java.util.List;

import kr.co.fishing.bko.common.utils.CommonUtil;

import org.apache.commons.lang.StringUtils;


public class Function {

	/**
	 * 리스트에 해당 데이터가 있는지 판단한다.
	 * 
	 * @param list 검색대상 리스트 
	 * @param str 검색할 값
	 * @return 검색결과
	 */
	public static boolean contains(List<String> list, String str) {
		
		if (list != null && str != null) {
			return list.contains(str);
		}
		
		return false;
	}
	
	/**
	 * 대상 문자열이 없을경우 디폴트 값을 리턴한다.
	 * 
	 * @param target 대상문자 
	 * @param def 디폴트문자
	 * @return 디폴트문자
	 */
	public static String defaultValue(String target, String def) {
		
		if (StringUtils.isEmpty(target)) {
			return def;
		}
		
		return target;
	}
	
	/**
	 * 리스트를 구분자로 연결한다.
	 * 
	 * @param list 대상 리스트 
	 * @param separator 구분문자
	 * @return 검색결과
	 */
	public static String join(List<String> list, String separator) {
		
		return CommonUtil.join(list, separator);
	}
	
	/**
     * 백분율을 계산 한다.
     * 
     * @return 계산결과
     */
    public static String rate(String target, String total, Integer digits) {
        
        return CommonUtil.rate(target, total, digits);
    }
}
