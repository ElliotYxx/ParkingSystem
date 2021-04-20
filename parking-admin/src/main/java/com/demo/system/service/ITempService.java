package com.demo.system.service;

import com.demo.system.entity.ParkingTemp;
import com.demo.system.vo.VoPage;

import java.util.Optional;

/**
 * @Author Sheva
 * @Date 2021/3/24
 */
public interface ITempService {
    /**
     * 停车临时表的分页查询
     * @param pageNo
     * @param limit
     * @return
     */
    VoPage selectTemps(int pageNo, int limit);

    Optional<ParkingTemp> selectTempById(Long id);

    void addTemp(ParkingTemp parkingTemp);

    Integer deleteTempById(Long id);

    void updateTemp(ParkingTemp temp);
}
