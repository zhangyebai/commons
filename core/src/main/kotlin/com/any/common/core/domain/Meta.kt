package com.any.common.core.domain

/**
 * javadoc Meta
 * <p>
 *     前后端交互 通用 key-value 对 class
 * <p>
 * @author zhang yebai
 * @date 2021/6/29 3:16 PM
 * @version 1.0.0
 **/
@Suppress(names = ["unused"])
open class Meta<K, V> constructor(
    open var key: K? = null,
    open var value: V? = null
) {


    override fun toString(): String {
        return "Meta(key=$key, value=$value)"
    }

    companion object {

        /**
         * javadoc of
         * @apiNote 构建meta
         *
         * @param k Key
         * @param v Value
         * @return Meta
         * @author zhang yebai
         * @date 2021/6/29 2:53 PM
         **/
        @JvmStatic
        fun <K, V> of(k: K?, v: V?): Meta<K, V> = Meta(k, v)
    }
}