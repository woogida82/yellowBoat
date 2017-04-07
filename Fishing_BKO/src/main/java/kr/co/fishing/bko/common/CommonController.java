package kr.co.fishing.bko.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.fishing.bko.beans.MenuBean;
import kr.co.fishing.bko.common.beans.CommonFileBean;
import kr.co.fishing.bko.common.utils.CommonConstant.AJAX_RESULT;
import kr.co.fishing.bko.common.utils.ContextUtil;
import kr.co.fishing.bko.common.utils.DateUtil;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/bko/common")
public class CommonController {
    
    /** 웹콘텐츠 패스 */
    @Value("#{config['web.root']}") 
    private String WEB_PATH;
    
    /** 이미지 업로드 패스 */
    @Value("#{config['image.upload']}") 
    private String IMG_UPLOAD_PATH;
    
    /** CDN 도메인 */
    @Value("#{config['cdn.domain.name']}") 
    private String CDN_DOMAIN_NAME;
    
    /** 파일 루트 패스 */
    @Value("#{config['file.root']}") 
    private String FILE_ROOT_PATH;
    
    @Autowired
    private CommonService commonService;
    
    @Autowired
    private ContextUtil contextUtil;
	
	/**
	 * 세션체크용
	 * AJAX로 파일 업로드를 포함한 FormSubmit 처리시 사용한다.
	 * 
	 * @param request
	 * @param response
	 * @param menuBean
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
    @RequestMapping("/checkSession")
    public Map<String,Object> checkSession(HttpServletRequest request, HttpServletResponse response, 
    		@ModelAttribute MenuBean menuBean) throws Exception {

        Map<String,Object> resultMap = new HashMap<String,Object>();

        try {
            resultMap.put("result", AJAX_RESULT.OK);
        } catch(Exception e) {
            resultMap.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }

        return resultMap;
    }
	
	/**
	 * AJAX 파일 업로드 처리
	 * 
	 * @param request
	 * @param response
	 * @param multipartFile
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/ajaxFileUpload")
    public void doFileUpload(HttpServletRequest request, HttpServletResponse response, 
            @RequestParam("upload") MultipartFile multipartFile, @RequestParam("saveId") String saveId,
            @RequestParam(value="resizeSize", defaultValue="1000") int resizeSize,
            @RequestParam(value="CKEditorFuncNum", required=false) String ckEditorFuncNum) throws Exception {
	    
        JSONObject jsonObj = new JSONObject();

        try {
            
            if (StringUtils.isNotEmpty(saveId)) {
                saveId = saveId.replaceAll("\\.\\./", "");
            }
            
            if (!saveId.startsWith("/")) {
                saveId = "/" + saveId;
            }
            
            String fileName = multipartFile.getOriginalFilename().trim();
            String fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
            
            String today = DateUtil.date2Str(new Date(), "yyyyMMdd");
            String newFileName = String.valueOf(System.nanoTime()) + fileExt;
            
            // 이미지 업로드 기본 패스
            File uploadPath = new File(WEB_PATH, IMG_UPLOAD_PATH);
            
            File savePath = new File(new File(uploadPath, saveId), today);
            if (!savePath.exists()) {
                savePath.mkdirs();
            }
            
            File saveFile = new File(savePath, newFileName);
            
            if (".png".equals(fileExt) || ".jpg".equals(fileExt) || ".gif".equals(fileExt) || ".bmp".equals(fileExt)) {
                // 이미지 사이즈 취득
                BufferedImage bimg = ImageIO.read(multipartFile.getInputStream());
                int width = bimg.getWidth();
                int height = bimg.getHeight();
                
                // 업로드한 이미지의 width가 1000을 넘었을시, MAX width 를 1000으로 리사이징 한다. 
                if (width > resizeSize) {
                    // width를 1000으로 설정하면 height는 자동 조정 된다.
                    BufferedImage img = ImageIO.read(multipartFile.getInputStream());
                    BufferedImage thumbImg = Scalr.resize(img, Scalr.Mode.FIT_TO_WIDTH, resizeSize, height, Scalr.OP_ANTIALIAS);
                    ImageIO.write(thumbImg, "png", saveFile);
                    
                    bimg = ImageIO.read(saveFile);
                    width = bimg.getWidth();
                    height = bimg.getHeight();
                } else {
                    multipartFile.transferTo(saveFile);
                }
                
                jsonObj.put("imgWidth", width);
                jsonObj.put("imgHeight", height);
            } else {
                multipartFile.transferTo(saveFile);
            }
            
            DecimalFormat df = new DecimalFormat("#,###.##");
            String kbytes = "0";
            if (multipartFile.getSize() > 0) {
                kbytes = df.format(multipartFile.getSize() / 1024);
            }
            
            jsonObj.put("filePath", IMG_UPLOAD_PATH + saveId + "/" + today + "/" + newFileName);
            jsonObj.put("dispFilePath", CDN_DOMAIN_NAME + jsonObj.get("filePath"));
            jsonObj.put("fileName", fileName);
            jsonObj.put("fileSize", kbytes);
            
            jsonObj.put("result", AJAX_RESULT.OK);
        } catch(Exception e) {
            jsonObj.put("result", AJAX_RESULT.NG);
            e.printStackTrace();
        }
        
        // 일반적인 ajax 업로드인 경우
        if (StringUtils.isEmpty(ckEditorFuncNum)) {
            response.getWriter().write(jsonObj.toString());
        } 
        // ck에디터 업로드인 경우
        else {
            
            String callback = "<script type='text/javascript'>";
            callback += "window.parent.CKEDITOR.tools.callFunction(" + ckEditorFuncNum + ",'" + jsonObj.get("dispFilePath") + "','업로드 완료');";
            callback += "</script>";
            
            response.getWriter().write(callback);
        }

        
    }
    
    /**
     * AJAX 파일 다운로드 처리
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/ajaxFileDownload")
    public void doFileDownload(HttpServletRequest request, HttpServletResponse response, 
            @RequestParam("downId") String downId, @RequestParam Map<String, String> paramMap) throws Exception {

        OutputStream out = null;
        FileInputStream fis = null;

        try {
            
            CommonFileBean fileBean = commonService.selectFileInfo("T_FILE_" + downId, paramMap);
            
            String fileOrigName = fileBean.getFileNm();
            fileOrigName = fileOrigName.replaceAll(",", "");
            
            File file = new File(FILE_ROOT_PATH, fileBean.getFilePath());
            
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileOrigName.getBytes("KSC5601"),"8859_1"));
            response.setHeader("Content-Transfer-Encoding", "binary");
            out = response.getOutputStream();
            
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
             
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
            if (fis != null) {
                fis.close();
            }
            
            if (out != null) {
                out.close();
            }
        }
    }
    
}
