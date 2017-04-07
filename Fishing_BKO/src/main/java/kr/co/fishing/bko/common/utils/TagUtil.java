package kr.co.fishing.bko.common.utils;

import java.util.List;

import kr.co.fishing.bko.common.beans.CommonCodeBean;

import org.apache.commons.lang.StringUtils;

public class TagUtil {
    
    /**
     * 셀렉트박스 태그를 작성한다
     * 
     * @param boxName
     * @param dataList
     * @param selected
     * @param selectDefault
     * @param selectDefaultName
     * @param include
     * @param isMakeDefault
     * @return
     */
    public static String makeSelectBox(String boxName, List<CommonCodeBean> dataList, String selected, String selectDefault, String selectDefaultName, String include, boolean isMakeDefault) {
        StringBuffer sb = new StringBuffer();
        
        if (include == null) {
            include = "";
        }
        
        // 셀렉트 박스 만들기
        sb.append("<select id=\""+boxName+"\" name=\""+boxName+"\" "+include+">");
        
        if (isMakeDefault) {
            
            if (selectDefault == null) {
                selectDefault = "";
            }
                        
            if (selectDefaultName == null) {
                selectDefaultName = "";
            }
            
            sb.append("<option value=\""+selectDefault+"\">"+selectDefaultName+"</option>");
        }
        
        if (dataList != null && dataList.size() > 0) {
            
            String [] selectedCodes = StringUtils.split(selected, ",");
            
            for (int i = 0;i < dataList.size();i++) {
                
                CommonCodeBean data = dataList.get(i);
                
                String code = data.getCommonCd();
                String name = data.getCommonNm();
                
                String selectCheck = "";
                
                if (selectedCodes != null) {
                    for (String selectedCode : selectedCodes) {
                        if (StringUtils.equals(code, selectedCode)) {
                            selectCheck = "selected=\"selected\"";
                            break;
                        }
                        
                    }
                }
                
                if (code.indexOf("\"") >= 0) {
                    sb.append("<option value='"+code+"' "+selectCheck+">"+name+"</option>");
                } else {
                    sb.append("<option value=\""+code+"\" "+selectCheck+">"+name+"</option>");
                }
                
            }
        }       
        sb.append("</select>");
        
        return sb.toString();
    }

    /**
     * 체크박스 태그를 작성한다.
     * 
     * @param boxName 체크박스명
     * @param dataList 체크박스의 데이터 리스트
     * @param selected 선택된 데이터 정보
     * @param include 테그에 포함될 내용 (style, js 등)
     * 
     * @return
     */
    public static String makeCheckBox(String boxName, List<CommonCodeBean> dataList, String selected, String include, String subInclude) {
        
        StringBuffer sb = new StringBuffer();
        
        if (include == null) {
            include = "";
        }
        
        sb.append("<ul " + include + ">");
        
        if (dataList != null) {
            
            String [] selectedCodes = StringUtils.split(selected, ",");
            
            for (int i = 0; i < dataList.size(); i++) {
                
                CommonCodeBean data = dataList.get(i);
                
                String code = data.getCommonCd();
                String name = data.getCommonNm();
                
                sb.append("<li>");
                sb.append("<input type=\"checkbox\" id=\"" + boxName + (i + 1) + "\" name=\"" + boxName + "\" ");
                
                sb.append("value=\"" + code + "\" ");
                
                if (StringUtils.isNotEmpty(subInclude)) {
                    sb.append(subInclude + " ");
                    subInclude = subInclude.replaceAll("valid\\s*=\\s*('|\")\\{.+\\}('|\")", "");
                }
                
                if (selectedCodes != null) {
                    for (String selectedCode : selectedCodes) {
                        if (StringUtils.equals(code, selectedCode)) {
                            sb.append("checked=\"checked\" ");
                            break;
                        }
                    }
                }
                
                sb.append("/>");
                
                sb.append("<label for=\"" + boxName + (i + 1) + "\">" + name + "</label>");
                
                sb.append("</li>");
                
            }
        }
        
        sb.append("</ul>");
        
        return sb.toString();
    }
    
    /**
     * 라디오버튼 태그를 작성한다.
     * 
     * @param boxName 라디오버튼명
     * @param dataList 라디오버튼의 데이터 리스트
     * @param selected 선택된 데이터 정보
     * @param include 테그에 포함될 내용 (style, js 등)
     *  
     * @return
     */
    public static String makeRadioBtn(String boxName, List<CommonCodeBean> dataList, String selected, String include, String subInclude) {
        
        StringBuffer sb = new StringBuffer();
        
        if (include == null) {
            include = "";
        }
        
        sb.append("<ul " + include + ">");
        
        if (dataList != null) {
            
            for (int i = 0; i < dataList.size(); i++) {
                
                CommonCodeBean data = dataList.get(i);
                
                String code = data.getCommonCd();
                String name = data.getCommonNm();
                
                sb.append("<li>");
                sb.append("<input type=\"radio\" id=\"" + boxName + (i + 1) + "\" name=\"" + boxName + "\" ");
                
                sb.append("value=\"" + code + "\" ");
                
                if (StringUtils.isNotEmpty(subInclude)) {
                    sb.append(subInclude + " ");
                    subInclude = subInclude.replaceAll("valid\\s*=\\s*('|\")\\{.+\\}('|\")", "");
                }
                
                if (StringUtils.equals(code, selected)) {
                    sb.append("checked=\"checked\" ");
                }
                
                sb.append("/>");
                
                sb.append("<label for=\"" + boxName + (i + 1) + "\">" + name + "</label>");
                
                sb.append("</li>");
                
            }
        }
        
        sb.append("</ul>");
        
        return sb.toString();
    }
}
