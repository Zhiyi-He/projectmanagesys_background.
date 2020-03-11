package com.xiaobao.pro_manage_sys.service.user.impl;

import com.xiaobao.pro_manage_sys.entity.user.Applicant;
import com.xiaobao.pro_manage_sys.repository.user.UserRepository;
import com.xiaobao.pro_manage_sys.service.user.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ApplicantServiceImpl implements UserService<Applicant> {

    @Resource
    UserRepository<Applicant> applicantUserRepository;


    @Override
    public Applicant findByUsernameAndPassword(String username, String password) {
        return applicantUserRepository.findByUsernameAndPassword(username,password);
    }
}
