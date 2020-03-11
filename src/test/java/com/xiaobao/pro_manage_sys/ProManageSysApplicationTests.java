package com.xiaobao.pro_manage_sys;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

import java.io.Serializable;

@SpringBootTest
@EnableCaching
class ProManageSysApplicationTests implements Serializable {

    @Cacheable(value = "username",key = "'test'")
    @Test
    public String contextLoads() {
        return "admin";
    }

}
