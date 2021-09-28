package com.any.common.core.function


import java.util.function.Supplier

/**
 * javadoc ActionProxy
 * <p>
 *     串行代理执行
 *     通常将多个sql事务串在同一个事务中, 串行执行共享同一个事务
 * <p>
 * @author zhang yebai
 * @date 2021/6/17 9:45 AM
 * @version 1.0.0
 **/
@Suppress(names = ["unused"])
open class ActionProxy {

    /**
     * javadoc proxy
     * @apiNote 代理单个action
     *
     * @param action action function
     * @author zhang yebai
     * @date 2021/7/8 2:38 PM
     **/
    open fun proxy(action: ActionFunction) = action.action()

    /**
     * javadoc proxy
     * @apiNote 代理至少一个action
     *
     * @param action action function
     * @param actions action functions
     * @author zhang yebai
     * @date 2021/7/8 2:38 PM
     **/
    open fun proxy(action: ActionFunction, vararg actions: ActionFunction) {
        action.action()
        actions.iterator().forEach { it.action() }
    }

    /**
     * javadoc proxy
     * @apiNote 代理多个action
     *
     * @param actions action function list
     * @author zhang yebai
     * @date 2021/7/8 2:39 PM
     **/
    open fun proxy(actions: List<ActionFunction>) = actions.forEach { it.action() }

    /**
     * javadoc bridge
     * @apiNote 桥接有返回结果的单个action
     *
     * @param s action supplier
     * @return Any?
     * @author zhang yebai
     * @date 2021/7/8 2:40 PM
     **/
    open fun bridge(s: Supplier<Any>): Any? = s.get()

    /**
     * javadoc bridge
     * @apiNote 桥接有返回结果的多个action
     *
     * @param s action supplier
     * @param ss action suppliers
     * @return List<Any?>
     * @author zhang yebai
     * @date 2021/7/8 2:40 PM
     **/
    open fun bridge(s: Supplier<Any>, vararg ss: Supplier<Any>): List<Any?> {
        val l: MutableList<Any?> = mutableListOf()
        l.add(s.get())
        ss.iterator().forEach { l.add(it.get()) }
        return l
    }

    /**
     * javadoc bridge
     * @apiNote 桥接有返回结果的多个action
     *
     * @param ss action suppliers
     * @return List<Any?>
     * @author zhang yebai
     * @date 2021/7/8 2:40 PM
     **/
    open fun bridge(ss: List<Supplier<Any>>): List<Any?> {
        return ss.map { it.get() }.toList()
    }
}