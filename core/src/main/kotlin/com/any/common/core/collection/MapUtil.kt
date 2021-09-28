package com.any.common.core.collection

import java.util.*

@Suppress("unused")
object MapUtil {

    /**
     * javadoc get
     * @apiNote map get扩展
     *
     * @param map map
     * @param key key
     * @return value on key
     * @author zhang yebai
     * @date 2021/7/13 5:06 PM
     **/
    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    fun <T> get(map: Map<*, *>, key: Any?): T? {
        return key?.let { map[key]?.let { iv -> iv as T } }
    }

    /**
     * javadoc get
     * @apiNote map get扩展
     *
     * @param map map
     * @param key key
     * @return value on key
     * @author zhang yebai
     * @date 2021/7/13 5:06 PM
     **/
    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    fun <T> get(map: Map<*, *>, key: Any?, defaultValue: T): T {
        return key?.let { map[key]?.let { iv -> iv as T } } ?: defaultValue
    }

    /**
     * javadoc forceGet
     * @apiNote map get扩展
     *
     * @param map map
     * @param key key
     * @return value on key
     * @author zhang yebai
     * @date 2021/7/13 5:06 PM
     **/
    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    fun <T> forceGet(map: Map<*, *>, key: Any): T {
        return map[key] as T
    }

    /**
     * javadoc optGet
     * @apiNote map get扩展
     *
     * @param map map
     * @param key key
     * @return value on key
     * @author zhang yebai
     * @date 2021/7/13 5:06 PM
     **/
    @JvmStatic
    fun <T> optGet(map: Map<*, *>, key: Any?): Optional<T> = Optional.ofNullable(get(map, key))
}