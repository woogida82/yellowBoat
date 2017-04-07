package kr.co.fishing.bko.file;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.fishing.bko.beans.FileBean;

@Service
public class FileServiceImpl implements FileService {
    
    @Autowired
    private FileDao fileDao;
    
    public int selectFileListCnt(FileBean bean) throws Exception{
        return fileDao.selectFileListCnt(bean);
    }
    
    public List<FileBean> selectFileList(FileBean bean) throws Exception{
        return fileDao.selectFileList(bean);
    }
    
    public FileBean selectFile(FileBean bean) throws Exception{
        return fileDao.selectFile(bean);
    }
    
    public int insertFile(FileBean bean) throws Exception{
        return fileDao.insertFile(bean);
    }
    
    public int updateFile(FileBean bean) throws Exception{
        return fileDao.updateFile(bean);
    }
    
    public void deleteFile(FileBean bean) throws Exception{
        fileDao.deleteFile(bean);
    }
    
    public int updateFileStatus(FileBean bean) throws Exception{
        return fileDao.updateFileStatus(bean);
    }
}
