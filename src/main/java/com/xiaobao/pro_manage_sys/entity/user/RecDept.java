package com.xiaobao.pro_manage_sys.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.xiaobao.pro_manage_sys.entity.TechDept;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

  @Column(name = "tel")
  private String tel;

  @Column(name = "contact")
  private String contact;

  @Column(name = "legalPerson")
  private String legalPerson;

  @Column(name = "email")
  private String email;

  @Column(name = "postal_code")
  private String postalCode;

  @Column(name = "address")
  private String address;

  @OneToMany(
      fetch = FetchType.LAZY,
      mappedBy = "recDept",
      cascade = CascadeType.ALL,
      targetEntity = RepDept.class)
  @JsonIgnore
  private Set<RepDept> repDepts = new HashSet<>(0);

  @OneToMany(
      fetch = FetchType.LAZY,
      mappedBy = "recDept",
      cascade = CascadeType.ALL,
      targetEntity = TechDept.class)
  @JsonManagedReference
  private List<TechDept> techDepts = new ArrayList<>(0);

  public RecDept() {}

  public RecDept(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public List<TechDept> getTechDepts() {
    return techDepts;
  }

  public void setTechDepts(List<TechDept> techDepts) {
    this.techDepts = techDepts;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getLegalPerson() {
    return legalPerson;
  }

  public void setLegalPerson(String legalPerson) {
    this.legalPerson = legalPerson;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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
