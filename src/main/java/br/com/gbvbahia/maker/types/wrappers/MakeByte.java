/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.types.wrappers;

import java.lang.reflect.Field;

/**
 * @since v.1 11/05/2012
 * @author Guilherme
 */
public final class MakeByte {

    /**
     * Retorna um número aleatório limitado ao max passado.
     *
     * @param max Minimo 1.
     * @return Byte limitado ao max.
     */
    public static Byte getMax(final byte max) {
        return MakeLong.getMax(max).byteValue();
    }

    /**
     * Gera um número entre os valores solicitados.
     *
     * @param min Número minimo aceitavel.
     * @param max Número máximo aceitavel.
     * @return Número aleatório.
     */
    public static Byte getIntervalo(final byte min, final byte max) {
        return MakeLong.getIntervalo(min, max).byteValue();
    }

    /**
     * Retorna True para tipos Byte ou byte.
     *
     * @param f Field a ser avaliado.
     * @return True para tipos Byte ou byte, False para outros tipos.
     */
    public static boolean isByte(Field f) {
        if (f.getType().equals(Byte.class)
                || f.getType().equals(byte.class)) {
            return true;
        }
        return false;
    }

    /**
     * Não pode ser instânciado.
     */
    private MakeByte() {
    }
}
