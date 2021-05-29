package com.demo.system.controller.system;

import com.demo.common.res.ResultResponse;
import com.demo.common.utils.Constants;
import com.demo.system.entity.ParkingRecord;
import com.demo.system.service.IRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Sheva
 * @Date 2021/5/7
 */
@Slf4j
@RestController
public class SysIndexController {

    @Autowired
    private IRecordService recordService;

    @GetMapping("/index/record")
    public ResultResponse parkingRecordList(){
        List<ParkingRecord> recordList = this.recordService.getParkingRecord();
        return new ResultResponse(Constants.STATUS_OK, Constants.MESSAGE_OK, recordList);
    }
}
