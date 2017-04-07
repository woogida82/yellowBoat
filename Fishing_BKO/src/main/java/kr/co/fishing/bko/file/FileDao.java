package kr.co.fishing.bko.file;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.fishing.bko.beans.FileBean;

@Repository
public class FileDao {
    
    @Autowired
    private SqlSession sqlSession;
    
    public int selectFileListCnt(FileBean bean) throws Exception{
        return sqlSession.selectOne("file.selectFileListCnt", bean);
    }
    
    public List<FileBean> selectFileList(FileBean bean) throws Exception{
        return sqlSession.selectList("file.selectFileList", bean);
    }
    
    public FileBean selectFile(FileBean bean) throws Exception{
        return sqlSession.selectOne("file.selectFile", bean);
    }
    
    public int insertFile(FileBean bean) throws Exception{
        return sqlSession.insert("file.insertFile", bean);
    }
    
    public int updateFile(FileBean bean) throws Exception{
        return sqlSession.update("file.updateFile", bean);
    }
    
    public int deleteFile(FileBean bean) throws Exception{
        return sqlSession.delete("file.deleteFile", bean);
    }
    
    public int updateFileStatus(FileBean bean) throws Exception{
        return sqlSession.update("file.updateFileStatus",bean);
    }
}
