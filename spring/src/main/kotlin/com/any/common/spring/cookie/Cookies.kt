package com.any.common.spring.cookie

import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*
import javax.servlet.http.HttpServletRequest

@Suppress(names = ["unused"])
object Cookies {

    /**
     * javadoc list
     * @apiNote 从 cookie 读取 key 为 name 的值列表
     *          从thread-local中获取HttpServletRequest
     *
     * @param name 键值
     * @return List<String>
     * @author zhang yebai
     * @date 2021/7/5 3:18 PM
     **/
    @JvmStatic
    fun list(name: String): List<String> {
        val attributes: ServletRequestAttributes =
            RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
        return list(name, attributes.request)
    }

    /**
     * javadoc list
     * @apiNote 从 cookie 读取 key 为 name 的值列表
     *
     * @param name 键值
     * @param request http request
     * @return List<String>
     * @author zhang yebai
     * @date 2021/7/5 3:18 PM
     **/
    @JvmStatic
    fun list(name: String, request: HttpServletRequest): List<String> {
        val cookies = request.cookies ?: return mutableListOf()
        return cookies.filter { name.equals(it.name, true) && Objects.nonNull(it.value) }.map { it.value }.toList()
    }

    /**
     * javadoc findFirst
     * @apiNote 从 cookie 读取 key 为 name 的第一个
     *          从thread-local中获取HttpServletRequest
     *
     * @param name 键名
     * @return String
     * @author zhang yebai
     * @date 2021/7/5 3:19 PM
     **/
    @JvmStatic
    fun findFirst(name: String): String? {
        val values = list(name)
        return if (values.isNotEmpty()) values.first() else null
    }

    /**
     * javadoc findFirst
     * @apiNote 从 cookie 读取 key 为 name 的第一个
     *
     * @param name 键名
     * @param request http request
     * @return String
     * @author zhang yebai
     * @date 2021/7/5 3:19 PM
     **/
    @JvmStatic
    fun findFirst(name: String, request: HttpServletRequest): String? {
        val values = list(name, request)
        return if (values.isNotEmpty()) values.first() else null
    }

    /**
     * javadoc findLast
     * @apiNote 从 cookie 读取 key 为 name 的第一个
     *          从thread-local中获取HttpServletRequest
     *
     * @param name 键名
     * @return String
     * @author zhang yebai
     * @date 2021/7/5 3:19 PM
     **/
    @JvmStatic
    fun findLast(name: String): String? {
        val values = list(name)
        return if (values.isNotEmpty()) values.last() else null
    }

    /**
     * javadoc findLast
     * @apiNote 从 cookie 读取 key 为 name 的第一个
     *
     * @param name 键名
     * @param request http request
     * @return String
     * @author zhang yebai
     * @date 2021/7/5 3:19 PM
     **/
    @JvmStatic
    fun findLast(name: String, request: HttpServletRequest): String? {
        val values = list(name, request)
        return if (values.isNotEmpty()) values.last() else null
    }

    /**
     * javadoc all
     * @apiNote 获取http请求中所有的cookie参数
     *
     * @param request http 请求
     * @return MutableMap
     * @author zhang yebai
     * @date 2021/9/22 3:30 PM
     **/
    @JvmStatic
    fun all(request: HttpServletRequest): MutableMap<String, Any?> {
        val map: MutableMap<String, Any?> = mutableMapOf()
        request.cookies?.let {
            it.iterator().forEachRemaining { c -> map[c.name] = c.value }
        }
        return map
    }
}