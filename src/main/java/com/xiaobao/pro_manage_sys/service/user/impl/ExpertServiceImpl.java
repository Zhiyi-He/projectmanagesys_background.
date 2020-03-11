package com.xiaobao.pro_manage_sys.service.user.impl;
import com.xiaobao.pro_manage_sys.entity.user.Expert;
import com.xiaobao.pro_manage_sys.repository.user.UserRepository;
import com.xiaobao.pro_manage_sys.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpertServiceImpl implements UserService<Expert> {

    @Autowired
    UserRepository<Expert> expertUserRepository;


    @Override
    public Expert findByUsernameAndPassword(String username, String password) {
        return expertUserRepository.findByUsernameAndPassword(username,password);
    }
}
