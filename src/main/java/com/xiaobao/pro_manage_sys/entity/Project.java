package com.xiaobao.pro_manage_sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xiaobao.pro_manage_sys.entity.user.Applicant;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade = CascadeType.ALL)
  @JsonIgnore
  List<Score> scores = new ArrayList<>(0);

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "pro_id")
  private Integer id;

  @Column(name = "pro_name")
  private String proName;

  @Column(name = "score")
  private Integer score;

  @Column(name = "funds")
  private Integer funds;

  @Column(name = "pro_type")
  private String proType;

  @Column(name = "subject")
  private String subject;
  /** 项目申报状态：0---项目更新（唯一） 1---未通过 2---打回修改 3---初级审核 4---二级审核 5---三级审核 6---待分配专家 7---专家评审 8---已通过 */
  @Column(name = "pro_status")
  private Integer proStatus;

  @Column(name = "time")
  private Integer time;

  @Column(name = "keywords")
  private String keywords;

  @Column(name = "[desc]")
  private String desc;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinColumn(name = "app_id")
  //  @JsonIgnore
  private Applicant applicant;

  public Project() {}

  public List<Score> getScores() {
    return scores;
  }

  public void setScores(List<Score> scores) {
    this.scores = scores;
  }

  public Integer getTime() {
    return time;
  }

  public void setTime(Integer time) {
    this.time = time;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public Integer getProStatus() {
    return proStatus;
  }

  public void setProStatus(Integer proStatus) {
    this.proStatus = proStatus;
  }

  public String getProName() {
    return proName;
  }

  public void setProName(String proName) {
    this.proName = proName;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public Integer getFunds() {
    return funds;
  }

  public void setFunds(Integer funds) {
    this.funds = funds;
  }

  public String getProType() {
    return proType;
  }

  public void setProType(String proType) {
    this.proType = proType;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public Applicant getApplicant() {
    return applicant;
  }

  public void setApplicant(Applicant applicant) {
    this.applicant = applicant;
  }
}
