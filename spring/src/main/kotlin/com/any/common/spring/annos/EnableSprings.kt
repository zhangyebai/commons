package com.any.common.spring.annos

import com.any.common.spring.Springs
import org.springframework.context.annotation.Import
import java.lang.annotation.Inherited

@Suppress(names = ["unused"])
@Target(AnnotationTarget.TYPE)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Inherited
@Import(value = [Springs::class])
annotation class EnableSprings
