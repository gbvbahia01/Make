/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories.types;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.MakeEntity;
import br.com.gbvbahia.maker.factories.types.common.ValueFactory;
import br.com.gbvbahia.maker.types.wrappers.MakeInteger;
import java.lang.reflect.Field;
import java.util.List;
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
     * Log local, devido a complexidade de informações esse é
     * necessário.
     */
    private Log logger = LogInfo.getLog("PatternFactory");

    public <T> void makeValue(Field f, T entity) throws IllegalAccessException, IllegalArgumentException {
        if (MakeEntity.patternsList == null) {
            LogInfo.logWarnInformation("PatternFactory",
                    I18N.getMsg("regexListNull",
                    entity.getClass().getSimpleName(), f.getName()));
        } else {
            String keyExp = entity.getClass().getSimpleName()
                    + "." + f.getName();
            logger.debug(I18N.getMsg("keyPatternInteration", keyExp));
            for (String key : MakeEntity.patternsList.keySet()) {
                if (key.equals(keyExp)) {
                    List<String> list = MakeEntity.patternsList.get(key);
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
