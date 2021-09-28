package com.any.common.core.capacity

import com.any.common.core.domain.Tuple
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * javadoc IoCapacityUtil
 * <p>
 *     特指计算机相关io (网络io读写, 文件io占用, 磁盘io占用等io)容量计算
 * <p>
 * @author zhang yebai
 * @date 2021/6/18 2:34 PM
 * @version 1.0.0
 **/
@Suppress(names = ["unused"])
object IoCapacityUtil {

    private val DF_CAPACITY_FORMATTER = DecimalFormat("#0.00")

    private val CAPACITY_BASE_1 = BigDecimal(1024)

    private val CAPACITY_BASE_2 = BigDecimal(1024 * 1024)

    private val CAPACITY_BASE_3 = BigDecimal(1024 * 1024 * 1024)

    private fun pattern(scale: Int = 2): String = if (scale > 0) "#." + "#".repeat(scale) else "#"

    /*********************************** bytes start ********************************************/

    /**
     * javadoc b2kb
     * @apiNote byte to kb
     *
     * @param bytes bytes
     * @param scale 保留小数位精度
     * @param roundingMode 进位模式
     * @return BigDecimal
     * @author zhang yebai
     * @date 2021/6/18 3:53 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun b2kb(bytes: Long?, scale: Int = 2, roundingMode: RoundingMode = RoundingMode.HALF_UP): BigDecimal =
        bytes?.let { BigDecimal(it).divide(CAPACITY_BASE_1, scale, roundingMode) } ?: BigDecimal(0)

    /**
     * javadoc b2kbs
     * @apiNote byte to kb string
     *
     * @param bytes bytes
     * @param scale 保留小数位精度
     * @param roundingMode 进位模式
     * @return String
     * @author zhang yebai
     * @date 2021/6/18 3:53 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun b2kbs(bytes: Long?, scale: Int = 2, roundingMode: RoundingMode = RoundingMode.HALF_UP): String =
        b2kb(bytes, scale, roundingMode).let { DecimalFormat(pattern(scale)).format(it) }

    /**
     * javadoc b2mb
     * @apiNote byte to mb
     *
     * @param bytes bytes
     * @param scale 保留小数位精度
     * @param roundingMode 进位模式
     * @return BigDecimal
     * @author zhang yebai
     * @date 2021/6/18 3:53 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun b2mb(bytes: Long?, scale: Int = 2, roundingMode: RoundingMode = RoundingMode.HALF_UP): BigDecimal =
        bytes?.let { BigDecimal(it).divide(CAPACITY_BASE_2, scale, roundingMode) } ?: BigDecimal(0)


    /**
     * javadoc b2mbs
     * @apiNote byte to mb string
     *
     * @param bytes bytes
     * @param scale 保留小数位精度
     * @param roundingMode 进位模式
     * @return String
     * @author zhang yebai
     * @date 2021/6/18 3:53 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun b2mbs(bytes: Long?, scale: Int = 2, roundingMode: RoundingMode = RoundingMode.HALF_UP): String =
        b2mb(bytes, scale, roundingMode).let { DecimalFormat(pattern(scale)).format(it) }


    /**
     * javadoc b2gb
     * @apiNote byte to gb
     *
     * @param bytes bytes
     * @param scale 保留小数位精度
     * @param roundingMode 进位模式
     * @return BigDecimal
     * @author zhang yebai
     * @date 2021/6/18 3:53 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun b2gb(bytes: Long?, scale: Int = 2, roundingMode: RoundingMode = RoundingMode.HALF_UP): BigDecimal =
        bytes?.let { BigDecimal(it).divide(CAPACITY_BASE_3, scale, roundingMode) } ?: BigDecimal(0)

    /**
     * javadoc b2gbs
     * @apiNote byte to gb string
     *
     * @param bytes bytes
     * @param scale 保留小数位精度
     * @param roundingMode 进位模式
     * @return String
     * @author zhang yebai
     * @date 2021/6/18 3:53 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun b2gbs(bytes: Long?, scale: Int = 2, roundingMode: RoundingMode = RoundingMode.HALF_UP): String =
        b2gb(bytes, scale, roundingMode).let { DecimalFormat(pattern(scale)).format(it) }

    /**
     * javadoc b2Any
     * @apiNote 字节大小自动转换成相应的单位:
     *          如 1126 bytes => 1.1 KB
     *
     * @param bytes bytes
     * @param scale 保留小数位精度
     * @param roundingMode 进位模式
     * @return Tuple<String, String>
     * @author zhang yebai
     * @date 2021/6/18 3:53 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun b2Any(bytes: Long?, scale: Int = 2, roundingMode: RoundingMode = RoundingMode.HALF_UP): Tuple<String, String> {
        return bytes?.let {
            when {
                it < CAPACITY_BASE_1.longValueExact() -> {
                    Tuple.of(it.toString(), "B")
                }
                it < CAPACITY_BASE_2.longValueExact() -> {
                    Tuple.of(b2kbs(it, scale, roundingMode), "KB")
                }
                it < CAPACITY_BASE_3.longValueExact() -> {
                    Tuple.of(b2mbs(it, scale, roundingMode), "MB")
                }
                else -> {
                    Tuple.of(b2gbs(it, scale, roundingMode), "GB")
                }
            }
        } ?: Tuple.of("0", "B")
    }

    /*********************************** bytes end ********************************************/

    /*********************************** kilo bytes start ********************************************/

    /**
     * javadoc kb2b
     * @apiNote kb to bytes
     *
     * @param kbs kilo bytes
     * @return Long
     * @author zhang yebai
     * @date 2021/6/18 3:53 PM
     **/
    @JvmStatic
    fun kb2b(kbs: Long?): Long = kbs?.let { kbs * 1024 } ?: 0

    /**
     * javadoc kb2mb
     * @apiNote kb to mb
     *
     * @param kbs kilo bytes
     * @param scale 保留小数位精度
     * @param roundingMode 进位模式
     * @return BigDecimal
     * @author zhang yebai
     * @date 2021/6/18 3:53 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun kb2mb(kbs: Long?, scale: Int = 2, roundingMode: RoundingMode = RoundingMode.HALF_UP): BigDecimal =
        kbs?.let { BigDecimal(it).divide(CAPACITY_BASE_1, scale, roundingMode) } ?: BigDecimal(0)

    /**
     * javadoc kb2mbs
     * @apiNote kb to mb string
     *
     * @param kbs kilo bytes
     * @param scale 保留小数位精度
     * @param roundingMode 进位模式
     * @return String
     * @author zhang yebai
     * @date 2021/6/18 3:53 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun kb2mbs(kbs: Long?, scale: Int = 2, roundingMode: RoundingMode = RoundingMode.HALF_UP): String =
        kb2mb(kbs, scale, roundingMode).let { DecimalFormat(pattern(scale)).format(it) }

    /**
     * javadoc kb2gb
     * @apiNote kb to gb
     *
     * @param kbs kilo bytes
     * @param scale 保留小数位精度
     * @param roundingMode 进位模式
     * @return BigDecimal
     * @author zhang yebai
     * @date 2021/6/18 3:53 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun kb2gb(kbs: Long?, scale: Int = 2, roundingMode: RoundingMode = RoundingMode.HALF_UP): BigDecimal =
        kbs?.let { BigDecimal(it).divide(CAPACITY_BASE_2, scale, roundingMode) } ?: BigDecimal(0)

    /**
     * javadoc b2gbs
     * @apiNote kb to gb string
     *
     * @param kbs kilo bytes
     * @param scale 保留小数位精度
     * @param roundingMode 进位模式
     * @return String
     * @author zhang yebai
     * @date 2021/6/18 3:53 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun kb2gbs(kbs: Long?, scale: Int = 2, roundingMode: RoundingMode = RoundingMode.HALF_UP): String =
        kb2gb(kbs, scale, roundingMode).let { DecimalFormat(pattern(scale)).format(it) }

    /**
     * javadoc kb2Any
     * @apiNote 字节大小自动转换成相应的单位:
     *          如 1126 kb => 1.1 MB
     *             819 kb ==> 819 KB
     *
     * @param kbs kilo bytes
     * @param scale 保留小数位精度
     * @param roundingMode 进位模式
     * @return Tuple<String, String>
     * @author zhang yebai
     * @date 2021/6/18 3:53 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun kb2Any(kbs: Long?, scale: Int = 2, roundingMode: RoundingMode = RoundingMode.HALF_UP): Tuple<String, String> {
        return kbs?.let {
            when {
                it < CAPACITY_BASE_1.longValueExact() -> {
                    Tuple.of(it.toString(), "KB")
                }
                it < CAPACITY_BASE_2.longValueExact() -> {
                    Tuple.of(kb2mbs(it, scale, roundingMode), "MB")
                }
                else -> {
                    Tuple.of(kb2gbs(it, scale, roundingMode), "GB")
                }
            }
        } ?: Tuple.of("0", "KB")
    }

    /*********************************** kilo bytes end ********************************************/

    /*********************************** mega bytes start ********************************************/

    /**
     * javadoc mb2b
     * @apiNote mb to bytes
     *
     * @param mbs mega bytes
     * @return Long
     * @author zhang yebai
     * @date 2021/6/18 3:53 PM
     **/
    @JvmStatic
    fun mb2b(mbs: Long?): Long = mbs?.let { it * 1024 * 1024 } ?: 0

    /**
     * javadoc mb2kb
     * @apiNote mb to kb
     *
     * @param mbs mega bytes
     * @return Long
     * @author zhang yebai
     * @date 2021/6/18 3:53 PM
     **/
    @JvmStatic
    fun mb2kb(mbs: Long?): Long = mbs?.let { it * 1024 } ?: 0

    /**
     * javadoc mb2gb
     * @apiNote mb to gb
     *
     * @param mbs mega bytes
     * @param scale 保留小数位精度
     * @param roundingMode 进位模式
     * @return BigDecimal
     * @author zhang yebai
     * @date 2021/6/18 3:53 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun mb2gb(mbs: Long?, scale: Int = 2, roundingMode: RoundingMode = RoundingMode.HALF_UP): BigDecimal =
        mbs?.let { BigDecimal(it).divide(CAPACITY_BASE_1, scale, roundingMode) } ?: BigDecimal(0)

    /**
     * javadoc mb2gbs
     * @apiNote mb to gb string
     *
     * @param mbs mega bytes
     * @param scale 保留小数位精度
     * @param roundingMode 进位模式
     * @return String
     * @author zhang yebai
     * @date 2021/6/18 3:53 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun mb2gbs(mbs: Long?, scale: Int = 2, roundingMode: RoundingMode = RoundingMode.HALF_UP): String =
        mb2gb(mbs, scale, roundingMode).let { DecimalFormat(pattern(scale)).format(it) }

    /**
     * javadoc mb2Any
     * @apiNote 字节大小自动转换成相应的单位:
     *          如 1126 mb => 1.1 GB
     *
     * @param mbs mega bytes
     * @param scale 保留小数位精度
     * @param roundingMode 进位模式
     * @return Tuple<String, String>
     * @author zhang yebai
     * @date 2021/6/18 3:53 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun mb2Any(mbs: Long?, scale: Int = 2, roundingMode: RoundingMode = RoundingMode.HALF_UP): Tuple<String, String> {
        return mbs?.let {
            when {
                it < CAPACITY_BASE_1.longValueExact() -> {
                    Tuple.of(it.toString(), "MB")
                }
                else -> {
                    Tuple.of(mb2gbs(it, scale, roundingMode), "GB")
                }
            }
        } ?: Tuple.of("0", "MB")
    }

    /*********************************** mega bytes end ********************************************/


    /*********************************** giga bytes start ********************************************/

    /**
     * javadoc gb2b
     * @apiNote gb to bytes
     *
     * @param gbs giga bytes
     * @return Long
     * @author zhang yebai
     * @date 2021/6/18 3:53 PM
     **/
    @JvmStatic
    fun gb2b(gbs: Long?): Long = gbs?.let { it * 1024 * 1024 * 1024 } ?: 0

    /**
     * javadoc gb2kb
     * @apiNote gb to kb
     *
     * @param gbs giga bytes
     * @return Long
     * @author zhang yebai
     * @date 2021/6/18 3:53 PM
     **/
    @JvmStatic
    fun gb2kb(gbs: Long?): Long = gbs?.let { it * 1024 * 1024 } ?: 0

    /**
     * javadoc gb2mb
     * @apiNote gb to mb
     *
     * @param gbs giga bytes
     * @return Long
     * @author zhang yebai
     * @date 2021/6/18 3:53 PM
     **/
    @JvmStatic
    fun gb2mb(gbs: Long?): Long = gbs?.let { it * 1024 } ?: 0

    /*********************************** giga bytes end ********************************************/

}