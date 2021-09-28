package com.any.common.core.param

import java.lang.RuntimeException


/**
 * javadoc Params
 * <p>
 *     参数检测相关utils
 * <p>
 * @author zhang yebai
 * @date 2021/6/21 2:35 PM
 * @version 1.0.0
 **/
@Suppress(names = ["unused"])
object Params {

    /**
     * javadoc check
     * @apiNote 检测参数
     *
     * @param obj 检测对象
     * @param raiseException 检测不通过时是否抛出异常
     * @return null
     * @author zhang yebai
     * @date 2021/6/21 2:35 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun check(obj: Any, raiseException: Boolean = false): Boolean = checkParams(obj, raiseException)

    private fun checkParams(obj: Any, raiseException: Boolean): Boolean {
        val fields = obj.javaClass.declaredFields
        for (field in fields) {
            field.isAccessible = true
            val value = field.get(obj)
            if (field.isAnnotationPresent(Require::class.java)) {
                if (value == null) {
                    return doParamResult(field.name, raiseException)
                }
                val annotation = field.getAnnotation(Require::class.java)
                if (annotation.notEmpty) {
                    if (value is String) {
                        if (value.length <= 0) {
                            return doParamContentResult(field.name, raiseException)
                        }
                    } else if (value is Collection<*>) {
                        if (value.isEmpty()) {
                            return doParamContentResult(field.name, raiseException)
                        }
                    } else if (value is Map<*, *>) {
                        if (value.isEmpty()) {
                            return doParamContentResult(field.name, raiseException)
                        }
                    } else if (value is Array<*>) {
                        if (value.size <= 0) {
                            return doParamContentResult(field.name, raiseException)
                        }
                    } else {
                        /* do nothing */
                    }
                }
            }
        }
        return true
    }

    private fun doParamResult(name: String, raiseException: Boolean): Boolean {
        if (!raiseException) {
            return false
        }
        throw RuntimeException("参数 [$name]不能为空")
    }

    private fun doParamContentResult(name: String, raiseException: Boolean): Boolean {
        if (!raiseException) {
            return false
        }
        throw RuntimeException("参数 [$name] 内容不能为空")
    }
}