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
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VoUser implements Serializable {

    private String username;
    private String password;

}
