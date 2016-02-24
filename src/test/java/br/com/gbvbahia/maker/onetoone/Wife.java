package br.com.gbvbahia.maker.onetoone;

import javax.persistence.OneToOne;

import org.apache.commons.logging.Log;

import br.com.gbvbahia.maker.log.LogInfo;

public class Wife {

  private static Log logger = LogInfo.getLog(Wife.class.getName());

  private int age;

  private String id;

  public Wife() {
    super();
    if (logger == null) {
      logger = LogInfo.getLog(Wife.class.getName());
    }
    logger.warn("A Wife was created");
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
