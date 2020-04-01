package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.ChangePwdAdmin;
import com.cskaoyan.mall.bean.Result;
import com.cskaoyan.mall.service.impl.AdminServiceImpl;
import com.cskaoyan.mall.utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/api/admin/admin/*")
public class AdminServlet extends HttpServlet {
    private AdminServiceImpl adminService = new AdminServiceImpl();
    Gson gson = new Gson();

    //注册，登录
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("post");
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/", "");
        System.out.println(action);
        switch (action){
            case "login":
                login(request, response);
                break;
            case "addAdminss":
                addAdminss(request, response);
                break;
            case "changePwd":
                changePwd(request, response);
                break;
            case "updateAdminss":
                updateAdminss(request, response);
                break;
            case "getSearchAdmins":
                getSearchAdmins(request, response);
                break;
        }
    }

    private void getSearchAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        Admin admin = gson.fromJson(requestBody, Admin.class);
        List<Admin> admins = adminService.querySearchAdmins(admin);
        Result res = new Result();
        res.setCode(0);
        res.setData(admins);
        response.getWriter().println(gson.toJson(res));
    }

    private void updateAdminss(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        Admin admin = gson.fromJson(requestBody, Admin.class);
        int result = adminService.updateAdmins(admin);
        Result res = new Result();
        if(result == 200)
            res.setCode(0);
        else{
            res.setCode(10000);
            res.setMessage("当前访问异常，稍后重试");
        }
        response.getWriter().println(gson.toJson(res));
    }

    private void changePwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        ChangePwdAdmin change = gson.fromJson(requestBody, ChangePwdAdmin.class);
        Result res = new Result();
        System.out.println(change);
        int result = adminService.changePwd(change);
        if(result == 200)
            res.setCode(0);
        else{
            res.setCode(10000);
            if(result == 300)
                res.setMessage("旧密码错误");
            else if(result == 301)
                res.setMessage("确认密码输入错误");
            else
                res.setMessage("访问异常，请稍后再试");
        }
        response.getWriter().println(gson.toJson(res));
    }

    private void addAdminss(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        Admin admin = gson.fromJson(requestBody, Admin.class);
        int result = adminService.addAdmins(admin);
        Result res = new Result();
        if(result == 200){
            res.setCode(0);
        }else{
            res.setCode(10000);
        }
        response.getWriter().println(gson.toJson(res));
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        Gson gson = new Gson();
        Admin admin = gson.fromJson(requestBody, Admin.class);
        //System.out.println(admin);
        int result = adminService.login(admin);
        Result res = new Result();
        if(result == 200){
            res.setCode(0);
            HashMap<String, String> map = new HashMap<>();
            map.put("token", admin.getEmail());
            map.put("name", admin.getNickname());
            res.setData(map);
        }else if(result == 404){
            res.setCode(10000);
            res.setMessage("用户名或密码错误");
        }else{
            res.setCode(10000);
            res.setMessage("当前访问异常，稍后重试");
        }
        response.getWriter().println(gson.toJson(res));
    }

    //查询，删除
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get");
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/", "");
        switch (action){
            case "allAdmins":
                allAdmins(request, response);
                break;
            case "deleteAdmins":
                deleteAdmins(request, response);
                break;
            case "getAdminsInfo":
                getAdminsInfo(request, response);
                break;

        }
    }

    private void getAdminsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Admin> admin = adminService.getAdminsInfo(id);
        Result res = new Result();
        res.setCode(0);
        res.setData(admin);
        response.getWriter().println(gson.toJson(res));
    }

    private void deleteAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int result = adminService.deleteAdmins(id);
        Result res = new Result();
        if(result == 200){
            res.setCode(0);
        }else{
            res.setCode(10000);
            res.setMessage("当前访问异常，稍后重试");
        }
        response.getWriter().println(gson.toJson(res));
    }

    private void allAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Admin> adminList =  adminService.queryAllAdmins();
        Result result = new Result();
        result.setCode(0);
        result.setData(adminList);
        response.getWriter().println(gson.toJson(result));
    }
}
