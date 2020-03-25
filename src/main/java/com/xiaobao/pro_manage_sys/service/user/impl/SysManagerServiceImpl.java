package com.xiaobao.pro_manage_sys.service.user.impl;

import com.xiaobao.pro_manage_sys.entity.user.SysManager;
import com.xiaobao.pro_manage_sys.repository.user.UserRepository;
import com.xiaobao.pro_manage_sys.service.user.SysManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysManagerServiceImpl implements SysManagerService {

    @Autowired
    UserRepository<SysManager> sysManagerUserRepository;


    @Override
    public SysManager findByUsernameAndPassword(String username, String password) {
        return sysManagerUserRepository.findByUsernameAndPassword(username,password);
    }
}
