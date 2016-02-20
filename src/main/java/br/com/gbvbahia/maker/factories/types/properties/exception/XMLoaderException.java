package br.com.gbvbahia.maker.factories.types.properties.exception;

/**
 * Usada somente se não encontrar o arquivo make.xml
 * 
 * @since v.1 01/02/2016
 * @author Guilherme
 */
public class XMLoaderException extends RuntimeException {

  public XMLoaderException(Throwable throwable, String message) {
    super(message, throwable);
  }

  /**
   * @since v.1 01/02/2016
   */
  private static final long serialVersionUID = 1L;

}
