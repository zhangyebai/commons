package com.any.common.core.event

import java.util.concurrent.Executor

/**
 * javadoc AsyncEventContext
 * <p>
 *     异步执行监听器事件处理
 * <p>
 * @author zhang yebai
 * @date 2021/7/20 11:52 AM
 * @version 1.0.0
 **/
@Suppress("unused")
class AsyncEventContext<T> private constructor(
    private val executor: Executor
) : EventContext<T>() {

    override fun publish(t: T): AsyncEventContext<T> {
        super.listeners.forEach { executor.execute { it.handle(t) } }
        return this
    }


    companion object {

        /**
         * javadoc of
         * @apiNote 构建异步事件发布上下文
         *
         * @param executor 线程池
         * @return AsyncEventContext<T>
         * @author zhang yebai
         * @date 2021/7/20 12:02 PM
         **/
        @JvmStatic
        fun <T> of(executor: Executor): AsyncEventContext<T> = AsyncEventContext(executor)
    }
}