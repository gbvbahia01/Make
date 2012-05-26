/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories.types;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.types.wrappers.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import javax.validation.constraints.*;
import org.apache.commons.lang3.StringUtils;

/**
 * Factory para classes anotadas com @Min e/ou @Max da JSR303.
 *
 * @since v.1 20/05/2012
 * @author Guilherme
 */
public class NumberFactory implements ValueFactory {

    /**
     * Nome da entidade que está tendo um atributo fabricado.
     */
    private String entityName;

    @Override
    public <T> void makeValue(final Field f, final T entity)
            throws IllegalAccessException, IllegalArgumentException {
        this.entityName = entity.getClass().getSimpleName();
        insertValue(f, entity);
    }

    /**
     * Verifica o tipo do campo e insere o valor correspondente.
     *
     * @param <T> Generic que representa proprietário do campo. Por
     * exemplo: Classe carro tem um campo int rodas, o Field f seria
     * rodas e T seria a classe Carro que terá o campo definido.
     * @param f O campo que terá o valor definido.
     * @param entity Objeto que contém o Field.
     * @throws IllegalAccessException se no momento de execução não
     * houver acesso ao campo.
     * @throws IllegalArgumentException Se algum argumento anotado não
     * for válido.
     */
    private <T> void insertValue(Field f, T entity)
            throws IllegalAccessException, IllegalArgumentException {
        if (f.getType().equals(Integer.class)) {
            f.set(entity, valueToInteger(f));
        } else if (f.getType().equals(int.class)) {
            f.set(entity, valueToInteger(f).intValue());
        } else if (f.getType().equals(Long.class)) {
            f.set(entity, valueToLong(f));
        } else if (f.getType().equals(long.class)) {
            f.set(entity, valueToLong(f).longValue());
        } else if (f.getType().equals(Byte.class)) {
            f.set(entity, valueToByte(f));
        } else if (f.getType().equals(byte.class)) {
            f.set(entity, valueToByte(f).byteValue());
        } else if (f.getType().equals(Short.class)) {
            f.set(entity, valueToShort(f));
        } else if (f.getType().equals(short.class)) {
            f.set(entity, valueToShort(f).shortValue());
        } else if (f.getType().equals(Double.class)) {
            f.set(entity, valueToDouble(f));
        } else if (f.getType().equals(double.class)) {
            f.set(entity, valueToDouble(f).doubleValue());
        } else if (f.getType().equals(Float.class)) {
            f.set(entity, valueToFloat(f));
        } else if (f.getType().equals(float.class)) {
            f.set(entity, valueToFloat(f).floatValue());
        } else if (f.getType().equals(BigInteger.class)) {
            f.set(entity, valueToBigInteger(f));
        } else if (f.getType().equals(BigDecimal.class)) {
            f.set(entity, valueToBigDecimal(f));
        } else if (f.getType().equals(String.class)) {
            f.set(entity, valueToString(f));
        } else {
            throw new IllegalArgumentException(I18N.getMsg("tipoDesconhecidoMinMax"));
        }
    }

    /**
     * Cria um valor entre os valores anotados com
     * javax.validation.constraints.Min ou
     * javax.validation.constraints.Max da especificação JSR303.<br>
     * Se não encontrar os valores irá utilizar os determinados nos
     * parâmetros minValue e/ou MaxValue
     *
     * @param f Campo a ter o valor determinado.
     * @param minValue Mínimo aceitavel, será utilizado se não houver
     * a anotação @Min da JSR303.
     * @param maxValue Máximo aceitavel, será utilizado se não houver
     * a anotação @Max da JSR303.
     * @return Array de duas posições, [0] será o minimo e [1] o
     * máximo.
     */
    private Number[] getMinMaxValues(Field f,
            Number minValue, Number maxValue) {
        Number[] toReturn = new Number[2];
        if (f.isAnnotationPresent(Min.class)) {
            toReturn[0] = new Long(f.getAnnotation(Min.class).value());
        } else if (f.isAnnotationPresent(DecimalMin.class)) {
            toReturn[0] = new Double(f.getAnnotation(DecimalMin.class).value());
        } else if (f.isAnnotationPresent(Digits.class)) {
            toReturn[0] = 0.0;
        } else {
            LogInfo.logDefaultValue(entityName, f, "NumberFactory");
            toReturn[0] = minValue.doubleValue();
        }
        if (f.isAnnotationPresent(Max.class)) {
            toReturn[1] = new Long(f.getAnnotation(Max.class).value());
        } else if (f.isAnnotationPresent(DecimalMax.class)) {
            toReturn[1] = new Double(f.getAnnotation(DecimalMax.class).value());
        } else if (f.isAnnotationPresent(Digits.class)) {
            toReturn[1] = maxDigits(f.getAnnotation(Digits.class).integer());
        } else {
            LogInfo.logDefaultValue(entityName, f, "NumberFactory");
            toReturn[1] = maxValue.doubleValue();
        }
        return toReturn;
    }

    private Integer valueToInteger(Field f) {
        Number[] minMax = getMinMaxValues(f, Integer.MIN_VALUE,
                Integer.MAX_VALUE);
        int min = minMax[0].intValue();
        int max = minMax[1].intValue();
        return MakeInteger.getIntervalo(min, max);
    }

    private Number valueToLong(Field f) {
        Number[] minMax = getMinMaxValues(f, Long.MIN_VALUE,
                Long.MAX_VALUE);
        long min = minMax[0].longValue();
        long max = minMax[1].longValue();
        return MakeLong.getIntervalo(min, max);
    }

    private String valueToString(Field f) {
        Number[] minMax = getMinMaxValues(f, -Double.MAX_VALUE,
                Double.MAX_VALUE);
        double min = minMax[0].doubleValue();
        double max = minMax[1].doubleValue();
        Double intervalo = MakeDouble.getIntervalo(min, max);
        return maxDecimal(f, intervalo).toString();
    }

    private BigInteger valueToBigInteger(Field f) {
        Number[] minMax = getMinMaxValues(f, Long.MIN_VALUE,
                Long.MAX_VALUE);
        long min = minMax[0].longValue();
        long max = minMax[1].longValue();
        return new BigInteger(new Long(MakeLong.getIntervalo(min,
                max)).toString());
    }

    private BigDecimal valueToBigDecimal(Field f) {
        Number[] minMax = getMinMaxValues(f, -Double.MAX_VALUE,
                Double.MAX_VALUE);
        double min = minMax[0].doubleValue();
        double max = minMax[1].doubleValue();
        Double intervalo = new Double(MakeDouble.getIntervalo(min, max));
        return new BigDecimal(maxDecimal(f, intervalo).toString());
    }

    private Double valueToDouble(Field f) {
        Number[] minMax = getMinMaxValues(f, -Double.MAX_VALUE,
                Double.MAX_VALUE);
        double min = minMax[0].doubleValue();
        double max = minMax[1].doubleValue();
        Double intervalo = MakeDouble.getIntervalo(min, max);
        return maxDecimal(f, intervalo).doubleValue();
    }

    private Float valueToFloat(Field f) {
        Number[] minMax = getMinMaxValues(f, -Float.MAX_VALUE,
                Float.MAX_VALUE);
        float min = minMax[0].floatValue();
        float max = minMax[1].floatValue();
        return MakeFloat.getIntervalo(min, max).floatValue();
    }

    private Byte valueToByte(Field f) {
        Number[] minMax = getMinMaxValues(f, Byte.MIN_VALUE,
                Byte.MAX_VALUE);
        byte min = minMax[0].byteValue();
        byte max = minMax[1].byteValue();
        return MakeByte.getIntervalo(min, max);
    }

    private Short valueToShort(Field f) {
        Number[] minMax = getMinMaxValues(f, Short.MIN_VALUE,
                Short.MAX_VALUE);
        short min = minMax[0].shortValue();
        short max = minMax[1].shortValue();
        return MakeShort.getIntervalo(min, max);
    }

    /**
     * Cria um número maior possivel com a quantidade de digitos
     * informado.<br> Máximo Long é 9,223,372,036,854,775,807L,
     * utilizo 8 porque 9 teria problema em:
     * 9,999,999,999,999,999,999L NOK, oito aguenta uma casa decimal a
     * mais: 8,888,888,888,888,888,888L OK
     *
     * @param integer Representa a quantidade de números.
     * @return Se integer fo 2, retorna 88, se for 3, 888...
     */
    private Long maxDigits(int integer) {
        String toReturn = "";
        return new Long(StringUtils.leftPad(toReturn, integer, "8"));
    }

    /**
     * Verifica se existe a anotação Digits no field, se existir
     * garante que o valor a ser definido esteja dentro da qantidade
     * máxima delimitada.
     *
     * @param f Field que terá o valor determinado.
     * @param valor Valor que será inserido no field.
     * @return O valor passado se não houver @Digits, se houver o
     * valor será alterado para se encaixar na necessidade.
     */
    private Number maxDecimal(Field f, Number valor) {
        if (f.isAnnotationPresent(Digits.class)) {
            int maxDecimal = f.getAnnotation(Digits.class).fraction();
            return new BigDecimal(valor.toString()).setScale(maxDecimal,
                    RoundingMode.HALF_EVEN).doubleValue();
        } else {
            return valor;
        }
    }
}