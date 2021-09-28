package com.any.common.core.filter.strategy

interface IStrategy<T> {

    /**
     * javadoc add
     * @apiNote 向池中添加对象
     *
     * @param t target
     * @param ts target list
     * @return IStrategy<T>
     * @author zhang yebai
     * @date 2021/7/19 3:37 PM
     **/
    fun add(t: T, vararg ts: T): IStrategy<T>

    /**
     * javadoc add
     * @apiNote 向池中添加对象
     *
     * @param list target list
     * @return IStrategy<T>
     * @author zhang yebai
     * @date 2021/7/19 3:38 PM
     **/
    fun add(list: List<T>): IStrategy<T>

    /**
     * javadoc clear
     * @apiNote 清除对象池
     *
     * @return List<T> 已清除的对象
     * @author zhang yebai
     * @date 2021/7/19 3:38 PM
     **/
    fun clear(): List<T>

    /**
     * javadoc include
     * @apiNote 是否包含目标对象
     *
     * @param target 目标对象
     * @return Boolean
     * @author zhang yebai
     * @date 2021/7/19 3:39 PM
     **/
    fun include(target: T): Boolean

    /**
     * javadoc include
     * @apiNote 是否包含列表
     *          返回包含的元素列表
     *
     * @param targets target list
     * @return List<T>
     * @author zhang yebai
     * @date 2021/7/19 3:39 PM
     **/
    fun include(targets: List<T>): List<T>
}