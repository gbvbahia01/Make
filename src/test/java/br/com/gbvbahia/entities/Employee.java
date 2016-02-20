package br.com.gbvbahia.entities;

import java.util.Calendar;

import javax.persistence.Entity;

@Entity
public class Employee {

  private String name;
  private Integer age;
  private int monthBorn;
  private String adress;
  private Calendar born;

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return this.age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getAdress() {
    return this.adress;
  }

  public void setAdress(String adress) {
    this.adress = adress;
  }

  public Calendar getBorn() {
    return this.born;
  }

  public void setBorn(Calendar born) {
    this.born = born;
  }

  public int getMonthBorn() {
    return this.monthBorn;
  }

  public void setMonthBorn(int monthBorn) {
    this.monthBorn = monthBorn;
  }
}
