package com.any.common.core.codec

import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * javadoc Codecs
 * <p>
 *     编解码
 * <p>
 * @author zhang yebai
 * @date 2021/6/29 2:14 PM
 * @version 1.0.0
 **/
object Codecs {
    private val DIGITS_LOWER = charArrayOf(
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
        'e', 'f'
    )

    /**
     * Used to build output as Hex
     */
    private val DIGITS_UPPER = charArrayOf(
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
        'E', 'F'
    )

    /**
     * javadoc md5
     * @apiNote 对内容进行md5摘要算法
     *
     * @param text 需要进行md5摘要的数据内容
     * @param toLowerCase 是否是小写输出
     * @return String 摘要字符串
     * @author zhang yebai
     * @date 2021/6/29 2:14 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun md5(text: String, toLowerCase: Boolean = true): String {
        return try {
            val messageDigest: MessageDigest = MessageDigest.getInstance("MD5")
            val bytes: ByteArray = text.toByteArray(StandardCharsets.UTF_8)
            messageDigest.update(bytes, 0, text.length)
            encodeHexString(messageDigest.digest(), toLowerCase)
        } catch (ex: NoSuchAlgorithmException) {
            throw RuntimeException("CodecUtil.md5() exception", ex)
        }
    }

    private fun encodeHexString(data: ByteArray, toLowerCase: Boolean = true): String =
        String(encodeHex(data, if (toLowerCase) DIGITS_LOWER else DIGITS_UPPER))

    private fun encodeHex(data: ByteArray, toDigits: CharArray): CharArray {
        val l = data.size
        val out = CharArray(l shl 1)
        // two characters form the hex value.
        var i = 0
        var j = 0
        while (i < l) {
            out[j++] = toDigits[0xF0 and data[i].toInt() ushr 4]
            out[j++] = toDigits[0x0F and data[i].toInt()]
            i++
        }
        return out
    }
}