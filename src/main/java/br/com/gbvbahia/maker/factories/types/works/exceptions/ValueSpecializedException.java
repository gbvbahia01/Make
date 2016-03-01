package br.com.gbvbahia.maker.factories.types.works.exceptions;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.properties.exception.MakeCreationException;
import br.com.gbvbahia.maker.factories.types.works.commons.ValueSpecializedFactory;

/**
 * When a problem occurs in a ValueSpecialized make class this exception will be launched.
 *
 * @since v.1 16/06/2012
 * @author Guilherme
 */
public class ValueSpecializedException extends MakeCreationException {

  private static final long serialVersionUID = 1517631196990226457L;
  /**
   * The work class where the problem occurs.
   */
  private Class<? extends ValueSpecializedFactory> origemClass;
  /**
   * Used at msg.properties.
   */
  private String msgPropertieKey;
  /**
   * Var args mesg at msg.properties {0}...
   */
  private String[] varArgMsgVariations;

  /**
   * Pass the information about the error.
   * 
   * @param origem Where the problem occurs.
   * @param workKey Used at msg.properties.
   * @param variations a varag for message.
   */
  public ValueSpecializedException(Class<? extends ValueSpecializedFactory> origem,
      String msgPropertieKey, String[] variations) {
    this(origem, msgPropertieKey, variations, null);
  }

  /**
   * Pass the information about the error.
   * 
   * @param origem Where the problem occurs.
   * @param msgPropertieKey Used at msg.properties.
   */
  public ValueSpecializedException(Class<? extends ValueSpecializedFactory> origem,
      String msgPropertieKey) {
    this(origem, msgPropertieKey, new String[] {}, null);
  }

  /**
   * Pass the information about the error.
   * 
   * @param origem Where the problem occurs.
   * @param msgPropertieKey Used at msg.properties.
   * @param cause If a real exception occurs pass it here.
   */
  public ValueSpecializedException(Class<? extends ValueSpecializedFactory> origem,
      String msgPropertieKey, Throwable cause) {
    this(origem, msgPropertieKey, new String[] {}, cause);
  }

  /**
   * Pass the information about the error.
   * 
   * @param origem Where the problem occurs.
   * @param msgPropertieKey Used at msg.properties.
   * @param variations a varag for message.
   * @param cause If a real exception occurs pass it here.
   */
  public ValueSpecializedException(Class<? extends ValueSpecializedFactory> origem,
      String msgPropertieKey, String[] variations, Throwable cause) {
    super(I18N.getMsg(msgPropertieKey, (Object[]) variations), cause);
    this.origemClass = origem;
    this.msgPropertieKey = msgPropertieKey;
    this.varArgMsgVariations = variations;
  }

  /**
   * chave no msg.properties.
   *
   * @return chave a ser buscada no msg.properties.
   */
  public String getMsgPropertieKey() {
    return this.msgPropertieKey;
  }

  /**
   * Preenche as lacunas no msg.properties {0}...
   *
   * @return String[] ou null se não houver.
   */
  public String[] getVarArgMsgVariations() {
    return this.varArgMsgVariations;
  }

  /**
   * Nome da classe onde a exceção foi gerada.
   *
   * @return Nome da classe.
   */
  public Class<? extends ValueSpecializedFactory> getOrigemClass() {
    return this.origemClass;
  }
}
