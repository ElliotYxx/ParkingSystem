package com.demo.system.controller.car;

import com.demo.common.utils.Constants;
import com.demo.common.utils.ImageUtil;
import com.demo.common.utils.PyUtil;
import com.demo.system.entity.ParkingTemp;
import com.demo.system.service.ITempService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Author Sheva
 * @Date 2021/3/13
 */
@Slf4j
@RestController
public class CarFileController {

    @Autowired
    private ITempService tempService;

    /**
     * 文件上传测试控制类
     * @param file 文件
     * @return 给前端的响应信息
     */
    @PostMapping("/file/receive")
    public ResponseBody fileReceive(@RequestParam("file") MultipartFile file){
        log.info("传入的文件名为： " + file.getOriginalFilename());
        String filePath = ImageUtil.saveFile(file);
        // 接收到文件后直接对车牌进行识别
        Map<String, String> result = PyUtil.recognizePlate(filePath);
        ParkingTemp parkingTemp = new ParkingTemp();
        if (result.get("number") != null){
            parkingTemp.setPlateNumber(result.get("number"));
            parkingTemp.setColor(result.get("color"));
        }else{
            parkingTemp.setPlateNumber("null");
            parkingTemp.setColor("null");
        }
        parkingTemp.setFileUrl(filePath);
        tempService.addTemp(parkingTemp);
        return null;
    }
}
