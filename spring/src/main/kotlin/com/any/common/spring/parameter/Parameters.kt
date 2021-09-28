package com.any.common.spring.parameter

import javax.servlet.http.HttpServletRequest

@Suppress(names = ["unused"])
object Parameters {

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
        request.parameterNames.iterator().forEachRemaining { map[it] = request.getParameter(it) }
        return map
    }
}