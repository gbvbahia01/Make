/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories.types;

import br.com.gbvbahia.i18n.I18N;
import java.lang.reflect.Field;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Guilherme
 */
public class LogInfo {

    /**
     * Espaço nas colunas de informação do log.
     */
    private static final int SPACE = 20;

    /**
     * Log para utilização genérica das factorys.
     *
     * @param <T> Tipo da entidade.
     * @param entity Entidade que está sendo fabricada.
     * @param f Campo que está sendo determinado.
     * @param logFor Nome da classe que está utilizando o log.
     */
    protected static <T> void logDefaultValue(T entity, Field f,
            String logFor) {
        Log logger = getLog(logFor);
        logger.debug(I18N.getMsg("defaultValue",
                StringUtils.rightPad(entity.getClass().getSimpleName(),
                SPACE, " "),
                StringUtils.rightPad(f.getType().getSimpleName(),
                SPACE, " "), f.getName()));
    }

    /**
     * Log para utilização genérica das factorys.
     *
     * @param <T> Tipo da entidade.
     * @param entity Entidade que está sendo fabricada.
     * @param f Campo que está sendo determinado.
     * @param logFor Nome da classe que está utilizando o log.
     */
    protected static <T> void logDefaultValue(String entity, Field f,
            String logFor) {
        Log logger = getLog(logFor);
        logger.debug(I18N.getMsg("defaultValue",
                StringUtils.rightPad(entity, SPACE, " "),
                StringUtils.rightPad(f.getType().getSimpleName(),
                SPACE, " "), f.getName()));
    }

    /**
     * Cria log padrão para padronizar log.
     * @param logFor Nome da classe que utiliza o log.
     * @return Log para ser utilizado.
     */
    private static Log getLog(String logFor) {
        Log logger = LogFactory.getLog(StringUtils.rightPad(logFor,
                SPACE, " "));
        return logger;
    }
}
