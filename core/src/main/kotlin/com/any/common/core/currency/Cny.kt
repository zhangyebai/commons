package com.any.common.core.currency

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * javadoc
 * <p>
 *     cny = 人民币
 * <p>
 * @author zhang yebai
 * @date 2021/6/18 2:10 PM
 * @version 1.0.0
 **/
@Suppress(names = ["unused"])
object Cny {

    private val CENTS_BASE: BigDecimal = BigDecimal(100L)

    private val UNIT_FORMATTER: DecimalFormat = DecimalFormat("#.##")

    /**
     * javadoc cent2unit
     * @apiNote 人民币分转元
     *
     * @param cents 分
     * @param roundingMode 进位模式, 默认向上进位
     * @return BigDecimal
     * @author zhang yebai
     * @date 2021/6/18 2:13 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun cent2unit(cents: Long, roundingMode: RoundingMode = RoundingMode.HALF_UP): BigDecimal =
        BigDecimal(cents).setScale(2, roundingMode).divide(CENTS_BASE, 2, roundingMode)

    /**
     * javadoc cent2unitString
     * @apiNote 人民币分转元字符串, 保留两位小数
     *
     * @param cents 分
     * @param formatter 小数位格式化器
     * @return String
     * @author zhang yebai
     * @date 2021/6/18 2:18 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun cent2unitString(cents: Long, formatter: DecimalFormat = UNIT_FORMATTER): String =
        formatter.format(cent2unit(cents))

    /**
     * javadoc unit2cents
     * @apiNote 元转分
     *
     * @param units 人民币元
     * @return Long
     * @author zhang yebai
     * @date 2021/6/18 2:26 PM
     **/
    @JvmStatic
    fun unit2cents(units: BigDecimal?): Long = units?.multiply(CENTS_BASE)?.longValueExact() ?: 0

    /**
     * javadoc unit2cents
     * @apiNote 元转分
     *
     * @param units 人民币元
     * @return Long
     * @author zhang yebai
     * @date 2021/6/18 2:29 PM
     **/
    @JvmStatic
    fun unit2cents(units: String?): Long = units?.let { BigDecimal(units).multiply(CENTS_BASE).longValueExact() } ?: 0
}