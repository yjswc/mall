package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.ChangePwdAdmin;

import java.util.List;

public interface AdminService {
    int login(Admin admin);

    List<Admin> queryAllAdmins();

    int addAdmins(Admin admin);

    int deleteAdmins(int id);

    int changePwd(ChangePwdAdmin change);

    List<Admin> getAdminsInfo(int id);

    int updateAdmins(Admin admin);
}
