package com.any.common.core.event

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.locks.ReentrantLock

/**
 * javadoc EventContext
 * <p>
 *     同步执行事件监听处理
 * <p>
 * @author zhang yebai
 * @date 2021/7/20 11:52 AM
 * @version 1.0.0
 **/
@Suppress("unused")
open class EventContext<T> /*private constructor()*/ : IContext<T> {

    protected val listeners: ArrayList<IEventListener<T>> = ArrayList()

    private val lock = ReentrantLock()

    private val log: Logger = LoggerFactory.getLogger(EventContext::class.java)


    override fun publish(t: T): EventContext<T> {
        listeners.forEach { it.handle(t) }
        return this
    }

    override fun removeListener(listener: IEventListener<T>): EventContext<T> {
        lock.lock()
        try {
            listeners.remove(listener)
        } finally {
            lock.unlock()
        }
        return this
    }

    override fun appendListener(listener: IEventListener<T>): EventContext<T> {
        lock.lock()
        try {
            listeners.add(listener)
        } finally {
            lock.unlock()
        }
        return this
    }
}