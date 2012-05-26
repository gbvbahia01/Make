/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.factories.types;

import br.com.gbvbahia.i18n.I18N;
import java.lang.reflect.Field;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.StopWatch;
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
    
    /**
     * Log para duração da execução do método.
     * @param clazz Classe utilizadora.
     * @param method Método a ser "timerizado".
     * @param timeWatch Contabilizador do tempo.
     */
    public static void logTimeDuration(String clazz, String method,
            StopWatch timeWatch){
        Log logger = getLog(clazz);
        String time = DurationFormatUtils.formatDuration(
                    timeWatch.getTime(), "HH:mm:ss");
            logger.info("Método: " + method
                    + " Duração: " + time);
    }
    
    /**
     * Log para vizualização de parâmetros.
     * @param clazz CLasse utilizadora.
     * @param method  Informações a serem logadas.
     */
    public static void logInfoParameter(String clazz, String method) {
        Log logger = getLog(clazz);
            logger.info("Método: " + method);
    }
    
    public static void logWarnInformation(String clazz, String info) {
        Log logger = getLog(clazz);
        logger.warn(info);
    }
}
