package com.any.common.core.file

import com.any.common.core.domain.Tuple
import java.io.File
import java.nio.charset.Charset

@Suppress(names = ["unused"])
object Files {

    /**
     * javadoc suffix
     * @apiNote 获取文件后缀, 如果没有返回null
     *
     * @param name 文件名
     * @param spliterator 分隔符
     * @return String
     * @author zhang yebai
     * @date 2021/6/17 8:13 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun suffix(name: String, spliterator: String = "."): String? = when (val idx = name.lastIndexOf(spliterator)) {
        -1 -> {
            null
        }
        (name.length - 1) -> {
            null
        }
        else -> name.substring(idx + 1)
    }

    /**
     * javadoc suffix
     * @apiNote 获取文件名称(不包含后缀)
     *
     * @param name 文件名
     * @param spliterator 分隔符
     * @return String
     * @author zhang yebai
     * @date 2021/6/17 8:13 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun prefix(name: String, spliterator: String = "."): String = when (val idx = name.lastIndexOf(spliterator)) {
        -1 -> name
        else -> name.substring(0, idx)
    }

    /**
     * javadoc prefixAndSuffix
     * @apiNote 获取文件名称, 文件后缀(如果没有返回null)
     *
     * @param name 文件名
     * @param spliterator 分隔符
     * @return Tuple<String, String>
     * @author zhang yebai
     * @date 2021/6/17 8:13 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun prefixAndSuffix(name: String, spliterator: String = "."): Tuple<String, String> =
        Tuple.of(prefix(name, spliterator), suffix(name, spliterator))

    /**
     * javadoc bytes
     * @apiNote 不适合作大文件直接读取, 会oom, 适合读取小文件内容
     *
     * @param name 文件全路径 or 相对路径
     * @return ByteArray byte[]
     * @author zhang yebai
     * @date 2021/6/28 3:18 PM
     **/
    @JvmStatic
    fun bytes(name: String): ByteArray = File(name).readBytes()

    /**
     * javadoc strings
     * @apiNote 不适合作大文件直接读取, 会oom, 适合读取小文件内容
     *
     * @param name 文件全路径 or 相对路径
     * @param charset 文本编码
     * @return String 文本内容字符串
     * @author zhang yebai
     * @date 2021/6/28 3:22 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun strings(name: String, charset: Charset = Charsets.UTF_8): String = File(name).readText(charset)

    /**
     * javadoc lines
     * @apiNote 不适合作大文件直接读取, 会oom, 适合读取小文件内容
     *
     * @param name 文件全路径 or 相对路径
     * @param charset 文本编码
     * @return String 文本内容字符串
     * @author zhang yebai
     * @date 2021/6/28 3:22 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun lines(name: String, charset: Charset = Charsets.UTF_8): List<String> = File(name).readLines(charset)
}