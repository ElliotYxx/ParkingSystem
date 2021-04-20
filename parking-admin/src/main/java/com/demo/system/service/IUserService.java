package com.demo.system.service;

import com.demo.system.entity.User;
import com.demo.system.vo.VoPage;

/**
 * @Author Sheva
 * @Date 2021/2/23
 */
public interface IUserService {

    /**
     * 登陆校验
     * @param user
     * @return
     */
    User checkLogin(User user);

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
     * @return
     */
    VoPage selectUsers(int pageNo, int limit);

    /**
     * 根据 id 删除用户
     * @param userId
     * @return
     */
    Integer deleteUserById(Long userId);
    /**
     * 添加用户
     * @param user
     * @return
     */
    void addUser(User user);

    void updateUser(User user);
}
