package com.xiaobao.pro_manage_sys.entity;

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

  @Column(name = "new_file_name")
  private String newFileName;

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

  public LocalFile() {}

  public LocalFile(
      String oldFileName,
      String newFileName,
      String ext,
      long size,
      String type,
      String date,
      String path) {
    this.oldFileName = oldFileName;
    this.newFileName = newFileName;
    this.ext = ext;
    this.size = size;
    this.type = type;
    this.date = date;
    this.path = path;
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

  public String getNewFileName() {
    return newFileName;
  }

  public void setNewFileName(String newFileName) {
    this.newFileName = newFileName;
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
