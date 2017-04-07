package kr.co.fishing.bko.common.beans;


public class CommonFileBean {
    
    /** 파일 상대 패스 */
    private String filePath;
    /** 파일 원본 명칭 */
    private String fileNm;
    /** 파일 구분 (0: 웹영역, 1: 파일영역)*/
    private String fileType;
    
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getFileNm() {
        return fileNm;
    }
    public void setFileNm(String fileNm) {
        this.fileNm = fileNm;
    }
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    
    
}
