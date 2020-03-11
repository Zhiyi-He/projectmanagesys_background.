package com.xiaobao.pro_manage_sys.service.user.impl;

import com.xiaobao.pro_manage_sys.entity.user.RepDept;
import com.xiaobao.pro_manage_sys.repository.user.UserRepository;
import com.xiaobao.pro_manage_sys.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepDeptServiceImpl implements UserService<RepDept> {

    @Autowired
    UserRepository<RepDept> repDeptUserRepository;


    @Override
    public RepDept findByUsernameAndPassword(String username, String password) {
        return repDeptUserRepository.findByUsernameAndPassword(username,password);
    }
}
