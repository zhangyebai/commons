package com.any.common.spring.header

import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import javax.servlet.http.HttpServletRequest

@Suppress(names = ["unused"])
object Headers {


    /**
     * javadoc find
     * @apiNote 从 header 读取 key 为 name 的值
     *          从thread-local中获取HttpServletRequest
     *
     * @param name 键名
     * @return String
     * @author zhang yebai
     * @date 2021/7/5 3:19 PM
     **/
    @JvmStatic
    fun find(name: String): String? {
        val attributes: ServletRequestAttributes =
            RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
        return find(name, attributes.request)
    }

    /**
     * javadoc find
     * @apiNote 从 header 读取 key 为 name 的值
     *
     * @param name 键名
     * @param request http request
     * @return String
     * @author zhang yebai
     * @date 2021/7/5 3:19 PM
     **/
    @JvmStatic
    fun find(name: String, request: HttpServletRequest): String? {
        return request.getHeader(name)
    }

    /**
     * javadoc all
     * @apiNote 获取http请求中的所有header参数
     *
     * @param request http 请求
     * @return MutableMap
     * @author zhang yebai
     * @date 2021/9/22 3:26 PM
     **/
    @JvmStatic
    fun all(request: HttpServletRequest): MutableMap<String, Any?> {
        val map: MutableMap<String, Any?> = mutableMapOf()
        request.headerNames?.let {
            it.iterator().forEachRemaining { h -> map[h] = request.getHeader(h) }
        }
        return map
    }
}