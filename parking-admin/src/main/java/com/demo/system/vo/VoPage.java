package com.demo.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


/**
 * @Author Sheva
 * @Date 2021/2/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VoPage {
    private int total;
    private List<?> items;
}
