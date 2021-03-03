package com.demo.system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Author sheva
 * @create 2021/2/24 10:50
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private Timestamp createTime;
    @Column
    private Integer status;
    @Column
    private Integer roleId;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Integer status, Integer roleId) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.roleId = roleId;
    }
}
