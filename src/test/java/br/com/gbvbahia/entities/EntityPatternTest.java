package br.com.gbvbahia.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @since v.1
 * @author Guilherme
 */
public class EntityPatternTest {


  @NotNull
  @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`"
      + "{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:" + "[a-z0-9-]*[a-z0-9])?")
  private String email;

  @Override
  public String toString() {
    return "EntityPattern{" + " email=" + this.email + '}';
  }
}
