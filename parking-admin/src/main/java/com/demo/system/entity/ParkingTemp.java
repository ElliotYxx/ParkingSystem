package com.demo.system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 正在停车实体类
 * @Author Sheva
 * @Date 2021/3/24
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class ParkingTemp {
    /** 正在停车记录id **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** 车牌号 **/
    @Column
    private String plateNumber;
    /** 车牌颜色 **/
    @Column
    private String color;
    /** 创建时间 **/
    @Column
    private Timestamp createTime;
    /** 车牌图像存储路径 **/
    @Column
    private String fileUrl;
}
