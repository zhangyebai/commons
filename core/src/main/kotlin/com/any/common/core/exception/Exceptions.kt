package com.any.common.core.exception

import java.lang.Exception

@Suppress("unused")
object Exceptions {


    @JvmStatic
    fun runtimeException(exception: Exception): RuntimeException {
        return if (exception is RuntimeException) {
            exception
        } else {
            RuntimeException(exception)
        }
    }
}