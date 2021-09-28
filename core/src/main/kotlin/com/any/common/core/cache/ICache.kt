package com.any.common.core.cache

import java.util.concurrent.TimeUnit

@Suppress(names = ["unused"])
interface ICache {

    /* how to implement it in java
       <code>
            @SuppressWarnings(value = "NullableProblems")
            @Service
            public class CacheService implements ICache{

                @Override
                public <T extends Cacheable> T set(T cacheable){
                    // do cache
                    return cacheable;
                }

                @Override
                public <T extends Cacheable> T set(T cacheable, long expire, TimeUnit unit){
                    // do cache
                    return cacheable;
                }

                @Override
                public <T> T set(String key, T cache){
                    // do cache
                    return cache;
                }

                @Override
                public <T> T set(String key, T cache, long expire, TimeUnit unit){
                    // do cache
                    return cache;
                }

                @Override
                public <T> T get(String key){
                    // get cache
                    T t = null;
                    return t;
                }

                @Override
                public boolean exists(String key){
                    // check cache
                    return true;
                }

                @Override
                public void expire(String key, long expire, TimeUnit unit){
                    // do expire
                }

                @Override
                public void del(String key){
                    // do del
                }
        }
       </code>
     */

    /**
     * javadoc set
     * @apiNote 设置缓存
     *
     * @param cacheable 可缓存的对象
     * @return T
     * @author zhang yebai
     * @date 2021/6/29 11:15 AM
     **/
    fun <T : Cacheable> set(cacheable: T): T

    /**
     * javadoc set
     * @apiNote 设置缓存, 带超时时间
     *
     * @param cacheable 可缓存的对象
     * @param expire 超时时间
     * @param unit 超时时间单位
     * @return T
     * @author zhang yebai
     * @date 2021/6/29 11:16 AM
     **/
    fun <T : Cacheable> set(cacheable: T, expire: Long, unit: TimeUnit): T

    /**
     * javadoc set
     * @apiNote 通过key-value形式设置缓存
     *
     * @param key 缓存键
     * @param cache 缓存值
     * @return T
     * @author zhang yebai
     * @date 2021/6/29 11:17 AM
     **/
    fun <T> set(key: String, cache: T): T

    /**
     * javadoc set
     * @apiNote 通过key-value形式设置缓存
     *          带超时时间
     *
     * @param key 缓存键
     * @param cache 缓存值
     * @param expire 超时时间
     * @param unit 超时时间单位
     * @return T
     * @author zhang yebai
     * @date 2021/6/29 11:17 AM
     **/
    fun <T> set(key: String, cache: T, expire: Long, unit: TimeUnit): T

    /**
     * javadoc get
     * @apiNote 获取缓存内容
     *
     * @param key 缓存键
     * @return T?
     * @author zhang yebai
     * @date 2021/6/29 11:18 AM
     **/
    fun <T> get(key: String): T?

    /**
     * javadoc exists
     * @apiNote 判断缓存是否存在
     *
     * @param key 缓存键
     * @return Boolean
     * @author zhang yebai
     * @date 2021/6/29 11:18 AM
     **/
    fun exists(key: String): Boolean

    /**
     * javadoc expire
     * @apiNote 设置缓存超时时间
     *
     * @param key 缓存键
     * @param expire 超时时间
     * @param unit 超时时间单位
     * @author zhang yebai
     * @date 2021/6/29 11:19 AM
     **/
    fun expire(key: String, expire: Long, unit: TimeUnit)

    /**
     * javadoc del
     * @apiNote 删除缓存
     *
     * @param key 缓存键
     * @author zhang yebai
     * @date 2021/6/29 11:19 AM
     **/
    fun del(key: String)
}