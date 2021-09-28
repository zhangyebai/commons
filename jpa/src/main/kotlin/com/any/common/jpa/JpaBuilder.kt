package com.any.common.jpa

import com.any.common.jpa.domain.*
import com.any.common.jpa.domain.enums.JpaDynamicOperator
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

/**
 * javadoc JpaBuilder
 * <p>
 * <p>
 * @author zhang yebai
 * @date 2021/7/28 9:59 AM
 * @version 1.0.0
 **/
@Suppress("unused")
class JpaBuilder<T> private constructor() {

    /**
     * 条件集合
     **/
    private val conditions = mutableListOf<JpaConditionWrapper>()

    /**
     * 当前是否在条件组中
     **/
    private var grouping = false

    /**
     * 条件组临时wrapper
     **/
    private var wrapper: JpaConditionWrapper? = null

    /**
     * 条件组合操作因子
     **/
    private var operator: JpaDynamicOperator = JpaDynamicOperator.AND


    /**
     * javadoc start
     * @apiNote 开始组合条件
     *
     * @param outOperator 组合条件与外部条件的组合因子
     * @param innerOperator 内部组合因子
     * @return JpaBuilder<T>
     * @author zhang yebai
     * @date 2021/7/28 4:48 PM
     **/
    fun start(outOperator: JpaDynamicOperator, innerOperator: JpaDynamicOperator): JpaBuilder<T> {
        this.grouping = true
        wrapper = JpaConditionWrapper(outOperator, innerOperator)
        return this
    }

    /**
     * javadoc end
     * @apiNote 结束组合条件
     *
     * @return JpaBuilder<T>
     * @author zhang yebai
     * @date 2021/7/28 4:49 PM
     **/
    fun end(): JpaBuilder<T> {
        this.grouping = false
        this.wrapper?.let {
            if (it.effective()) {
                conditions.add(it)
            }
        }
        this.wrapper = null
        return this
    }

    /**
     * javadoc and
     * @apiNote 与条件
     *
     * @return JpaBuilder<T>
     * @author zhang yebai
     * @date 2021/7/28 4:49 PM
     **/
    fun and(): JpaBuilder<T> {
        this.operator = JpaDynamicOperator.AND
        return this
    }

    /**
     * javadoc or
     * @apiNote 或条件
     *
     * @return JpaBuilder<T>
     * @author zhang yebai
     * @date 2021/7/28 4:49 PM
     **/
    fun or(): JpaBuilder<T> {
        this.operator = JpaDynamicOperator.OR
        return this
    }

    /**
     * javadoc beNull
     * @apiNote where user_name is null
     *
     * @param key 属性名
     * @return JpaBuilder<T>
     * @author zhang yebai
     * @date 2021/7/28 4:49 PM
     **/
    fun beNull(key: String): JpaBuilder<T> {
        val condition = JpaNull(key)
        this.handleCondition(condition)
        return this
    }

    /**
     * javadoc notNull
     * @apiNote where user_name is not null
     *
     * @param key 属性名
     * @return JpaBuilder<T>
     * @author zhang yebai
     * @date 2021/7/28 4:50 PM
     **/
    fun notNull(key: String): JpaBuilder<T> {
        val condition = JpaNotNull(key)
        this.handleCondition(condition)
        return this
    }

    /**
     * javadoc eq
     * @apiNote where user_name = 'zyb'
     *
     * @param key 属性名
     * @param value 属性值
     * @return JpaBuilder<T>
     * @author zhang yebai
     * @date 2021/7/28 4:50 PM
     **/
    fun eq(key: String, value: Any): JpaBuilder<T> {
        val condition = JpaEq(value, key)
        this.handleCondition(condition)
        return this
    }

    /**
     * javadoc bet
     * @apiNote where user_id between 1 and 100
     *
     * @param key 属性名
     * @param start left range
     * @param end right range
     * @return JpaBuilder<T>
     * @author zhang yebai
     * @date 2021/7/28 4:51 PM
     **/
    fun bet(key: String, start: Comparable<Any>, end: Comparable<Any>): JpaBuilder<T> {
        val condition = JpaBet(start, end, key)
        this.handleCondition(condition)
        return this
    }

    /**
     * javadoc ge
     * @apiNote where user_id >= 1
     *
     * @param key 属性名
     * @param value 属性值
     * @return JpaBuilder<T>
     * @author zhang yebai
     * @date 2021/7/28 4:52 PM
     **/
    fun ge(key: String, value: Number): JpaBuilder<T> {
        val condition = JpaGe(value, key)
        this.handleCondition(condition)
        return this
    }

    /**
     * javadoc gt
     * @apiNote where user_id > 1
     *
     * @param key 属性名
     * @param value 属性值
     * @return JpaBuilder<T>
     * @author zhang yebai
     * @date 2021/7/28 4:52 PM
     **/
    fun gt(key: String, value: Number): JpaBuilder<T> {
        val condition = JpaGt(value, key)
        this.handleCondition(condition)
        return this
    }

    /**
     * javadoc in
     * @apiNote where user_id in [1, 2, 3]
     *
     * @param key 属性名
     * @param collection 属性值集合
     * @return JpaBuilder<T>
     * @author zhang yebai
     * @date 2021/7/28 4:54 PM
     **/
    fun `in`(key: String, collection: Collection<Any>): JpaBuilder<T> {
        val condition = JpaIn(collection, key)
        this.handleCondition(condition)
        return this
    }

    /**
     * javadoc le
     * @apiNote where user_id <= 4
     *
     * @param key 属性名
     * @param value 属性值
     * @return JpaBuilder<T>
     * @author zhang yebai
     * @date 2021/7/28 4:55 PM
     **/
    fun le(key: String, value: Number): JpaBuilder<T> {
        val condition = JpaLe(value, key)
        this.handleCondition(condition)
        return this
    }

    /**
     * javadoc like
     * @apiNote where user_name like '%zy%'
     *
     * @param key 属性名
     * @param value 属性值
     * @return JpaBuilder<T>
     * @author zhang yebai
     * @date 2021/7/28 4:55 PM
     **/
    fun like(key: String, value: String): JpaBuilder<T> {
        val condition = JpaLike(value, key)
        this.handleCondition(condition)
        return this
    }

    /**
     * javadoc leftLike
     * @apiNote where user_name like '%zy'
     *
     * @param key 属性名
     * @param value 属性值
     * @return JpaBuilder<T>
     * @author zhang yebai
     * @date 2021/7/28 4:56 PM
     **/
    fun leftLike(key: String, value: String): JpaBuilder<T> {
        val condition = JpaLeftLike(value, key)
        this.handleCondition(condition)
        return this
    }

    /**
     * javadoc rightLike
     * @apiNote where user_name like 'zy%'
     *
     * @param key 属性名
     * @param value 属性值
     * @return JpaBuilder<T>
     * @author zhang yebai
     * @date 2021/7/28 4:56 PM
     **/
    fun rightLike(key: String, value: String): JpaBuilder<T> {
        val condition = JpaRightLike(value, key)
        this.handleCondition(condition)
        return this
    }

    /**
     * javadoc lt
     * @apiNote where user_id < 5
     *
     * @param key 属性名
     * @param value 属性值
     * @return JpaBuilder<T>
     * @author zhang yebai
     * @date 2021/7/28 4:57 PM
     **/
    fun lt(key: String, value: Number): JpaBuilder<T> {
        val condition = JpaLt(value, key)
        this.handleCondition(condition)
        return this
    }

    /**
     * javadoc neq
     * @apiNote not equals
     *          where user_id <> 1
     *
     * @param key 属性名
     * @param value 属性值
     * @return JpaBuilder<T>
     * @author zhang yebai
     * @date 2021/7/28 4:57 PM
     **/
    fun neq(key: String, value: Any): JpaBuilder<T> {
        val condition = JpaNeq(value, key)
        this.handleCondition(condition)
        return this
    }

    /**
     * javadoc notIn
     * @apiNote where user_id not in [1, 3, 5]
     *
     * @param key 属性名
     * @param collection 属性值集合
     * @return JpaBuilder<T>
     * @author zhang yebai
     * @date 2021/7/28 4:58 PM
     **/
    fun notIn(key: String, collection: Collection<Any>): JpaBuilder<T> {
        val condition = JpaNotIn(collection, key)
        this.handleCondition(condition)
        return this
    }

    private fun handleCondition(condition: JpaCondition) {
        if (grouping) {
            this.wrapper?.append(condition)
        } else {
            val tempWrapper = JpaConditionWrapper(this.operator, JpaDynamicOperator.AND)
            tempWrapper.append(condition)
            conditions.add(tempWrapper)
        }
    }

    private fun buildPredicate(
        wrapper: JpaConditionWrapper,
        root: Root<*>,
        query: CriteriaQuery<*>,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return if (wrapper.grouped()) {
            val predicates = wrapper.toPredicates(root, query, criteriaBuilder)
            if (wrapper.innerAnd()) criteriaBuilder.and(*predicates) else criteriaBuilder.or(*predicates)
        } else {
            wrapper.toPredicate(root, query, criteriaBuilder)
        }
    }


    /**
     * javadoc build
     * @apiNote 构建jpa Specification
     *
     * @return Specification<T>
     * @author zhang yebai
     * @date 2021/7/28 4:47 PM
     **/
    fun build(): Specification<T> {
        return Specification { root, query, criteriaBuilder ->
            val ps = this.conditions.map {
                val p = buildPredicate(it, root, query, criteriaBuilder)
                if(it.outAnd()) criteriaBuilder.and(p) else criteriaBuilder.or(p)
            }.toTypedArray()
            query.where(*ps).restriction
        }
    }

    override fun toString(): String {
        return "JpaBuilder(conditions=$conditions)"
    }


    companion object {

        /**
         * javadoc builder
         * @apiNote jpa 条件构建器新实例
         *
         * @return JpaBuilder<T>
         * @author zhang yebai
         * @date 2021/7/28 4:46 PM
         **/
        @JvmStatic
        fun <T> builder() = JpaBuilder<T>()
    }
}

/**
 * javadoc JpaConditionWrapper
 * <p>
 *     jpa condition wrapper class
 * <p>
 * @author zhang yebai
 * @date 2021/7/28 4:46 PM
 * @version 1.0.0
 **/
class JpaConditionWrapper(
    private var outOperator: JpaDynamicOperator = JpaDynamicOperator.AND,
    private var innerOperator: JpaDynamicOperator = JpaDynamicOperator.OR,
    private val conditions: MutableList<JpaCondition> = mutableListOf()
) {

    fun outAnd(): Boolean {
        return outOperator == JpaDynamicOperator.AND
    }

    fun innerAnd(): Boolean {
        return innerOperator == JpaDynamicOperator.AND
    }

    fun append(condition: JpaCondition) {
        conditions.add(condition)
    }

    fun effective(): Boolean {
        return conditions.isNotEmpty()
    }

    fun grouped(): Boolean {
        return conditions.size > 1
    }


    fun toPredicate(root: Root<*>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate {
        return conditions.first().toPredicate(root, query, criteriaBuilder)
    }

    fun toPredicates(root: Root<*>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Array<Predicate> {
        return conditions.map { it.toPredicate(root, query, criteriaBuilder) }.toTypedArray()
    }
}