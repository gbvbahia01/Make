package br.com.gbvbahia.maker.onetoone;

import javax.persistence.OneToOne;

public class Wife {

  private int age;

  private String id;

  public Wife() {
    super();
  }

  @OneToOne(mappedBy = "wife")
  private Husband husband;

  public int getAge() {
    return this.age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Husband getHusband() {
    return this.husband;
  }

  public void setHusband(Husband husband) {
    this.husband = husband;
  }
}
