package br.com.gbvbahia.maker.factories;

import br.com.gbvbahia.entities.Jsr303ReadSetupTest;
import br.com.gbvbahia.maker.MakeEntity;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;

/**
 * This class test the inner class Setup inside of class Factory.
 * 
 * @since v.2 02/21/2016
 * @author guilhermebraga
 */
public class SetupTest {

  @After
  public void rollback() {
    Factory.loadSetup("make.xml");
  }

  /**
   * Test the first "if" on Setup.useDefaultValuesFactory.<br>
   * This test is evaluating setup working with make.xml.<br>
   * <JSR303 value="read" /><!-- read or ignore --><br>
   * <Null value="never" /><!-- never some all -->
   */
  @Test
  public void testDefaultValuesFactorySetupMakeXml() {
    Factory.loadSetup("make.xml");
    Jsr303ReadSetupTest nullFields = MakeEntity.makeEntity(Jsr303ReadSetupTest.class);
    Assert.assertNull("Default Integer must be null", nullFields.getDefaultInt());
    Assert.assertNull("Default Double must be null", nullFields.getDefaultDouble());
    Assert.assertNull("Default String must be null", nullFields.getDefaultString());
    Assert.assertNotNull(
        "Cannot be null because the null node is never and annotation Null was not put.",
        nullFields.getKeyField());
    Assert.assertNotNull("Cannot be null because the annotation @NotNull.",
        nullFields.getNotNullCharacter());
    Assert.assertNotNull(
        "Cannot be null because the null node is never and annotation Null was not put.",
        nullFields.getNotKeyField());
  }

  @Test
  public void testFileMakeJsrReadNullSome() {
    Factory.loadSetup("make_read_some.xml");
    Jsr303ReadSetupTest someFields = MakeEntity.makeEntity(Jsr303ReadSetupTest.class);
    Assert.assertNull("Default Integer must be null", someFields.getDefaultInt());
    Assert.assertNull("Default Double must be null", someFields.getDefaultDouble());
    Assert.assertNull("Default String must be null", someFields.getDefaultString());
    Assert.assertNotNull("Cannot be null because the keyField is mapped as a keyfield in xml.",
        someFields.getKeyField());
    Assert.assertNotNull("Cannot be null because the annotation @NotNull.",
        someFields.getNotNullCharacter());
    Assert.assertTrue(
        "Can be null because the null node is some and annotation Null or NotNull was not put.",
        (someFields.getNotKeyField() == null) || (someFields.getNotKeyField() != null));
  }

}
