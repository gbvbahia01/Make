package br.com.gbvbahia.entities;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class Jsr303ReadSetupTest {

  @Id
  private Long imId;

  @Null
  private Integer defaultInt = 3;
  @Null
  private Double defaultDouble = 0.3;
  @Null
  private String defaultString = "NotNull";

  private Float keyField;
  @NotNull
  private Character notNullCharacter;

  private Byte notKeyField;

  public Integer getDefaultInt() {
    return this.defaultInt;
  }

  public void setDefaultInt(Integer defaultInt) {
    this.defaultInt = defaultInt;
  }

  public Double getDefaultDouble() {
    return this.defaultDouble;
  }

  public void setDefaultDouble(Double defaultDouble) {
    this.defaultDouble = defaultDouble;
  }

  public String getDefaultString() {
    return this.defaultString;
  }

  public void setDefaultString(String defaultString) {
    this.defaultString = defaultString;
  }

  public Float getKeyField() {
    return this.keyField;
  }

  public void setKeyField(Float keyField) {
    this.keyField = keyField;
  }

  public Character getNotNullCharacter() {
    return this.notNullCharacter;
  }

  public void setNotNullCharacter(Character notNullCharacter) {
    this.notNullCharacter = notNullCharacter;
  }

  public Byte getNotKeyField() {
    return this.notKeyField;
  }

  public void setNotKeyField(Byte notKeyField) {
    this.notKeyField = notKeyField;
  }

  public Long getImId() {
    return this.imId;
  }

  public void setImId(Long imId) {
    this.imId = imId;
  }

}
