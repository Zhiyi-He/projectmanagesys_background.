package com.xiaobao.pro_manage_sys.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.xiaobao.pro_manage_sys.entity.user.Expert;

import javax.persistence.*;

@Entity
@Table(name = "score")
public class Score {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "pe_id")
  private Integer id;

  @Column(name = "score")
  private int score;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "pro_id")
  @JsonBackReference
  private Project project;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinColumn(name = "exp_id")
  private Expert expert;

  public Score(Project project, Expert expert) {
    this.project = project;
    this.expert = expert;
  }

  public Score() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public Expert getExpert() {
    return expert;
  }

  public void setExpert(Expert expert) {
    this.expert = expert;
  }
}
