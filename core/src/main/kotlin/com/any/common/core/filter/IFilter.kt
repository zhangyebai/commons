package com.any.common.core.filter

interface IFilter<T> {

    /**
     * javadoc pass
     * @apiNote 检测元素是否能通过
     *
     * @param t target
     * @return Boolean
     * @author zhang yebai
     * @date 2021/7/19 3:36 PM
     **/
    fun pass(t: T): Boolean

    /**
     * javadoc blocked
     * @apiNote 找出不通过的元素
     *
     * @param ts target list
     * @return List<T>
     * @author zhang yebai
     * @date 2021/7/19 3:37 PM
     **/
    fun blocked(ts: List<T>): List<T>
}