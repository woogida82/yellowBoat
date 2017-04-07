package kr.co.fishing.bko.file;

import java.util.List;

import kr.co.fishing.bko.beans.FileBean;

public interface FileService {

    int selectFileListCnt(FileBean bean) throws Exception;
    
    List<FileBean> selectFileList(FileBean bean) throws Exception;

    FileBean selectFile(FileBean bean) throws Exception;
    
    int insertFile(FileBean bean) throws Exception;
    
    int updateFile(FileBean bean) throws Exception;
    
    void deleteFile(FileBean bean) throws Exception;
    
    int updateFileStatus(FileBean bean) throws Exception;
}
