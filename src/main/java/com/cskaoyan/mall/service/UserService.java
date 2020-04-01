package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    int getSearchUser(List<User>userList, String word);
    int deleteUser(int id);
    int login(User user);
    int signUp(User user);
}
