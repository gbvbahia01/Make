/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.wrappers;

/**
 * @since v.1 11/05/2012
 * @author Guilherme
 */
public class MakeShort {

    /**
     * Retorna um número aleatório limitado ao max passado.
     *
     * @param max Minimo 1.
     * @return Short limitado ao max.
     */
    public static Short getMax(final short max) {
        return MakeLong.getMax(max).shortValue();
    }

    /**
     * Gera um número entre os valores solicitados.
     *
     * @param min Número minimo aceitavel.
     * @param max Número máximo aceitavel.
     * @return Número aleatório.
     */
    public static Short getIntervalo(final short min, final short max) {
        return MakeLong.getIntervalo(min, max).shortValue();
    }

    /**
     * Não pode ser instânciada.
     */
    private MakeShort() {
    }
}
