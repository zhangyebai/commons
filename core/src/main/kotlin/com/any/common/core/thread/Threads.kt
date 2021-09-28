package com.any.common.core.thread

import com.any.common.core.function.ActionFunction
import java.util.concurrent.Callable
import java.util.function.Supplier

@Suppress("unused")
object Threads {

    /**
     * javadoc thread
     * @apiNote 构建线程对象
     *
     * @param action 需要执行的动作
     * @return Thread 线程对象
     * @author zhang yebai
     * @date 2021/7/13 8:44 PM
     **/
    @JvmStatic
    fun thread(action: ActionFunction): Thread = Thread { action.action() }

    /**
     * javadoc runnable
     * @apiNote 构建runnable对象
     *
     * @param action 需要执行的动作
     * @return Runnable
     * @author zhang yebai
     * @date 2021/7/13 8:45 PM
     **/
    @JvmStatic
    fun runnable(action: ActionFunction): Runnable = Runnable { action.action() }

    /**
     * javadoc callable
     * @apiNote 构建callable对象
     *
     * @param action 带返回值的对象执行逻辑
     * @return Callable<T>
     * @author zhang yebai
     * @date 2021/7/13 8:45 PM
     **/
    @JvmStatic
    fun <T> callable(action: Supplier<T>): Callable<T> = Callable { action.get() }
}