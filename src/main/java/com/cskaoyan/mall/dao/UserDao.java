package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.bean.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    List<User> queryAllUser();

    int searchUser(List<User> userList, String nickname);

    int deleteUser(int id);

    User login(User user) throws SQLException;

    Long checkEmail(String email) throws SQLException;

    Long checkName(String nickname) throws SQLException;

    void signUp(User user) throws SQLException;
}
