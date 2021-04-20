package com.demo.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author Sheva
 * @Date 2021/2/24 11:10
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.demo.system.controller",
                                "com.demo.system.service",
                                "com.demo.system.config"})
public class StartParkingAdmin {
    public static void main(String[] args) {
        SpringApplication.run(StartParkingAdmin.class, args);
    }
}
