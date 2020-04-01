package com.cskaoyan.mall.dao.impl;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.dao.UserDao;
import com.cskaoyan.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public List<User> queryAllUser() {
        List<User> userList = null;
        try {
            userList = runner.query("select * from user", new BeanListHandler<User>(User.class));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public int searchUser(List<User> userList, String nickname) {
        String sql = "select * from user where nickname = ?";
        try {
            runner.query(sql, new BeanListHandler<User>(User.class), nickname);
            return 200;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 400;
    }

    @Override
    public int deleteUser(int id) {
        try {
            runner.update("delete from user where id = ?", id);
            return 200;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 400;
    }

    @Override
    public User login(User user) throws SQLException {
        String sql = "select * from user where email = ? and pwd = ?";
        return runner.query(sql, new BeanHandler<>(User.class), user.getEmail(), user.getPwd());

    }

    @Override
    public Long checkEmail(String email) throws SQLException {
        String sql = "select count(id) from user where email = ?";
        Long ret = runner.query(sql, new ScalarHandler<Long>(), email);
        return ret;
    }

    @Override
    public Long checkName(String nickname) throws SQLException {
        String sql = "select count(id) from user where nickname = ?";
        Long ret = runner.query(sql, new ScalarHandler<Long>(), nickname);
        return ret;
    }

    @Override
    public void signUp(User user) throws SQLException {
        runner.update("insert into user value(null,?,?,?,?,?,?)", user.getEmail(), user.getNickname(), user.getPwd(),
                    user.getRecipient(), user.getAddress(), user.getPhone());
    }
}
