package br.com.gbvbahia.maker.onetoone;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import br.com.gbvbahia.maker.MakeEntity;
import br.com.gbvbahia.maker.factories.Factory;
import br.com.gbvbahia.maker.log.LogInfo;

public class OneToOneTest extends TestCase {

  @After
  public void rollback() {
    Factory.loadSetup("make.xml");
  }


  @Test
  public void testHusbandCreationReadNever() {
    Factory.loadSetup("make.xml");
    Husband husband = MakeEntity.makeEntity(Husband.class);
    Assert.assertNotNull("husband was not made", husband);

    Assert.assertTrue("The husbands are not the same",
        husband.equals(husband.getWife().getHusband()));
    Assert.assertTrue("The husbands are not the same",
        husband.getWife().equals(husband.getWife().getHusband().getWife()));
  }


  @Test
  public void testWifeCreationReadNever() {
    Factory.loadSetup("make.xml");
    Wife wife = MakeEntity.makeEntity(Wife.class);
    Assert.assertNotNull("wife was not made", wife);
    Assert.assertNotNull("wife's husband was not made", wife.getHusband());
    Assert.assertTrue("The wifes are not the same", wife.equals(wife.getHusband().getWife()));
    Assert.assertTrue("The husbands are not the same",
        wife.getHusband().equals(wife.getHusband().getWife().getHusband()));
  }

  @Test
  public void testHusbandCreationReadSome() {
    Factory.loadSetup("make_read_some.xml");
    Husband husband = MakeEntity.makeEntity(Husband.class);
    Assert.assertNotNull("husband was not made", husband);
    if (husband.getWife() == null) {
      Assert.assertTrue("Some can have wife null", true);
      LogInfo.getLog(OneToOneTest.class.getName() + " testHusbandCreationReadSome").info(
          "A husband´s wife was not created");
      return;
    }
    if (husband.getWife().getHusband() == null) {
      Assert.assertTrue("Some can husbend of wife null", true);
      LogInfo.getLog(OneToOneTest.class.getName() + " testHusbandCreationReadSome").info(
          "A husband for wife was not created");
      return;
    }
    Assert.assertTrue("The husbands are not the same",
        husband.getWife().equals(husband.getWife().getHusband().getWife()));
  }

  @Test
  public void testHusbandCreationReadAll() {
    Factory.loadSetup("make_read_all.xml");
    Husband husband = MakeEntity.makeEntity(Husband.class);
    Assert.assertNotNull("husband was not made", husband);
    Assert.assertNull("The wife is not null", husband.getWife());
  }

  @Test
  public void testWifeCreationReadAll() {
    Factory.loadSetup("make_read_all.xml");
    Wife wife = MakeEntity.makeEntity(Wife.class);
    Assert.assertNotNull("wife was not made", wife);
    Assert.assertNull("wife's husband is not null", wife.getHusband());
  }

  @Test
  public void testHusbandCreationIgnoreNever() {
    Factory.loadSetup("make_ignore_never.xml");
    Husband husband = MakeEntity.makeEntity(Husband.class);
    Assert.assertNotNull("husband was not made", husband);

    Assert.assertTrue("The husbands are not the same",
        husband.equals(husband.getWife().getHusband()));
    Assert.assertTrue("The husbands are not the same",
        husband.getWife().equals(husband.getWife().getHusband().getWife()));
  }

  @Test
  public void testWifeCreationIgnoreNever() {
    Factory.loadSetup("make_ignore_never.xml");
    Wife wife = MakeEntity.makeEntity(Wife.class);
    Assert.assertNotNull("wife was not made", wife);
    Assert.assertNotNull("wife's husband was not made", wife.getHusband());
    Assert.assertTrue("The wifes are not the same", wife.equals(wife.getHusband().getWife()));
    Assert.assertTrue("The husbands are not the same",
        wife.getHusband().equals(wife.getHusband().getWife().getHusband()));
  }

  @Test
  public void testHusbandCreationIgnoreAll() {
    Factory.loadSetup("make_ignore_all.xml");
    Husband husband = MakeEntity.makeEntity(Husband.class);
    Assert.assertNotNull("husband was not made", husband);
    Assert.assertNull("The wife is not null", husband.getWife());
  }

  @Test
  public void testWifeCreationIgnoreAll() {
    Factory.loadSetup("make_ignore_all.xml");
    Wife wife = MakeEntity.makeEntity(Wife.class);
    Assert.assertNotNull("wife was not made", wife);
    Assert.assertNull("The husband is not null", wife.getHusband());
  }
}
