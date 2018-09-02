package com.example.springboot.controller;

import com.example.springboot.entity.User;
import com.example.springboot.util.AuthUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {

    @PostMapping(value = "/api/login")
    public Map<String,Object> login(@RequestBody User user, @RequestParam(value = "timestamp") String timestamp,HttpServletRequest request){
        System.out.println(user);
        System.out.println("timestamp:" + timestamp);
        //String userName = "luhg";

        Map<String,Object> result = new HashMap<>();

        String userName = user.getUserName();
        List<String> names = new ArrayList<>(2);
        names.add("luhg");
        names.add("luhg3");
        if (!names.contains(userName)){
            result.put("sucess",false);
            return result;
        }

        String passWord = "342521lu";

        boolean check = AuthUtil.checkTimeValid(timestamp,10);
        if (!check){
            result.put("sucess",false);
            return result;
        }else{
            String md5 = AuthUtil.encodeMd5(userName + timestamp + passWord);
            if (md5!=null&&md5.equals(user.getPassWord())){
                HttpSession session = request.getSession(); //创建session set-cookie:JSESSIONID
                session.setAttribute("loginSuccess",true);
                session.setAttribute("username",userName);
                result.put("success",true);
            }
        }
        return result;
    }

    @GetMapping(value = "/api/logout")
    public Map<String,Object> logout(HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();

        HttpSession session = request.getSession();
        if(session.getAttribute("loginSuccess") != null){
            session.removeAttribute("loginSuccess");
        }

        result.put("success",true);
        return result;
    }
}
