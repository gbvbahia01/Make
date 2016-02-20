package br.com.gbvbahia.maker.works.common;

/**
 * Utilizada para guardar a classe que será criada para a colção e a quantidade máxima e mínima.
 *
 * @since v.1 16/06/2012
 * @author Guilherme
 */
public class CollectionsHelper<C> {

  /**
   * Tipo do objeto a ser criado.
   */
  private Class<C> clazz;
  /**
   * Mínimo a ser criado.
   */
  private Integer min;
  /**
   * Máximo a ser criado.
   */
  private Integer max;

  /**
   * Tipo do objeto a ser criado.
   *
   * @return Class.
   */
  public Class<C> getClazz() {
    return this.clazz;
  }

  /**
   * Máximo a ser criado.
   *
   * @return Integer.
   */
  public Integer getMax() {
    return this.max;
  }

  /**
   * Mínimo a ser criado.
   *
   * @return Integer.
   */
  public Integer getMin() {
    return this.min;
  }

  /**
   * Contrutor que definie os valores de auxilio para a geração dos objetos a serem inseridos na
   * coleção.
   *
   * @param clazzInCollection Tipo do objeto a ser criado para a lista.
   * @param minInCollection Quantidade mánima a ser criada para ser inserida na coleção.
   * @param maxInCollection Quantidade máxima a ser criada para ser inserida na coleção.
   */
  public CollectionsHelper(final Class<C> clazzInCollection, final Integer minInCollection,
      final Integer maxInCollection) {
    this.clazz = clazzInCollection;
    this.min = minInCollection;
    this.max = maxInCollection;
  }

  @Override
  public String toString() {
    return "CollectionsHelper{" + "clazz=" + this.clazz + ", min=" + this.min + ", max=" + this.max
        + '}';
  }


}
