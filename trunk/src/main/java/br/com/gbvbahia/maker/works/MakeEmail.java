/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.works;

import br.com.gbvbahia.maker.types.string.MakeCharacter;
import br.com.gbvbahia.maker.types.wrappers.MakeInteger;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Guilherme
 */
public class MakeEmail {

    /**
     * Gera um e-mail válido aleatoriamente. O texto após @ é fixo em
     * algumas possibilidades.
     * <code>{"@hotmail.com", "@gmail.com","@ig.com.br",
     * "@amazon.com","@mycompany.com","@aol.com",
     * "@yahoo.com", "@bol.com.br", "@globo.com", "@nikko.jp",
     * "@uol.com.br"};</code>
     *
     * @return Uma string no formato de email.
     */
    public static String getEmail() {
        String[] emails = {"@hotmail.com","@ig.com.br",
            "@amazon.com","@mycompany.com","@aol.com",
            "@gmail.com", "@yahoo.com", "@bol.com.br", "@globo.com",
            "@nikko.jp", "@uol.com.br", "@saber.com.br"};
        int tamanho = MakeInteger.getIntervalo(3, 8);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            if (MakeInteger.getMax(2) % 2 == 0) {
                builder.append(MakeCharacter.getLetter());
            } else {
                builder.append(MakeCharacter.getNumber());
            }
        }
        int emailFim = MakeInteger.getMax(emails.length - 1);
        return StringUtils.replace(StringUtils.lowerCase(builder.toString()),
                " ", "_") + emails[emailFim];
    }
}
