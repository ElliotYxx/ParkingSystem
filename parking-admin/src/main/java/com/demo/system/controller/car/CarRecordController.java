package com.demo.system.controller.car;

import com.demo.common.res.ResultResponse;
import com.demo.common.utils.Constants;
import com.demo.system.entity.ParkingRecord;
import com.demo.system.service.IRecordService;
import com.demo.system.vo.VoPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Sheva
 * @Date 2021/3/24
 */
@Slf4j
@RestController
public class CarRecordController {

    @Autowired
    private IRecordService recordService;

    @GetMapping("car/record")
    public ResultResponse recordList(@RequestParam("page") int pageNo,
                                      @RequestParam("limit") int limit){
        ResultResponse res = null;
        VoPage page = this.recordService.selectRecords(pageNo - 1, limit);
        // log.info("查询到的分页信息：" + page.toString());
        res = new ResultResponse(Constants.STATUS_OK, Constants.MESSAGE_OK, page);
        return res;
    }

    @PostMapping("/car/record/delete/{id}")
    public ResultResponse deleteRecord(@PathVariable Long id){
        ResultResponse res = new ResultResponse();
        if(this.recordService.deleteRecordById(id) == 1){
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
        }else{
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL + "删除停车记录失败...");
        }
        return res;
    }

    @PostMapping("/car/record/update")
    public ResultResponse updateRecord(@RequestBody ParkingRecord record){
        ResultResponse res = new ResultResponse();
        log.info("接收到的temp更新信息为：" + record.toString());
        try{
            recordService.updateRecord(record);
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
        }catch (Exception e){
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL + e.getMessage());
        }
        return res;
    }
}
