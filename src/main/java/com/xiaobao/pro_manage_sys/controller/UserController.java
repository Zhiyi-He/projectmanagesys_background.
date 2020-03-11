package com.xiaobao.pro_manage_sys.controller;

import com.xiaobao.pro_manage_sys.entity.user.User;
import com.xiaobao.pro_manage_sys.util.FileUtil;
import com.xiaobao.pro_manage_sys.util.Result;
import com.xiaobao.pro_manage_sys.util.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    RedisTemplate redisTemplate;


    @PostMapping("/user/login/{loginParam}")
    public Result findByUsernameAndPassword(@PathVariable("loginParam") String loginParam,
                                            @RequestBody User user){
        Map<String,Object> data=new HashMap<>();

        String username = user.getUsername();
        String password = user.getPassword();
        User admin= UserLogin.findByUsernameAndPassword(loginParam,username,password);


        if (admin != null) {
            //生成Token令牌
            String token= UUID.randomUUID()+"";
            redisTemplate.opsForValue().set(token,admin, Duration.ofMinutes(30L));

            data.put("username", admin.getUsername());
            data.put("userType", loginParam);
            data.put("token",token);
            return new Result(data,"登录成功",20000);
        } else {
            return new Result(data,"用户名或者密码错误",40000);
        }
    }

    @GetMapping("/user/userInfo")
    public Result getUserInfo(HttpServletRequest request){
        //从请求头中获取token值
        String token=request.getHeader("x-token");

        Object user= redisTemplate.opsForValue().get(token);
        if(user!=null){
            return new Result(user,"获取登录用户成功",20000);
        }else {
            return new Result(null,"获取登录用户失败",40000);
        }

    }

    @GetMapping("/download")
    public Result downLoad(@PathParam("filename") String filename,
                           @PathParam("parentPath") String parentPath,
                           HttpServletResponse response) throws IOException {
        Boolean flag=FileUtil.downloadFile(parentPath,filename,response);
        if(flag){
            return new Result(null,"下载成功",20000);
        }else{
            return new Result(null,"下载失败",40000);
        }
    }

}
