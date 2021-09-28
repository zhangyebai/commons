package com.any.common.spring.annos

import org.springframework.stereotype.Component
import java.lang.annotation.Inherited

/**
 * javadoc Processor
 * <p>
 *     spring context orm processor
 * <p>
 * @author zhang yebai
 * @date 2021/7/5 2:36 PM
 * @version 1.0.0
 **/
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Inherited
@Component
annotation class Processor(val value: String = "")