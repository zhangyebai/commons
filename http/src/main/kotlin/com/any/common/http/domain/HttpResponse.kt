package com.any.common.http.domain

data class HttpResponse(
    val httpStatusCode: Int,
    val headers: List<Map<String, String>> = listOf(),
    val body: ByteArray = byteArrayOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HttpResponse

        if (httpStatusCode != other.httpStatusCode) return false
        if (headers != other.headers) return false
        if (!body.contentEquals(other.body)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = httpStatusCode
        result = 31 * result + (headers.hashCode())
        result = 31 * result + (body.contentHashCode())
        return result
    }
}
