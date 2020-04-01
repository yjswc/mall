package com.cskaoyan.mall.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.ChangePwdAdmin;
import com.cskaoyan.mall.dao.AdminDao;
import com.cskaoyan.mall.dao.impl.AdminDaoImpl;
import com.cskaoyan.mall.service.AdminService;
import com.cskaoyan.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public int login(Admin admin) {
        return adminDao.login(admin);
    }

    @Override
    public List<Admin> queryAllAdmins() {
        return adminDao.queryAllAdmins();
    }

    @Override
    public int addAdmins(Admin admin) {
        return adminDao.addAdmins(admin);
    }

    @Override
    public int deleteAdmins(int id) {
        return adminDao.deleteAdmins(id);
    }

    @Override
    public int changePwd(ChangePwdAdmin change) {
        return adminDao.changePwd(change);
    }

    @Override
    public List<Admin> getAdminsInfo(int id) {
        return adminDao.getAdminsInfo(id);
    }

    @Override
    public int updateAdmins(Admin admin) {//?
        return adminDao.updateAdmins(admin);
    }

    @Override
    public List<Admin> querySearchAdmins(Admin admin) {
        String baseSql = "select * from admin where 1 = 1 ";
        List<Object> params = new ArrayList<>();
        if(!StringUtils.isEmpty(admin.getEmail())){
            baseSql = baseSql + "and email like ?";
            params.add("%" + admin.getEmail() + "%");
        }
        if(!StringUtils.isEmpty(admin.getNickname())){
            baseSql = baseSql + "and nickname like ?";
            params.add("%" + admin.getNickname() + "%");
        }
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Admin> admins = null;
        try {
            admins = runner.query(baseSql, new BeanListHandler<>(Admin.class), params.toArray());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }
}
