package com.any.common.core.datetime

/**
 * javadoc DataTimePattern
 * <p>
 *     日期-时间 格式化形式
 * <p>
 * @author zhang yebai
 * @date 2021/7/12 10:09 AM
 * @version 1.0.0
 **/
@Suppress(names = ["unused"])
object DataTimePattern {

    /**
     * 最通用的日期格式化形式
     **/
    const val DATE_NORMAL = "yyyy-MM-dd"

    /**
     * 简单日期-时间格式化形式
     **/
    const val DATE_SIMPLE = "MM-dd"

    /**
     * 兼容格式的日期格式化器
     * 2020-01-06 通过该格式输出为: 2020-1-6
     * 用该格式反序列化(解析)日期时能同时兼容 yyyy-MM-dd 和 yyyy-M-d
     **/
    const val DATE_NORMAL_COMPATIBLE = "yyyy-M-d"

    /**
     * 兼容格式简单日期-时间格式化形式
     *
     **/
    const val DATE_SIMPLE_COMPATIBLE = "M-d"

    /**
     * 中国常用日期格式化方式
     **/
    const val DATE_CHINA = "yyyy年MM月dd日"

    /**
     * 中国常用简单日期格式化方式
     **/
    const val DATE_CHINA_SIMPLE = "MM月dd日"

    /**
     * 兼容格式 中国常用日期格式化方式
     **/
    const val DATE_CHINA_COMPATIBLE = "yyyy年M月d日"

    /**
     * 兼容格式 中国常用简单日期格式化方式
     **/
    const val DATE_CHINA_SIMPLE_COMPATIBLE = "M月d日"

    /**
     * 最通用的事件格式化形式
     **/
    const val TIME_NORMAL = "HH:mm:ss"

    /**
     * 标准时间格式
     **/
    const val TIME_STANDARD = "HH:mm:ss.SSS"

    /**
     * 兼容形式的最通用的事件格式化形式
     * 12:30:08 通过该格式会输出 12:30:8
     **/
    const val TIME_NORMAL_COMPATIBLE = "H:m:s"

    /**
     * 中国格式时间
     * 12:30:09 输出 12时30分09秒
     **/
    const val TIME_CHINA = "HH时mm分ss秒"

    /**
     * 标准格式中国时间格式
     **/
    const val TIME_CHINA_STANDARD = "HH时mm分ss秒SSS毫秒"

    /**
     * 兼容格式标准格式中国时间格式
     **/
    const val TIME_CHINA_STANDARD_COMPATIBLE = "H时m分s秒S毫秒"

    /**
     * 兼容格式的中国时间
     **/
    const val TIME_CHINA_COMPATIBLE = "H时m分s秒"

    /**
     * 最常用的日期-时间格式化形式
     **/
    const val DT_NORMAL = "yyyy-MM-dd HH:mm:ss"

    /**
     * 标准日期-时间格式, 带毫秒
     **/
    const val DT_STANDARD = "yyyy-MM-dd HH:mm:ss.SSS"

    /**
     * 兼容格式的最常用的日期-时间格式化形式
     **/
    const val DT_NORMAL_COMPATIBLE = "yyyy-M-d H:m:s"

    /**
     * 中国常用格式日期-时间格式化形式
     **/
    const val DT_CHINA = "yyyy年MM月dd日 HH时mm分ss秒"

    /**
     * 兼容格式中国常用格式日期-时间格式化形式
     **/
    const val DT_CHINA_COMPATIBLE = "yyyy年M月d日 H时m分s秒"

    /**
     * 中国常用格式日期-时间格式化形式
     **/
    const val DT_CHINA_STANDARD = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒"

    /**
     * 兼容格式中国常用格式日期-时间格式化形式
     **/
    const val DT_CHINA_STANDARD_COMPATIBLE = "yyyy年M月d日 H时m分s秒S毫秒"
}