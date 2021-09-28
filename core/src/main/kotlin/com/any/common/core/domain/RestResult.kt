package com.any.common.core.domain

import java.lang.RuntimeException
import java.util.*
import java.util.function.Function

/**
 * javadoc RestResult
 * <p>
 *     http result
 * <p>
 * @author zhang yebai
 * @date 2021/7/8 2:49 PM
 * @version 1.0.0
 **/
@Suppress("unused")
class RestResult<T> constructor(
    var code: Int? = null,
    var message: String? = null,
    var data: T? = null
) {

    /**
     * javadoc to
     * @apiNote 从 RestResult<T> 转换到 RestResult<R>
     *
     * @param converter RestResult data 转换器
     * @return RestResult<R>
     * @author zhang yebai
     * @date 2021/7/23 9:38 AM
     **/
    fun <R> to(converter: Function<T, R>): RestResult<R> {
        val d = this.data?.let { converter.apply(it) }
        return RestResult(this.code, this.message, d)
    }


    /**
     * javadoc successfully
     * @apiNote 判断接口调用是否成功
     *
     * @param code 目标code值
     * @return Boolean
     * @author zhang yebai
     * @date 2021/9/14 12:58 PM
     **/
    @JvmOverloads
    fun successfully(code: Int = 0): Boolean = Objects.nonNull(this.code) && this.code == code

    /**
     * javadoc successfullyGet
     * @apiNote 如果接口调用成功则返回data, 否则抛出runtime exception, 并携带message
     *
     * @param code 目标code
     * @return T
     * @author zhang yebai
     * @date 2021/9/14 12:59 PM
     **/
    @JvmOverloads
    fun successfullyGet(code: Int = 0): T?{
        if(this.successfully(code)){
            return this.data
        }
        throw RuntimeException(this.message)
    }

    companion object {

        /**
         * javadoc from
         * @apiNote 从 RestResult<T> 构造 RestResult<R>
         *
         * @param result from
         * @return RestResult<R>
         * @author zhang yebai
         * @date 2021/7/23 9:38 AM
         **/
        @JvmStatic
        fun <T, R> from(result: RestResult<T>, converter: Function<T, R>): RestResult<R> = result.to(converter)

        /**
         * javadoc ok
         * @apiNote
         *
         * @param data T
         * @return RestResult
         * @author zhang yebai
         * @date 2021/9/14 1:53 PM
         **/
        @JvmStatic
        fun <T> ok(data: T): RestResult<T> = RestResult(0, "success", data)

        /**
         * javadoc of
         * @apiNote 构建http result
         *
         * @param code c
         * @param message m
         * @param data d
         * @return HttpResult
         * @author zhang yebai
         * @date 2021/6/30 6:37 PM
         **/
        @JvmStatic
        @JvmOverloads
        fun <T> of(code: Int? = null, message: String? = null, data: T? = null): RestResult<T> =
            RestResult(code, message, data)
    }

    override fun toString(): String {
        return "HttpResult(code=$code, message=$message, data=$data)"
    }
}