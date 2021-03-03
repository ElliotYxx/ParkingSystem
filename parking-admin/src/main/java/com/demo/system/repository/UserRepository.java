package com.demo.system.repository;

import com.demo.system.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author sheva
 * @create 2021/2/24 10:37
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 通过用户名寻找用户
     * @param username
     * @return
     */
    @Query("select u from User u where u.username = :username")
    User findByUsername(@Param("username") String username);

    /**
     * 用户的分页查询
     * @param pageable
     * @return
     */
    @Query("select u from User u")
    List<User> selectUsers(Pageable pageable);

    /**
     * 用户的删除操作
     */
    @Transactional
    @Modifying
    @Query("delete from User u where u.id = :userId")
    Integer deleteUserById(@Param("userId") Long userId);
}
