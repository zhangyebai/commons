package com.any.common.http

import com.any.common.http.domain.HttpMethod
import com.any.common.http.domain.HttpResponse
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

/**
 * javadoc HttpClient
 * <p>
 *     http client utils
 * <p>
 * @author zhang yebai
 * @date 2021/7/9 12:17 PM
 * @version 1.0.0
 **/
@Suppress(names = ["unused"])
object HttpClient {

    private const val SEPARATOR = "?"

    private const val PARAM_SEPARATOR = "&"

    private const val EQUALS_SEPARATOR = "="

    /**
     * javadoc easyGet
     * @apiNote GET 请求
     *
     * @param url 请求地址, 如果没有 https:// http://前缀会默认添加一个http://前缀, 所以使用https时必须添加前缀
     * @param params query 参数
     * @param headers header 参数
     * @param cookies cookie 参数
     * @return byte[]
     * @author zhang yebai
     * @date 2021/7/9 12:06 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun easyGet(
        url: String,
        params: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        cookies: Map<String, String>? = null
    ): ByteArray {
        val path = url(url, params)
        val request = request(path, headers, cookies, HttpMethod.GET)
        return easyExecute(request)
    }

    /**
     * javadoc get
     * @apiNote GET 请求
     *
     * @param url 请求地址, 如果没有 https:// http://前缀会默认添加一个http://前缀, 所以使用https时必须添加前缀
     * @param params query 参数
     * @param headers header 参数
     * @param cookies cookie 参数
     * @return HttpResponse
     * @author zhang yebai
     * @date 2021/7/9 12:06 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun get(
        url: String,
        params: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        cookies: Map<String, String>? = null
    ): HttpResponse {
        val path = url(url, params)
        val request = request(path, headers, cookies, HttpMethod.GET)
        return execute(request)
    }

    /**
     * javadoc getAsync
     * @apiNote 异步 GET 请求
     *
     * @param url 请求地址, 如果没有 https:// http://前缀会默认添加一个http://前缀, 所以使用https时必须添加前缀
     * @param params query 参数
     * @param headers header 参数
     * @param cookies cookie 参数
     * @return HttpResponse
     * @author zhang yebai
     * @date 2021/7/9 12:06 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun getAsync(
        url: String,
        params: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        cookies: Map<String, String>? = null
    ): Future<HttpResponse> {
        val path = url(url, params)
        val request = request(path, headers, cookies, HttpMethod.GET)
        val callable = Callable<HttpResponse> { execute(request) }
        return Executors.newSingleThreadExecutor().submit(callable)
    }

    /**
     * javadoc easyPost
     * @apiNote POST 请求
     *
     * @param url 请求地址, 如果没有 https:// http://前缀会默认添加一个http://前缀, 所以使用https时必须添加前缀
     * @param body 请求body 参数
     * @param forms form表单形式的body 参数
     * @param params query 参数
     * @param headers header参数
     * @param cookies cookie 参数
     * @return byte[]
     * @author zhang yebai
     * @date 2021/7/9 12:09 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun easyPost(
        url: String,
        body: String? = null,
        forms: Map<String, String>? = null,
        params: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        cookies: Map<String, String>? = null
    ): ByteArray {
        val path = url(url, params)
        val request = postRequest(path, headers, cookies, body, forms)
        return easyExecute(request)
    }

    /**
     * javadoc post
     * @apiNote POST 请求
     *
     * @param url 请求地址, 如果没有 https:// http://前缀会默认添加一个http://前缀, 所以使用https时必须添加前缀
     * @param body 请求body 参数
     * @param forms form表单形式的body 参数
     * @param params query 参数
     * @param headers header参数
     * @param cookies cookie 参数
     * @return HttpResponse
     * @author zhang yebai
     * @date 2021/7/9 12:09 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun post(
        url: String,
        body: String? = null,
        forms: Map<String, String>? = null,
        params: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        cookies: Map<String, String>? = null
    ): HttpResponse {
        val path = url(url, params)
        val request = postRequest(path, headers, cookies, body, forms)
        return execute(request)
    }

    /**
     * javadoc postAsync
     * @apiNote 异步 POST 请求
     *
     * @param url 请求地址, 如果没有 https:// http://前缀会默认添加一个http://前缀, 所以使用https时必须添加前缀
     * @param body 请求body 参数
     * @param forms form表单形式的body 参数
     * @param params query 参数
     * @param headers header参数
     * @param cookies cookie 参数
     * @return HttpResponse
     * @author zhang yebai
     * @date 2021/7/9 12:09 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun postAsync(
        url: String,
        body: String? = null,
        forms: Map<String, String>? = null,
        params: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        cookies: Map<String, String>? = null
    ): Future<HttpResponse> {
        val path = url(url, params)
        val request = postRequest(path, headers, cookies, body, forms)
        val callable = Callable<HttpResponse> { execute(request) }
        return Executors.newSingleThreadExecutor().submit(callable)
    }


    /**
     * javadoc easyPut
     * @apiNote PUT 请求
     *
     * @param url 请求地址, 如果没有 https:// http://前缀会默认添加一个http://前缀, 所以使用https时必须添加前缀
     * @param body 请求body 参数
     * @param params query 参数
     * @param headers header参数
     * @param cookies cookie 参数
     * @return byte[]
     * @author zhang yebai
     * @date 2021/7/9 12:09 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun easyPut(
        url: String,
        body: String? = null,
        params: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        cookies: Map<String, String>? = null
    ): ByteArray {
        val path = url(url, params)
        val request = request(path, headers, cookies, HttpMethod.PUT, body)
        return easyExecute(request)
    }

    /**
     * javadoc put
     * @apiNote PUT 请求
     *
     * @param url 请求地址, 如果没有 https:// http://前缀会默认添加一个http://前缀, 所以使用https时必须添加前缀
     * @param body 请求body 参数
     * @param params query 参数
     * @param headers header参数
     * @param cookies cookie 参数
     * @return HttpResponse
     * @author zhang yebai
     * @date 2021/7/9 12:09 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun put(
        url: String,
        body: String? = null,
        params: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        cookies: Map<String, String>? = null
    ): HttpResponse {
        val path = url(url, params)
        val request = request(path, headers, cookies, HttpMethod.PUT, body)
        return execute(request)
    }

    /**
     * javadoc put
     * @apiNote PUT 请求
     *
     * @param url 请求地址, 如果没有 https:// http://前缀会默认添加一个http://前缀, 所以使用https时必须添加前缀
     * @param body 请求body 参数
     * @param params query 参数
     * @param headers header参数
     * @param cookies cookie 参数
     * @return HttpResponse
     * @author zhang yebai
     * @date 2021/7/9 12:09 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun putAsync(
        url: String,
        body: String? = null,
        params: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        cookies: Map<String, String>? = null
    ): Future<HttpResponse> {
        val path = url(url, params)
        val request = request(path, headers, cookies, HttpMethod.PUT, body)
        val callable: Callable<HttpResponse> = Callable<HttpResponse> { execute(request) }
        return Executors.newSingleThreadExecutor().submit(callable)
    }

    /**
     * javadoc easyPatch
     * @apiNote PATCH 请求
     *
     * @param url 请求地址, 如果没有 https:// http://前缀会默认添加一个http://前缀, 所以使用https时必须添加前缀
     * @param body 请求body 参数
     * @param params query 参数
     * @param headers header参数
     * @param cookies cookie 参数
     * @return byte[]
     * @author zhang yebai
     * @date 2021/7/9 12:09 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun easyPatch(
        url: String,
        body: String? = null,
        params: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        cookies: Map<String, String>? = null
    ): ByteArray {
        val path = url(url, params)
        val request = request(path, headers, cookies, HttpMethod.PATCH, body)
        return easyExecute(request)
    }

    /**
     * javadoc patch
     * @apiNote PATCH 请求
     *
     * @param url 请求地址, 如果没有 https:// http://前缀会默认添加一个http://前缀, 所以使用https时必须添加前缀
     * @param body 请求body 参数
     * @param params query 参数
     * @param headers header参数
     * @param cookies cookie 参数
     * @return HttpResponse
     * @author zhang yebai
     * @date 2021/7/9 12:09 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun patch(
        url: String,
        body: String? = null,
        params: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        cookies: Map<String, String>? = null
    ): HttpResponse {
        val path = url(url, params)
        val request = request(path, headers, cookies, HttpMethod.PATCH, body)
        return execute(request)
    }

    /**
     * javadoc patch
     * @apiNote 异步PATCH 请求
     *
     * @param url 请求地址, 如果没有 https:// http://前缀会默认添加一个http://前缀, 所以使用https时必须添加前缀
     * @param body 请求body 参数
     * @param params query 参数
     * @param headers header参数
     * @param cookies cookie 参数
     * @return HttpResponse
     * @author zhang yebai
     * @date 2021/7/9 12:09 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun patchAsync(
        url: String,
        body: String? = null,
        params: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        cookies: Map<String, String>? = null
    ): Future<HttpResponse> {
        val path = url(url, params)
        val request = request(path, headers, cookies, HttpMethod.PATCH, body)
        val callable = Callable<HttpResponse> { execute(request) }
        return Executors.newSingleThreadExecutor().submit(callable)
    }

    /**
     * javadoc easyDelete
     * @apiNote DELETE 请求
     *
     * @param url 请求地址, 如果没有 https:// http://前缀会默认添加一个http://前缀, 所以使用https时必须添加前缀
     * @param body 请求body 参数
     * @param params query 参数
     * @param headers header参数
     * @param cookies cookie 参数
     * @return byte[]
     * @author zhang yebai
     * @date 2021/7/9 12:09 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun easyDelete(
        url: String,
        body: String? = null,
        params: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        cookies: Map<String, String>? = null
    ): ByteArray {
        val path = url(url, params)
        val request = request(path, headers, cookies, HttpMethod.DELETE, body)
        return easyExecute(request)
    }

    /**
     * javadoc delete
     * @apiNote DELETE 请求
     *
     * @param url 请求地址, 如果没有 https:// http://前缀会默认添加一个http://前缀, 所以使用https时必须添加前缀
     * @param body 请求body 参数
     * @param params query 参数
     * @param headers header参数
     * @param cookies cookie 参数
     * @return byte[]
     * @author zhang yebai
     * @date 2021/7/9 12:09 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun delete(
        url: String,
        body: String? = null,
        params: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        cookies: Map<String, String>? = null
    ): HttpResponse {
        val path = url(url, params)
        val request = request(path, headers, cookies, HttpMethod.DELETE, body)
        return execute(request)
    }

    /**
     * javadoc deleteAsync
     * @apiNote 异步 DELETE 请求
     *
     * @param url 请求地址, 如果没有 https:// http://前缀会默认添加一个http://前缀, 所以使用https时必须添加前缀
     * @param body 请求body 参数
     * @param params query 参数
     * @param headers header参数
     * @param cookies cookie 参数
     * @return byte[]
     * @author zhang yebai
     * @date 2021/7/9 12:09 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun deleteAsync(
        url: String,
        body: String? = null,
        params: Map<String, String>? = null,
        headers: Map<String, String>? = null,
        cookies: Map<String, String>? = null
    ): Future<HttpResponse> {
        val path = url(url, params)
        val request = request(path, headers, cookies, HttpMethod.DELETE, body)
        val callable = Callable<HttpResponse> { execute(request) }
        return Executors.newSingleThreadExecutor().submit(callable)
    }


    private fun execute(request: Request): HttpResponse {
        OkHttpClient().newCall(request).execute().use {
            val headers = it.headers.map { h -> mutableMapOf(h) }.toList()
            val bytes = it.body?.bytes() ?: byteArrayOf()
            return HttpResponse(it.code, headers, bytes)
        }
    }


    private fun easyExecute(request: Request): ByteArray {
        return OkHttpClient().newCall(request).execute().use {
            it.body?.bytes() ?: byteArrayOf()
        }
    }

    private fun postRequest(
        url: String,
        headers: Map<String, String>?,
        cookies: Map<String, String>?,
        body: String? = null,
        forms: Map<String, String>? = null
    ): Request {
        val builder = builder(url, headers, cookies)
        if (forms != null) {
            val formBody = FormBody.Builder()
            forms.forEach { formBody.addEncoded(it.key, it.value) }
            builder.post(formBody.build())
        } else {
            builder.post((body ?: "").toRequestBody())
        }

        return builder.build()
    }

    private fun builder(url: String, headers: Map<String, String>?, cookies: Map<String, String>?): Request.Builder {
        val builder = Request.Builder().url(url)
        headers?.forEach { builder.addHeader(it.key, it.value) }
        cookies?.map { it.key + EQUALS_SEPARATOR + it.value }?.joinToString("; ") { it }
            ?.let { builder.addHeader("cookie", it) }
        return builder
    }

    private fun request(
        url: String,
        headers: Map<String, String>?,
        cookies: Map<String, String>?,
        method: HttpMethod,
        body: String? = null,
    ): Request {
        val builder = builder(url, headers, cookies)
        when (method) {
            HttpMethod.GET -> builder.get()
            //HttpMethod.POST -> {(body ?: "").toRequestBody().let { builder.post(it) }}
            HttpMethod.PUT -> (body ?: "").toRequestBody().let { builder.put(it) }
            HttpMethod.PATCH -> (body ?: "").toRequestBody().let { builder.patch(it) }
            HttpMethod.DELETE -> builder.delete((body ?: "").toRequestBody())
            else -> {
                // unreachable!
            }
        }
        return builder.build()
    }

    private fun url(url: String, params: Map<String, String>?): String {
        var completeUrl = url
        if (!completeUrl.startsWith("https://", true) && !completeUrl.startsWith("http://", true)) {
            completeUrl = "http://$completeUrl"
        }
        val ps = params?.map { it.key + EQUALS_SEPARATOR + it.value }?.joinToString(PARAM_SEPARATOR) { it }
        return ps?.let {
            if (completeUrl.endsWith(SEPARATOR, true)) completeUrl + it else completeUrl + SEPARATOR + it
        } ?: completeUrl
    }
}