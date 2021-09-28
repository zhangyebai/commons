@file:Suppress("MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate")

package com.any.common.core.domain

/**
 * javadoc AppRestResult
 * <p>
 *     为了兼容app接口
 *     这个class 和 AppResult 只能作为出参使用 只能作为出参使用 只能作为出参使用
 *     不要拿这俩 class 去接收其它接口的返回值
 *     不要拿这俩 class 去接收其它接口的返回值
 *     不要拿这俩 class 去接收其它接口的返回值
 * <p>
 * @author zhang yebai
 * @date 2021/8/18 5:26 PM
 * @version 1.0.0
 **/
class AppRestResult<T> constructor(
    val result: AppResult,
    val data: T?
){
    override fun toString(): String {
        return "AppRestResult(result=$result, data=$data)"
    }
}