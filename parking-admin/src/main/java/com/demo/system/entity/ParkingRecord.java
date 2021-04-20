package com.demo.system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 停车历史记录实体类
 * @Author Sheva
 * @Date 2021/3/24
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class ParkingRecord {
    /** 停车历史记录id **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** 车牌号 **/
    @Column
    private String plateNumber;
    /** 车牌颜色 **/
    @Column
    private String color;
    /** 车辆入库时间 **/
    @Column
    private Timestamp createTime;
    /** 车辆出库时间 **/
    @Column
    private Timestamp leftTime;
    /** 停车费用 **/
    @Column
    private BigDecimal cost;
    /** 车牌图像存储路径 **/
    @Column
    private String fileUrl;

    /** 相关构造函数 **/
    public ParkingRecord(String plateNumber, String color, Timestamp createTime, BigDecimal cost, String fileUrl) {
        this.plateNumber = plateNumber;
        this.color = color;
        this.createTime = createTime;
        this.cost = cost;
        this.fileUrl = fileUrl;
    }
}
