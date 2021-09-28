package com.any.common.core.domain

import java.util.function.Function

/**
 * javadoc ExtRestResult
 * <p>
 *     扩展的http result
 * <p>
 * @author zhang yebai
 * @date 2021/7/8 2:48 PM
 * @version 1.0.0
 **/
@Suppress(names = ["unused"])
class ExtRestResult<T> constructor(
    var code: Int? = null,
    var message: String? = null,
    var data: T? = null,
    var extra: Any? = null
) {

    /**
     * javadoc to
     * @apiNote 从 ExtRestResult<T> 转换到 ExtRestResult<R>
     *
     * @param converter RestResult data 转换器
     * @return RestResult<R>
     * @author zhang yebai
     * @date 2021/7/23 9:38 AM
     **/
    fun <R> to(converter: Function<T, R>): ExtRestResult<R> {
        val d = this.data?.let { converter.apply(it) }
        return ExtRestResult(this.code, this.message, d, this.extra)
    }

    companion object {


        /**
         * javadoc from
         * @apiNote 从 ExtRestResult<T> 构造 ExtRestResult<R>
         *
         * @param result from
         * @return RestResult<R>
         * @author zhang yebai
         * @date 2021/7/23 9:38 AM
         **/
        @JvmStatic
        fun <T, R> from(result: ExtRestResult<T>, converter: Function<T, R>): ExtRestResult<R> = result.to(converter)

        /**
         * javadoc of
         * @apiNote 构建http extend result
         *
         * @param code c
         * @param message m
         * @param data d
         * @param extra extend extra data
         * @return HttpResult
         * @author zhang yebai
         * @date 2021/6/30 6:37 PM
         **/
        @JvmStatic
        @JvmOverloads
        fun <T> of(code: Int? = null, message: String? = null, data: T? = null, extra: Any? = null) =
            ExtRestResult(code, message, data, extra)
    }

    override fun toString(): String {
        return "ExtHttpResult(code=$code, message=$message, data=$data, extra=$extra)"
    }
}