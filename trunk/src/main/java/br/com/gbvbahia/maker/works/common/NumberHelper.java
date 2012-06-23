/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.works.common;

import br.com.gbvbahia.maker.types.complex.MakeBigDecimal;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeLong;
import java.math.RoundingMode;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * Realiza o trabalho de manipular String passadas para o tipo correto.
 *
 * @since v.1 17/06/2012
 * @author Guilherme
 */
public class NumberHelper {

    /**
     * Valor mínimo aceito.
     */
    private String min;
    /**
     * Valor máximo aceito.
     */
    private String max;
    /**
     * Valor a ser devolvido.
     */
    private String value;

    /**
     * Valor calculado de min e max.
     *
     * @return Um valor entre min e max, incluídos.
     */
    public String getValue() {
        if (StringUtils.contains(min, ".") || StringUtils.contains(max, ".")) {
            int decimal = StringUtils.substringAfter(min, ".").length();
            value = MakeBigDecimal.getIntervalo(new Double(min).doubleValue(),
                    new Double(max).doubleValue()).setScale(decimal,
                    RoundingMode.UP).toString();
        } else {
            value = MakeLong.getIntervalo(new Long(min).longValue(),
                    new Long(max).longValue()).toString();
        }
        return value;
    }

    /**
     * Construtor obrigatório para criação do número no intervalo.
     *
     * @param min Mínimo aceitavel.
     * @param max Máximo aceitavel.
     */
    public NumberHelper(String min, String max) {
        this.min = min;
        this.max = max;
    }

    public String getMax() {
        return max;
    }

    public String getMin() {
        return min;
    }

    @Override
    public String toString() {
        return "min=" + min + ", max=" + max;
    }
    
}
