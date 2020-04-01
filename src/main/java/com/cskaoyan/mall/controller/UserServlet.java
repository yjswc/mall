package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Result;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.service.UserService;
import com.cskaoyan.mall.service.impl.UserServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/user/*")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/user/", "");
        switch (action){
            case "allUser":
                getAllUser(request, response);
                break;
            case "deleteUser":
                deleteUser(request, response);
                break;
            case "searchUser":
                getSearchUser(request, response);
                break;
        }
    }

    private void getSearchUser(HttpServletRequest request, HttpServletResponse response) {

    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int result = userService.deleteUser(id);
        Result res = new Result();
        if (result == 200)
            res.setCode(0);
        else {
            res.setCode(10000);
            res.setMessage("error");
        }
        response.getWriter().println(gson.toJson(res));
    }

    private void getAllUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> userList = userService.getAllUser();
        Result res = new Result();
        res.setCode(0);
        res.setData(userList);
        response.getWriter().println(gson.toJson(res));
    }
}
