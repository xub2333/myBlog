package com.example.demo.service;

import com.example.demo.po.User;

/**
 * @author xubin
 * @date 2020/7/29 21:40
 */
public interface UserService {
    User checkUser(String username,String password);
}
