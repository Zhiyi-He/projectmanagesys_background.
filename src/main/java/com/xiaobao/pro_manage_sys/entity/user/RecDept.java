package com.xiaobao.pro_manage_sys.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rec_dept")
public class RecDept {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rcd_id")
  private Integer id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "dept_name")
  private String deptName;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "recDept", cascade = CascadeType.ALL)
  @JsonIgnore
  private Set<RepDept> repDepts = new HashSet<>(0);

  public RecDept() {}

  public RecDept(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public Set<RepDept> getRepDepts() {
    return repDepts;
  }

  public void setRepDepts(Set<RepDept> repDepts) {
    this.repDepts = repDepts;
  }

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
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
}
