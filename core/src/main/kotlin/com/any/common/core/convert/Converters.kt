package com.any.common.core.convert

import java.util.*
import java.util.function.Function
import java.util.function.Supplier

@Suppress(names = ["unused"])
object Converters {

    /**
     * javadoc to
     * @apiNote 如果 t 不是null, 则使用 f 将 t 转换成 R 并返回, 否则返回 null
     *
     * @param t 待转换目标
     * @param f 转换函数
     * @return R
     * @author zhang yebai
     * @date 2021/6/17 9:51 AM
     **/
    @JvmStatic
    fun <R, T> to(t: T?, f: Function<T, R>): R? = t?.let { f.apply(t) }

    /**
     * javadoc to
     * @apiNote 如果 t 不是null, 则使用 f 将 t 转换成 R 并返回, 否则返回 null
     *
     * @param opt 待转换目标
     * @param f 转换函数
     * @return R
     * @author zhang yebai
     * @date 2021/6/17 9:51 AM
     **/
    @JvmStatic
    fun <R, T> to(opt: Optional<T>, f: Function<T, R>): R? = opt.map { f.apply(it) }.orElse(null)

    /**
     * javadoc to
     * @apiNote 使用目标生成函数生成 目标 t
     *          再使用目标转换函数将 t 转换成 r
     *
     * @param s 目标生成函数
     * @param f 目标转换函数
     * @return null
     * @author zhang yebai
     * @date 2021/6/17 10:00 AM
     **/
    @JvmStatic
    fun <R, T> to(s: Supplier<T>, f: Function<T, R>): R? = f.apply(s.get())

    /**
     * javadoc opt
     * @apiNote 如果 t 不是null, 则使用 f 将 t 转换成 Optional.of(R) 并返回, 否则返回 Optional.empty()
     *          kotlin不必使用该方法, 该方法是为了方便 java 使用
     *
     * @param t 待转换目标
     * @param f 转换函数
     * @return Optional
     * @author zhang yebai
     * @date 2021/6/17 9:55 AM
     **/
    @JvmStatic
    fun <R, T> opt(t: T?, f: Function<T, R>): Optional<R> = Optional.ofNullable(to(t, f))

    /**
     * javadoc opt
     * @apiNote 使用目标生成函数生成 目标 t
     *          再使用目标转换函数将 t 转换成 Optional<R>
     *
     * @param s 目标生成函数
     * @param f 目标转换函数
     * @return Optional
     * @author zhang yebai
     * @date 2021/6/17 10:03 AM
     **/
    @JvmStatic
    fun <R, T> opt(s: Supplier<T>, f: Function<T, R>): Optional<R> = Optional.ofNullable(to(s, f))
}