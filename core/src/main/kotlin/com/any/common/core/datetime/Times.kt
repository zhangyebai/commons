package com.any.common.core.datetime

import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * javadoc Times
 * <p>
 *     时间相关util, 与日期-时间分开, 方便应用层使用
 * <p>
 * @author zhang yebai
 * @date 2021/8/20 2:29 PM
 * @version 1.0.0
 **/
@Suppress("unused")
object Times {

    private val T_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern(DataTimePattern.TIME_NORMAL)

    /***********************************************************time start*******************************************************************/

    /**
     * javadoc time
     * @apiNote 使用指定的格式化器格式化输出当前时间
     *          如不指定格式化器, 使用默认的格式化器 HH:mm:ss
     *
     * @param timeFormatter 时间格式化器
     * @return String
     * @author zhang yebai
     * @date 2021/6/17 9:36 AM
     **/
    @JvmStatic
    @JvmOverloads
    fun time(timeFormatter: DateTimeFormatter = T_FORMATTER): String = timeFormatter.format(LocalTime.now())

    /**
     * javadoc time
     * @apiNote 使用指定的格式格式化输出当前时间
     *
     * @param pattern 时间输出格式
     * @return String
     * @author zhang yebai
     * @date 2021/6/17 9:37 AM
     **/
    @JvmStatic
    fun time(pattern: String): String = DateTimeFormatter.ofPattern(pattern).format(LocalTime.now())

    /**
     * javadoc time
     * @apiNote 使用指定的格式格式化输出指定的事件
     *
     * @param time 时间
     * @param pattern 时间输出格式
     * @return String
     * @author zhang yebai
     * @date 2021/6/17 9:37 AM
     **/
    @JvmStatic
    fun time(time: LocalTime, pattern: String): String = DateTimeFormatter.ofPattern(pattern).format(time)

    /**
     * javadoc time
     * @apiNote 使用指定的时间格式化器格式化输出指定的事件
     *          如不指定格式化器, 则使用默认的时间格式化器 HH:mm:ss
     *
     * @param time 时间
     * @param timeFormatter 时间格式化器
     * @return String
     * @author zhang yebai
     * @date 2021/6/17 9:38 AM
     **/
    @JvmStatic
    @JvmOverloads
    fun time(time: LocalTime, timeFormatter: DateTimeFormatter = T_FORMATTER): String = timeFormatter.format(time)

    /**
     * javadoc toTime
     * @apiNote 当前时间
     *
     * @return LocalTime
     * @author zhang yebai
     * @date 2021/6/17 9:39 AM
     **/
    @JvmStatic
    fun toTime(): LocalTime = LocalTime.now()

    /**
     * javadoc toTime
     * @apiNote 按指定的格式解析时间
     *
     * @param time 字符串时间
     * @param pattern 时间格式
     * @return LocalTime
     * @author zhang yebai
     * @date 2021/6/17 9:39 AM
     **/
    @JvmStatic
    fun toTime(time: String, pattern: String): LocalTime = LocalTime.parse(time, DateTimeFormatter.ofPattern(pattern))

    /**
     * javadoc toTime
     * @apiNote 按指定的时间格式化器解析字符串时间
     *          如不指定时间格式化器, 则使用默认的时间格式化器 HH:mm:ss
     *
     * @param time 字符串时间
     * @param timeFormatter 时间格式化器
     * @return LocalTime
     * @author zhang yebai
     * @date 2021/6/17 9:40 AM
     **/
    @JvmStatic
    @JvmOverloads
    fun toTime(time: String, timeFormatter: DateTimeFormatter = T_FORMATTER): LocalTime =
        LocalTime.parse(time, timeFormatter)

    /***********************************************************time end*******************************************************************/
}