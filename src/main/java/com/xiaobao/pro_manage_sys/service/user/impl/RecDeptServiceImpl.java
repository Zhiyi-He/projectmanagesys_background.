package com.xiaobao.pro_manage_sys.service.user.impl;

import com.xiaobao.pro_manage_sys.entity.user.RecDept;
import com.xiaobao.pro_manage_sys.repository.user.UserRepository;
import com.xiaobao.pro_manage_sys.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecDeptServiceImpl implements UserService<RecDept> {

    @Autowired
    UserRepository<RecDept> applicantUserRepository;


    @Override
    public RecDept findByUsernameAndPassword(String username, String password) {
        return applicantUserRepository.findByUsernameAndPassword(username,password);
    }
}
