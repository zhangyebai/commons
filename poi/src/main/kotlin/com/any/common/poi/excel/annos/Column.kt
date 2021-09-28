package com.any.common.poi.excel.annos

import java.lang.annotation.Inherited

/**
 * javadoc Column
 * <p>
 *     excel 列
 * <p>
 * @author zhang yebai
 * @date 2021/7/2 4:00 PM
 * @version 1.0.0
 **/
@kotlin.annotation.Target(AnnotationTarget.FIELD)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Inherited
annotation class Column(
    val name: String = "",
    val order: Int = 0
)
