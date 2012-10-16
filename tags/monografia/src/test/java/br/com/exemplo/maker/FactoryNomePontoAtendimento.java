
package br.com.exemplo.maker;

import br.com.gbvbahia.maker.works.common.ValueSpecializedFactory;
import java.lang.reflect.Field;
import org.apache.commons.lang3.StringUtils;

public class FactoryNomePontoAtendimento implements ValueSpecializedFactory{
     /**
     * Como o propertie deve estár definido no valor: "isNomePontoAtendimento".
     */
    public static final String KEY_PROPERTIE = "isNomePontoAtendimento";
    public static int contator = 1;
    /**
     * Construtor padrão.
     */
    public FactoryNomePontoAtendimento() {
    }
    @Override
    public <T> void makeValue(final String testName, final Field f,
            final T entity, final boolean makeRelationships)
            throws IllegalAccessException, IllegalArgumentException {
        f.set(entity, "Ponto: " + contator);
    }
    @Override
    public boolean workValue(final String value) {
              if (KEY_PROPERTIE.equals(StringUtils.trim(value))) {
            return true;
        }
        return false;
    }
    /**
     * Irá avaliar se o tipo do Field é trabalhado pelo mesmo, aqui
     * deve ser String.
     *
     * @param f Field a ter o valor definido.
     * @return True para String False para o resto.
     */
    @Override
    public <T> boolean isWorkWith(Field f, T entity) {
        return f.getType().equals(String.class);
    }
}
