package com.any.common.core.event

/**
 * javadoc IPriorityContext
 * <p>
 *     携带优先级顺序的事件发布上下文
 * <p>
 * @author zhang yebai
 * @date 2021/7/20 11:50 AM
 * @version 1.0.0
 **/
@Suppress("unused")
interface IPriorityContext<T> {

    /**
     * javadoc publish
     * @apiNote 发布事件
     *
     * @param t 目标事件
     * @return IPriorityContext<T>
     * @author zhang yebai
     * @date 2021/7/20 11:51 AM
     **/
    fun publish(t: T): IPriorityContext<T>

    /**
     * javadoc removeListener
     * @apiNote 移除事件监听器
     *
     * @param listener 事件监听器
     * @return IContext<T>
     * @author zhang yebai
     * @date 2021/7/20 11:47 AM
     **/
    fun removeListener(listener: IEventListener<T>): IPriorityContext<T>

    /**
     * javadoc appendListener
     * @apiNote 添加事件监听器
     *          priority 值越低优先级越高
     *
     * @param listener 事件监听器
     * @param priority 事件监听器优先级
     * @return IContext<T>
     * @author zhang yebai
     * @date 2021/7/20 11:47 AM
     **/
    fun appendListener(listener: IEventListener<T>, priority: Int): IPriorityContext<T>
}