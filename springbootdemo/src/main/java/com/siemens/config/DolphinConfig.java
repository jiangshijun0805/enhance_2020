package com.siemens.config;

import com.siemens.utils.DynamodbMethod;
import com.siemens.utils.IotMethod;
import com.siemens.utils.S3Method;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (c) 2020 Siemens AG. All rights reserved. This software is the confidential and
 * proprietary information of Siemens AG. This file is part of springbootdemo.
 *
 * @author jiangshijun
 * @description
 * @date 2020/4/2 13:42
 */

@Configuration
public class DolphinConfig {
    @Bean
    public DynamodbMethod db() {
        return new DynamodbMethod();
    }

    @Bean
    public IotMethod iot() {
        return new IotMethod();
    }

    @Bean
    public S3Method s3() {
        return new S3Method();
    }
}
