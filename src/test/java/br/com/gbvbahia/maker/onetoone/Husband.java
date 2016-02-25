package br.com.gbvbahia.maker.onetoone;

import javax.persistence.OneToOne;

public class Husband {

  private int age;

  private String id;

  @OneToOne
  private Wife wife;

  public Husband() {
    super();
  }

  public int getAge() {
    return this.age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Wife getWife() {
    return this.wife;
  }

  public void setWife(Wife wife) {
    this.wife = wife;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

}
