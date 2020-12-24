package io.github.ljwlgl.util;

import io.github.ljwlgl.enums.TimeUnitEnum;
import jdk.vm.ci.meta.Local;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static io.github.ljwlgl.util.DateUtil.YYYYMMDDHHMMSS;

/**
 * @author johnxiaohe
 * @since 2020/12/24
 * LocalDateTime类型时间处理相关类
 **/
public class LocalDateTimeUtil {

    /**
     * @Description 将日期时分秒归0
     * @param target
     */
    public static LocalDateTime clearHourMinSecond(LocalDateTime target){
        return target.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    /**
     * @Description 将日期时分秒归为当天最大值
     * @param target
     */
    public static LocalDateTime maxHourMinSecond(LocalDateTime target){
        return target.withHour(23).withMinute(59).withSecond(59);
    }
    /**
     * @Description 将日期置为当月最后一天
     * @param target
     */
    public static LocalDateTime toMonthLastDay(LocalDateTime target){
        return toMonthFirstDay(target.plusMonths(1)).minusDays(1);
    }

    /**
     * @Description 将日期置为当月最后一天并归0时分秒
     * @param target
     */
    public static LocalDateTime toMonthLastDayAndTimeClear(LocalDateTime target){
        target = clearHourMinSecond(target);
        return toMonthLastDay(target);
    }

    /**
     * @Description 将日期置为当月最后一天并将时分秒设置为最大值
     * @param target
     */
    public static LocalDateTime toMonthLastDayAndTimeMax(LocalDateTime target){
        target = maxHourMinSecond(target);
        return toMonthLastDay(target);
    }

    /**
     * @Description 将日期置为当月第一天
     * @param target
     */
    public static LocalDateTime toMonthFirstDay(LocalDateTime target){
        return target.withDayOfMonth(1);
    }

    /**
     * @Description 将日期置为当月第一天，并归0时分秒
     * @param target
     */
    public static LocalDateTime toMonthFirstDayAndTimeClear(LocalDateTime target){
        target = clearHourMinSecond(target);
        return toMonthFirstDay(target);
    }

    /**
     * @Description 将日期置为当月第一天，并将时分秒设置为最大值
     * @param target
     */
    public static LocalDateTime toMonthFirstDayAndTimeMax(LocalDateTime target){
        target = maxHourMinSecond(target);
        return toMonthFirstDay(target);
    }

    /**
     * @Description 根据当前时间获取下一个指定时间单位和步长的时间
     * @param current 	当前时间
     * @param step		步长
     * @param timeUnit	时间单位
     */
    public static LocalDateTime nextDateTime(LocalDateTime current , Integer step , TimeUnitEnum timeUnit)throws Exception{
        if(step<1){
            throw new Exception("请检查步长数值");
        }
        LocalDateTime targetTime;
        switch (timeUnit){
            case DAY :
                targetTime = current.plusDays(step);
                break;
            case WEEK :
                targetTime = current.plusDays(step * 7);
                break;
            case MONTH :
                targetTime = current.plusMonths(step);
                break;
            case YEAR :
                targetTime = current.plusYears(step);
                break;
            default :
                throw new Exception("不支持的时间单位");
        }
        return targetTime;
    }
    /**
     * @author Hexy
     * @Description 根据当前时间获取上一个指定时间单位和步长的时间
     * @param current 	当前时间
     * @param step		步长
     * @param timeUnit	时间单位
     */
    public static LocalDateTime lastDateTime(LocalDateTime current , Integer step , TimeUnitEnum timeUnit)throws Exception{
        if(step<1){
            throw new Exception("请检查步长数值！");
        }
        LocalDateTime targetTime ;
        switch (timeUnit){
            case DAY :
                targetTime = current.minusDays(step);
                break;
            case WEEK :
                targetTime = current.minusDays(step * 7);
                break;
            case MONTH :
                targetTime = current.minusMonths(step);
                break;
            case YEAR :
                targetTime = current.minusYears(step);
                break;
            default :
                throw new Exception("不支持的时间单位");
        }
        return targetTime;
    }

    /**
     * 获取Instant
     * @param localDateTime
     */
    public static Instant toInstant(LocalDateTime localDateTime){
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

    /**
     * Date转换为LocalDateTime
     * @param date
     */
    public static LocalDateTime toLocalDateTime(Date date){
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 毫秒转为LocalDateTime
     * @param milli
     */
    public static LocalDateTime toLocalDateTime(Long milli){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milli), ZoneId.systemDefault());
    }

    /**
     * 获取当前毫秒值
     * @param localDateTime
     */
    public static Long toEpochMilli(LocalDateTime localDateTime){
        return toInstant(localDateTime).toEpochMilli();
    }

    /**
     * 格式化为字符串
     * @param localDateTime
     * @param pattern
     */
    public static String toString(LocalDateTime localDateTime, String pattern){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 字符串转换为LocalDateTime
     * @param strDateTime
     */
    public static LocalDateTime toLocalDateTime(String strDateTime){
        return LocalDateTime.parse(strDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * LocalDateTime转换为Date
     * @param localDateTime
     */
    public static Date toDate(LocalDateTime localDateTime){
        return Date.from(toInstant(localDateTime));
    }

}
