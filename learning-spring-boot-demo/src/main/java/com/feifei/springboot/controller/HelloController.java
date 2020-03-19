package com.feifei.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class HelloController {


    @GetMapping("/hello")
    public String hello(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(new Date());
        System.out.println("this time ="+time);
        return "hello,time = "+time;
    }
}
