package com.any.common.mybatis.domain

import com.any.common.core.domain.Pagination
import com.github.pagehelper.Page
import com.github.pagehelper.PageInfo
import java.util.function.Function

@Suppress("unused")
class MybatisPagination<T> constructor(
    override var page: Int? = 1,
    override var size: Int? = 0,
    override var pages: Long? = 0,
    override var total: Long? = 0,
    override var list: List<T>? = mutableListOf()
) : Pagination<T>(
    page, size, pages, total, list
) {

    override fun toString(): String {
        return "MybatisPagination(page=$page, size=$size, pages=$pages, total=$total, list=$list)"
    }

    /**
     * javadoc to
     * @apiNote 现有分页转换成新分页
     *
     * @param converter 分页中的数据转换器
     * @return Pagination<R>
     * @author zhang yebai
     * @date 2021/7/16 2:53 PM
     **/
    override fun <R> to(converter: Function<T, R>): MybatisPagination<R> {
        val data = list?.mapNotNull { converter.apply(it) }?.toMutableList() ?: mutableListOf()
        return MybatisPagination(page, size, pages, total, data)
    }

    companion object {

        /**
         * javadoc from
         * @apiNote 从 mybatis page 转换
         *
         * @param page mybatis page
         * @return MybatisPagination<T>
         * @author zhang yebai
         * @date 2021/7/21 7:30 PM
         **/
        @JvmStatic
        fun <T> from(page: Page<T>): MybatisPagination<T> = MybatisPagination(
            page.pageNum,
            page.pageSize,
            page.pages.toLong(),
            page.total,
            page.result
        )

        /**
         * javadoc from
         * @apiNote 从 mybatis page 经过 converter 转换
         *
         * @param page mybatis page
         * @param converter 元素转换器
         * @return MybatisPagination
         * @author zhang yebai
         * @date 2021/7/21 7:31 PM
         **/
        @JvmStatic
        fun <T, R> from(page: Page<T>, converter: Function<T, R>): MybatisPagination<R> {
            val list = page.result.map { converter.apply(it) }.toMutableList()
            return MybatisPagination(
                page.pageNum,
                page.pageSize,
                page.pages.toLong(),
                page.total,
                list
            )
        }

        /**
         * javadoc from
         * @apiNote 从 mybatis page 转换
         *
         * @param pageInfo mybatis 分页信息
         * @return MybatisPagination<T>
         * @author zhang yebai
         * @date 2021/7/21 7:32 PM
         **/
        @JvmStatic
        fun <T> from(pageInfo: PageInfo<T>): MybatisPagination<T> = MybatisPagination(
            pageInfo.pageNum,
            pageInfo.pageSize,
            pageInfo.pages.toLong(),
            pageInfo.total,
            pageInfo.list
        )

        /**
         * javadoc from
         * @apiNote 从 mybatis page 经过 converter 转换
         *
         * @param pageInfo mybatis 分页信息
         * @param converter 元素转换器
         * @return MybatisPagination<R>
         * @author zhang yebai
         * @date 2021/7/21 7:34 PM
         **/
        @JvmStatic
        fun <T, R> from(pageInfo: PageInfo<T>, converter: Function<T, R>): MybatisPagination<R> {
            val list = pageInfo.list.map { converter.apply(it) }.toMutableList()
            return MybatisPagination(
                pageInfo.pageNum,
                pageInfo.pageSize,
                pageInfo.pages.toLong(),
                pageInfo.total,
                list
            )
        }
    }
}