package com.demo.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author Sheva
 * @Date 2021/2/23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VoToken implements Serializable {
    private String token;
}
