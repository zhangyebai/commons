package com.any.common.core.filter.strategy

import java.util.concurrent.ConcurrentHashMap

@Suppress("unused")
class FilterStrategy<T> : IStrategy<T> {

    private val repository = ConcurrentHashMap<T, Byte>()

    private val v: Byte = 1


    override fun add(t: T, vararg ts: T): FilterStrategy<T> {
        repository.putIfAbsent(t, v)
        for (item in ts) {
            repository.putIfAbsent(item, v)
        }
        return this
    }

    override fun add(list: List<T>): FilterStrategy<T> {
        list.forEach { repository.putIfAbsent(it, v) }
        return this
    }

    override fun clear(): List<T> {
        val list = repository.keys().toList()
        repository.clear()
        return list
    }

    override fun include(target: T): Boolean = repository[target]?.let { it == v } ?: false

    override fun include(targets: List<T>): List<T> {
        return if (targets.isEmpty()) {
            mutableListOf()
        } else {
            val ts = mutableListOf<T>()
            for (item in targets) {
                if (include(item)) {
                    ts.add(item)
                }
            }
            ts
        }
    }
}