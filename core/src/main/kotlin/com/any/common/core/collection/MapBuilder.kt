package com.any.common.core.collection

import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Suppress(names = ["unused"])
class MapBuilder<K, V> private constructor(private val map: MutableMap<K, V>) {

    fun put(key: K, value: V): MapBuilder<K, V> {
        map[key] = value
        return this
    }

    fun put(map: Map<K, V>): MapBuilder<K, V> {
        this.map.putAll(map)
        return this
    }

    fun build(): Map<K, V> {
        return map
    }

    companion object {
        @JvmStatic
        fun <K, V> hashMapBuilder(): MapBuilder<K, V> = MapBuilder(HashMap<K, V>())

        @JvmStatic
        fun <K, V> hashMapBuilder(capacity: Int): MapBuilder<K, V> = MapBuilder(HashMap<K, V>(capacity))

        @JvmStatic
        fun <K, V> concurrentHashMapBuilder(): MapBuilder<K, V> = MapBuilder(ConcurrentHashMap<K, V>())

        @JvmStatic
        fun <K, V> concurrentHashMapBuilder(capacity: Int): MapBuilder<K, V> =
            MapBuilder(ConcurrentHashMap<K, V>(capacity))

        @JvmStatic
        fun <K, V> linkedHashMapBuilder(): MapBuilder<K, V> = MapBuilder(LinkedHashMap<K, V>())

        @JvmStatic
        fun <K, V> linkedHashMapBuilder(capacity: Int): MapBuilder<K, V> = MapBuilder(LinkedHashMap<K, V>(capacity))

        @JvmStatic
        fun <K, V> hashTableBuilder(): MapBuilder<K, V> = MapBuilder(Hashtable())

        @JvmStatic
        fun <K, V> hashTableBuilder(capacity: Int): MapBuilder<K, V> = MapBuilder(Hashtable(capacity))
    }
}