package com.demo.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 图片相关工具类
 * @Author Sheva
 * @Date 2021/3/13
 */
@Slf4j
public class ImageUtil {

    /**
     * 保存前端测试上传的车辆图像信息
     * @param file 上传文件
     * @return 返回图像的保存路径
     */
    public static String saveFile(MultipartFile file){
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String fileName = file.getOriginalFilename();
        int lastIndex = fileName.lastIndexOf(".");
        String suffix = fileName.substring(lastIndex);
        try{
            String md5 = DigestUtils.md5DigestAsHex(file.getBytes());
            byte[] bs = new byte[1024];
            int len;
            File tempFile = new File(Constants.IMAGE_SAVE_PATH);
            if (!tempFile.exists()){
                tempFile.mkdirs();
            }
            String filePath = tempFile.getPath() + File.separator + md5 + suffix;
            inputStream = file.getInputStream();
            outputStream = new FileOutputStream(filePath);
            while((len = inputStream.read(bs)) != -1){
                outputStream.write(bs, 0, len);
            }

            log.info("文件保存成功...");
            return filePath;
        }catch (Exception e){
            log.error(e.getMessage());
        }finally {
            try{
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            }catch (Exception ioe){
                log.error(ioe.getMessage());
            }
        }
        return null;
    }
}
