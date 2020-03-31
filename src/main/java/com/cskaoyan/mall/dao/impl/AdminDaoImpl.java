package com.cskaoyan.mall.dao.impl;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.ChangePwdAdmin;
import com.cskaoyan.mall.dao.AdminDao;
import com.cskaoyan.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
    @Override
    public int login(Admin admin) {
        Admin query;
        try {
             query = runner.query("select * from admin where email = ? and pwd = ?",
                    new BeanHandler<>(Admin.class),
                    admin.getEmail(),
                    admin.getPwd());
        } catch (SQLException e) {
            e.printStackTrace();
            return 500;
        }
        if(query != null){
            return 200;
        }
        return 404;
    }

    @Override
    public List<Admin> queryAllAdmins() {

        List<Admin> adminList = null;
        try {
            adminList = runner.query("select * from admin", new BeanListHandler<Admin>(Admin.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;
    }

    @Override
    public int addAdmins(Admin admin) {

        try {
            runner.execute("insert into admin values(null, ?, ?, ?)",
                            admin.getEmail(),admin.getNickname(), admin.getPwd());
            return 200;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 404;
    }

    @Override
    public int deleteAdmins(int id) {

        try {
            runner.execute("delete from admin where id = ?", id);
            return 200;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 404;
    }

    @Override
    public int changePwd(ChangePwdAdmin change) {
        if(!change.getNewPwd().equals(change.getConfirmedPwd()))
            return 300;//密码确认错误
        String sql = "select pwd from admin where email = ?";
        try {
            String query = runner.query(sql, new ScalarHandler<String>(), change.getEmail());
            if(query != change.getOldPwd())//密码应该是以密文存储在数据库，先这样写了。
                return 301;
            runner.update("update admin set pwd = ? where email = ?",
                            change.getConfirmedPwd(), change.getEmail());
            return 200;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 404;
    }

    @Override
    public List<Admin> getAdminsInfo(int id) {
        String sql = "select * from admin where id = ?";
        List<Admin> admin = null;
        try {
            admin = runner.query(sql, new BeanListHandler<Admin>(Admin.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public int updateAdmins(Admin admin) {
        String sql = "update admin set email = ? , nickname = ?, pwd = ? where id = ?";
        try {
            runner.update(sql, admin.getEmail(), admin.getNickname(), admin.getPwd(), admin.getId());
            return 200;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 404;
    }
}
