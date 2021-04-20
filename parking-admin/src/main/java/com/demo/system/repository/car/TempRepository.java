package com.demo.system.repository.car;

import com.demo.system.entity.ParkingTemp;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author Sheva
 * @Date 2021/3/24
 */
public interface TempRepository extends JpaRepository<ParkingTemp, Long> {

    /**
     * 停车临时表的分页查询
     * @param pageable
     * @return
     */
    @Query("select pt from ParkingTemp pt")
    List<ParkingTemp> selectTemps(Pageable pageable);

    /**
     * 停车临时表的删除操作
     */
    @Transactional
    @Modifying
    @Query("delete from ParkingTemp pt where pt.id = :id")
    Integer deleteTempById(@Param("id") Long id);

}
