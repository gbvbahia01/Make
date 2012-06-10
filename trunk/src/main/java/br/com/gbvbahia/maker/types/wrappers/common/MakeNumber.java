/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.wrappers.common;

import br.com.gbvbahia.maker.log.LogInfo;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.validation.constraints.*;
import org.apache.commons.lang3.StringUtils;

/**
 * Unifica trabalhos das classes que criam números.
 *
 * @since v.1 10/06/2012
 * @author Guilherme
 */
public abstract class MakeNumber {

    /**
     * Se o field tiver o tipo que o produtor trabalha retorna true,
     * caso contrario false. Por exemplo, se o Field for String,
     * MakeLong retorna false e MakeString retornaria true.
     *
     * @param f O field que terá o valor definido.
     * @return True para se trabalha com e false se não trabalha.
     */
    public abstract boolean isMyType(Field f);

    /**
     * Insere o valor no field da entidade passada, o valor deverá ser
     * fabricado de acordo com a anotação da JSR303 que houver no
     * field.
     *
     * @param <T> Tipo da entidade passada.
     * @param f Field da entidade a ser populado
     * @param entity Entidade a ter um field populado.
     */
    public abstract <T> void insertValue(Field f, T entity)
            throws IllegalArgumentException, IllegalAccessException;

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
    protected Number[] getMinMaxValues(
            final Field f,
            final Number minValue,
            final Number maxValue) {
        Number[] toReturn = new Number[2];
        if (f.isAnnotationPresent(Min.class)) {
            toReturn[0] = new Long(f.getAnnotation(Min.class).value());
        } else if (f.isAnnotationPresent(DecimalMin.class)) {
            toReturn[0] = new Double(f.getAnnotation(DecimalMin.class).value());
        } else if (f.isAnnotationPresent(Digits.class)) {
            toReturn[0] = 0.0;
        } else {
            LogInfo.logDefaultValue(f.getDeclaringClass().getSimpleName(),
                    f, "NumberFactory");
            toReturn[0] = minValue.doubleValue();
        }
        if (f.isAnnotationPresent(Max.class)) {
            toReturn[1] = new Long(f.getAnnotation(Max.class).value());
        } else if (f.isAnnotationPresent(DecimalMax.class)) {
            toReturn[1] = new Double(f.getAnnotation(DecimalMax.class).value());
        } else if (f.isAnnotationPresent(Digits.class)) {
            toReturn[1] = maxDigits(f.getAnnotation(Digits.class).integer());
        } else {
            LogInfo.logDefaultValue(f.getDeclaringClass().getSimpleName(),
                    f, "NumberFactory");
            toReturn[1] = maxValue.doubleValue();
        }
        return toReturn;
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
    protected Long maxDigits(final int integer) {
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
    protected Number maxDecimal(final Field f, final Number valor) {
        if (f.isAnnotationPresent(Digits.class)) {
            int maxDecimal = f.getAnnotation(Digits.class).fraction();
            return new BigDecimal(valor.toString()).setScale(maxDecimal,
                    RoundingMode.HALF_EVEN).doubleValue();
        } else {
            return valor;
        }
    }
}
