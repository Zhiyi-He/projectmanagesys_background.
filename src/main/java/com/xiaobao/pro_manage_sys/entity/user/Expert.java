package com.xiaobao.pro_manage_sys.entity.user;

import javax.persistence.*;

@Entity
@Table(name = "expert")
public class Expert {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "exp_id")
  private Integer id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "exp_name")
  private String name;

  @Column(name = "id_card")
  private String idCard;

  @Column(name = "tel")
  private String tel;

  @Column(name = "email")
  private String email;

  public Expert() {}

  public Expert(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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
