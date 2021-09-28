package com.any.common.core.event

import java.util.concurrent.locks.ReentrantLock
import kotlin.collections.ArrayList

/**
 * javadoc PriorityContext
 * <p>
 *     按监听器优先级顺序同步执行事件监听逻辑
 * <p>
 * @author zhang yebai
 * @date 2021/7/20 11:52 AM
 * @version 1.0.0
 **/
@Suppress("unused")
class PriorityContext<T> : IPriorityContext<T> {

    private val listeners: ArrayList<EventListener<T>> = ArrayList()

    private val lock = ReentrantLock()

    override fun publish(t: T): IPriorityContext<T> {
        listeners.forEach { it.listener.handle(t) }
        return this
    }

    override fun removeListener(listener: IEventListener<T>): IPriorityContext<T> {
        lock.lock()
        var l: EventListener<T>? = null
        try {
            for (item in listeners) {
                if (item.listener == listener) {
                    l = item
                    break
                }
            }
            l?.let {
                listeners.remove(it)
                //listeners.sortWith { o1, o2 -> o1.priority - o2.priority }
            }
        } finally {
            lock.unlock()
        }
        return this
    }

    override fun appendListener(listener: IEventListener<T>, priority: Int): IPriorityContext<T> {
        lock.lock()
        try {
            listeners.add(EventListener(listener, priority))
            listeners.sortWith { o1, o2 -> o1.priority - o2.priority }
        } finally {
            lock.unlock()
        }
        return this
    }
}

private data class EventListener<T> constructor(
    val listener: IEventListener<T>,
    val priority: Int
)