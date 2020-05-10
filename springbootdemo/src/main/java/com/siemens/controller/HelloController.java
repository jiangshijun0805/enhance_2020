package com.siemens.controller;

import com.siemens.model.CityAqiEntity;
import com.siemens.utils.DynamodbMethod;
import com.siemens.utils.IotMethod;
import com.siemens.utils.S3Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (c) 2020 Siemens AG. All rights reserved. This software is the confidential and
 * proprietary information of Siemens AG. This file is part of springbootdemo.
 *
 * @author jiangshijun
 * @description
 * @date 2020/4/2 12:47
 */
@RequestMapping("/dolphin")
@RestController
public class HelloController {

    @Autowired
    private IotMethod iot;

    @Autowired
    private S3Method s3;

    @Autowired
    private DynamodbMethod db;

    @GetMapping("/hello")
    public String Hello(@RequestParam("name") String name){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = df.format(new Date());
        String message = "name = "+name+", date ="+date;
        System.out.println(message);
        return message;
    }

    @GetMapping("/testDB")
    public Object testDB( ){
        CityAqiEntity cityAqiEntity = db.load(CityAqiEntity.class,"0512");
        System.out.println(cityAqiEntity);
        return cityAqiEntity;
    }

    @GetMapping("/testS3")
    public Object testS3( ){
        String content = s3.getObjectAsString("chenxi-demo2","2018-11-19-1542620580587.txt");
        System.out.println(content);
        return content;
    }

    @GetMapping("/testIOT")
    public Object testIOT( ){
        String thingShadow = iot.getThingShadow("19080770022053722342");
        System.out.println(thingShadow);
        return thingShadow;
    }
}
