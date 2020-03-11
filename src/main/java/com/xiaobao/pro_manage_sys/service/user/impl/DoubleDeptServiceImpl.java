package com.xiaobao.pro_manage_sys.service.user.impl;
import com.xiaobao.pro_manage_sys.entity.user.DoubleDept;
import com.xiaobao.pro_manage_sys.repository.user.UserRepository;
import com.xiaobao.pro_manage_sys.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoubleDeptServiceImpl implements UserService<DoubleDept> {

    @Autowired
    UserRepository<DoubleDept> doubleDeptUserRepository;


    @Override
    public DoubleDept findByUsernameAndPassword(String username, String password) {
        return doubleDeptUserRepository.findByUsernameAndPassword(username,password);
    }
}