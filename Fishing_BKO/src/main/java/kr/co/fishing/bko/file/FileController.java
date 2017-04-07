package kr.co.fishing.bko.file;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.fishing.bko.beans.FileBean;
import kr.co.fishing.bko.common.beans.AdminBean;
import kr.co.fishing.bko.common.utils.CommonConstant.AJAX_RESULT;
import kr.co.fishing.bko.common.utils.CommonConstant.SESSION_KEY;
import kr.co.fishing.bko.common.utils.DateUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/bko/file")
public class FileController {
    
    /** 파일 루트 패스 */
    @Value("#{config['web.root']}") 
    private String FILE_ROOT_PATH;
    
    /** 이미지 파일패스 */
    @Value("#{config['image.upload']}") 
    private String FILE_PATH;
    
    /** 이미지 업로드(원본) */
    @Value("#{config['image.original']}") 
    private String FILE_ORIGINAL;
    
    /** 이미지 업로드(리싸이즈) */
    @Value("#{config['image.resized']}") 
    private String FILE_RESIZED;
    
    /** 파일 구분 */
    @Value("#{config['file.nm']}") 
    private String FILE_PREFIX;
    
    /** 구분 원본 */
    @Value("#{config['group.original']}") 
    private String GROUP_ORIGINAL;
    
    /** 구분 리싸이즈 */
    @Value("#{config['group.resized']}") 
    private String GROUP_RESIZED;    
    
    @Autowired
    private FileService fileService;
    
    @RequestMapping("")
    public String pdf(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        
        return "file/fileList";
    }
    
    @ResponseBody
    @RequestMapping("/fileList")
    public Map<String,Object> fileListPaging(HttpServletRequest request, HttpServletResponse response, @ModelAttribute FileBean bean) throws Exception {
        
        Map<String,Object> resultMap = new HashMap<String,Object>();
        
        try {
            
            int resultCnt = fileService.selectFileListCnt(bean);
            
            if(resultCnt > 0){
                List<FileBean> resultList = fileService.selectFileList(bean);
                resultMap.put("rows", resultList);
                resultMap.put("records", resultCnt);
            }
            
            resultMap.put("result", AJAX_RESULT.OK);
            
        } catch(Exception e) {
            
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }
        
        return resultMap;
    }
    
    @ResponseBody
    @RequestMapping("/selectFile")
    public Map<String,Object> selectFile(HttpServletRequest request, HttpServletResponse response, @ModelAttribute FileBean bean) throws Exception {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        
        try {
            
            resultMap.put("fileInfo", fileService.selectFile(bean));
            resultMap.put("result", AJAX_RESULT.OK);
            
        } catch(Exception e) {
            
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }
        return resultMap;
    }
    
    private void saveFile(FileBean bean) throws Exception {
        
        MultipartFile multipartFile = bean.getUploadFile();
        
        String fileName = multipartFile.getOriginalFilename().trim();
        String fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                
        String today = DateUtil.date2Str(new Date(), "yyyyMMddHHmmssSSS");
        String newFileName = bean.getEventId() + "_" + FILE_PREFIX + "_" + GROUP_ORIGINAL + "_" + today + fileExt;
        
        String createDt = DateUtil.date2Str(new Date(), "yyyyMMddHH");
        
        // 파일 업로드 패스
        File uploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_ORIGINAL+"/"+createDt);
        
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        
        File saveFile = new File(uploadPath, newFileName);
        
        multipartFile.transferTo(saveFile);
        
        bean.setOrgFilePath(FILE_PATH+FILE_ORIGINAL+"/"+createDt+ "/" + newFileName);
        bean.setOrgFileNm(multipartFile.getOriginalFilename());
        
        
        /** 썸네일 */
        
        File thumnailUploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_RESIZED+"/"+createDt);
        
        if (!thumnailUploadPath.exists()) {
            thumnailUploadPath.mkdirs();
        }        
        
        String newThumnailFileName = bean.getEventId() + "_" + FILE_PREFIX + "_" + GROUP_RESIZED + "_" + today + ".png";
        
        File thumnailSaveFile = new File(thumnailUploadPath, newThumnailFileName); 
        
        if (saveFile.exists()) { 
            thumnailSaveFile.getParentFile().mkdirs(); 
            Thumbnails.of(saveFile).size(190, 150).outputFormat("png").toFile(thumnailSaveFile); 
        }

        bean.setThumFilePath(FILE_PATH+FILE_RESIZED+"/"+createDt+ "/" + newThumnailFileName);
        bean.setThumFileSizeCd("00");
    }
    
    @RequestMapping("/insertFile")
    public void insertFile(HttpServletRequest request, HttpServletResponse response, @ModelAttribute FileBean bean) throws Exception {

        JSONObject jsonObj = new JSONObject();
        
        try {
            
            if (bean.getUploadFile() != null && bean.getUploadFile().getSize() > 0) {
                // 파일업로드 처리
                saveFile(bean);
            }
            
            fileService.insertFile(bean);
            jsonObj.put("result", AJAX_RESULT.OK);
            
        } catch(Exception e) {
            
            jsonObj.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }
        
        response.getWriter().write(jsonObj.toString());
    }
    
    @RequestMapping("/updateFile")
    public void updateFile(HttpServletRequest request, HttpServletResponse response, @ModelAttribute FileBean bean) throws Exception {
        JSONObject jsonObj = new JSONObject();
        
        try {
            
            if (bean.getUploadFile() != null && bean.getUploadFile().getSize() > 0) {
                // 파일업로드 처리
                saveFile(bean);
            }
            
            fileService.updateFile(bean);
            jsonObj.put("result", AJAX_RESULT.OK);
            
        } catch(Exception e) {
            
            jsonObj.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }
        
        response.getWriter().write(jsonObj.toString());
    }
    
    @ResponseBody
    @RequestMapping("/deleteFile")
    public Map<String,Object> deleteFile(HttpServletRequest request, HttpServletResponse response, @ModelAttribute FileBean bean) throws Exception {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        
        try {
            
            fileService.deleteFile(bean);
            resultMap.put("result", AJAX_RESULT.OK);
            
        } catch(Exception e) {
            
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }
        
        return resultMap;
    }
    
    @ResponseBody
    @RequestMapping("/updateFileStatus")
    public Map<String,Object> updateFileStatus(HttpServletRequest request, HttpServletResponse response, 
                                              @RequestParam(value="idxs[]") String[] idxs, 
                                              @RequestParam(value="status") String status) throws Exception {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        
        try {
            AdminBean adminBean = (AdminBean) request.getSession().getAttribute(SESSION_KEY.ADMIN);
            FileBean bean = new FileBean();
            
            if(idxs != null && idxs.length > 0) {
                bean.setIdxs(idxs);
                bean.setStatus(status);
                bean.setAdminBean(adminBean);
            }
            
            fileService.updateFileStatus(bean);
            resultMap.put("result", AJAX_RESULT.OK);
            
        } catch(Exception e) {
            
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }
        
        return resultMap;
    }
}
