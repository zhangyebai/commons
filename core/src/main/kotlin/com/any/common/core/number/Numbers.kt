package com.any.common.core.number

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

@Suppress(names = ["unused"])
object Numbers {

    // 以下格式化表达式以 π 的输出为例
    // N for number, 整数部分
    // P for point, 小数部分

    /**
     * π: 3.14
     **/
    const val N_P_1 = "#.00"

    /**
     * π: 3
     **/
    const val N_1 = "#"

    /**
     * π: 03.14
     **/
    const val NN_P_1 = "00.##"

    /**
     * 标准货币格式化
     **/
    const val STANDARD_MONEY = ",###"

    private val DF = DecimalFormat("#.00")

    private val BASE = BigDecimal(100)


    private const val SCALE = 2

    /**
     * javadoc string
     * @apiNote 格式化输出 BigDecimal
     *
     * @param from 被转换对象 BigDecimal
     * @param df DecimalFormat
     * @return String
     * @author zhang yebai
     * @date 2021/6/23 5:43 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun string(from: BigDecimal, df: DecimalFormat = DF): String = df.format(from)

    /**
     * javadoc string
     * @apiNote 格式化输出 BigDecimal
     *
     * @param from 被转换对象 BigDecimal
     * @param pattern 格式化表达式1
     * @return String
     * @author zhang yebai
     * @date 2021/6/23 5:43 PM
     **/
    @JvmStatic
    fun string(from: BigDecimal, pattern: String): String = DecimalFormat(pattern).format(from)

    /**
     * javadoc to
     * @apiNote
     *
     * @param from 被转换对象
     * @param scale 保留小数位数
     * @param base 除数
     * @param roundingMode 进位模式
     * @return BigDecimal
     * @author zhang yebai
     * @date 2021/6/23 5:45 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun to(
        from: Int,
        scale: Int = SCALE,
        base: BigDecimal = BASE,
        roundingMode: RoundingMode = RoundingMode.HALF_UP
    ): BigDecimal = BigDecimal(from).divide(base, scale, roundingMode)

    /**
     * javadoc to
     * @apiNote
     *
     * @param from 被转换对象
     * @param scale 保留小数位数
     * @param base 除数
     * @param roundingMode 进位模式
     * @return BigDecimal
     * @author zhang yebai
     * @date 2021/6/23 5:45 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun to(
        from: Long,
        scale: Int = SCALE,
        base: BigDecimal = BASE,
        roundingMode: RoundingMode = RoundingMode.HALF_UP
    ): BigDecimal = BigDecimal(from).divide(base, scale, roundingMode)

    /**
     * javadoc to
     * @apiNote
     *
     * @param from 被转换对象
     * @param scale 保留小数位数
     * @param base 除数
     * @param roundingMode 进位模式
     * @return BigDecimal
     * @author zhang yebai
     * @date 2021/6/23 5:45 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun to(
        from: Double,
        scale: Int = SCALE,
        base: BigDecimal = BASE,
        roundingMode: RoundingMode = RoundingMode.HALF_UP
    ): BigDecimal = BigDecimal(from).divide(base, scale, roundingMode)

    /**
     * javadoc to
     * @apiNote
     *
     * @param from 被转换对象
     * @param scale 保留小数位数
     * @param base 除数
     * @param roundingMode 进位模式
     * @return BigDecimal
     * @author zhang yebai
     * @date 2021/6/23 5:45 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun to(
        from: Float,
        scale: Int = SCALE,
        base: BigDecimal = BASE,
        roundingMode: RoundingMode = RoundingMode.HALF_UP
    ): BigDecimal = BigDecimal(from.toDouble()).divide(base, scale, roundingMode)

    /**
     * javadoc to
     * @apiNote
     *
     * @param from 被转换对象
     * @param scale 保留小数位数
     * @param base 除数
     * @param roundingMode 进位模式
     * @return BigDecimal
     * @author zhang yebai
     * @date 2021/6/23 5:45 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun to(
        from: String,
        scale: Int = SCALE,
        base: BigDecimal = BASE,
        roundingMode: RoundingMode = RoundingMode.HALF_UP
    ): BigDecimal = BigDecimal(from).divide(base, scale, roundingMode)
}