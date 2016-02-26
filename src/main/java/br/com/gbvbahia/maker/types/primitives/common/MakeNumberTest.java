package br.com.gbvbahia.maker.types.primitives.common;

import org.junit.Assert;
import org.junit.Test;

import br.com.gbvbahia.maker.types.primitives.numbers.MakeLong;

public class MakeNumberTest {

  @Test
  public void testMaxDigits() {
    MakeLong madeLong = new MakeLong();
    Assert.assertEquals("Max digits is not correct - 18", Long.valueOf(999999999999999999L),
        madeLong.maxDigits(18));
    Assert.assertEquals("Max digits is not correct - 21", Long.valueOf(Long.MAX_VALUE),
        madeLong.maxDigits(21));
    Assert.assertEquals("Max digits is not correct - 3", Long.valueOf(999L), madeLong.maxDigits(3));
  }

}
