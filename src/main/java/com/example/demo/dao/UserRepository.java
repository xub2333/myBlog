package com.example.demo.dao;

import com.example.demo.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xubin
 * @date 2020/7/29 21:52
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username,String password);


}
