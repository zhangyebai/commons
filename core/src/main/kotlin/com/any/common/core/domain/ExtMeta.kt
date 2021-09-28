package com.any.common.core.domain

/**
 * javadoc ExtMeta
 * <p>
 *     不能再扩了, 任何超过 **三个** 属性的对象, 务必序列化成专用 class
 * <p>
 * @author zhang yebai
 * @date 2021/6/29 3:08 PM
 * @version 1.0.0
 **/
@Suppress(names = ["unused"])
class ExtMeta<K, V> constructor(override var key: K? = null, override var value: V? = null, var extra: Any? = null) :
    Meta<K, V>(key, value) {

    override fun toString(): String {
        return "ExtMeta(key=$key, value=$value, extra=$extra)"
    }

    companion object {

        /**
         * javadoc of
         * @apiNote 构建 ExtMeta
         *
         * @param k key
         * @param v value
         * @return ExtMeta
         * @author zhang yebai
         * @date 2021/6/29 3:07 PM
         **/
        @JvmStatic
        @JvmOverloads
        fun <K, V> of(k: K, v: V, ext: Any? = null): ExtMeta<K, V> = ExtMeta(k, v, ext)
    }
}