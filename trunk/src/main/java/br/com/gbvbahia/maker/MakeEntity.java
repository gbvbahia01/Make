/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker;

import br.com.gbvbahia.i18n.I18N;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme
 */
public class MakeEntity {

    public static <T> T makeEntity(Class<T> entity) {
        try {
            T toReturn = entity.newInstance();
            for (Field f :entity.getDeclaredFields()){
                for (Annotation ann : f.getAnnotations()){
                    
                }
            }
            
        } catch (InstantiationException ex) {
            Logger logger = Logger.getLogger(MakeEntity.class.getName());
            logger.log(Level.SEVERE, I18N.getMsg("instantiationException",
                    entity.getName()), ex);
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            Logger logger = Logger.getLogger(MakeEntity.class.getName());
            logger.log(Level.SEVERE, I18N.getMsg("illegalAccessException",
                    entity.getName()), ex);
            throw new RuntimeException(ex);
        }
        return null;
    }
}
