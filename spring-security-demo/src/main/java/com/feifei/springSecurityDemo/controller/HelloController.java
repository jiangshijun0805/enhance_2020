package com.feifei.springSecurityDemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd :HH:mm:ss");
        String time = simpleDateFormat.format(new Date());
        System.out.println("current time = "+time);
        return time;
    }

}
