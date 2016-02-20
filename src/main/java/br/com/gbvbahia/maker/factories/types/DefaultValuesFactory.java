package br.com.gbvbahia.maker.factories.types;

import java.lang.reflect.Field;

import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.types.primitives.MakeBoolean;
import br.com.gbvbahia.maker.types.primitives.MakeCharacter;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeDouble;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeFloat;

/**
 * Essa clase cria valores default para os fields informados.<br>
 * Primitivos recebem 0 e false.<br>
 * Objetos recebem null como valor.<br>
 * Data Type Default Value (for fields)<br>
 * byte 0<br>
 * short 0<br>
 * int 0<br>
 * long 0L<br>
 * float 0.0f<br>
 * double 0.0d<br>
 * char '\u0000'<br>
 * String (or any object) null<br>
 * boolean false
 * 
 * @author P9924903
 *
 */
public class DefaultValuesFactory implements ValueFactory {

  @Override
  public <T> void makeValue(Field field, T entity, String... testName)
      throws IllegalAccessException, IllegalArgumentException {
    if (!field.getType().isPrimitive()) {
      field.set(entity, null);
      return;
    }
    if (MakeDouble.isDouble(field) || MakeFloat.isFloat(field)) {
      field.set(entity, 0.0f);
      return;
    }
    if (MakeBoolean.isBoolean(field)) {
      field.set(entity, false);
      return;
    }
    if (MakeCharacter.isCharacter(field)) {
      field.set(entity, '\u0000');
      return;
    }
    field.set(entity, Byte.valueOf((byte) 0));
  }

  @Override
  public <T> boolean isWorkWith(Field field, T entity) {
    return true;
  }

}
