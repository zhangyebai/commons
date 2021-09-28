package com.any.common.core.enums

import com.any.common.core.domain.Tuple
import java.util.*

@Suppress(names = ["unused"])
object Enums {

    /**
     * javadoc find
     * @apiNote 查询枚举
     *
     * @param k key值
     * @param clazz 枚举对象class
     * @return Optional<T>
     * @author zhang yebai
     * @date 2021/7/5 4:24 PM
     **/
    @JvmStatic
    fun <T : IEnum<K, V>, K : Any, V : Any> find(k: K?, clazz: Class<T>): Optional<T> {
        k?.let {
            for (e in clazz.enumConstants) {
                if (Objects.equals(k, e.k())) {
                    return Optional.ofNullable(e)
                }
            }
        }
        return Optional.empty()
    }

    /**
     * javadoc listEnums
     * @apiNote 根据key值查
     *          key 为null时返回空list
     *
     * @param clazz 枚举对象class
     * @param k 枚举对象的k()
     * @return List<T>
     * @author zhang yebai
     * @date 2021/7/5 4:18 PM
     **/
    @JvmStatic
    fun <T : IEnum<K, V>, K : Any, V : Any> listEnums(clazz: Class<T>, k: K? = null): List<T> =
        k?.let { clazz.enumConstants.filter { Objects.equals(k, it.k()) }.toList() } ?: mutableListOf()

    /**
     * javadoc list
     * @apiNote 根据key值查
     *          key 为null时返回全队对象枚举list
     *
     * @param clazz 枚举对象class
     * @param k 枚举对象的k()
     * @return List<Tuple<K, V>>
     * @author zhang yebai
     * @date 2021/7/5 4:18 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun <T : IEnum<K, V>, K : Any, V : Any> list(clazz: Class<T>, k: K? = null): List<Tuple<K, V>> =
        clazz.enumConstants.filter { Objects.isNull(k) || Objects.equals(k, it.k()) }.map { Tuple.of(it.k(), it.v()) }
            .toList()
}