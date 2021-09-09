package com.lucky.kali.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author BenjaminEngle
 */
public class LocalDateTimeUtil {

    /**
     * 格式化时间
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 日志打印
     */
    private static final Logger log = LoggerFactory.getLogger(LocalDateTimeUtil.class);

    /**
     * 判断传来的时间是否小于当前日期
     *
     * @param time 时间
     * @return 判断结果
     */
    public static boolean judgeDateTime(Date time) {
        /*取当前时间毫秒数*/
        String now = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        LocalDateTime localDateTime = LocalDateTime.parse(now, DATE_TIME_FORMATTER);
        ZoneId zoneId = ZoneId.systemDefault();
        long nowDate = localDateTime.atZone(zoneId).toInstant().toEpochMilli();
        /*取传来时间的毫秒数*/
        LocalDateTime transTime = LocalDateTime.ofInstant(time.toInstant(), zoneId);
        long transTimeToLong = transTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        /*判断传来的日期是否小于当前时间*/
        boolean judge = nowDate < transTimeToLong;
        log.info(judge ? "传来的时间：" + transTime + "小于当前时间" : "传来的时间：" + transTime + "大于当前时间");
        return judge;
    }

    /**
     * LocalDateTime转Date
     *
     * @param localDateTime 日期
     * @return 转换结果
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        Instant instant = zonedDateTime.toInstant();
        Date date = Date.from(instant);
        log.info("localDateTimeToDate：转换前：" + localDateTime + "   转换后：" + date);
        return date;
    }

    /**
     * Date转LocalDateTime
     *
     * @param date 日期
     * @return 转换结果
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), zoneId);
        log.info("dateToLocalDateTime：转换前：" + date + "   转换后：" + localDateTime);
        return localDateTime;
    }
}
