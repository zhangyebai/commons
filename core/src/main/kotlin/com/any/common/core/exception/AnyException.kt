package com.any.common.core.exception

@Suppress("unused")
class AnyException : RuntimeException {

    var code: Int

    constructor() : super(){
        this.code = -1
    }
    constructor(message: String?) : super(message){
        this.code = -1
    }
    constructor(message: String?, cause: Throwable?) : super(message, cause){
        this.code = -1
    }
    constructor(cause: Throwable?) : super(cause){
        this.code = -1
    }
    constructor(message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) : super(
        message,
        cause,
        enableSuppression,
        writableStackTrace
    ){
        this.code = -1
    }

    constructor(code: Int, message: String?): super(message){
        this.code = code
    }
}