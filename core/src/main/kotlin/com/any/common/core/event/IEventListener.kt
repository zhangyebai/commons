package com.any.common.core.event

/**
 * javadoc IEventListener
 * <p>
 *     事件处理器
 * <p>
 * @author zhang yebai
 * @date 2021/7/20 11:48 AM
 * @version 1.0.0
 **/
@Suppress("unused")
interface IEventListener<T> {

    /**
     * javadoc handle
     * @apiNote 处理事件
     *          尽量不要抛出异常, 否则异常将传递至所有处理器链, 从而导致其他监听器得不到处理
     *          尽量不要修改 t 对象, 除非知道修改后造成的影响
     *
     * @param t 目标事件
     * @author zhang yebai
     * @date 2021/7/20 11:48 AM
     **/
    fun handle(t: T)
}