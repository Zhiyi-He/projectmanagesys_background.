package com.xiaobao.pro_manage_sys.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepository<T> extends JpaRepository<T,Integer> {

    public T findByUsernameAndPassword(String username, String password);

}
