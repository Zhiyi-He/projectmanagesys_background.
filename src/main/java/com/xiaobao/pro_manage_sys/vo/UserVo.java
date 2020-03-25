package com.xiaobao.pro_manage_sys.vo;

import java.io.Serializable;

public class UserVo implements Serializable {

  private int id;
  private String username;
  private String password;

  public UserVo() {}

  public UserVo(int id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
