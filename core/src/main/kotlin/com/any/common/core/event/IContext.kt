package com.any.common.core.event

/**
 * javadoc IContext
 * <p>
 *     事件上下文
 * <p>
 * @author zhang yebai
 * @date 2021/7/20 11:47 AM
 * @version 1.0.0
 **/
@Suppress("unused")
interface IContext<T> {

    /**
     * javadoc publish
     * @apiNote 发布事件
     *
     * @param t event target
     * @return IContext<T>
     * @author zhang yebai
     * @date 2021/7/20 11:47 AM
     **/
    fun publish(t: T): IContext<T>

    /**
     * javadoc removeListener
     * @apiNote 移除事件监听器
     *
     * @param listener 事件监听器
     * @return IContext<T>
     * @author zhang yebai
     * @date 2021/7/20 11:47 AM
     **/
    fun removeListener(listener: IEventListener<T>): IContext<T>

    /**
     * javadoc appendListener
     * @apiNote 添加事件监听器
     *
     * @param listener 事件监听器
     * @return IContext<T>
     * @author zhang yebai
     * @date 2021/7/20 11:47 AM
     **/
    fun appendListener(listener: IEventListener<T>): IContext<T>
}