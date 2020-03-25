package com.xiaobao.pro_manage_sys.service.user.impl;

import com.xiaobao.pro_manage_sys.entity.user.ProManager;
import com.xiaobao.pro_manage_sys.repository.user.UserRepository;
import com.xiaobao.pro_manage_sys.service.user.ProManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProManagerServiceImpl implements ProManagerService {

    @Autowired
    UserRepository<ProManager> proManagerUserRepository;


    @Override
    public ProManager findByUsernameAndPassword(String username, String password) {
        return proManagerUserRepository.findByUsernameAndPassword(username,password);
    }
}
