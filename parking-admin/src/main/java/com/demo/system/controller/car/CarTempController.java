package com.demo.system.controller.car;

import com.demo.common.res.ResultResponse;
import com.demo.common.utils.Constants;
import com.demo.common.utils.DateUtil;
import com.demo.system.entity.ParkingRecord;
import com.demo.system.entity.ParkingTemp;
import com.demo.system.service.IRecordService;
import com.demo.system.service.ITempService;
import com.demo.system.vo.VoPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @Author Sheva
 * @Date 2021/3/24
 */
@Slf4j
@RestController
public class CarTempController {

    @Autowired
    private ITempService tempService;

    @Autowired
    private IRecordService recordService;

    @GetMapping("car/parking")
    public ResultResponse parkingList(@RequestParam("page") int pageNo,
                                      @RequestParam("limit") int limit){
        ResultResponse res = null;
        VoPage page = this.tempService.selectTemps(pageNo - 1, limit);
        log.info("查询到的分页信息：" + page.toString());
        res = new ResultResponse(Constants.STATUS_OK, Constants.MESSAGE_OK, page);
        return res;
    }

    @PostMapping("/car/temp/add")
    public ResultResponse addTemp(@RequestBody ParkingTemp temp){
        ResultResponse res = new ResultResponse();
        tempService.addTemp(temp);
        res.setCode(Constants.STATUS_OK);
        res.setMessage(Constants.MESSAGE_OK);
        return res;
    }
    @PostMapping("/car/temp/delete/{id}")
    public ResultResponse deleteTemp(@PathVariable Long id){
        ResultResponse res = new ResultResponse();
        if(this.tempService.deleteTempById(id) == 1){
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
        }else{
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL + "删除停车临时记录失败...");
        }
        return res;
    }

    @PostMapping("/car/unpark/{id}")
    public ResultResponse unpark(@PathVariable Long id){
        ResultResponse res = new ResultResponse();
        ParkingTemp temp = tempService.selectTempById(id).get();
        long minutes = DateUtil.calculateTimeDif(temp.getCreateTime().toString());
        log.info("停车时间总共为：" + minutes + "分钟...");
        if (minutes == -1){
            res.setMessage(Constants.MESSAGE_FAIL + "车辆驶出失败...");
            res.setCode(Constants.STATUS_FAIL);
        }else{
            BigDecimal timeDecimal = new BigDecimal(minutes);
            BigDecimal kDecimal = new BigDecimal(Constants.COST_PER_MINUTE);
            BigDecimal cost = minutes == 0 ? kDecimal : timeDecimal.multiply(kDecimal);
            ParkingRecord record = new ParkingRecord(temp.getPlateNumber(), temp.getColor(),
                    temp.getCreateTime(), cost, temp.getFileUrl());
            recordService.addRecord(record);
            if (tempService.deleteTempById(temp.getId()) == 0){
                res.setMessage(Constants.MESSAGE_FAIL + "车辆临时记录删除失败...");
                res.setCode(Constants.STATUS_FAIL);
            }else{
                res.setMessage(Constants.MESSAGE_OK);
                res.setCode(Constants.STATUS_OK);
            }
        }
        return res;
    }

    @PostMapping("/car/temp/update")
    public ResultResponse updateTemp(@RequestBody ParkingTemp temp){
        ResultResponse res = new ResultResponse();
        log.info("接收到的temp更新信息为：" + temp.toString());
        try{
            tempService.updateTemp(temp);
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
        }catch (Exception e){
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL + e.getMessage());
        }
        return res;

    }

}
