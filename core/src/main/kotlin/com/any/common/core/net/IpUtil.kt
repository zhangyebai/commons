package com.any.common.core.net

import java.net.Inet4Address
import java.net.UnknownHostException

@Suppress(names = ["unused"])
object IpUtil {

    /**
     * javadoc ip2long
     * @apiNote 按顺序将点分10进制ip地址转换成long数字
     *
     * @param ip ipV4地址
     * @return Long
     * @author zhang yebai
     * @date 2021/6/17 9:46 AM
     **/
    @JvmStatic
    fun ip2long(ip: String): Long {
        val parts = ip.split(".")
        return (parts[0].toLong() shl 24) + (parts[1].toLong() shl 16) + (parts[2].toLong() shl 8) + (parts[3].toLong())
    }

    /**
     * javadoc ip2longReverse
     * @apiNote 按反序将点分10进制ip地址转换成long数字
     *
     * @param ip ipV4地址
     * @return Long
     * @author zhang yebai
     * @date 2021/6/17 9:47 AM
     **/
    @JvmStatic
    fun ip2longReverse(ip: String): Long {
        val parts = ip.split(".")
        return (parts[3].toLong() shl 24) + (parts[2].toLong() shl 16) + (parts[1].toLong() shl 8) + (parts[0].toLong())
    }

    /**
     * javadoc long2ip
     * @apiNote 按正序将数字解析成点分10进制ip地址
     *
     * @param ip long类型ip地址
     * @return String
     * @author zhang yebai
     * @date 2021/6/17 9:48 AM
     **/
    @JvmStatic
    fun long2ip(ip: Long): String {
        return (ip ushr 24).toString() + "." + ((ip and 0x00FFFFFF) ushr 16).toString() + "." + ((ip and 0x0000FFFF) ushr 8).toString() + "." + (ip and 0x000000FF).toString()
    }

    /**
     * javadoc long2ipReverse
     * @apiNote 按反序将数字解析成点分10进制ip地址
     *
     * @param ip long类型ip地址
     * @return String
     * @author zhang yebai
     * @date 2021/6/17 9:48 AM
     **/
    @JvmStatic
    fun long2ipReverse(ip: Long): String {
        return (ip and 0x000000FF).toString() + "." + ((ip and 0x0000FFFF) ushr 8).toString() + "." + ((ip and 0x00FFFFFF) ushr 16).toString() + "." + (ip ushr 24).toString()
    }

    /**
     * javadoc localIp
     * @apiNote 取本机地址, 如无法取到返回环回地址 127.0.0.1
     *
     * @return String
     * @author zhang yebai
     * @date 2021/6/17 9:49 AM
     **/
    @JvmStatic
    fun localIp(): String {
        return try {
            return Inet4Address.getLocalHost().hostAddress
        } catch (ignore: UnknownHostException) {
            // 如果获取失败，则使用随机数备用
            "127.0.0.1"
        }
    }
}