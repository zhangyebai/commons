package com.any.common.jpa.domain

import javax.persistence.criteria.*

/**
 * javadoc JpaCondition
 * <p>
 *      jpa动态条件标志
 * <p>
 * @author zhang yebai
 * @date 2021/7/22 1:52 PM
 * @version 1.0.0
 **/
interface JpaCondition {

    /**
     * javadoc setColumn
     * @apiNote 设置列名
     *
     * @param name 列名
     * @author zhang yebai
     * @date 2021/7/28 4:42 PM
     **/
    fun setColumn(name: String)

    /**
     * javadoc toPredicate
     * @apiNote 转换成jpa的predicate
     *
     * @param root jpa entity root
     * @param query jpa entity query
     * @param criteriaBuilder jpa entity query builder
     * @return Predicate
     * @author zhang yebai
     * @date 2021/7/28 4:43 PM
     **/
    fun <T> toPredicate(root: Root<T>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate
}


/**
 * javadoc JpaNull
 * <p>
 *     attribute is null
 * <p>
 * @author zhang yebai
 * @date 2021/7/28 4:43 PM
 * @version 1.0.0
 **/
class JpaNull(private var key: String = "") : JpaCondition {

    constructor(): this("")

    override fun setColumn(name: String) {
        this.key = name
    }

    override fun <T> toPredicate(root: Root<T>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate {
        val path: Path<String> = root[key]
        return criteriaBuilder.isNull(path)
    }

    override fun toString(): String {
        return "JpaNull(key='$key')"
    }
}

/**
 * javadoc JpaNotNull
 * <p>
 *     attribute is not null
 * <p>
 * @author zhang yebai
 * @date 2021/7/28 4:44 PM
 * @version 1.0.0
 **/
class JpaNotNull(private var key: String = "") : JpaCondition {
    override fun setColumn(name: String) {
        this.key = name
    }

    override fun <T> toPredicate(root: Root<T>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate {
        val path: Path<String> = root[key]
        return criteriaBuilder.isNotNull(path)
    }

    override fun toString(): String {
        return "JpaNotNull(key='$key')"
    }
}


/**
 * javadoc JpaEq
 * <p>
 *     equal: =
 * <p>
 * @author zhang yebai
 * @date 2021/7/22 11:57 AM
 * @version 1.0.0
 **/
class JpaEq<T>(private val eq: T, private var key: String = "") : JpaCondition {
    constructor(eq: T) : this(eq, "")

    override fun setColumn(name: String) {
        this.key = name
    }

    override fun <T> toPredicate(root: Root<T>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate {
        val path: Path<Any> = root[key]
        return criteriaBuilder.equal(path, eq)
    }

    override fun toString(): String {
        return "JpaEq(eq=$eq, key='$key')"
    }
}

/**
 * javadoc JpaBet
 * <p>
 *     between: between start and end
 * <p>
 * @author zhang yebai
 * @date 2021/7/22 12:02 PM
 * @version 1.0.0
 **/
class JpaBet<T : Comparable<T>>(private val start: T, private val end: T, private var key: String) : JpaCondition {

    constructor(start: T, end: T): this(start, end, "")

    override fun setColumn(name: String) {
        this.key = name
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> toPredicate(root: Root<T>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate {
        val path: Path<Comparable<Any>> = root[key]
        return criteriaBuilder.between(path, start as Comparable<Any>, end as Comparable<Any>)
    }

    override fun toString(): String {
        return "JpaBet(start=$start, end=$end, key='$key')"
    }
}

/**
 * javadoc JpaGe
 * <p>
 *     great and equal than: >=
 * <p>
 * @author zhang yebai
 * @date 2021/7/22 11:57 AM
 * @version 1.0.0
 **/
class JpaGe<T : Number>(private val ge: T, private var key: String = "") : JpaCondition {

    constructor(ge: T): this(ge, "")

    override fun setColumn(name: String) {
        this.key = name
    }

    override fun <T> toPredicate(root: Root<T>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate {
        val path: Path<Number> = root[key]
        return criteriaBuilder.ge(path, ge)
    }

    override fun toString(): String {
        return "JpaGe(ge=$ge, key='$key')"
    }
}

/**
 * javadoc JpaGt
 * <p>
 *     great than: >
 * <p>
 * @author zhang yebai
 * @date 2021/7/22 11:58 AM
 * @version 1.0.0
 **/
class JpaGt<T : Number>(private val gt: T, private var key: String = "") : JpaCondition {

    constructor(gt: T): this(gt, "")

    override fun setColumn(name: String) {
        this.key = name
    }

    override fun <T> toPredicate(root: Root<T>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate {
        val path: Path<Number> = root[key]
        return criteriaBuilder.gt(path, gt)
    }

    override fun toString(): String {
        return "JpaGt(gt=$gt, key='$key')"
    }
}

/**
 * javadoc JpaIn
 * <p>
 *     IN [xx, xx]
 * <p>
 * @author zhang yebai
 * @date 2021/7/22 12:01 PM
 * @version 1.0.0
 **/
class JpaIn<E>(private val collection: Collection<E>, private var key: String = "") : JpaCondition {

    constructor(collection: Collection<E>): this(collection, "")

    override fun setColumn(name: String) {
        this.key = name
    }

    override fun <T> toPredicate(root: Root<T>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate {
        val path: Path<Any> = root[key]
        val inn = criteriaBuilder.`in`(path)
        for (item in collection) {
            inn.value(item)
        }
        return criteriaBuilder.and(inn)
    }

    override fun toString(): String {
        return "JpaIn(collection=$collection, key='$key')"
    }
}

/**
 * javadoc JpaLe
 * <p>
 *     less equal than: <=
 * <p>
 * @author zhang yebai
 * @date 2021/7/22 11:58 AM
 * @version 1.0.0
 **/
class JpaLe<T : Number>(private val le: T, private var key: String = "") : JpaCondition {

    constructor(le: T): this(le, "")

    override fun setColumn(name: String) {
        this.key = name
    }

    override fun <T> toPredicate(root: Root<T>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate {
        val path: Path<Number> = root[key]
        return criteriaBuilder.le(path, le)
    }

    override fun toString(): String {
        return "JpaLe(le=$le, key='$key')"
    }
}

/**
 * javadoc JpaLike
 * <p>
 *     left right like: '%...%'
 * <p>
 * @author zhang yebai
 * @date 2021/7/22 12:00 PM
 * @version 1.0.0
 **/
open class JpaLike(open val like: String, open var key: String = "") : JpaCondition {

    constructor(like: String): this(like, "")

    override fun setColumn(name: String) {
        this.key = name
    }

    override fun <T> toPredicate(root: Root<T>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate {
        val path: Path<String> = root[key]
        return criteriaBuilder.like(path, "%$like%")
    }

    override fun toString(): String {
        return "JpaLike(like='$like', key='$key')"
    }


}

/**
 * javadoc JpaLeftLike
 * <p>
 *      left like '%...'
 * <p>
 * @author zhang yebai
 * @date 2021/7/22 11:59 AM
 * @version 1.0.0
 **/
data class JpaLeftLike(override val like: String, override var key: String = "") : JpaLike(like, key) {

    constructor(like: String): this(like, "")

    override fun setColumn(name: String) {
        this.key = name
    }

    override fun <T> toPredicate(root: Root<T>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate {
        val path: Path<String> = root[key]
        return criteriaBuilder.like(path, "%$like")
    }

    override fun toString(): String {
        return "JpaLeftLike(like='$like', key='$key')"
    }
}

/**
 * javadoc JpaRightLike
 * <p>
 *     right like '...%'
 * <p>
 * @author zhang yebai
 * @date 2021/7/23 4:19 PM
 * @version 1.0.0
 **/
class JpaRightLike(override val like: String, override var key: String = "") : JpaLike(like, key) {
    constructor(like: String): this(like, "")

    override fun setColumn(name: String) {
        this.key = name
    }

    override fun <T> toPredicate(root: Root<T>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate {
        val path: Path<String> = root[key]
        return criteriaBuilder.like(path, "$like%")
    }

    override fun toString(): String {
        return "JpaRightLike(like='$like', key='$key')"
    }
}


/**
 * javadoc JpaLt
 * <p>
 *     less than: <
 * <p>
 * @author zhang yebai
 * @date 2021/7/22 12:00 PM
 * @version 1.0.0
 **/
class JpaLt<T : Number>(private val lt: T, private var key: String = "") : JpaCondition {

    constructor(lt: T): this(lt, "")

    override fun setColumn(name: String) {
        this.key = name
    }

    override fun <T> toPredicate(root: Root<T>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate {
        val path: Path<Number> = root[key]
        return criteriaBuilder.lt(path, lt)
    }

    override fun toString(): String {
        return "JpaLt(lt=$lt, key='$key')"
    }


}

/**
 * javadoc JpaNeq
 * <p>
 *     jpa not equal: <> !=
 * <p>
 * @author zhang yebai
 * @date 2021/7/23 4:20 PM
 * @version 1.0.0
 **/
class JpaNeq<T>(private val neq: T, private var key: String = "") : JpaCondition {

    constructor(neq: T): this(neq, "")

    override fun setColumn(name: String) {
        this.key = name
    }


    override fun <T> toPredicate(root: Root<T>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate {
        val path: Path<Any> = root[key]
        return criteriaBuilder.notEqual(path, neq)
    }

    override fun toString(): String {
        return "JpaNeq(neq=$neq, key='$key')"
    }


}

/**
 * javadoc JpaNotIn
 * <p>
 *     not in : not in [xx, xx]
 * <p>
 * @author zhang yebai
 * @date 2021/7/22 12:01 PM
 * @version 1.0.0
 **/
class JpaNotIn<E>(private val collection: Collection<E>, private var key: String = "") : JpaCondition {

    constructor(collection: Collection<E>): this(collection, "")

    override fun setColumn(name: String) {
        this.key = name
    }

    override fun <T> toPredicate(root: Root<T>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate {
        val path: Path<Any> = root[key]
        val inn = criteriaBuilder.`in`(path)
        for (item in collection) {
            inn.value(item)
        }
        return criteriaBuilder.not(inn)
    }

    override fun toString(): String {
        return "JpaNotIn(collection=$collection, key='$key')"
    }
}