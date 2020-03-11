package com.xiaobao.pro_manage_sys.service.user.impl;

import com.xiaobao.pro_manage_sys.entity.user.SysManager;
import com.xiaobao.pro_manage_sys.repository.user.UserRepository;
import com.xiaobao.pro_manage_sys.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysManagerServiceImpl implements UserService<SysManager> {

    @Autowired
    UserRepository<SysManager> sysManagerUserRepository;


    @Override
    public SysManager findByUsernameAndPassword(String username, String password) {
        return sysManagerUserRepository.findByUsernameAndPassword(username,password);
    }
}
