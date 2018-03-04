package com.example.springboot.Controller;

import com.example.springboot.model.User;
import com.example.springboot.util.AuthUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @PostMapping(value = "/api/login")
    public Map<String,Object> login(@RequestBody User user, @RequestParam(value = "timestamp") String timestamp,HttpServletRequest request){
        System.out.println(user);
        System.out.println("timestamp:" + timestamp);
        String userName = "luhg";
        String passWord = "342521lu";

        Map<String,Object> result = new HashMap<>();

        boolean check = AuthUtil.checkTimeValid(timestamp,10);
        if (!check){
            result.put("sucess",false);
            return result;
        }else{
            String md5 = AuthUtil.encodeMd5(userName + timestamp + passWord);
            if (md5!=null&&md5.equals(user.getPassWord())){
                HttpSession session = request.getSession(); //创建session set-cookie:JSESSIONID
                session.setAttribute("loginSuccess",true);
                result.put("success",true);
            }
        }
        return result;
    }
}
