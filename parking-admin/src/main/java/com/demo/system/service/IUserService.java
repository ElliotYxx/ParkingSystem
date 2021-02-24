package com.demo.system.service;

import com.demo.system.entity.User;
import com.demo.system.vo.VoPage;
import com.demo.system.vo.VoUser;

/**
 * @Author Sheva
 * @Date 2021/2/23
 */
public interface IUserService {
    /**
     * 查询用户
     * @param user
     * @return
     */
    VoUser checkLogin(VoUser user);

    /**
     * 通过用户名寻找用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 用户的分页查询
     * @param pageNo
     * @param limit
     * @param idSort
     * @return
     */
    VoPage selectUsers(int pageNo, int limit);
}
