package com.xiaobao.pro_manage_sys.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xiaobao.pro_manage_sys.entity.Project;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "applicant")
public class Applicant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "app_id")
  private Integer id;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "app_name", nullable = false)
  private String name;

  @Column(name = "sex")
  private Integer sex;

  @Column(name = "id_card", nullable = false)
  private String idCard;

  @Column(name = "folk")
  private String folk;

  @Column(name = "edu")
  private String edu;

  @Column(name = "school")
  private String school;

  @Column(name = "profession")
  private String profession;

  @Column(name = "birthday")
  private String birthday;

  @Column(name = "tel")
  private String tel;

  @Column(name = "email")
  private String email;

  @Column(name = "pro_num", nullable = false)
  private Integer proNum;

  @Column(name = "[desc]")
  private String desc;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "applicant", cascade = CascadeType.ALL)
  @JsonIgnore
  private Set<Project> proList = new HashSet<>(0);

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, targetEntity = RepDept.class)
  @JoinColumn(name = "rpd_id")
  private RepDept repDept;

  public Applicant() {}

  public Applicant(
      String username,
      String password,
      Integer proNum,
      RepDept repDept,
      String name,
      String idCard) {
    this.username = username;
    this.password = password;
    this.proNum = proNum;
    this.repDept = repDept;
    this.name = name;
    this.idCard = idCard;
  }

  public String getSchool() {
    return school;
  }

  public void setSchool(String school) {
    this.school = school;
  }

  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }

  public RepDept getRepDept() {
    return repDept;
  }

  public void setRepDept(RepDept repDept) {
    this.repDept = repDept;
  }

  public Set<Project> getProList() {
    return proList;
  }

  public void setProList(Set<Project> proList) {
    this.proList = proList;
  }

  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
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

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public Integer getProNum() {
    return proNum;
  }

  public void setProNum(Integer proNum) {
    this.proNum = proNum;
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
    return "Applicant{"
        + "id="
        + id
        + ", username='"
        + username
        + '\''
        + ", password='"
        + password
        + '\''
        + ", name='"
        + name
        + '\''
        + ", idCard='"
        + idCard
        + '\''
        + ", folk='"
        + folk
        + '\''
        + ", edu="
        + edu
        + ", profession='"
        + profession
        + '\''
        + ", birthday='"
        + birthday
        + '\''
        + ", tel='"
        + tel
        + '\''
        + ", email='"
        + email
        + '\''
        + ", proNum="
        + proNum
        + ", desc='"
        + desc
        + '\''
        + ", proList="
        + proList
        + '}';
  }
}
