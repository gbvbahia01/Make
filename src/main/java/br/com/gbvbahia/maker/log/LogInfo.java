/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.maker.log;

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
public final class LogInfo {

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
    public static <T> void logDefaultValue(T entity, Field f,
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
    public static <T> void logDefaultValue(String entity, Field f,
            String logFor) {
        Log logger = getLog(logFor);
        logger.debug(I18N.getMsg("defaultValue",
                StringUtils.rightPad(entity, SPACE, " "),
                StringUtils.rightPad(f.getType().getSimpleName(),
                SPACE, " "), f.getName()));
    }

    /**
     * Cria log padrão para padronizar log.
     *
     * @param logFor Nome da classe que utiliza o log.
     * @return Log para ser utilizado.
     */
    public static Log getLog(String logFor) {
        Log logger = LogFactory.getLog("Make :: "
                + StringUtils.rightPad(logFor, SPACE, " "));
        return logger;
    }

    /**
     * Log para duração da execução do método.
     *
     * @param clazz Classe utilizadora.
     * @param method Método a ser "timerizado".
     * @param timeWatch Contabilizador do tempo.
     */
    public static void logTimeDuration(String clazz, String method,
            StopWatch timeWatch) {
        Log logger = getLog(clazz);
        String time = DurationFormatUtils.formatDuration(
                timeWatch.getTime(), "HH:mm:ss");
        logger.info("Método: " + method
                + " Duração: " + time);
    }

    /**
     * Log para vizualização de parâmetros.
     *
     * @param clazz CLasse utilizadora.
     * @param method Informações a serem logadas.
     */
    public static void logInfoParameter(String clazz, String method) {
        Log logger = getLog(clazz);
        logger.info("Método: " + method);
    }

    /**
     * Lança a informação em log modo info.
     *
     * @param clazz Nome da classe que chama.
     * @param info Informação a ser escrita no log.
     */
    public static void logInfoInformation(String clazz, String info) {
        Log logger = getLog(clazz);
        logger.info(info);
    }

    /**
     * Lança a informação em log modo debug.
     *
     * @param clazz Nome da classe que chama.
     * @param info Informação a ser escrita no log.
     */
    public static void logDebugInformation(String clazz, String info) {
        Log logger = getLog(clazz);
        logger.debug(info);
    }

    /**
     * Lança a informação em log modo warn.
     *
     * @param clazz Nome da classe que chama.
     * @param info Informação a ser escrita no log.
     */
    public static void logWarnInformation(String clazz, String info) {
        Log logger = getLog(clazz);
        logger.warn(info);
    }

    /**
     * Gera um log de erro.
     *
     * @param clazz Classe solicitante.
     * @param info informação a ser inserida no log.
     * @param t Causador do erro.
     */
    public static void logErrorInformation(final String clazz,
            final String info, final Throwable t) {
        Log logger = getLog(clazz);
        logger.error(info, t);
    }

    /**
     * Log de criação de entidade, exibe separador inicial e nome da
     * classe que está sendo criada. Modo info.
     *
     * @param clazz Classe solicitante.
     * @param ob informação a ser inserida no log.
     */
    public static void logMakeStartInfo(final String clazz, Class ob) {
        Log logger = LogFactory.getLog(clazz);
        logger.info("--------------------//--------------------");
        logger.info(I18N.getMsg("makeclass",
                ob.getSimpleName()));
    }

    /**
     * Log que exibe informações sobre o field a ser criado, nome,
     * tipo e valor inserido. Modo info.
     *
     * @param clazz Classe solicitante.
     * @param f field a ter valor definido.
     * @param entity Entidade que possiu o field a ser populado.
     */
    public static <T> void logFieldInfo(final String clazz, Field f,
            T entity) throws IllegalArgumentException, IllegalAccessException {
        Log logger = LogFactory.getLog(clazz);
        logger.info("Field: " + StringUtils.rightPad(f.getName(), SPACE, " ")
                + " Type: " + StringUtils.rightPad(f.getType().getSimpleName(), SPACE, " ")
                + " Valor Definido: " + f.get(entity));
    }

    /**
     * Log que exibe o fechamento da criação da entidade, exibe no da
     * entidade que foi criada e fechamento da criação. Modo info.
     *
     * @param clazz Classe solicitante.
     * @param ob Classe da entidade que foi criada.
     */
    public static void logMakeEndInfo(final String clazz, Class ob) {
        Log logger = LogFactory.getLog(clazz);
        logger.info(I18N.getMsg("makeEnd", ob.getSimpleName()));
        logger.info("********************//********************");
    }

    /**
     * Log de criação de entidade, exibe separador inicial e nome da
     * classe que está sendo criada. Modo debug.
     *
     * @param clazz Classe solicitante.
     * @param ob informação a ser inserida no log.
     */
    public static void logMakeStartDebug(final String clazz, Class ob) {
        Log logger = LogFactory.getLog(clazz);
        logger.debug("--------------------//--------------------");
        logger.debug(I18N.getMsg("makeclass",
                ob.getSimpleName()));
    }

    /**
     * Log que exibe informações sobre o field a ser criado, nome,
     * tipo e valor inserido. Modo debug.
     *
     * @param clazz Classe solicitante.
     * @param f field a ter valor definido.
     * @param entity Entidade que possiu o field a ser populado.
     */
    public static <T> void logFieldDebug(final String clazz, Field f,
            T entity) throws IllegalArgumentException, IllegalAccessException {
        Log logger = LogFactory.getLog(clazz);
        logger.debug("Field: " + StringUtils.rightPad(f.getName(), SPACE, " ")
                + " Type: " + StringUtils.rightPad(f.getType().getSimpleName(), SPACE, " ")
                + " Valor Definido: " + f.get(entity));
    }

    /**
     * Log que exibe o fechamento da criação da entidade, exibe no da
     * entidade que foi criada e fechamento da criação. Modo debug.
     *
     * @param clazz Classe solicitante.
     * @param ob Classe da entidade que foi criada.
     */
    public static void logMakeEndDebug(final String clazz, Class ob) {
        Log logger = LogFactory.getLog(clazz);
        logger.debug(I18N.getMsg("makeEnd", ob.getSimpleName()));
        logger.debug("********************//********************");
    }

    /**
     * Utilizado em fields que não tiveram valor definido, utiliza
     * warn.
     *
     * @param clazz Nome da classe que utiliza o log.
     * @param f Field que não teve valor definido.
     */
    public static void logFieldNull(final String clazz, Field f) {
        Log logger = LogFactory.getLog(clazz);
        logger.warn(I18N.getMsg("fieldSettedNull", f.getName(),
                f.getType().getSimpleName()));
    }

    /**
     * Não pode ser instânciado.
     */
    private LogInfo() {
    }
}
