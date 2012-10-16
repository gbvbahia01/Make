/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories.types;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.primitives.numbers.MakeInteger;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.apache.commons.logging.Log;

/**
 * Passe uma lista com possibilidades para ser definida no field.
 * <br>Para utilizar a lista utilize:
 * MakeEntity.makeEntity(Class&lt;T> entity, Map&lt;String,
 * List&lt;String>> patterns) <br>
 *
 * @since v.1 26/05/2012
 * @author Guilherme
 */
public class PatternFactory implements ValueFactory {

    /**
     * Devido a complexidade de gerar uma string com base em uma
     * expressão regular é disponibilizado uma forma de passar várias
     * strings que atendam um field.<br> key: NomeClasse.nomeFiled
     * valor: Uma lista com várias possibilidades para o field. Minimo
     * de uma possibilidade deve ser inserida.
     */
    public Map<String, List<String>> patternsList;

    public PatternFactory(Map<String, List<String>> patternsList) {
        this.patternsList = patternsList;
    }

    public <T> boolean isWorkWith(Field f, T entity) {
        if (f.isAnnotationPresent(NotNull.class)) {
            if (f.isAnnotationPresent(Pattern.class)) {
                return true;
            }
        }
        return false;
    }

    public PatternFactory() {
    }
    /**
     * Log local, devido a complexidade de informações esse é
     * necessário.
     */
    private Log logger = LogInfo.getLog("PatternFactory");

    public <T> void makeValue(final String testName, final Field f,
            final T entity, final boolean makeRelationships)
            throws IllegalAccessException, IllegalArgumentException {
        if (patternsList == null) {
            LogInfo.logWarnInformation("PatternFactory",
                    I18N.getMsg("regexListNull",
                    entity.getClass().getSimpleName(), f.getName()));
        } else {
            String keyExp = entity.getClass().getSimpleName()
                    + "." + f.getName();
            logger.debug(I18N.getMsg("keyPatternInteration", keyExp));
            for (String key : patternsList.keySet()) {
                if (key.equals(keyExp)) {
                    List<String> list = patternsList.get(key);
                    Integer posicao = MakeInteger.getIntervalo(0,
                            list.size() - 1);
                    String valuePattern = list.get(posicao);
                    f.set(entity, valuePattern);
                }
            }
            if (f.get(entity) == null) {
                LogInfo.logWarnInformation("PatternFactory",
                        I18N.getMsg("regexListFieldNotFound", keyExp));
            }
        }
    }
}
