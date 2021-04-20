package com.demo.system.service;

import com.demo.system.entity.ParkingRecord;
import com.demo.system.vo.VoPage;

/**
 * @Author Sheva
 * @Date 2021/3/24
 */
public interface IRecordService {

    /**
     * 停车记录表的分页查询
     * @param pageNo
     * @param limit
     * @return
     */
    VoPage selectRecords(int pageNo, int limit);

    void addRecord(ParkingRecord parkingRecord);

    Integer deleteRecordById(Long id);

    void updateRecord(ParkingRecord record);

}
