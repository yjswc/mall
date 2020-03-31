package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.ChangePwdAdmin;
import com.cskaoyan.mall.dao.AdminDao;
import com.cskaoyan.mall.dao.impl.AdminDaoImpl;
import com.cskaoyan.mall.service.AdminService;

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
    public int updateAdmins(Admin admin) {
        return adminDao.updateAdmins(admin);
    }
}
