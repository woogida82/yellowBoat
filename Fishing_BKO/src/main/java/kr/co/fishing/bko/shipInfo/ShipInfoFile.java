package kr.co.fishing.bko.shipInfo;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import kr.co.fishing.bko.beans.ShipInfoBean;
import kr.co.fishing.bko.common.utils.DateUtil;
import net.coobird.thumbnailator.Thumbnails;


public class ShipInfoFile {
  
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
    
    public void saveFile(ShipInfoBean bean) throws Exception {
        if(bean.getUploadFileMain() != null && bean.getUploadFileMain().getSize() > 0)this.saveMainFile(bean);
        if(bean.getUploadFile01() != null && bean.getUploadFile01().getSize() > 0)this.save01File(bean);
        if(bean.getUploadFile02() != null && bean.getUploadFile02().getSize() > 0)this.save02File(bean);
        if(bean.getUploadFile03() != null && bean.getUploadFile03().getSize() > 0)this.save03File(bean);
        if(bean.getUploadFile04() != null && bean.getUploadFile04().getSize() > 0)this.save04File(bean);
        if(bean.getUploadFile05() != null && bean.getUploadFile05().getSize() > 0)this.save05File(bean);
        if(bean.getUploadFile06() != null && bean.getUploadFile06().getSize() > 0)this.save06File(bean);
        if(bean.getUploadFile07() != null && bean.getUploadFile07().getSize() > 0)this.save07File(bean);
        if(bean.getUploadFile08() != null && bean.getUploadFile08().getSize() > 0)this.save08File(bean);
        if(bean.getUploadFile09() != null && bean.getUploadFile09().getSize() > 0)this.save09File(bean);
        if(bean.getUploadFile10() != null && bean.getUploadFile10().getSize() > 0)this.save10File(bean);
    } 
    
    private void saveMainFile(ShipInfoBean bean) throws Exception {
        
        MultipartFile multipartFile = bean.getUploadFileMain();
        
        String fileName = multipartFile.getOriginalFilename().trim();
        String fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                
        String today = DateUtil.date2Str(new Date(), "yyyyMMddHHmmssSSS");
        String newFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_ORIGINAL + "_" + today + fileExt;
        
        String createDt = DateUtil.date2Str(new Date(), "yyyyMMddHH");
        
        // 파일 업로드 패스
        File uploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_ORIGINAL+"/"+createDt);
        
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        
        File saveFile = new File(uploadPath, newFileName);
        
        multipartFile.transferTo(saveFile);
        
        bean.setOrgMainImgPath(FILE_PATH+FILE_ORIGINAL+"/"+createDt+ "/" + newFileName);
        bean.setOrgMainImgNm(multipartFile.getOriginalFilename());
        
        
        /** 썸네일 */
        
        File thumnailUploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_RESIZED+"/"+createDt);
        
        if (!thumnailUploadPath.exists()) {
            thumnailUploadPath.mkdirs();
        }        
        
        String newThumnailFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_RESIZED + "_" + today + ".png";
        
        File thumnailSaveFile = new File(thumnailUploadPath, newThumnailFileName); 
        
        if (saveFile.exists()) { 
            thumnailSaveFile.getParentFile().mkdirs(); 
            Thumbnails.of(saveFile).size(190, 150).outputFormat("png").toFile(thumnailSaveFile); 
        }

        bean.setThumMainImgPath(FILE_PATH+FILE_RESIZED+"/"+createDt+ "/" + newThumnailFileName);
        bean.setThumMainImgSizeCd("00");
    }    
   
    private void save01File(ShipInfoBean bean) throws Exception {
        
        MultipartFile multipartFile = bean.getUploadFile01();
        
        String fileName = multipartFile.getOriginalFilename().trim();
        String fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                
        String today = DateUtil.date2Str(new Date(), "yyyyMMddHHmmssSSS");
        String newFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_ORIGINAL + "_" + today + fileExt;
        
        String createDt = DateUtil.date2Str(new Date(), "yyyyMMddHH");
        
        // 파일 업로드 패스
        File uploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_ORIGINAL+"/"+createDt);
        
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        
        File saveFile = new File(uploadPath, newFileName);
        
        multipartFile.transferTo(saveFile);
        
        bean.setOrg01ImgPath(FILE_PATH+FILE_ORIGINAL+"/"+createDt+ "/" + newFileName);
        bean.setOrg01ImgNm(multipartFile.getOriginalFilename());
        
        
        /** 썸네일 */
        
        File thumnailUploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_RESIZED+"/"+createDt);
        
        if (!thumnailUploadPath.exists()) {
            thumnailUploadPath.mkdirs();
        }        
        
        String newThumnailFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_RESIZED + "_" + today + ".png";
        
        File thumnailSaveFile = new File(thumnailUploadPath, newThumnailFileName); 
        
        if (saveFile.exists()) { 
            thumnailSaveFile.getParentFile().mkdirs(); 
            Thumbnails.of(saveFile).size(190, 150).outputFormat("png").toFile(thumnailSaveFile); 
        }

        bean.setThum01ImgPath(FILE_PATH+FILE_RESIZED+"/"+createDt+ "/" + newThumnailFileName);
        bean.setThum01ImgSizeCd("00");
    }    
    
    private void save02File(ShipInfoBean bean) throws Exception {
        
        MultipartFile multipartFile = bean.getUploadFile02();
        
        String fileName = multipartFile.getOriginalFilename().trim();
        String fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                
        String today = DateUtil.date2Str(new Date(), "yyyyMMddHHmmssSSS");
        String newFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_ORIGINAL + "_" + today + fileExt;
        
        String createDt = DateUtil.date2Str(new Date(), "yyyyMMddHH");
        
        // 파일 업로드 패스
        File uploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_ORIGINAL+"/"+createDt);
        
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        
        File saveFile = new File(uploadPath, newFileName);
        
        multipartFile.transferTo(saveFile);
        
        bean.setOrg02ImgPath(FILE_PATH+FILE_ORIGINAL+"/"+createDt+ "/" + newFileName);
        bean.setOrg02ImgNm(multipartFile.getOriginalFilename());
        
        
        /** 썸네일 */
        
        File thumnailUploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_RESIZED+"/"+createDt);
        
        if (!thumnailUploadPath.exists()) {
            thumnailUploadPath.mkdirs();
        }        
        
        String newThumnailFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_RESIZED + "_" + today + ".png";
        
        File thumnailSaveFile = new File(thumnailUploadPath, newThumnailFileName); 
        
        if (saveFile.exists()) { 
            thumnailSaveFile.getParentFile().mkdirs(); 
            Thumbnails.of(saveFile).size(190, 150).outputFormat("png").toFile(thumnailSaveFile); 
        }

        bean.setThum02ImgPath(FILE_PATH+FILE_RESIZED+"/"+createDt+ "/" + newThumnailFileName);
        bean.setThum02ImgSizeCd("00");
    }    
    
    private void save03File(ShipInfoBean bean) throws Exception {
        
        MultipartFile multipartFile = bean.getUploadFile03();
        
        String fileName = multipartFile.getOriginalFilename().trim();
        String fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                
        String today = DateUtil.date2Str(new Date(), "yyyyMMddHHmmssSSS");
        String newFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_ORIGINAL + "_" + today + fileExt;
        
        String createDt = DateUtil.date2Str(new Date(), "yyyyMMddHH");
        
        // 파일 업로드 패스
        File uploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_ORIGINAL+"/"+createDt);
        
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        
        File saveFile = new File(uploadPath, newFileName);
        
        multipartFile.transferTo(saveFile);
        
        bean.setOrg03ImgPath(FILE_PATH+FILE_ORIGINAL+"/"+createDt+ "/" + newFileName);
        bean.setOrg03ImgNm(multipartFile.getOriginalFilename());
        
        
        /** 썸네일 */
        
        File thumnailUploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_RESIZED+"/"+createDt);
        
        if (!thumnailUploadPath.exists()) {
            thumnailUploadPath.mkdirs();
        }        
        
        String newThumnailFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_RESIZED + "_" + today + ".png";
        
        File thumnailSaveFile = new File(thumnailUploadPath, newThumnailFileName); 
        
        if (saveFile.exists()) { 
            thumnailSaveFile.getParentFile().mkdirs(); 
            Thumbnails.of(saveFile).size(190, 150).outputFormat("png").toFile(thumnailSaveFile); 
        }

        bean.setThum03ImgPath(FILE_PATH+FILE_RESIZED+"/"+createDt+ "/" + newThumnailFileName);
        bean.setThum03ImgSizeCd("00");
    }    
    
    private void save04File(ShipInfoBean bean) throws Exception {
        
        MultipartFile multipartFile = bean.getUploadFile04();
        
        String fileName = multipartFile.getOriginalFilename().trim();
        String fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                
        String today = DateUtil.date2Str(new Date(), "yyyyMMddHHmmssSSS");
        String newFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_ORIGINAL + "_" + today + fileExt;
        
        String createDt = DateUtil.date2Str(new Date(), "yyyyMMddHH");
        
        // 파일 업로드 패스
        File uploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_ORIGINAL+"/"+createDt);
        
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        
        File saveFile = new File(uploadPath, newFileName);
        
        multipartFile.transferTo(saveFile);
        
        bean.setOrg04ImgPath(FILE_PATH+FILE_ORIGINAL+"/"+createDt+ "/" + newFileName);
        bean.setOrg04ImgNm(multipartFile.getOriginalFilename());
        
        
        /** 썸네일 */
        
        File thumnailUploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_RESIZED+"/"+createDt);
        
        if (!thumnailUploadPath.exists()) {
            thumnailUploadPath.mkdirs();
        }        
        
        String newThumnailFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_RESIZED + "_" + today + ".png";
        
        File thumnailSaveFile = new File(thumnailUploadPath, newThumnailFileName); 
        
        if (saveFile.exists()) { 
            thumnailSaveFile.getParentFile().mkdirs(); 
            Thumbnails.of(saveFile).size(190, 150).outputFormat("png").toFile(thumnailSaveFile); 
        }

        bean.setThum04ImgPath(FILE_PATH+FILE_RESIZED+"/"+createDt+ "/" + newThumnailFileName);
        bean.setThum04ImgSizeCd("00");
    }    
    
    private void save05File(ShipInfoBean bean) throws Exception {
        
        MultipartFile multipartFile = bean.getUploadFile05();
        
        String fileName = multipartFile.getOriginalFilename().trim();
        String fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                
        String today = DateUtil.date2Str(new Date(), "yyyyMMddHHmmssSSS");
        String newFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_ORIGINAL + "_" + today + fileExt;
        
        String createDt = DateUtil.date2Str(new Date(), "yyyyMMddHH");
        
        // 파일 업로드 패스
        File uploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_ORIGINAL+"/"+createDt);
        
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        
        File saveFile = new File(uploadPath, newFileName);
        
        multipartFile.transferTo(saveFile);
        
        bean.setOrg05ImgPath(FILE_PATH+FILE_ORIGINAL+"/"+createDt+ "/" + newFileName);
        bean.setOrg05ImgNm(multipartFile.getOriginalFilename());
        
        
        /** 썸네일 */
        
        File thumnailUploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_RESIZED+"/"+createDt);
        
        if (!thumnailUploadPath.exists()) {
            thumnailUploadPath.mkdirs();
        }        
        
        String newThumnailFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_RESIZED + "_" + today + ".png";
        
        File thumnailSaveFile = new File(thumnailUploadPath, newThumnailFileName); 
        
        if (saveFile.exists()) { 
            thumnailSaveFile.getParentFile().mkdirs(); 
            Thumbnails.of(saveFile).size(190, 150).outputFormat("png").toFile(thumnailSaveFile); 
        }

        bean.setThum05ImgPath(FILE_PATH+FILE_RESIZED+"/"+createDt+ "/" + newThumnailFileName);
        bean.setThum05ImgSizeCd("00");
    }    
    
    private void save06File(ShipInfoBean bean) throws Exception {
        
        MultipartFile multipartFile = bean.getUploadFile06();
        
        String fileName = multipartFile.getOriginalFilename().trim();
        String fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                
        String today = DateUtil.date2Str(new Date(), "yyyyMMddHHmmssSSS");
        String newFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_ORIGINAL + "_" + today + fileExt;
        
        String createDt = DateUtil.date2Str(new Date(), "yyyyMMddHH");
        
        // 파일 업로드 패스
        File uploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_ORIGINAL+"/"+createDt);
        
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        
        File saveFile = new File(uploadPath, newFileName);
        
        multipartFile.transferTo(saveFile);
        
        bean.setOrg06ImgPath(FILE_PATH+FILE_ORIGINAL+"/"+createDt+ "/" + newFileName);
        bean.setOrg06ImgNm(multipartFile.getOriginalFilename());
        
        
        /** 썸네일 */
        
        File thumnailUploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_RESIZED+"/"+createDt);
        
        if (!thumnailUploadPath.exists()) {
            thumnailUploadPath.mkdirs();
        }        
        
        String newThumnailFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_RESIZED + "_" + today + ".png";
        
        File thumnailSaveFile = new File(thumnailUploadPath, newThumnailFileName); 
        
        if (saveFile.exists()) { 
            thumnailSaveFile.getParentFile().mkdirs(); 
            Thumbnails.of(saveFile).size(190, 150).outputFormat("png").toFile(thumnailSaveFile); 
        }

        bean.setThum06ImgPath(FILE_PATH+FILE_RESIZED+"/"+createDt+ "/" + newThumnailFileName);
        bean.setThum06ImgSizeCd("00");
    }    
    
    private void save07File(ShipInfoBean bean) throws Exception {
        
        MultipartFile multipartFile = bean.getUploadFile07();
        
        String fileName = multipartFile.getOriginalFilename().trim();
        String fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                
        String today = DateUtil.date2Str(new Date(), "yyyyMMddHHmmssSSS");
        String newFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_ORIGINAL + "_" + today + fileExt;
        
        String createDt = DateUtil.date2Str(new Date(), "yyyyMMddHH");
        
        // 파일 업로드 패스
        File uploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_ORIGINAL+"/"+createDt);
        
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        
        File saveFile = new File(uploadPath, newFileName);
        
        multipartFile.transferTo(saveFile);
        
        bean.setOrg07ImgPath(FILE_PATH+FILE_ORIGINAL+"/"+createDt+ "/" + newFileName);
        bean.setOrg07ImgNm(multipartFile.getOriginalFilename());
        
        
        /** 썸네일 */
        
        File thumnailUploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_RESIZED+"/"+createDt);
        
        if (!thumnailUploadPath.exists()) {
            thumnailUploadPath.mkdirs();
        }        
        
        String newThumnailFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_RESIZED + "_" + today + ".png";
        
        File thumnailSaveFile = new File(thumnailUploadPath, newThumnailFileName); 
        
        if (saveFile.exists()) { 
            thumnailSaveFile.getParentFile().mkdirs(); 
            Thumbnails.of(saveFile).size(190, 150).outputFormat("png").toFile(thumnailSaveFile); 
        }

        bean.setThum07ImgPath(FILE_PATH+FILE_RESIZED+"/"+createDt+ "/" + newThumnailFileName);
        bean.setThum07ImgSizeCd("00");
    }    
    
    private void save08File(ShipInfoBean bean) throws Exception {
        
        MultipartFile multipartFile = bean.getUploadFile08();
        
        String fileName = multipartFile.getOriginalFilename().trim();
        String fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                
        String today = DateUtil.date2Str(new Date(), "yyyyMMddHHmmssSSS");
        String newFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_ORIGINAL + "_" + today + fileExt;
        
        String createDt = DateUtil.date2Str(new Date(), "yyyyMMddHH");
        
        // 파일 업로드 패스
        File uploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_ORIGINAL+"/"+createDt);
        
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        
        File saveFile = new File(uploadPath, newFileName);
        
        multipartFile.transferTo(saveFile);
        
        bean.setOrg08ImgPath(FILE_PATH+FILE_ORIGINAL+"/"+createDt+ "/" + newFileName);
        bean.setOrg08ImgNm(multipartFile.getOriginalFilename());
        
        
        /** 썸네일 */
        
        File thumnailUploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_RESIZED+"/"+createDt);
        
        if (!thumnailUploadPath.exists()) {
            thumnailUploadPath.mkdirs();
        }        
        
        String newThumnailFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_RESIZED + "_" + today + ".png";
        
        File thumnailSaveFile = new File(thumnailUploadPath, newThumnailFileName); 
        
        if (saveFile.exists()) { 
            thumnailSaveFile.getParentFile().mkdirs(); 
            Thumbnails.of(saveFile).size(190, 150).outputFormat("png").toFile(thumnailSaveFile); 
        }

        bean.setThum08ImgPath(FILE_PATH+FILE_RESIZED+"/"+createDt+ "/" + newThumnailFileName);
        bean.setThum08ImgSizeCd("00");
    }    
    
    private void save09File(ShipInfoBean bean) throws Exception {
        
        MultipartFile multipartFile = bean.getUploadFile09();
        
        String fileName = multipartFile.getOriginalFilename().trim();
        String fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                
        String today = DateUtil.date2Str(new Date(), "yyyyMMddHHmmssSSS");
        String newFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_ORIGINAL + "_" + today + fileExt;
        
        String createDt = DateUtil.date2Str(new Date(), "yyyyMMddHH");
        
        // 파일 업로드 패스
        File uploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_ORIGINAL+"/"+createDt);
        
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        
        File saveFile = new File(uploadPath, newFileName);
        
        multipartFile.transferTo(saveFile);
        
        bean.setOrg09ImgPath(FILE_PATH+FILE_ORIGINAL+"/"+createDt+ "/" + newFileName);
        bean.setOrg09ImgNm(multipartFile.getOriginalFilename());
        
        
        /** 썸네일 */
        
        File thumnailUploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_RESIZED+"/"+createDt);
        
        if (!thumnailUploadPath.exists()) {
            thumnailUploadPath.mkdirs();
        }        
        
        String newThumnailFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_RESIZED + "_" + today + ".png";
        
        File thumnailSaveFile = new File(thumnailUploadPath, newThumnailFileName); 
        
        if (saveFile.exists()) { 
            thumnailSaveFile.getParentFile().mkdirs(); 
            Thumbnails.of(saveFile).size(190, 150).outputFormat("png").toFile(thumnailSaveFile); 
        }

        bean.setThum09ImgPath(FILE_PATH+FILE_RESIZED+"/"+createDt+ "/" + newThumnailFileName);
        bean.setThum09ImgSizeCd("00");
    }   
    
    private void save10File(ShipInfoBean bean) throws Exception {
        
        MultipartFile multipartFile = bean.getUploadFile10();
        
        String fileName = multipartFile.getOriginalFilename().trim();
        String fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                
        String today = DateUtil.date2Str(new Date(), "yyyyMMddHHmmssSSS");
        String newFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_ORIGINAL + "_" + today + fileExt;
        
        String createDt = DateUtil.date2Str(new Date(), "yyyyMMddHH");
        
        // 파일 업로드 패스
        File uploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_ORIGINAL+"/"+createDt);
        
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        
        File saveFile = new File(uploadPath, newFileName);
        
        multipartFile.transferTo(saveFile);
        
        bean.setOrg10ImgPath(FILE_PATH+FILE_ORIGINAL+"/"+createDt+ "/" + newFileName);
        bean.setOrg10ImgNm(multipartFile.getOriginalFilename());
        
        
        /** 썸네일 */
        
        File thumnailUploadPath = new File(FILE_ROOT_PATH+FILE_PATH+FILE_RESIZED+"/"+createDt);
        
        if (!thumnailUploadPath.exists()) {
            thumnailUploadPath.mkdirs();
        }        
        
        String newThumnailFileName = bean.getShipId() + "_" + FILE_PREFIX + "_" + GROUP_RESIZED + "_" + today + ".png";
        
        File thumnailSaveFile = new File(thumnailUploadPath, newThumnailFileName); 
        
        if (saveFile.exists()) { 
            thumnailSaveFile.getParentFile().mkdirs(); 
            Thumbnails.of(saveFile).size(190, 150).outputFormat("png").toFile(thumnailSaveFile); 
        }

        bean.setThum10ImgPath(FILE_PATH+FILE_RESIZED+"/"+createDt+ "/" + newThumnailFileName);
        bean.setThum10ImgSizeCd("00");
    }    
}
