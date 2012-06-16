/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.properties;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.log.LogInfo;
import br.com.gbvbahia.maker.types.string.MakeString;
import br.com.gbvbahia.maker.works.common.ValuePropertiesFactory;
import java.lang.reflect.Field;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Guilherme
 */
public class CEPWorkTest implements ValuePropertiesFactory {

    /**
     * Como o propertie deve estár definido no valor: "isCEP".
     */
    public static final String KEY_PROPERTIE = "isCEP";

    @Override
    public boolean workValue(String value) {
        LogInfo.logDebugInformation("CEPWorkTest",
                I18N.getMsg("workValueMake", value));
        if (KEY_PROPERTIE.equals(StringUtils.trim(value))) {
            return true;
        }
        LogInfo.logDebugInformation("CEPWorkTest",
                I18N.getMsg("notIsWork", "CEP", value));
        return false;
    }

    @Override
    public <T> boolean isWorkWith(Field f, T entity) {
        return f.getType().equals(String.class);
    }

    @Override
    public <T> void makeValue(final String testName, final Field f,
            final T entity, final boolean makeRelationships)
            throws IllegalAccessException, IllegalArgumentException {
        f.set(entity, MakeString.getString(6, MakeString.StringType.NUMBER));
    }
}
