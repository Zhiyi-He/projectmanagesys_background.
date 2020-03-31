package com.xiaobao.pro_manage_sys.entity.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.xiaobao.pro_manage_sys.entity.Score;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "expert")
public class Expert {

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "expert", cascade = CascadeType.ALL)
  @JsonManagedReference
  List<Score> scores = new ArrayList<>(0);

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

  @Column(name = "sex")
  private Integer sex;

  @Column(name = "birthday")
  private String birthday;

  @Column(name = "folk")
  private String folk;

  @Column(name = "edu")
  private String edu;

  @Column(name = "profession")
  private String profession;

  @Column(name = "title")
  private Integer title;

  @Column(name = "tel")
  private String tel;

  @Column(name = "email")
  private String email;

  @Column(name = "address")
  private String address;

  @Column(name = "work_unit")
  private String workUnit;

  public Expert() {}

  public Expert(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public List<Score> getScores() {
    return scores;
  }

  public void setScores(List<Score> scores) {
    this.scores = scores;
  }

  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getFolk() {
    return folk;
  }

  public void setFolk(String folk) {
    this.folk = folk;
  }

  public String getEdu() {
    return edu;
  }

  public void setEdu(String edu) {
    this.edu = edu;
  }

  public String getProfession() {
    return profession;
  }

  public void setProfession(String profession) {
    this.profession = profession;
  }

  public Integer getTitle() {
    return title;
  }

  public void setTitle(Integer title) {
    this.title = title;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getWorkUnit() {
    return workUnit;
  }

  public void setWorkUnit(String workUnit) {
    this.workUnit = workUnit;
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
