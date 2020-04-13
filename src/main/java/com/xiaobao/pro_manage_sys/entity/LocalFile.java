package com.xiaobao.pro_manage_sys.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "file")
public class LocalFile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "old_file_name")
  private String oldFileName;

  @Column(name = "name")
  private String name;

  @Column(name = "title")
  private String title;

  @Column(name = "ext")
  private String ext;

  @Column(name = "size")
  private long size;

  @Column(name = "type")
  private String type;

  @Column(name = "date")
  private String date;

  @Column(name = "file_type")
  private Integer fileType;

  @Column(name = "path")
  private String path;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "pro_id")
  @JsonBackReference
  private Project project;

  public LocalFile() {}

  public LocalFile(
      String oldFileName,
      String name,
      String title,
      String ext,
      long size,
      String type,
      String date,
      String path) {
    this.oldFileName = oldFileName;
    this.name = name;
    this.title = title;
    this.ext = ext;
    this.size = size;
    this.type = type;
    this.date = date;
    this.path = path;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Integer getFileType() {
    return fileType;
  }

  public void setFileType(Integer fileType) {
    this.fileType = fileType;
  }

  public String getOldFileName() {
    return oldFileName;
  }

  public void setOldFileName(String oldFileName) {
    this.oldFileName = oldFileName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getExt() {
    return ext;
  }

  public void setExt(String ext) {
    this.ext = ext;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
