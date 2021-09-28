package com.any.common.spring.storage

@Suppress(names = ["unused"])
class LocalStorage<T> {

    private val localStorage = InheritableThreadLocal<T>()

    /**
     * javadoc exists
     * @apiNote 是否存在
     *
     * @return Boolean
     * @author zhang yebai
     * @date 2021/7/5 3:32 PM
     **/
    fun exists(): Boolean = localStorage.get() != null

    /**
     * javadoc get
     * @apiNote
     *
     * @return T
     * @author zhang yebai
     * @date 2021/7/5 3:33 PM
     **/
    fun get(): T? {
        return localStorage.get()
    }

    /**
     * javadoc set
     * @apiNote
     *
     * @param bo
     * @author zhang yebai
     * @date 2021/7/5 3:33 PM
     **/
    fun set(bo: T): LocalStorage<T> {
        localStorage.set(bo)
        return this
    }

    /**
     * javadoc clear
     * @apiNote
     *
     * @author zhang yebai
     * @date 2021/7/5 3:33 PM
     **/
    fun clear(): LocalStorage<T> {
        localStorage.remove()
        return this
    }
}