package com.any.common.core.datetime

import com.any.common.core.domain.Tuple
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

/**
 * javadoc DateTimes
 * <p>
 *     本utils不欢迎 java.util.Date
 *     日期-时间 格式
 *     尝试使用 dt 代表 datetime
 *             dts 代表 datetime-string
 *             d 代表 date
 *             t 代表 time
 *             发现会带来使用迟疑, 容易误解
 * <p>
 * @author zhang yebai
 * @date 2021/6/17 9:18 AM
 * @version 1.0.0
 **/
@Suppress(names = ["unused"])
object DateTimes {

    private val DT_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern(DataTimePattern.DT_NORMAL)

    private val ZONE_ID: ZoneId = ZoneId.systemDefault()


    /***********************************************************date time start*******************************************************************/
    /**
     * javadoc datetime
     * @apiNote 当前 日期-时间 按给定格式输出
     *
     * @param pattern 输出 日期-时间 格式
     * @return String
     * @author zhang yebai
     * @date 2021/6/17 9:18 AM
     **/
    @JvmStatic
    fun datetime(pattern: String): String = DateTimeFormatter.ofPattern(pattern).format(LocalDateTime.now())

    /**
     * javadoc datetime
     * @apiNote 按给定的格式化器输出当前 日期-时间
     *          如不指定, 使用默认的格式化器 yyyy-MM-dd HH:mm:ss
     *
     * @param datetimeFormatter 输出格式化器
     * @return String
     * @author zhang yebai
     * @date 2021/6/17 9:20 AM
     **/
    @JvmStatic
    @JvmOverloads
    fun datetime(datetimeFormatter: DateTimeFormatter = DT_FORMATTER): String =
        datetimeFormatter.format(LocalDateTime.now())

    /**
     * javadoc datetime
     * @apiNote 按给定的格式输出给定的 日期-时间
     *
     * @param dateTime 指定的 日期-时间
     * @param pattern 输出格式
     * @return String
     * @author zhang yebai
     * @date 2021/6/17 9:21 AM
     **/
    @JvmStatic
    fun datetime(dateTime: LocalDateTime, pattern: String): String =
        DateTimeFormatter.ofPattern(pattern).format(dateTime)

    /**
     * javadoc datetime
     * @apiNote 按给定的格式化器格式化输出给定的日期-时间
     *          如不指定格式化器, 使用默认的格式化器 yyyy-MM-dd HH:mm:ss
     *
     * @param dateTime 指定的 日期-时间
     * @return String
     * @author zhang yebai
     * @date 2021/6/17 9:22 AM
     **/
    @JvmStatic
    @JvmOverloads
    fun datetime(dateTime: LocalDateTime, datetimeFormatter: DateTimeFormatter = DT_FORMATTER): String =
        datetimeFormatter.format(dateTime)

    /**
     * javadoc toDatetime
     * @apiNote 当前 日期-时间
     *
     * @return LocalDateTime
     * @author zhang yebai
     * @date 2021/6/17 9:23 AM
     **/
    @JvmStatic
    fun toDatetime(): LocalDateTime = LocalDateTime.now()

    /**
     * javadoc toDatetime
     * @apiNote
     *
     * @param datetime 字符串 日期-时间
     * @param pattern 解析格式
     * @return LocalDateTime
     * @author zhang yebai
     * @date 2021/6/17 9:24 AM
     **/
    @JvmStatic
    fun toDatetime(datetime: String, pattern: String): LocalDateTime =
        LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern(pattern))

    /**
     * javadoc toDatetime
     * @apiNote 使用给定的格式化器解析日期-时间
     *          如不指定格式化器, 使用默认的格式化器 yyyy-MM-dd HH:mm:ss
     *
     * @param datetime 字符串 日期-时间
     * @param datetimeFormatter 指定的格式化器
     * @return LocalDateTime
     * @author zhang yebai
     * @date 2021/6/17 9:24 AM
     **/
    @JvmStatic
    @JvmOverloads
    fun toDatetime(datetime: String, datetimeFormatter: DateTimeFormatter = DT_FORMATTER): LocalDateTime =
        LocalDateTime.parse(datetime, datetimeFormatter)

    /**
     * javadoc toDatetime
     * @apiNote 将时间戳, 如:  <code>System.currentTimeMillis(); java.sql.timestamp</code> 转化成日期-时间
     *
     * @param timestamp
     * @return LocalDateTime
     * @author zhang yebai
     * @date 2021/6/17 9:25 AM
     **/
    @JvmStatic
    fun toDatetime(timestamp: Long): LocalDateTime = Instant.ofEpochMilli(timestamp).atZone(ZONE_ID).toLocalDateTime()

    /**
     * javadoc toDatetime
     * @apiNote 将传统api中的Date转换成 日期-时间
     *
     * @param date java.util.Date
     * @return LocalDateTime
     * @author zhang yebai
     * @date 2021/6/17 9:28 AM
     **/
    @JvmStatic
    fun toDatetime(date: Date): LocalDateTime = date.toInstant().atZone(ZONE_ID).toLocalDateTime()

    /**
     * javadoc between
     * @apiNote 时间间隔
     *          只支持如下计时单位:
     *              - MILLISECONDS 毫秒
     *              - SECONDS 秒
     *              - MINUTES 分钟
     *              - HOURS 小时
     *              - HALF_DAYS 半天
     *              - DAYS 全天
     *              - MONTHS 月
     *              - YEARS 年
     *          超出以上计时单位直接抛异常
     *
     * @param pre 前一个日期
     * @param next 后一个日期
     * @param unit 计时单位
     * @return long
     * @author zhang yebai
     * @date 2021/7/9 6:32 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun between(pre: LocalDateTime, next: LocalDateTime, unit: ClockUnit? = ClockUnit.SECONDS): Long {
        return when (unit) {
            ClockUnit.MILLISECONDS -> ChronoUnit.MILLIS.between(pre, next)
            ClockUnit.SECONDS -> ChronoUnit.SECONDS.between(pre, next)
            ClockUnit.MINUTES -> ChronoUnit.MINUTES.between(pre, next)
            ClockUnit.HOURS -> ChronoUnit.HOURS.between(pre, next)
            ClockUnit.HALF_DAYS -> ChronoUnit.HALF_DAYS.between(pre, next)
            ClockUnit.DAYS -> ChronoUnit.DAYS.between(pre, next)
            ClockUnit.MONTHS -> ChronoUnit.MONTHS.between(pre, next)
            ClockUnit.YEARS -> ChronoUnit.YEARS.between(pre, next)
            else -> throw RuntimeException("unsupported time unit")
        }
    }

    /*************************************************************date time end*****************************************************************/

}