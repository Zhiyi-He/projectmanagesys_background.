package com.xiaobao.pro_manage_sys.entity.user;

import javax.persistence.*;

@Entity
@Table(name = "sys_manager")
public class SysManager {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "sm_id")
  private Integer id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  public SysManager() {}

  public SysManager(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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

  @Override
  public String toString() {
    return "User{"
        + "uId="
        + id
        + ", username='"
        + username
        + '\''
        + ", password='"
        + password
        + '\''
        + '}';
  }
}
