package br.com.gbvbahia.maker.factories;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;

import br.com.gbvbahia.entities.Jsr303ReadSetupTest;
import br.com.gbvbahia.maker.MakeEntity;
import br.com.gbvbahia.maker.factories.types.properties.exception.XMLoaderException;

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
   * Test for a file that not exists. At this case a XMLoaderException must be launched.
   */
  @Test(expected = XMLoaderException.class)
  public void testXmlLoaderException() {
    Factory.loadSetup("fileNotFound.xml");
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
    Assert.assertFalse("Make default does not ignore the JSR303", Factory.SETUP.ignoreJsr303());
    Assert.assertTrue("Make default reads the JSR303", Factory.SETUP.readJsr303());
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

    nullFields = MakeEntity.makeEntity(Jsr303ReadSetupTest.class, "testSetupKeyField");
    Assert.assertNull("Default Integer must be null", nullFields.getDefaultInt());
    Assert.assertNull("Default Double must be null", nullFields.getDefaultDouble());
    Assert.assertNull("Default String must be null", nullFields.getDefaultString());
    Assert.assertNotNull("Cannot be null because the field is mapped by xml.",
        nullFields.getKeyField());
    Assert.assertTrue(
        "keyField has no valid value: " + nullFields.getKeyField(),
        (nullFields.getKeyField() == 1.0) || (nullFields.getKeyField() == 2.0)
            || (nullFields.getKeyField() == 5.0));
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
    Assert.assertNotNull("Cannot be null because the annotation @NotNull.",
        someFields.getNotNullCharacter());

    someFields = MakeEntity.makeEntity(Jsr303ReadSetupTest.class, "testSetupKeyField");
    Assert.assertNotNull("Cannot be null because the keyField is mapped as a keyfield in xml.",
        someFields.getKeyField());
    Assert.assertTrue(
        "keyField has no valid value: " + someFields.getKeyField(),
        (someFields.getKeyField() == 1.0) || (someFields.getKeyField() == 2.0)
            || (someFields.getKeyField() == 5.0));
    Assert.assertNotNull("Cannot be null because the annotation @NotNull.",
        someFields.getNotNullCharacter());
    Assert.assertTrue(
        "Can be null because the null node is some and annotation Null or NotNull was not put.",
        (someFields.getNotKeyField() == null) || (someFields.getNotKeyField() != null));
  }

  @Test
  public void testFileMakeJerReadNullAll() {
    Factory.loadSetup("make_read_all.xml");
    Jsr303ReadSetupTest allFields = MakeEntity.makeEntity(Jsr303ReadSetupTest.class);
    Assert.assertNull("Default Integer must be null", allFields.getDefaultInt());
    Assert.assertNull("Default Double must be null", allFields.getDefaultDouble());
    Assert.assertNull("Default String must be null", allFields.getDefaultString());
    Assert.assertNull("Must be null because the test name was not informaded.",
        allFields.getKeyField());
    Assert.assertNotNull("Cannot be null because the annotation @NotNull.",
        allFields.getNotNullCharacter());
    Assert.assertNull(
        "Must be null because the null node is all and annotation @NotNull was not put.",
        allFields.getNotKeyField());

    allFields = MakeEntity.makeEntity(Jsr303ReadSetupTest.class, "testSetupKeyField");
    Assert.assertNotNull("Cannot be null because the keyField is mapped as a keyfield in xml.",
        allFields.getKeyField());
    Assert.assertTrue(
        "keyField has no valid value: " + allFields.getKeyField(),
        (allFields.getKeyField() == 1.0) || (allFields.getKeyField() == 2.0)
            || (allFields.getKeyField() == 5.0));
    Assert.assertNull(
        "Must be null because the null node is all and annotation @NotNull was not put.",
        allFields.getNotKeyField());
  }

  @Test
  public void testFileMakeJsrIgnoreNullAll() {
    Factory.loadSetup("make_ignore_all.xml");
    Jsr303ReadSetupTest allFields = MakeEntity.makeEntity(Jsr303ReadSetupTest.class);
    Assert.assertNull("Default Integer must be null", allFields.getDefaultInt());
    Assert.assertNull("Default Double must be null", allFields.getDefaultDouble());
    Assert.assertNull("Default String must be null", allFields.getDefaultString());
    Assert.assertNull("Must be null because the test name was not informaded.",
        allFields.getKeyField());
    Assert.assertNull("JSR303 must be ignored (@NotNull) and this field is not mapped.",
        allFields.getNotNullCharacter());
    Assert.assertNull("Must be null because the null node is all and this field is not mapped.",
        allFields.getNotKeyField());

    allFields = MakeEntity.makeEntity(Jsr303ReadSetupTest.class, "testSetupKeyField");
    Assert.assertNull("Default Integer must be null", allFields.getDefaultInt());
    Assert.assertNull("Default Double must be null", allFields.getDefaultDouble());
    Assert.assertNull("Default String must be null", allFields.getDefaultString());
    Assert.assertNotNull("Cannot be null because the test name was informaded.",
        allFields.getKeyField());
    Assert.assertTrue(
        "keyField has no valid value: " + allFields.getKeyField(),
        (allFields.getKeyField() == 1.0) || (allFields.getKeyField() == 2.0)
            || (allFields.getKeyField() == 5.0));
    Assert.assertNull("JSR303 must be ignored (@NotNull) and this field is not mapped.",
        allFields.getNotNullCharacter());
    Assert.assertNull("Must be null because the null node is all and this field is not mapped.",
        allFields.getNotKeyField());
  }

  @Test
  public void testFileMakeJsrIgnoreNullNever() {
    Factory.loadSetup("make_ignore_never.xml");
    Jsr303ReadSetupTest neverFields = MakeEntity.makeEntity(Jsr303ReadSetupTest.class);
    Assert.assertNotNull("Default Integer cannot be null", neverFields.getDefaultInt());
    Assert.assertNotNull("Default Double cannot be null", neverFields.getDefaultDouble());
    Assert.assertNotNull("Default String cannot be null", neverFields.getDefaultString());
    Assert.assertNotNull("keyField cannot be null because the test name was not informaded.",
        neverFields.getKeyField());
    Assert.assertNotNull("NotNullCharacter cannot be null.", neverFields.getNotNullCharacter());
    Assert.assertNotNull("NotKeyField cannot be null.", neverFields.getNotKeyField());
    neverFields = MakeEntity.makeEntity(Jsr303ReadSetupTest.class, "testSetupKeyField");
    Assert.assertNotNull("Default Integer cannot be null", neverFields.getDefaultInt());
    Assert.assertNull("Default Double must be null because of isDefault field",
        neverFields.getDefaultDouble());
    Assert.assertNotNull("Default String cannot be null", neverFields.getDefaultString());
    Assert.assertNotNull("keyField cannot be null because the test name was not informaded.",
        neverFields.getKeyField());
    Assert.assertTrue(
        "keyField has no valid value: " + neverFields.getKeyField(),
        (neverFields.getKeyField() == 1.0) || (neverFields.getKeyField() == 2.0)
            || (neverFields.getKeyField() == 5.0));
    Assert.assertNotNull("NotNullCharacter cannot be null.", neverFields.getNotNullCharacter());
    Assert.assertNotNull("NotKeyField cannot be null.", neverFields.getNotKeyField());
  }
}
