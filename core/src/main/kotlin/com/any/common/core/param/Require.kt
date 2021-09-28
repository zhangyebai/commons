package com.any.common.core.param

import java.lang.annotation.Inherited

/**
 * javadoc Require
 *         在入参中标志字段为必填
 *         如指定 notEmpty = true 则表示 集合 map String等对象不能为空
 * <p>
 * <p>
 * @author zhang yebai
 * @date 2021/6/21 2:32 PM
 * @version 1.0.0
 **/
@kotlin.annotation.Target(AnnotationTarget.FIELD)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Inherited
annotation class Require(
    val notEmpty: Boolean = false
)
