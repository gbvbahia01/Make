package br.com.gbvbahia.maker.log;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.gbvbahia.i18n.I18N;

/**
 * Class to help with logging information.
 * 
 * @since v1 12/2012
 * @author Guilherme Braga
 */
public final class LogInfo {

  /**
   * Space between information columns log.
   */
  private static final int SPACE = 20;

  /**
   * Log a debug information.
   * 
   * @param <T> Type of object is being made.
   * @param entity Object that is being made.
   * @param field Field of entity parameter is being made.
   * @param logFor Id that will be used to creation a log object.
   */
  public static <T> void logDefaultValue(T entity, Field field, String logFor) {
    Log logger = getLog(logFor);
    logger.debug(I18N.getMsg("defaultValue",
        StringUtils.rightPad(entity.getClass().getSimpleName(), SPACE, " "),
        StringUtils.rightPad(field.getType().getSimpleName(), SPACE, " "), field.getName()));
  }

  /**
   * Log a debug information.
   * 
   * @param entity String that represents a object that is being made.
   * @param field Field of entity parameter is being made.
   * @param logFor Id that will be used to creation a log object.
   */
  public static void logDefaultValue(String entity, Field field, String logFor) {
    Log logger = getLog(logFor);
    logger.debug(I18N.getMsg("defaultValue", StringUtils.rightPad(entity, SPACE, " "),
        StringUtils.rightPad(field.getType().getSimpleName(), SPACE, " "), field.getName()));
  }

  /**
   * Recover a logging object to log with a Make framework pattern.
   * 
   * @param logFor Id that will be used to creation a log object.
   * @return a log object.
   */
  public static Log getLog(String logFor) {
    Log logger = LogFactory.getLog("Make :: " + StringUtils.rightPad(logFor, SPACE, " "));
    return logger;
  }

  /**
   * Log the time execution for the timeWatch parameter.<br>
   * The log mode used here is info.
   * 
   * @param logFor Id that will be used to creation a log object.
   * @param method name of the method that was temporized.
   * @param timeWatch used to temporize.
   */
  public static void logTimeDuration(String logFor, String method, StopWatch timeWatch) {
    Log logger = getLog(logFor);
    String time = DurationFormatUtils.formatDuration(timeWatch.getTime(), "HH:mm:ss");
    logger.info("method" + method + " duration: " + time);
  }

  /**
   * Log a info information.
   *
   * @param logFor Id that will be used to creation a log object.
   * @param logInfoMsg Information to be logged.
   */
  public static void logInfoInformation(String logFor, String logInfoMsg) {
    Log logger = getLog(logFor);
    logger.info(logInfoMsg);
  }

  /**
   * Log a debug information.
   *
   * @param logFor Id that will be used to creation a log object
   * @param logInfoMsg Information to be logged.
   */
  public static void logDebugInformation(String logFor, String logInfoMsg) {
    Log logger = getLog(logFor);
    logger.debug(logInfoMsg);
  }

  /**
   * Log a warn information.
   *
   * @param logFor Id that will be used to creation a log object
   * @param logInfoMsg Information to be logged.
   */
  public static void logWarnInformation(String logFor, String logInfoMsg) {
    Log logger = getLog(logFor);
    logger.warn(logInfoMsg);
  }

  /**
   * Log a error information.
   *
   * @param logFor Id that will be used to creation a log object.
   * @param logInfoMsg Information to be logged.
   * @param throwable the error thats happened. It can be null.
   */
  public static void logErrorInformation(String logFor, String logInfoMsg, Throwable throwable) {
    Log logger = getLog(logFor);
    if (throwable != null) {
      logger.error(logInfoMsg, throwable);
    } else {
      logger.error(logInfoMsg);
    }
  }

  /**
   * Used to log information about the class is going to be made.<br>
   * The log mode used here is info.
   *
   * @param logFor Id that will be used to creation a log object.
   * @param entity represents the object class that is going to be made.
   */
  public static <T> void logMakeStartInfo(String logFor, Class<T> entity) {
    Log logger = LogFactory.getLog(logFor);
    logger.info("--------------------//--------------------");
    logger.info(I18N.getMsg("makeclass", entity.getSimpleName()));
  }

  /**
   * Used to log information about the field is going to be made.<br>
   * The log mode used here is info.
   * 
   * @param logFor Id that will be used to creation a log object.
   * @param field that will be worked a new value for.
   * @param entity that has the field to be worked.
   */
  public static <T> void logFieldInfo(String logFor, Field field, T entity)
      throws IllegalArgumentException, IllegalAccessException {
    Log logger = LogFactory.getLog(logFor);
    logger.info("Field: " + StringUtils.rightPad(field.getName(), SPACE, " ") + " Type: "
        + StringUtils.rightPad(field.getType().getSimpleName(), SPACE, " ") + " Made value: "
        + field.get(entity));
  }

  /**
   * Used when the creation of entity is done.<br>
   * The log mode used here is info.
   * 
   * @param logFor Id that will be used to creation a log object.
   * @param entity represents the object class that is going to be made.
   */
  public static <T> void logMakeEndInfo(String logFor, Class<T> entity) {
    Log logger = LogFactory.getLog(logFor);
    logger.info(I18N.getMsg("makeEnd", entity.getSimpleName()));
    logger.info("********************//********************");
  }

  /**
   * Used to log information about the field is going to be made.<br>
   * The log mode used here is debug.
   *
   * @param logFor Id that will be used to creation a log object.
   * @param entity represents the object class that is going to be made.
   */
  public static <T> void logMakeStartDebug(String logFor, Class<T> entity) {
    Log logger = LogFactory.getLog(logFor);
    logger.debug("--------------------//--------------------");
    logger.debug(I18N.getMsg("makeclass", entity.getSimpleName()));
  }

  /**
   * Used to log information about the field is going to be made.<br>
   * The log mode used here is debug.
   *
   * @param logFor Id that will be used to creation a log object.
   * @param field that will be worked a new value for.
   * @param entity that has the field to be worked.
   */
  public static <T> void logFieldDebug(final String logFor, Field field, T entity)
      throws IllegalArgumentException, IllegalAccessException {
    Log logger = LogFactory.getLog(logFor);
    logger.debug("Field: " + StringUtils.rightPad(field.getName(), SPACE, " ") + " Type: "
        + StringUtils.rightPad(field.getType().getSimpleName(), SPACE, " ") + " Made value: "
        + field.get(entity));
  }

  /**
   * Used when the creation of entity is done.<br>
   * The log mode used here is info.
   * 
   * @param logFor Id that will be used to creation a log object.
   * @param entity represents the object class that is going to be made.
   */
  public static <T> void logMakeEndDebug(String logFor, Class<T> entity) {
    Log logger = LogFactory.getLog(logFor);
    logger.debug(I18N.getMsg("makeEnd", entity.getSimpleName()));
    logger.debug("********************//********************");
  }

  /**
   * Made to use with fields where a value cannot be made.<br>
   * The log mode used here is warn.
   * 
   * @param logFor Id that will be used to creation a log object.
   * @param field that cannot be settled.
   */
  public static void logFieldNull(final String logFor, Field field) {
    Log logger = LogFactory.getLog(logFor);
    logger.warn(I18N.getMsg("fieldSettedNull", field.getName(), field.getType().getSimpleName()));
  }

  /**
   * Cannot be instantiated.
   */
  private LogInfo() {}
}
