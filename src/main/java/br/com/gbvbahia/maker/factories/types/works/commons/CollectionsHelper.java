package br.com.gbvbahia.maker.factories.types.works.commons;

/**
 * Helps to keep information about values and amount of objects that will be made in makeValue
 * method.<br>
 * 
 *
 * @since v.1 16/06/2012
 * @author Guilherme
 */
public class CollectionsHelper<C> {

  /**
   * Class of object the will be made.
   */
  private Class<C> clazz;
  /**
   * The minimum value that can be made.
   */
  private Integer min;
  /**
   * The maximum value that can be made.
   */
  private Integer max;

  public Class<C> getClazz() {
    return this.clazz;
  }

  public Integer getMax() {
    return this.max;
  }

  public Integer getMin() {
    return this.min;
  }

  /**
   * Pass the necessary information to create a object in makeValue method.
   * 
   * @param clazzInCollection The class that object must have.
   * @param minInCollection The minimum value the object can be.
   * @param maxInCollection The maximum value the object can be.
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
