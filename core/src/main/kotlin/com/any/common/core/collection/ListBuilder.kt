package com.any.common.core.collection

import java.util.*

@Suppress(names = ["unused"])
class ListBuilder<E> private constructor(private val list: MutableList<E>) {

    fun add(element: E): ListBuilder<E> {
        list.add(element)
        return this
    }

    fun add(elements: Array<E>): ListBuilder<E> {
        list.addAll(elements)
        return this
    }

    fun add(elements: List<E>): ListBuilder<E> {
        list.addAll(elements)
        return this
    }

    fun build(): List<E> {
        return list
    }

    companion object {
        @JvmStatic
        fun <E> arrayListBuilder(): ListBuilder<E> = ListBuilder(ArrayList<E>())

        @JvmStatic
        fun <E> arrayListBuilder(capacity: Int): ListBuilder<E> = ListBuilder(ArrayList<E>(capacity))

        @JvmStatic
        fun <E> linkedListBuilder(): ListBuilder<E> = ListBuilder(LinkedList<E>())
    }
}