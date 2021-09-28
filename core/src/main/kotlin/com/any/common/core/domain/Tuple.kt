package com.any.common.core.domain


/**
 * javadoc Tuple
 * <p>
 *     key-value pair
 *     is 开头会导致 jackson 序列化成字段
 * <p>
 * @author zhang yebai
 * @date 2021/6/17 4:08 PM
 * @version 1.0.0
 **/
@Suppress(names = ["unused"])
class Tuple<K, V> private constructor(var k: K? = null, var v: V? = null) {


    /**
     * javadoc beNull
     * @apiNote 判断k v是否全部为null
     *
     * @return Boolean
     * @author zhang yebai
     * @date 2021/6/17 3:57 PM
     **/
    fun beNull(): Boolean = this.k == null && this.v == null

    /**
     * javadoc anyNull
     * @apiNote 判断k v是否有任意一个为null
     *
     * @return Boolean
     * @author zhang yebai
     * @date 2021/6/17 4:09 PM
     **/
    fun anyNull(): Boolean = this.k == null || this.v == null

    /**
     * javadoc anyNotNull
     * @apiNote 判断k v是否有任意值不为null
     *
     * @return Boolean
     * @author zhang yebai
     * @date 2021/6/17 4:09 PM
     **/
    fun anyNotNull(): Boolean = this.k != null || this.v != null

    /**
     * javadoc beNotNull
     * @apiNote 判断k v是否全部不为null
     *
     * @return Boolean
     * @author zhang yebai
     * @date 2021/6/17 4:10 PM
     **/
    fun beNotNull(): Boolean = this.k != null && this.v != null


    override fun toString(): String {
        return "Tuple(k=$k, v=$v)"
    }


    companion object {

        @JvmStatic
        fun <K, V> empty(): Tuple<K, V> = Tuple()

        @JvmStatic
        fun <K, V> of(k: K?, v: V?): Tuple<K, V> = Tuple(k, v)

        /**
         * javadoc isEmpty
         * @apiNote 判断k v是否全部为null
         *
         * @param tuple 元组
         * @return Boolean
         * @author zhang yebai
         * @date 2021/6/17 3:57 PM
         **/
        @JvmStatic
        fun <K, V> isEmpty(tuple: Tuple<K, V>): Boolean = tuple.k == null && tuple.v == null

        /**
         * javadoc anyEmpty
         * @apiNote 判断k v是否有任意一个为null
         *
         * @param tuple 元组
         * @return Boolean
         * @author zhang yebai
         * @date 2021/6/17 4:09 PM
         **/
        @JvmStatic
        fun <K, V> anyEmpty(tuple: Tuple<K, V>): Boolean = tuple.k == null || tuple.v == null

        /**
         * javadoc anyNotEmpty
         * @apiNote 判断k v是否有任意值不为null
         *
         * @param tuple 元组
         * @return Boolean
         * @author zhang yebai
         * @date 2021/6/17 4:09 PM
         **/
        @JvmStatic
        fun <K, V> anyNotEmpty(tuple: Tuple<K, V>): Boolean = tuple.k != null || tuple.v != null

        /**
         * javadoc isNotNull
         * @apiNote 判断k v是否全部不为null
         *
         * @param tuple 元组
         * @return Boolean
         * @author zhang yebai
         * @date 2021/6/17 4:10 PM
         **/
        @JvmStatic
        fun <K, V> isNotEmpty(tuple: Tuple<K, V>): Boolean = tuple.k != null && tuple.v != null
    }
}