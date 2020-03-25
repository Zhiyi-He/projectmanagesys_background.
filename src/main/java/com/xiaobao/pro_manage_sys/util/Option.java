package com.xiaobao.pro_manage_sys.util;

import java.util.ArrayList;
import java.util.List;

public class Option {

  private String label;
  private Object value;
  private List<Option> children = new ArrayList<>();

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public List<Option> getChildren() {
    return children;
  }

  public void setChildren(List<Option> children) {
    this.children = children;
  }

  public Option(String label, Object value) {
    this.label = label;
    this.value = value;
  }
}
