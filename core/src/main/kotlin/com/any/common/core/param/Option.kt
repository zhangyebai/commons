package com.any.common.core.param

import java.lang.annotation.Inherited

/**
 * javadoc Option
 *         只起到标志作用, 无实际意义
 *         在入参中标志字段为选填
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
annotation class Option
