package com.any.common.jpa.domain

import com.any.common.core.domain.Pagination
import org.springframework.data.domain.Page
import java.util.function.Function

@Suppress("unused")
class JpaPagination<T> constructor(
    override var page: Int? = 1,
    override var size: Int? = 0,
    override var pages: Long? = 0,
    override var total: Long? = 0,
    override var list: List<T>? = mutableListOf()
) : Pagination<T>(
    page, size, pages, total, list
) {

    override fun toString(): String {
        return "JpaPagination(page=$page, size=$size, pages=$pages, total=$total, list=$list)"
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
    override fun <R> to(converter: Function<T, R>): JpaPagination<R> {
        val data = list?.mapNotNull { converter.apply(it) }?.toMutableList() ?: mutableListOf()
        return JpaPagination(page, size, pages, total, data)
    }

    companion object {

        /**
         * javadoc from
         * @apiNote 从jpa page 转换到JpaPagination
         *
         * @param page jpa page
         * @return JpaPagination<T>
         * @author zhang yebai
         * @date 2021/7/21 7:08 PM
         **/
        @JvmStatic
        fun <T> from(page: Page<T>): JpaPagination<T> = JpaPagination(
            page.pageable.pageNumber,
            page.pageable.pageSize,
            page.totalPages.toLong(),
            page.totalElements, page.toMutableList()
        )

        /**
         * javadoc from
         * @apiNote 从 jpa page 经过 converter 转换到 JpaPagination
         *
         * @param page jpa page
         * @param converter 元素转换器
         * @return JpaPagination
         * @author zhang yebai
         * @date 2021/7/21 7:09 PM
         **/
        @JvmStatic
        fun <T, R> from(page: Page<T>, converter: Function<T, R>): JpaPagination<R> {
            val list = page.map { converter.apply(it) }.toMutableList()
            return JpaPagination(
                page.pageable.pageNumber,
                page.pageable.pageSize,
                page.totalPages.toLong(),
                page.totalElements,
                list
            )
        }
    }
}