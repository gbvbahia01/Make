package br.com.gbvbahia.maker.factories.types.managers;

import java.util.ArrayList;
import java.util.List;

import br.com.gbvbahia.maker.types.complex.MakeBigDecimal;
import br.com.gbvbahia.maker.types.complex.MakeBigInteger;
import br.com.gbvbahia.maker.types.complex.MakeStringNumber;
import br.com.gbvbahia.maker.types.primitives.common.MakeNumber;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeByte;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeDouble;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeFloat;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeLong;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeShort;

/**
 * Use to manipulate a lot of classes that can make a lot of type of numbers.
 * 
 * @since v.1 12/12/2012
 * @author Guilherme Braga
 *
 */
public class MakeNumberManager {

  /**
   * All subclass of MakeNumber.
   */
  private final List<MakeNumber> factoriesNumber;

  /**
   * Load all Make numbers.
   */
  public MakeNumberManager() {
    super();
    this.factoriesNumber = new ArrayList<MakeNumber>();
  }

  /**
   * Call when the test start.
   */
  public void loadNumberFactories() {
    this.factoriesNumber.add(new MakeBigDecimal());
    this.factoriesNumber.add(new MakeBigInteger());
    this.factoriesNumber.add(new MakeByte());
    this.factoriesNumber.add(new MakeDouble());
    this.factoriesNumber.add(new MakeFloat());
    this.factoriesNumber.add(new MakeInteger());
    this.factoriesNumber.add(new MakeLong());
    this.factoriesNumber.add(new MakeShort());
    this.factoriesNumber.add(new MakeStringNumber());
  }

  /**
   * Call when the test over.
   */
  public void clear() {
    this.factoriesNumber.clear();
  }

  public List<MakeNumber> getFactoriesNumber() {
    return this.factoriesNumber;
  }
}
