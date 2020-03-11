package com.xiaobao.pro_manage_sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
//@ServletComponentScan
public class ProManageSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProManageSysApplication.class, args);
    }

}
