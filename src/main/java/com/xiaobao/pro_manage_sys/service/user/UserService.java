package com.xiaobao.pro_manage_sys.service.user;


public interface UserService<T> {

    /**
     * 通过username和password查询用户信息--登录
     * @param username
     * @param password
     * @return 当前登录用户
     */
    public T findByUsernameAndPassword(String username, String password);
}
