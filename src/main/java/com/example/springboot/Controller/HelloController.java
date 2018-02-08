package com.example.springboot.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String say(){
        String milisName = new SimpleDateFormat("yyyyMMddHH24miss").format(Calendar.getInstance().getTime());
        System.out.println(milisName);
        return "Hello Spring Boot";
    }

    @RequestMapping(value = "/api/upload/file",method = RequestMethod.POST)
    public Map<String,Object> uploadFile (@RequestParam(value = "editormd-image-file", required = false)MultipartFile file, HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();

        String fileName = file.getOriginalFilename();
        String filePath = "/opt/resources/images/";
        String datePath = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()) + "/";

        filePath = filePath + datePath; //  path:/home/luhg/local/resources/images/yyyy/MM/dd/

        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            boolean bol = targetFile.mkdirs();
            System.out.println("mkdir:" + bol);
        }

        try{
            FileOutputStream out = new FileOutputStream(filePath + fileName);
            out.write(file.getBytes());
            out.flush();
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        result.put("success",1);
        result.put("url", "/images/" + datePath + fileName);
        result.put("message","test upload successful");
        return result;
    }

    @RequestMapping(value = "/api/hello",method = RequestMethod.GET)
    public String apiSay(){
        return "Hello Spring Boot Api";
    }

}
