package com.any.common.core.io

import java.io.InputStream
import java.io.OutputStream

/**
 * javadoc IoUtil
 * <p>
 *     io utils
 * <p>
 * @author zhang yebai
 * @date 2021/7/7 11:24 AM
 * @version 1.0.0
 **/
@Suppress(names = ["unused"])
object IoUtil {

    private const val DEFAULT_BUFFER_SIZE = 4 * 1024

    /**
     * javadoc read
     * @apiNote 读取流中的数据
     *
     * @param input input stream
     * @return byte[]
     * @author zhang yebai
     * @date 2021/7/7 11:24 AM
     **/
    @JvmStatic
    fun read(input: InputStream): ByteArray = input.readBytes()


    /**
     * javadoc write
     * @apiNote 向流中写入数据
     *
     * @param bytes 待写入的数据
     * @param output output stream
     * @return Int 写入的数据量
     * @author zhang yebai
     * @date 2021/7/7 11:25 AM
     **/
    @JvmStatic
    fun write(bytes: ByteArray, output: OutputStream): Int {
        output.write(bytes)
        return bytes.size
    }

    /**
     * javadoc write
     * @apiNote 向流中写入数据
     *
     * @param input 输入流
     * @param output output stream
     * @return Int 写入的数据量
     * @author zhang yebai
     * @date 2021/7/7 11:25 AM
     **/
    @JvmStatic
    fun write(input: InputStream, output: OutputStream): Int {
        val bytes = input.readBytes()
        output.write(bytes)
        return bytes.size
    }
}