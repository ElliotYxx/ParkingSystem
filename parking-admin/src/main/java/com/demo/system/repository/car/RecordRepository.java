package com.demo.system.repository.car;

import com.demo.system.entity.ParkingRecord;
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
public interface RecordRepository extends JpaRepository<ParkingRecord, Long> {
    /**
     * 用户的分页查询
     * @param pageable
     * @return
     */
    @Query("select pr from ParkingRecord pr")
    List<ParkingRecord> selectRecords(Pageable pageable);

    /**
     * 用户记录表的删除操作
     */
    @Transactional
    @Modifying
    @Query("delete from ParkingRecord pr where pr.id = :id")
    Integer deleteRecordById(@Param("id") Long id);
}
