/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories.types;

import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.types.wrappers.MakeInteger;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.State;
import dk.brics.automaton.Transition;
import java.lang.reflect.Field;
import java.util.List;
import javax.validation.constraints.Pattern;

/**
 * O código de geração de String com base no pattern foi baseado no no
 * projeto: <br>http://code.google.com/p/xeger/<br> Não inseri o a
 * dependência por não existir repositório para o Maven do mesmo.<br>
 * Em testes verifiquei que a complexidade da regex pode causar erros
 * na geração da String, por isso uma segunda possibilidade foi
 * implementada, onde o desenvolvedor passa uma lista com
 * possibilidades para ser definida no field. <br>Para utilizar a
 * lista utilize: MakeEntity.makeEntity(Class&lt;T> entity,
 * Map&lt;String, List&lt;String>> patterns) <br> O map tem
 * preferência, se for informado o Make não irá tentar gerar a String
 * que se encaixa, se não for passado o Make tentará gerar a string e
 * erros podem ocorrer.
 *
 * @since v.1 26/05/2012
 * @author Guilherme
 */
public class PatternFactory implements ValueFactory {

    public <T> void makeValue(Field f, T entity) throws IllegalAccessException, IllegalArgumentException {
        //TODO fazer leitura do map antes de tentar gerar a String.
        String regex = f.getAnnotation(Pattern.class).regexp();
        f.set(entity, generateValue(regex));
    }

    /**
     * Gera a String com base na regex passada.
     * <br>http://code.google.com/p/xeger/<br>
     *
     * @param regex Expressão regular
     * @return String dentro do padrão Regex passado.
     */
    private String generateValue(String regex) {
        Automaton automaton = new RegExp(regex).toAutomaton();
        StringBuilder builder = new StringBuilder();
        generate(builder, automaton.getInitialState());
        return builder.toString();
    }

    /**
     * <br>http://code.google.com/p/xeger/<br>
     *
     * @param builder
     * @param state
     */
    private void generate(StringBuilder builder, State state) {
        List<Transition> transitions = state.getSortedTransitions(true);
        if (transitions.isEmpty()) {
            return;
        }
        int nroptions = state.isAccept() ? transitions.size() : transitions.size() - 1;
        int option = MakeInteger.getIntervalo(0, nroptions);
        if (state.isAccept() && option == 0) {
            return;
        }
        Transition transition = transitions.get(option - (state.isAccept() ? 1 : 0));
        appendChoice(builder, transition);
        generate(builder, transition.getDest());
    }

    /**
     * <br>http://code.google.com/p/xeger/<br>
     *
     * @param builder
     * @param transition
     */
    private void appendChoice(StringBuilder builder, Transition transition) {
        char c = (char) MakeInteger.getIntervalo(transition.getMin(),
                transition.getMax()).intValue();
        builder.append(c);
    }
}
