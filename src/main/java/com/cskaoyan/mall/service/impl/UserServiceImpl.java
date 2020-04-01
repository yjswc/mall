package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.dao.UserDao;
import com.cskaoyan.mall.dao.impl.UserDaoImpl;
import com.cskaoyan.mall.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> getAllUser() {
        return userDao.queryAllUser();
    }

    @Override
    public int getSearchUser(List<User> userList, String word) {
        return userDao.searchUser(userList, word);
    }

    @Override
    public int deleteUser(int id) {
        return userDao.deleteUser(id);
    }

    @Override
    public int login(User user) {
        try {
            user = userDao.login(user);
            if(user != null)
                return 200;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 400;
    }

    @Override
    public int signUp(User user) {
        try {
            if(userDao.checkEmail(user.getEmail()) == 1)
                return 301;
            if(userDao.checkName(user.getNickname()) == 1)
                return 302;
            userDao.signUp(user);
            return 200;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 400;
    }
}
