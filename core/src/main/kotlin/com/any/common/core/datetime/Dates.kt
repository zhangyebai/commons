package com.any.common.core.datetime

import com.any.common.core.domain.Tuple
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

/**
 * javadoc Dates
 * <p>
 *     日期类util 与 日期-时间 分开, 方便应用层使用
 * <p>
 * @author zhang yebai
 * @date 2021/8/20 2:28 PM
 * @version 1.0.0
 **/
@Suppress(names = ["unused"])
object Dates {

    private val D_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern(DataTimePattern.DATE_NORMAL)

    /***********************************************************date start*******************************************************************/

    /**
     * javadoc date
     * @apiNote 使用指定的格式化器输出当前日期
     *          如不指定格式化器, 使用默认的格式化器 yyyy-MM-dd
     *
     * @param dateFormatter 日期 格式化器
     * @return String
     * @author zhang yebai
     * @date 2021/6/17 9:30 AM
     **/
    @JvmStatic
    @JvmOverloads
    fun date(dateFormatter: DateTimeFormatter = D_FORMATTER): String = dateFormatter.format(LocalDate.now())

    /**
     * javadoc date
     * @apiNote 使用指定格式输出当前日期
     *
     * @param pattern 格式
     * @return String
     * @author zhang yebai
     * @date 2021/6/17 9:32 AM
     **/
    @JvmStatic
    fun date(pattern: String): String = DateTimeFormatter.ofPattern(pattern).format(LocalDate.now())

    /**
     * javadoc date
     * @apiNote 使用指定格式输出指定的日期
     *
     * @param date 日期
     * @param pattern 格式
     * @return String
     * @author zhang yebai
     * @date 2021/6/17 9:33 AM
     **/
    @JvmStatic
    fun date(date: LocalDate, pattern: String): String = DateTimeFormatter.ofPattern(pattern).format(date)

    /**
     * javadoc date
     * @apiNote 使用指定的格式化器格式化输出指定的日期
     *          如不指定格式化器, 使用默认的格式化器 yyyy-MM-dd
     *
     * @param date 日期
     * @param dateFormatter 格式化器
     * @return String
     * @author zhang yebai
     * @date 2021/6/17 9:33 AM
     **/
    @JvmStatic
    @JvmOverloads
    fun date(date: LocalDate, dateFormatter: DateTimeFormatter = D_FORMATTER): String = dateFormatter.format(date)

    /**
     * javadoc toDate
     * @apiNote 当前日期
     *
     * @return LocalDate
     * @author zhang yebai
     * @date 2021/6/17 9:34 AM
     **/
    @JvmStatic
    fun toDate(): LocalDate = LocalDate.now()

    /**
     * javadoc toDate
     * @apiNote 使用指定的格式解析指定的日期
     *
     * @param date 日期字符串
     * @param pattern 解析格式
     * @return LocalDate
     * @author zhang yebai
     * @date 2021/6/17 9:35 AM
     **/
    @JvmStatic
    fun toDate(date: String, pattern: String): LocalDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern))

    /**
     * javadoc toDate
     * @apiNote 使用指定的格式化器解析指定的日期字符串
     *          如不指定格式化器, 使用默认的格式化器 yyyy-MM-dd
     *
     * @param date 日期字符串
     * @param dateFormatter 格式化器
     * @return LocalDate
     * @author zhang yebai
     * @date 2021/6/17 9:35 AM
     **/
    @JvmStatic
    @JvmOverloads
    fun toDate(date: String, dateFormatter: DateTimeFormatter = D_FORMATTER): LocalDate =
        LocalDate.parse(date, dateFormatter)

    /**
     * javadoc firstOfWeek
     * @apiNote 查询指定日期所在周的第一天(周一)
     *          如果不指定日期, 则取当前日期
     *
     * @param date 日期
     * @return LocalDate
     * @author zhang yebai
     * @date 2021/6/17 4:14 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun firstOfWeek(date: LocalDate = LocalDate.now()): LocalDate =
        date.plusDays((DayOfWeek.MONDAY.value - date.dayOfWeek.value).toLong())

    /**
     * javadoc lastOfWeek
     * @apiNote 查询指定日期所在周的最后一天(周日)
     *          如果不指定日期, 则取当前日期
     *
     * @param date 日期
     * @return LocalDate
     * @author zhang yebai
     * @date 2021/6/17 4:14 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun lastOfWeek(date: LocalDate = LocalDate.now()): LocalDate =
        date.plusDays((DayOfWeek.SUNDAY.value - date.dayOfWeek.value).toLong())

    /**
     * javadoc marginOfWeek
     * @apiNote 查询指定日期所在周的第一天(周一) 和 最后一天(周日)
     *          如果不指定日期, 则取当前日期
     *
     * @param date 日期
     * @return Tuple<LocalDate, LocalDate>
     * @author zhang yebai
     * @date 2021/6/17 4:15 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun marginOfWeek(date: LocalDate = LocalDate.now()): Tuple<LocalDate, LocalDate> =
        Tuple.of(firstOfWeek(date), lastOfWeek(date))

    /**
     * javadoc firstOfMonth
     * @apiNote 查询指定日期所在月的第一天
     *          如果不指定日期, 则取当前日期
     *
     * @param date 日期
     * @return LocalDate
     * @author zhang yebai
     * @date 2021/6/17 4:15 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun firstOfMonth(date: LocalDate = LocalDate.now()): LocalDate = LocalDate.of(date.year, date.monthValue, 1)

    /**
     * javadoc lastOfMonth
     * @apiNote 查询指定日期所在月的最后一天
     *          如果不指定日期, 则取当前日期
     *
     * @param date 日期
     * @return LocalDate
     * @author zhang yebai
     * @date 2021/6/17 4:15 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun lastOfMonth(date: LocalDate = LocalDate.now()): LocalDate =
        LocalDate.of(date.year, date.monthValue, 1).plusMonths(1).plusDays(-1)

    /**
     * javadoc marginOfMonth
     * @apiNote 查询指定日期所在月的第一天 和 最后一天
     *          如果不指定日期, 则取当前日期
     *
     * @param date 日期
     * @return Tuple<LocalDate, LocalDate>
     * @author zhang yebai
     * @date 2021/6/17 4:15 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun marginOfMonth(date: LocalDate = LocalDate.now()): Tuple<LocalDate, LocalDate> =
        Tuple.of(firstOfMonth(date), lastOfMonth(date))

    /**
     * javadoc firstOfYear
     * @apiNote 查询指定 年份 的第一天
     *
     * @param year 年份
     * @return LocalDate
     * @author zhang yebai
     * @date 2021/6/17 4:15 PM
     **/
    @JvmStatic
    fun firstOfYear(year: Int): LocalDate = LocalDate.of(year, 1, 1)

    /**
     * javadoc firstOfYear
     * @apiNote 查询指定 日期 所在年的第一天
     *          如果不指定日期, 则取当前日期
     *
     * @param date 日期
     * @return LocalDate
     * @author zhang yebai
     * @date 2021/6/17 4:15 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun firstOfYear(date: LocalDate = LocalDate.now()): LocalDate = LocalDate.of(date.year, 1, 1)

    /**
     * javadoc marginOfMonth
     * @apiNote 查询指定 年份 的最后一天
     *
     * @param year 年份
     * @return LocalDate
     * @author zhang yebai
     * @date 2021/6/17 4:15 PM
     **/
    @JvmStatic
    fun lastOfYear(year: Int): LocalDate = firstOfYear(year).plusYears(1).plusDays(-1)

    /**
     * javadoc marginOfMonth
     * @apiNote 查询指定 日期 所在年的最后一天
     *
     * @param date 日期
     * @return LocalDate
     * @author zhang yebai
     * @date 2021/6/17 4:15 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun lastOfYear(date: LocalDate = LocalDate.now()): LocalDate =
        LocalDate.of(date.year, 1, 1).plusYears(1).plusDays(-1)

    /**
     * javadoc marginOfYear
     * @apiNote 查询指定年份的第一天 和 最后一天
     *          如果不指定日期, 则取当前日期
     *
     * @param year 年份
     * @return Tuple<LocalDate, LocalDate>
     * @author zhang yebai
     * @date 2021/6/17 4:15 PM
     **/
    @JvmStatic
    fun marginOfYear(year: Int): Tuple<LocalDate, LocalDate> = Tuple.of(firstOfYear(year), lastOfYear(year))

    /**
     * javadoc marginOfYear
     * @apiNote 查询指定日期所在的第一天 和 最后一天
     *          如果不指定日期, 则取当前日期
     *
     * @param date 日期
     * @return Tuple<LocalDate, LocalDate>
     * @author zhang yebai
     * @date 2021/6/17 4:15 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun marginOfYear(date: LocalDate = LocalDate.now()): Tuple<LocalDate, LocalDate> =
        Tuple.of(firstOfYear(date), lastOfYear(date))

    /**
     * javadoc yesterday
     * @apiNote 昨天
     *
     * @return LocalDate
     * @author zhang yebai
     * @date 2021/7/9 5:44 PM
     **/
    @JvmStatic
    fun yesterday(): LocalDate = LocalDate.now().plusDays(-1)

    /**
     * javadoc tomorrow
     * @apiNote 明天
     *
     * @return LocalDate
     * @author zhang yebai
     * @date 2021/7/9 5:45 PM
     **/
    @JvmStatic
    fun tomorrow(): LocalDate = LocalDate.now().plusDays(1)

    /**
     * javadoc lastMonth
     * @apiNote 上个月
     *
     * @return LocalDate
     * @author zhang yebai
     * @date 2021/7/9 5:45 PM
     **/
    @JvmStatic
    fun lastMonth(): LocalDate = LocalDate.now().plusMonths(-1)

    /**
     * javadoc nextMonth
     * @apiNote 下个月
     *
     * @return LocalDate
     * @author zhang yebai
     * @date 2021/7/9 5:45 PM
     **/
    @JvmStatic
    fun nextMonth(): LocalDate = LocalDate.now().plusMonths(1)

    /**
     * javadoc between
     * @apiNote 时间间隔
     *          只支持如下计时单位:
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
    fun between(pre: LocalDate, next: LocalDate, unit: ClockUnit? = ClockUnit.DAYS): Long {
        return when (unit) {
            ClockUnit.HALF_DAYS -> ChronoUnit.HALF_DAYS.between(pre, next)
            ClockUnit.DAYS -> ChronoUnit.DAYS.between(pre, next)
            ClockUnit.MONTHS -> ChronoUnit.MONTHS.between(pre, next)
            ClockUnit.YEARS -> ChronoUnit.YEARS.between(pre, next)
            else -> throw RuntimeException("unsupported clock unit")
        }
    }


    /***********************************************************date end*******************************************************************/

}