package com.feifei.demospringbootmvc.controller;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController{

    @GetMapping("/hello")
public String hello(){
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:SS");
    String time = simpleDateFormat.format(new Date());
    return time;
}

}