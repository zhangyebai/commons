package com.any.common.core.random

import java.util.concurrent.ThreadLocalRandom

/**
 * javadoc Randoms
 * <p>
 *     random utils
 * <p>
 * @author zhang yebai
 * @date 2021/6/16 7:53 PM
 * @version 1.0.0
 **/
@Suppress(names = ["unused"])
object Randoms {


    /**
     * javadoc numbers
     * @apiNote 随机数字字符串
     *
     * @param bits 字符串位数
     * @param low 数字下限0
     * @param high 数字上线9
     * @return String
     * @author zhang yebai
     * @date 2021/6/16 7:53 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun numbers(bits: Int, low: Int = 0, high: Int = 9): String =
        IntRange(1, bits).map { randomInt(low, high).toString() }.joinToString("") { it }

    /**
     * javadoc strings
     * @apiNote 随机字符串
     *
     * @param bits 字符串位数
     * @return String
     * @author zhang yebai
     * @date 2021/6/16 7:57 PM
     **/
    @JvmStatic
    fun strings(bits: Int): String =
        IntRange(1, bits).map { randomInt(100, 100 + 25) - 100 }.map { LITTLE_CASE_CHAR_TABLE[it].toString() }
            .joinToString("") { it }

    /**
     * javadoc capitals
     * @apiNote 随机字符串 大写
     *
     * @param bits 字符串位数
     * @return String
     * @author zhang yebai
     * @date 2021/6/16 7:57 PM
     **/
    @JvmStatic
    fun capitals(bits: Int): String =
        IntRange(1, bits).map { randomInt(100, 100 + 25) - 100 }.map { CAPITAL_CHAR_TABLE[it].toString() }
            .joinToString("") { it }

    /**
     * javadoc mixed
     * @apiNote 随机字符串 大小写混合
     *
     * @param bits 字符串位数
     * @return String
     * @author zhang yebai
     * @date 2021/6/16 7:57 PM
     **/
    @JvmStatic
    fun mixed(bits: Int): String = IntRange(1, bits).map { randomInt(100, 100 + 51) - 100 }
        .map { if (it > 25) CAPITAL_CHAR_TABLE[it - 26].toString() else LITTLE_CASE_CHAR_TABLE[it].toString() }
        .joinToString("") { it }

    private fun randomInt(low: Int, high: Int): Int = ThreadLocalRandom.current().nextInt(high - low) + low

    private val LITTLE_CASE_CHAR_TABLE = charArrayOf(
        'a',
        'b',
        'c',
        'd',
        'e',
        'f',
        'g',
        'h',
        'i',
        'j',
        'k',
        'l',
        'm',
        'n',
        'o',
        'p',
        'q',
        'r',
        's',
        't',
        'u',
        'v',
        'w',
        'x',
        'y',
        'z'
    )

    private val CAPITAL_CHAR_TABLE = charArrayOf(
        'A',
        'B',
        'C',
        'D',
        'E',
        'F',
        'G',
        'H',
        'I',
        'J',
        'K',
        'L',
        'M',
        'N',
        'O',
        'P',
        'Q',
        'R',
        'S',
        'T',
        'U',
        'V',
        'W',
        'X',
        'Y',
        'Z'
    )
}