package com.demo.system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 用户实体类
 * @Author sheva
 * @Date 2021/2/24 10:50
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class User {
    /** 用户id **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** 用户名 **/
    @Column
    private String username;
    /** 用户密码 **/
    @Column
    private String password;
    /** 创建时间 **/
    @Column
    private Timestamp createTime;
    /** 用户状态 **/
    @Column
    private Integer status;
    /** 用户角色  1对应普通用户  2对应管理员 **/
    @Column
    private Integer roleId;
}
