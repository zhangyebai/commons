package com.any.common.core.bean

@Suppress(names = ["unused"])
object Beans {

    /**
     * javadoc bean2map
     * @apiNote 将指定的bean转换成map
     *
     * @param bean 需要转换的bean
     * @return Map<String, Any>
     * @author zhang yebai
     * @date 2021/6/17 8:58 PM
     **/
    @JvmStatic
    fun bean2map(bean: Any): Map<String, Any> =
        bean.javaClass.declaredFields.map { it.isAccessible = true; it }.associateBy({ it.name }, { it.get(bean) })

    /**
     * javadoc map2bean
     * @apiNote 将指定的map 转换成 T 实例
     *          有任何数据不匹配均准换失败
     *
     * @param map
     * @param clazz T class
     * @return T
     * @author zhang yebai
     * @date 2021/6/18 4:16 PM
     **/
    @JvmStatic
    fun <T> map2bean(map: Map<String, Any>, clazz: Class<T>): T {
        val t = clazz.getDeclaredConstructor().let { it.isAccessible = true; it.newInstance() }
        clazz.declaredFields.forEach { it.isAccessible = true; it.set(t, map[it.name]) }
        return t
    }
}