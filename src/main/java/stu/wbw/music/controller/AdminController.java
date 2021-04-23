package stu.wbw.music.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import stu.wbw.music.service.AdminService;
import stu.wbw.music.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 判断是否登录成功
     */
    @RequestMapping(value = "/admin/login/status",method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest request, HttpSession session){
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        boolean flag = adminService.verifyPassword(name, password);
        if (flag){
            jsonObject.put(Constants.CODE,1);
            jsonObject.put(Constants.MSG,"登录成功");
            session.setAttribute(Constants.NAME,name);
            return jsonObject;
        }
        jsonObject.put(Constants.CODE,0);
        jsonObject.put(Constants.MSG,"账号或密码错误");
        return jsonObject;
    }
}
