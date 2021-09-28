package com.any.common.core.enums

import com.any.common.core.domain.Tuple

/**
 * javadoc IEnum
 * <p>
 *     枚举接口标志
 * <p>
 * @author zhang yebai
 * @date 2021/7/5 4:26 PM
 * @version 1.0.0
 **/
interface IEnum<K, V> {

    /**
     * javadoc meta
     * @apiNote 枚举承载的元信息
     *
     * @return Tuple<K, V>
     * @author zhang yebai
     * @date 2021/7/5 4:27 PM
     **/
    fun meta(): Tuple<K, V>

    /**
     * javadoc k
     * @apiNote 枚举源信息中的k值
     *
     * @return null
     * @author zhang yebai
     * @date 2021/7/5 4:27 PM
     **/
    fun k(): K

    /**
     * javadoc 枚举源信息中的V值
     * @apiNote
     *
     * @return V
     * @author zhang yebai
     * @date 2021/7/5 4:27 PM
     **/
    fun v(): V? = null
}