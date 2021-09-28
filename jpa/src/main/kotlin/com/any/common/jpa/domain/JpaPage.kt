package com.any.common.jpa.domain

class JpaPage private constructor(
    val page: Int,
    val size: Int,
    val orders: List<JpaOrder> = listOf()
) {


    companion object {

        /**
         * javadoc of
         * @apiNote 构建分页信息
         *
         * @param page 页索引, 从0开始, 与jpa保持一致
         * @param size 页大小
         * @param orders 排序信息
         * @return JpaPage 分页信息
         * @author zhang yebai
         * @date 2021/7/23 4:04 PM
         **/
        @JvmStatic
        fun of(page: Int, size: Int, vararg orders: JpaOrder) = JpaPage(page, size, orders.toList())

        /**
         * javadoc of
         * @apiNote 构建分页信息
         *
         * @param page 页索引, 从0开始, 与jpa保持一致
         * @param size 页大小
         * @param orders 排序信息
         * @return JpaPage 分页信息
         * @author zhang yebai
         * @date 2021/7/23 4:04 PM
         **/
        @JvmStatic
        fun of(page: Int, size: Int, orders: List<JpaOrder>) = JpaPage(page, size, orders)
    }

    override fun toString(): String {
        return "JpaPage(page=$page, size=$size, orders=$orders)"
    }
}