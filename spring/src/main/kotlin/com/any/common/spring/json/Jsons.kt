package com.any.common.spring.json

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * javadoc JsonUtil
 * <p>
 * <p>
 * @author zhang yebai
 * @date 2021/7/5 1:58 PM
 * @version 1.0.0
 **/
@Suppress(names = ["unused"])
object Jsons {

    private val JSON = ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

    private val log: Logger = LoggerFactory.getLogger(Jsons::class.java)

    /**
     * javadoc toJsonString
     * @apiNote 将对象转换成json字符串
     *
     * @param obj 待转对象
     * @return String nullable string
     * @author zhang yebai
     * @date 2021/7/5 2:10 PM
     **/
    @JvmStatic
    fun toJsonString(obj: Any?): String? {
        return obj?.let { JSON.writeValueAsString(it) }
    }

    /**
     * javadoc parseObject
     * @apiNote 从json字符串中解析java 对象
     *
     * @param json json字符串
     * @param clazz 目标对象class
     * @return T
     * @author zhang yebai
     * @date 2021/7/5 2:25 PM
     **/
    @JvmStatic
    fun <T> parseObject(json: String?, clazz: Class<T>): T? = json?.let { JSON.readValue(it, clazz) }


    /**
     * javadoc parseObject
     * @apiNote 从json字符串中解析 带泛型类型的java 对象
     *          本方法异常时返回null
     *          无法从字符串中解析出对象时返回null
     *
     * @param json json字符串
     * @param reference 泛型类型引用
     * @return T
     * @author zhang yebai
     * @date 2021/7/5 2:27 PM
     **/
    @JvmStatic
    fun <T> parseObject(json: String?, reference: TypeReference<T>): T? = json?.let { JSON.readValue(it, reference) }
}