package com.xiaobao.pro_manage_sys.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.xiaobao.pro_manage_sys.entity.user.RepDept;

import javax.persistence.*;

@Entity
@Table(name = "principal")
public class Principal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "pri_id")
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "personnel")
  private String personnel;

  @Column(name = "position")
  private String position;

  @Column(name = "tel")
  private String tel;

  @Column(name = "email")
  private String email;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, targetEntity = RepDept.class)
  @JoinColumn(name = "rpd_id")
  @JsonBackReference
  private RepDept repDept;

  public RepDept getRepDept() {
    return repDept;
  }

  public void setRepDept(RepDept repDept) {
    this.repDept = repDept;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPersonnel() {
    return personnel;
  }

  public void setPersonnel(String personnel) {
    this.personnel = personnel;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
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
}
