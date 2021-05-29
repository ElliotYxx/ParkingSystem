package com.demo.system.service.impl;

import com.demo.system.entity.ParkingTemp;
import com.demo.system.repository.car.TempRepository;
import com.demo.system.service.ITempService;
import com.demo.system.vo.VoPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author Sheva
 * @Date 2021/3/24
 */
@Slf4j
@Service
public class TempServiceImpl implements ITempService {
    @Autowired
    private TempRepository tempRepository;
    @Override
    public VoPage selectTemps(int pageNo, int limit) {
        VoPage page = new VoPage();
        PageRequest pageRequest = PageRequest.of(pageNo, limit);
        List<ParkingTemp> tempList = tempRepository.selectTemps(pageRequest);
        // log.info("查询出来的tempList：" + tempList.toString());
        page.setTotal(tempList.size());
        page.setItems(tempList);
        return page;
    }

    @Override
    public Optional<ParkingTemp> selectTempById(Long id) {
        return tempRepository.findById(id);
    }

    @Override
    public void addTemp(ParkingTemp parkingTemp) {
        tempRepository.save(parkingTemp);
    }

    @Override
    public Integer deleteTempById(Long id) {
        return tempRepository.deleteTempById(id);
    }

    @Override
    public void updateTemp(ParkingTemp temp) {
        tempRepository.save(temp);
    }
}
