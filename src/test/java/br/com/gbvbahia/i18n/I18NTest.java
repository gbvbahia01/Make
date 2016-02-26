package br.com.gbvbahia.i18n;

import junit.framework.TestCase;

import org.junit.Test;

/**
 *
 * @author Guilherme
 */
public class I18NTest extends TestCase {

  public I18NTest() {
    super("I18NTest: Internacionalização");
  }

  @Test
  public void testInternacionalizacao() throws Exception {
    assertEquals("Internacionalização não está funcionando", "Valor", I18N.getMsg("chave"));
  }
}
