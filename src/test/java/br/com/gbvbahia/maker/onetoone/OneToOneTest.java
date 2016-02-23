package br.com.gbvbahia.maker.onetoone;

import org.junit.Assert;
import org.junit.Test;

import br.com.gbvbahia.maker.MakeEntity;

public class OneToOneTest {


  @Test
  public void testHusbandCreation() {
    Husband husband = MakeEntity.makeEntity(Husband.class);
    Assert.assertNotNull("husband was not made", husband);

    Assert.assertTrue("The husbands are not the same",
        husband.equals(husband.getWife().getHusband()));
    Assert.assertTrue("The husbands are not the same",
        husband.getWife().equals(husband.getWife().getHusband().getWife()));
  }


  /**
   * BUG Detected needs to be the same
   */
  @Test
  public void testWifeCreation() {
    Wife wife = MakeEntity.makeEntity(Wife.class);
    Assert.assertNotNull("wife was not made", wife);
    Assert.assertNotNull("wife's husband was not made", wife.getHusband());

    Assert.assertTrue("The wifes are not the same", wife.equals(wife.getHusband().getWife()));
    Assert.assertTrue("The husbands are not the same",
        wife.getHusband().equals(wife.getHusband().getWife().getHusband()));
  }
}
