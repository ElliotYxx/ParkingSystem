package com.demo.system.service.impl;

import com.demo.system.entity.User;
import com.demo.system.repository.UserRepository;
import com.demo.system.service.IUserService;
import com.demo.system.vo.VoPage;
import com.demo.system.vo.VoUser;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.IllegalFormatCodePointException;
import java.util.List;

/**
 * @Author Sheva
 * @Date 2021/2/24
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public VoUser checkLogin(VoUser user) {
        User u = userRepository.findByUsername(user.getUsername());
        if (u != null && u.getPassword().equals(user.getPassword())){
            return user;
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public VoPage selectUsers(int pageNo, int limit) {
        VoPage page = new VoPage();
        PageRequest pageRequest = PageRequest.of(pageNo, limit);
        List<User> userList = userRepository.selectUsers(pageRequest);
        log.info("查询出来的userList：" + userList.toString());
        page.setTotal(userList.size());
        page.setItems(userList);
        return page;
    }
}
