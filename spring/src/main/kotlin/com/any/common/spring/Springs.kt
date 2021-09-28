package com.any.common.spring

import org.springframework.beans.factory.ListableBeanFactory
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

/**
 * javadoc Springs
 * <p>
 *     spring 相关utils
 *     必须使用 @EnableSprings 手动开启
 *     参考自hu-tool:
 *     <link>https://github.com/dromara/hutool</link>
 * <p>
 * @author zhang yebai
 * @date 2021/7/8 10:50 AM
 * @version 1.0.0
 **/
@Suppress(names = ["unused"])
@Component
class Springs : BeanFactoryPostProcessor, ApplicationContextAware {

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        Companion.applicationContext = applicationContext
    }

    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        Companion.beanFactory = beanFactory
    }

    companion object {
        @JvmStatic
        private var applicationContext: ApplicationContext? = null

        @JvmStatic
        private var beanFactory: ConfigurableListableBeanFactory? = null

        /**
         * javadoc getBeanFactory
         * @apiNote 获取spring 上下文及bean factory
         *
         * @return ListableBeanFactory
         * @author zhang yebai
         * @date 2021/7/8 10:28 AM
         **/
        @JvmStatic
        fun getBeanFactory(): ListableBeanFactory? = beanFactory ?: applicationContext

        /**
         * javadoc getBean
         * @apiNote 动态获取已注册的bean
         *
         * @param name bean名称
         * @return T
         * @author zhang yebai
         * @date 2021/7/8 10:32 AM
         **/
        @Suppress(names = ["UNCHECKED_CAST"])
        @JvmStatic
        fun <T> getBean(name: String): T? = getBeanFactory()?.let { it.getBean(name) as T }

        /**
         * javadoc getBean
         * @apiNote 动态获取已注册的bean
         *
         * @param clazz bean类型class
         * @return T
         * @author zhang yebai
         * @date 2021/7/8 10:32 AM
         **/
        @JvmStatic
        fun <T> getBean(clazz: Class<T>): T? = getBeanFactory()?.getBean(clazz)

        /**
         * javadoc getBean
         * @apiNote 动态获取已注册的bean
         *
         * @param name bean名称
         * @param clazz bean 类型 class
         * @return T
         * @author zhang yebai
         * @date 2021/7/8 10:32 AM
         **/
        @JvmStatic
        fun <T> getBean(name: String, clazz: Class<T>): T? = getBeanFactory()?.getBean(name, clazz)

        /**
         * javadoc getProperty
         * @apiNote 根据配置的key 获取配置的值
         *
         * @param key 配置的key
         * @return String 配置的值
         * @author zhang yebai
         * @date 2021/7/8 10:39 AM
         **/
        @JvmStatic
        fun getProperty(key: String): String? = applicationContext?.environment?.getProperty(key)

        /**
         * javadoc getProperty
         * @apiNote 根据配置的key 获取指定类型的配置的值
         *
         * @param key 配置的key
         * @return T 配置的值
         * @author zhang yebai
         * @date 2021/7/8 10:41 AM
         **/
        @JvmStatic
        fun <T> getProperty(key: String, clazz: Class<T>): T? = applicationContext?.environment?.getProperty(key, clazz)

        /**
         * javadoc listActiveProfiles
         * @apiNote 枚举spring 启动使用的环境配置名称
         *
         * @return List<String>
         * @author zhang yebai
         * @date 2021/7/8 10:42 AM
         **/
        @JvmStatic
        fun listActiveProfiles(): List<String> =
            applicationContext?.environment?.activeProfiles?.toList() ?: mutableListOf()

        /**
         * javadoc getActiveProfile
         * @apiNote 获取当前spring启动使用的环境配置名称
         *
         * @return String
         * @author zhang yebai
         * @date 2021/7/8 10:43 AM
         **/
        @JvmStatic
        fun getActiveProfile(): String? {
            val profiles = listActiveProfiles()
            return if (profiles.isNotEmpty()) {
                profiles[0]
            } else {
                null
            }
        }
    }
}