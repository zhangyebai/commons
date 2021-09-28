package com.any.common.mybatis

import com.any.common.mybatis.domain.MybatisPagination
import com.github.pagehelper.ISelect
import com.github.pagehelper.PageHelper

@Suppress("unused")
object Mybatis {

    /**
     * javadoc startPage
     * @apiNote 对page helper作二次封装, 感觉没必要, 因此该方法不发布
     *
     * @param page 页码索引, 从1开始
     * @param size 页元素数量
     * @param select 查询语句
     * @return MybatisPagination<T>
     * @author zhang yebai
     * @date 2021/7/26 5:49 PM
     **/
    @JvmStatic
    fun <T> startPage(page: Int, size: Int, select: ISelect): MybatisPagination<T> {
        val p = PageHelper.startPage<T>(page, size, true).doSelectPage<T>(select)
        return MybatisPagination.from(p)
    }
}