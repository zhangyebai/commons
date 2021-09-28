package com.any.common.core.filter

import com.any.common.core.filter.strategy.FilterStrategy
import com.any.common.core.filter.strategy.IStrategy

@Suppress("unused")
class BlackWhiteFilter<T> private constructor(
    private val black: IStrategy<T>,
    private val white: IStrategy<T>
) : IFilter<T> {

    /**
     * javadoc addToBlack
     * @apiNote 向黑名单中添加元素
     *
     * @param t target
     * @return BlackWhiteFilter<T>
     * @author zhang yebai
     * @date 2021/7/19 3:26 PM
     **/
    fun addToBlack(t: T): BlackWhiteFilter<T> {
        black.add(t)
        return this
    }

    /**
     * javadoc addToBlack
     * @apiNote 向黑名单中添加元素
     *
     * @param list 元素列表
     * @return BlackWhiteFilter<T>
     * @author zhang yebai
     * @date 2021/7/19 3:27 PM
     **/
    fun addToBlack(list: List<T>): BlackWhiteFilter<T> {
        black.add(list)
        return this
    }

    /**
     * javadoc clearBlack
     * @apiNote 清除黑名单列表
     *
     * @return BlackWhiteFilter<T>
     * @author zhang yebai
     * @date 2021/7/19 3:27 PM
     **/
    fun clearBlack(): BlackWhiteFilter<T> {
        black.clear()
        return this
    }

    /**
     * javadoc addToWhite
     * @apiNote 向白名单列表中添加数据
     *
     * @param t target
     * @return BlackWhiteFilter<T>
     * @author zhang yebai
     * @date 2021/7/19 3:28 PM
     **/
    fun addToWhite(t: T): BlackWhiteFilter<T> {
        white.add(t)
        return this
    }

    /**
     * javadoc addToWhite
     * @apiNote 想白名单中添加元素
     *
     * @param list 元素列表
     * @return BlackWhiteFilter<T>
     * @author zhang yebai
     * @date 2021/7/19 3:28 PM
     **/
    fun addToWhite(list: List<T>): BlackWhiteFilter<T> {
        white.add(list)
        return this
    }

    /**
     * javadoc clearWhite
     * @apiNote 清除白名单列表
     *
     * @return BlackWhiteFilter<T>
     * @author zhang yebai
     * @date 2021/7/19 3:29 PM
     **/
    fun clearWhite(): BlackWhiteFilter<T> {
        white.clear()
        return this
    }

    /**
     * javadoc pass
     * @apiNote 检测目标元素是否能通过
     *
     * @param t target
     * @return Boolean
     * @author zhang yebai
     * @date 2021/7/19 3:25 PM
     **/
    override fun pass(t: T): Boolean {
        return if (white.include(t)) {
            true
        } else {
            !black.include(t)
        }
    }

    /**
     * javadoc blocked
     * @apiNote 筛检出给定列表中是否有阻塞的元素
     *
     * @param ts targets
     * @return List<T>
     * @author zhang yebai
     * @date 2021/7/19 3:25 PM
     **/
    override fun blocked(ts: List<T>): List<T> {
        if (ts.isEmpty()) {
            return mutableListOf()
        }
        val set = mutableListOf<T>()
        for (item in ts) {
            if (!pass(item)) {
                set.add(item)
            }
        }
        return set
    }

    companion object {

        /**
         * javadoc of
         * @apiNote 使用默认的过滤策略构建黑白过滤器
         *
         * @return BlackWhiteFilter<T>
         * @author zhang yebai
         * @date 2021/7/19 3:21 PM
         **/
        @JvmStatic
        fun <T> of(): BlackWhiteFilter<T> = BlackWhiteFilter(FilterStrategy(), FilterStrategy())

        /**
         * javadoc of
         * @apiNote 使用过滤策略构建黑白过滤器
         *
         * @param black 黑策略
         * @param white 白策略
         * @return BlackWhiteFilter<T>
         * @author zhang yebai
         * @date 2021/7/19 3:22 PM
         **/
        @JvmStatic
        fun <T> of(black: IStrategy<T>, white: IStrategy<T>): BlackWhiteFilter<T> = BlackWhiteFilter(black, white)
    }
}