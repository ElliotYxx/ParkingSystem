package com.demo.system.service.impl;

import com.demo.system.entity.ParkingRecord;
import com.demo.system.repository.car.RecordRepository;
import com.demo.system.service.IRecordService;
import com.demo.system.vo.VoPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Sheva
 * @Date 2021/3/24
 */
@Slf4j
@Service
public class RecordServiceImpl implements IRecordService {

    @Autowired
    private RecordRepository recordRepository;
    @Override
    public VoPage selectRecords(int pageNo, int limit) {
        VoPage page = new VoPage();
        PageRequest pageRequest = PageRequest.of(pageNo, limit);
        List<ParkingRecord> recordList = recordRepository.selectRecords(pageRequest);
        log.info("查询出来的userList：" + recordList.toString());
        page.setTotal(recordList.size());
        page.setItems(recordList);
        return page;
    }

    @Override
    public List<ParkingRecord> getParkingRecord() {
        return recordRepository.findAll();
    }

    @Override
    public void addRecord(ParkingRecord parkingRecord) {
        recordRepository.save(parkingRecord);
    }

    @Override
    public Integer deleteRecordById(Long id) {
        return recordRepository.deleteRecordById(id);
    }

    @Override
    public void updateRecord(ParkingRecord record) {
        recordRepository.save(record);
    }
}
