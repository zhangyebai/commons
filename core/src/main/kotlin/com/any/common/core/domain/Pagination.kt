package com.any.common.core.domain


/**
 * javadoc Pagination
 * <p>
 *     增加public default value constructor主要是为了接口之间的序列化
 * <p>
 * @author zhang yebai
 * @date 2021/7/15 11:47 AM
 * @version 1.0.0
 **/
@Suppress("unused")
open class Pagination<T> constructor(
    open var page: Int? = 1,
    open var size: Int? = 0,
    open var pages: Long? = 0,
    open var total: Long? = 0,
    open var list: List<T>? = mutableListOf()
) {

    /**
     * javadoc to
     * @apiNote 现有分页转换成新分页
     *
     * @param converter 分页中的数据转换器
     * @return Pagination<R>
     * @author zhang yebai
     * @date 2021/7/16 2:53 PM
     **/
    open fun <R> to(converter: java.util.function.Function<T, R>): Pagination<R> {
        val data = list?.mapNotNull { converter.apply(it) }?.toMutableList() ?: mutableListOf()
        return Pagination(page, size, pages, total, data)
    }

    companion object {

        /**
         * javadoc from
         * @apiNote 分页传递转换
         *
         * @param pagination 源分页
         * @param converter 分页数据元素转换器: T -> R
         * @return Pagination<R>
         * @author zhang yebai
         * @date 2021/7/16 2:05 PM
         **/
        @JvmStatic
        fun <T, R> from(pagination: Pagination<T>, converter: java.util.function.Function<T, R>): Pagination<R> {
            val list = pagination.list?.mapNotNull { converter.apply(it) }?.toMutableList() ?: mutableListOf()
            return Pagination(pagination.page, pagination.size, pagination.pages, pagination.total, list)
        }

        /**
         * javadoc from
         * @apiNote 分页传递转换
         *
         * @param pagination 源分页
         * @param list 现数据
         * @return Pagination<T>
         * @author zhang yebai
         * @date 2021/7/16 10:52 AM
         **/
        @JvmStatic
        fun <T> from(pagination: Pagination<*>, list: List<T>): Pagination<T> =
            Pagination(pagination.page, pagination.size, pagination.pages, pagination.total, list)

        /**
         * javadoc of
         * @apiNote 全参数构建分页
         *
         * @param page 页索引
         * @param size 页大小
         * @param pages 总页数
         * @param total 总条数
         * @param list 数据列
         * @return Pagination<T>
         * @author zhang yebai
         * @date 2021/7/15 12:09 PM
         **/
        @JvmStatic
        fun <T> of(page: Int, size: Int, pages: Long, total: Long, list: List<T>): Pagination<T> =
            Pagination(page, size, pages, total, list)

        /**
         * javadoc of
         * @apiNote 构建分页, 计算总页数
         *
         * @param page 页索引
         * @param size 页大小
         * @param total 总条数
         * @param list 数据列
         * @return Pagination<T>
         * @author zhang yebai
         * @date 2021/7/15 12:09 PM
         **/
        @JvmStatic
        fun <T> of(page: Int, size: Int, total: Long, list: List<T>): Pagination<T> {
            var pages = total / size
            val remainder = total % size
            if (remainder > 0) {
                pages++
            }
            return of(page, size, pages, total, list)
        }

        /**
         * javadoc empty
         * @apiNote 构建空分页, page = 1, size = 0, pages = 0, total = 0, list = []
         *
         * @return Pagination<T>
         * @author zhang yebai
         * @date 2021/7/15 12:10 PM
         **/
        @JvmStatic
        fun <T> empty(): Pagination<T> = of(1, 0, 0, 0, mutableListOf())

        /**
         * javadoc page
         * @apiNote 校验分页参数page,
         *          不合法时返回默认的值 1
         *          合法时返回 page
         *
         * @param page 分页参数page
         * @return Int
         * @author zhang yebai
         * @date 2021/7/15 12:23 PM
         **/
        @JvmStatic
        fun page(page: Int?): Int {
            return when {
                page == null -> {
                    1
                }
                page <= 0 -> {
                    1
                }
                else -> {
                    page
                }
            }
        }

        /**
         * javadoc size
         * @apiNote 校验分页参数size
         *          不合法时返回默认的参数 10
         *          合法时返回size
         *
         * @param size 分页参数size
         * @return Int
         * @author zhang yebai
         * @date 2021/7/15 12:24 PM
         **/
        @JvmStatic
        fun size(size: Int?): Int {
            return when {
                size == null -> {
                    10
                }
                size <= 0 -> {
                    10
                }
                else -> {
                    size
                }
            }
        }
    }

    override fun toString(): String {
        return "Pagination(page=$page, size=$size, pages=$pages, total=$total, list=$list)"
    }
}