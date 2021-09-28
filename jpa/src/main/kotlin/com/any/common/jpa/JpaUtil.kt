package com.any.common.jpa

import com.any.common.core.reflection.Reflections
import com.any.common.jpa.domain.*
import com.any.common.jpa.domain.enums.JpaOrderModel
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import java.lang.reflect.Field
import javax.persistence.criteria.*

/**
 * javadoc JpaUtil
 * <p>
 *     实验性质, 谨慎使用
 *     未经测试, 谨慎使用
 *     自嗨性质, 谨慎使用
 * <p>
 * @author zhang yebai
 * @date 2021/7/22 11:42 AM
 * @version 1.0.0
 **/
@Suppress("unused")
object JpaUtil {

    /**
     * javadoc page
     * @apiNote 构建分页对象
     *
     * @param jpaPage 包装分页信息
     * @return Pageable jpa分页对象
     * @author zhang yebai
     * @date 2021/7/23 4:01 PM
     **/
    @JvmStatic
    fun page(jpaPage: JpaPage): Pageable =
        sort(jpaPage.orders)?.let { PageRequest.of(jpaPage.page, jpaPage.size, it) } ?: PageRequest.of(
            jpaPage.page,
            jpaPage.size
        )

    /**
     * javadoc sort
     * @apiNote 构建排序
     *
     * @param orders 排序规则
     * @return Sort
     * @author zhang yebai
     * @date 2021/7/23 3:38 PM
     **/
    @JvmStatic
    fun sort(orders: List<JpaOrder>): Sort? {
        val os = mutableListOf<Sort.Order>()
        for (o in orders) {
            os.add(Sort.Order(direction(o.mode), o.field))
        }
        return if (os.isEmpty()) null else Sort.by(os)
    }

    /**
     * javadoc sort
     * @apiNote 构建排序
     *
     * @param order 排序
     * @param orders 更多排序
     * @return Sort
     * @author zhang yebai
     * @date 2021/7/23 3:38 PM
     **/
    @JvmStatic
    fun sort(order: JpaOrder, vararg orders: JpaOrder): Sort {
        val os = mutableListOf<Sort.Order>()
        os.add(Sort.Order(direction(order.mode), order.field))
        for (o in orders) {
            os.add(Sort.Order(direction(o.mode), o.field))
        }
        return Sort.by(os)
    }

    private fun direction(mode: JpaOrderModel): Sort.Direction {
        return when (mode) {
            JpaOrderModel.ASC -> Sort.Direction.ASC
            JpaOrderModel.DESC -> Sort.Direction.DESC
        }
    }


    /**
     * javadoc dynamic
     * @apiNote Jpa Specification HQL 动态构建
     *
     * @param dynamicQuery 动态查询条件
     * @return Specification<E> E for Entity
     * @author zhang yebai
     * @date 2021/7/22 4:48 PM
     **/
    @JvmStatic
    fun <E> dynamic(dynamicQuery: JpaDynamicQuery): Specification<E> {
        val fields = Reflections.listClassFields(dynamicQuery::class.java)
        return Specification { root, query, criteriaBuilder ->
            val predicates = mutableListOf<Predicate>()
            for (field in fields) {
                predicate(field, dynamicQuery, root, query, criteriaBuilder)?.let { predicates.add(it) }
            }
            //criteriaBuilder.and(*predicates.toTypedArray())
            query.where(*predicates.toTypedArray()).restriction
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <E> predicate(
        field: Field,
        query: JpaDynamicQuery,
        root: Root<E>,
        q: CriteriaQuery<*>,
        criteriaBuilder: CriteriaBuilder
    ): Predicate? {
        field.isAccessible = true
        val value: Any? = field.get(query)
        val name: String = field.name
        if (value == null || value !is JpaCondition) {
            return null
        }

        val attribute = when (value) {
            is JpaEq<*> -> {
                name.substring(0, name.length - 2)
            }
            is JpaNeq<*> -> {
                name.substring(0, name.length - 3)
            }
            is JpaLeftLike -> {
                name.substring(0, name.length - 4)
            }
            is JpaRightLike -> {
                name.substring(0, name.length - 4)
            }
            is JpaLike -> {
                name.substring(0, name.length - 4)
            }
            is JpaGt<*> -> {
                name.substring(0, name.length - 2)
            }
            is JpaGe<*> -> {
                name.substring(0, name.length - 2)
            }
            is JpaLt<*> -> {
                name.substring(0, name.length - 2)
            }
            is JpaLe<*> -> {
                name.substring(0, name.length - 2)
            }
            is JpaIn<*> -> {
                name.substring(0, name.length - 2)
            }
            is JpaNotIn<*> -> {
                name.substring(0, name.length - 5)
            }
            is JpaBet<*> -> {
                name.substring(0, name.length - 3)
            }
            else -> null
        }
        return attribute?.let {
            value.setColumn(it)
            value.toPredicate(root, q, criteriaBuilder)
        }
    }
}