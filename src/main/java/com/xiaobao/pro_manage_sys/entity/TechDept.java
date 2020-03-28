package com.xiaobao.pro_manage_sys.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.xiaobao.pro_manage_sys.entity.user.RecDept;

import javax.persistence.*;

@Entity
@Table(name = "tech_dept")
public class TechDept {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "tech_id")
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "principal")
  private String principal;

  @Column(name = "tel")
  private String tel;

  @Column(name = "email")
  private String email;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, targetEntity = RecDept.class)
  @JoinColumn(name = "rcd_id")
  @JsonBackReference
  private RecDept recDept;

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

  public String getPrincipal() {
    return principal;
  }

  public void setPrincipal(String principal) {
    this.principal = principal;
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

  public RecDept getRecDept() {
    return recDept;
  }

  public void setRecDept(RecDept recDept) {
    this.recDept = recDept;
  }
}
