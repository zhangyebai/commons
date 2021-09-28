package com.any.common.core.domain

/**
 * javadoc NotNullTuple
 * <p>
 *     ...
 * <p>
 * @author zhang yebai
 * @date 2021/7/14 6:52 PM
 * @version 1.0.0
 **/
@Suppress(names = ["unused"])
class NotNullTuple<K, V> private constructor(var k: K, var v: V) {


    companion object {
        @JvmStatic
        fun <K, V> of(k: K, v: V): NotNullTuple<K, V> = NotNullTuple(k, v)
    }

    override fun toString(): String {
        return "NotNullTuple(k=$k, v=$v)"
    }
}