/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.wrappers;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.wrappers.common.MakeNumber;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * Gerador de números inteiros aleatório.
 *
 * @since 11/05/2012
 * @author Guilherme
 */
public class MakeLong extends MakeNumber {

    @Override
    public <T> void insertValue(final Field f, final T entity)
            throws IllegalArgumentException, IllegalAccessException {
        Number[] minMax = getMinMaxValues(f, Long.MIN_VALUE,
                Long.MAX_VALUE);
        long min = minMax[0].longValue();
        long max = minMax[1].longValue();
        if (f.getType().equals(Integer.class)) {
            f.set(entity, MakeLong.getIntervalo(min, max));
        } else {
            f.set(entity, MakeLong.getIntervalo(min, max).longValue());
        }
    }

    @Override
    public boolean isMyType(Field f) {
        return isLong(f);
    }
    /**
     * Gerador de números aleatórios.
     */
    public static final Random RANDOM = new Random();

    /**
     * Gera um número entre os valores solicitados.
     *
     * @param min Número minimo aceitavel.
     * @param max Número máximo aceitavel.
     * @return Número aleatório.
     */
    public static Long getIntervalo(final long min, final long max) {
        if (min > max) {
            throw new IllegalArgumentException(I18N.getMsg("nimMaiormax",
                    new Object[]{min, max}));
        }
        if (min == max) {
            return min;
        }
        double ale = RANDOM.nextDouble();
        long numero;
        if (min < 0 && max > 0) {
            long longValue;
            if (max + min == 0) {
                if (RANDOM.nextInt() % 2 == 0) {
                    longValue = new BigDecimal((ale * (max / 2 + min))).setScale(0, RoundingMode.HALF_EVEN).longValue();
                } else {
                    longValue = new BigDecimal((ale * (max + min / 2))).setScale(0, RoundingMode.HALF_EVEN).longValue();
                }
            } else {
                longValue = new BigDecimal((ale * (max + min))).setScale(0, RoundingMode.HALF_EVEN).longValue();
            }
            if (longValue > 0) {
                numero = min + longValue;
            } else {
                numero = max + longValue;
            }
        } else {
            try {
                numero = min + new BigDecimal((ale * (max - min))).setScale(0, RoundingMode.HALF_EVEN).longValue();
            } catch (StackOverflowError s) {
                LogInfo.logWarnInformation("MakeLong", I18N.getMsg("bigErroStack", max, min, ale));
                throw s;
            }
        }
        return numero;
    }

    /**
     * Retorna um número aleatório limitado ao max passado. Este
     * método embora receba long como parámetro trabalha com Inteiro e
     * o maior número a retornar será o Integer.MAX_VALUE.
     *
     * @param max Minimo 1.
     * @return Long limitado ao max, minimo é zero.
     */
    public static Long getMax(final long max) {
        if (max <= 0) {
            throw new IllegalArgumentException(I18N.getMsg("maxSmall"));
        }
        return getIntervalo(0, max);
    }

    /**
     * Retorna True para tipos Long ou long.
     *
     * @param f Field a ser avaliado.
     * @return True para tipos Long ou long, False para outros tipos.
     */
    public static boolean isLong(final Field f) {
        if (f.getType().equals(Long.class)
                || f.getType().equals(long.class)) {
            return true;
        }
        return false;
    }
}
