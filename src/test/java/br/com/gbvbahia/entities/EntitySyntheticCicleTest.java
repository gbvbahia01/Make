package br.com.gbvbahia.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * @since v.1
 * @author Guilherme
 */
@Entity
public class EntitySyntheticCicleTest {

  @NotNull
  private String nome;

  @NotNull
  private EntitySyntheticTest cicle;

  @NotNull
  @OneToOne(mappedBy = "cicleTest")
  private EntitySyntheticTest mapped;

  @Override
  public String toString() {
    return "EntitySyntheticCicleTest{" + "nome=" + this.nome + "mapped=" + this.mapped + '}';
  }


}
