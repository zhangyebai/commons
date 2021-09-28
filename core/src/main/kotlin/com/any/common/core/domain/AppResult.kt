package com.any.common.core.domain

/**
 * javadoc AppResult
 * <p>
 *     为了兼容app接口
 *     这个class 和 AppRestResult 只能作为出参使用 只能作为出参使用 只能作为出参使用
 *     不要拿这俩 class 去接收其它接口的返回值
 *     不要拿这俩 class 去接收其它接口的返回值
 *     不要拿这俩 class 去接收其它接口的返回值
 * <p>
 * @author zhang yebai
 * @date 2021/8/18 5:25 PM
 * @version 1.0.0
 **/
class AppResult constructor(
    val code: Int,
    val message: String,
){



    companion object{
        private val default_ok = AppResult(0, "success")
        private val default_error = AppResult(-1, "error")

        @JvmStatic
        fun of(code: Int, message: String): AppResult = AppResult(code, message)

        @JvmStatic
        fun ok(): AppResult = default_ok

        @JvmStatic
        fun error(): AppResult = default_error
    }

    override fun toString(): String {
        return "AppResult(code=$code, message='$message')"
    }
}