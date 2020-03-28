package com.xiaobao.pro_manage_sys.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.xiaobao.pro_manage_sys.entity.Principal;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rep_dept")
public class RepDept {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rpd_id")
  private Integer id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "dept_name")
  private String deptName;

  @Column(name = "pro_num")
  private Integer proNum;

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

  @Column(name = "deptType")
  private Integer deptType;

  @Column(name = "rpd_status")
  private Integer rpdStatus;

  @OneToMany(
      fetch = FetchType.LAZY,
      mappedBy = "repDept",
      cascade = CascadeType.ALL,
      targetEntity = Applicant.class)
  @JsonIgnore
  private List<Applicant> applicants = new ArrayList<>(0);

  @OneToMany(
      fetch = FetchType.LAZY,
      mappedBy = "repDept",
      cascade = CascadeType.ALL,
      targetEntity = Principal.class)
  @JsonManagedReference
  private List<Principal> principals = new ArrayList<>(0);

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, targetEntity = RecDept.class)
  @JoinColumn(name = "rcd_id")
  private RecDept recDept;

  public RepDept() {}

  public RepDept(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public Integer getRpdStatus() {
    return rpdStatus;
  }

  public void setRpdStatus(Integer rpdStatus) {
    this.rpdStatus = rpdStatus;
  }

  public List<Principal> getPrincipals() {
    return principals;
  }

  public void setPrincipals(List<Principal> principals) {
    this.principals = principals;
  }

  public Integer getDeptType() {
    return deptType;
  }

  public void setDeptType(Integer deptType) {
    this.deptType = deptType;
  }

  public String getLegalPerson() {
    return legalPerson;
  }

  public void setLegalPerson(String legalPerson) {
    this.legalPerson = legalPerson;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
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

  public RecDept getRecDept() {
    return recDept;
  }

  public void setRecDept(RecDept recDept) {
    this.recDept = recDept;
  }

  public List<Applicant> getApplicants() {
    return applicants;
  }

  public void setApplicants(List<Applicant> applicants) {
    this.applicants = applicants;
  }

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  public Integer getProNum() {
    return proNum;
  }

  public void setProNum(Integer proNum) {
    this.proNum = proNum;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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
