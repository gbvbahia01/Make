package br.com.gbvbahia.maker.onetoone;

import javax.persistence.OneToOne;

import org.apache.commons.logging.Log;

import br.com.gbvbahia.maker.log.LogInfo;

public class Husband {

  private static Log logger = LogInfo.getLog(Husband.class.getName());
  private int age;

  private String id;

  @OneToOne
  private Wife wife;

  public Husband() {
    super();
    logger.warn("A Husband was created");
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
