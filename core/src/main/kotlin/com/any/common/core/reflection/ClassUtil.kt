package com.any.common.core.reflection

import java.util.*

/**
 * javadoc ClassUtil
 * <p>
 *     java class 相关util
 *     大部分从spring中抄的
 * <p>
 * @author zhang yebai
 * @date 2021/7/30 4:08 PM
 * @version 1.0.0
 **/
object ClassUtil {

    private val primitiveWrapperMapping: MutableMap<Class<*>, Class<*>> = IdentityHashMap()

    init {
        primitiveWrapperMapping[Void::class.java] = Void.TYPE
        primitiveWrapperMapping[java.lang.Byte::class.java] = Byte::class.javaPrimitiveType!!
        primitiveWrapperMapping[java.lang.Short::class.java] = Short::class.javaPrimitiveType!!
        primitiveWrapperMapping[java.lang.Character::class.java] = Char::class.javaPrimitiveType!!
        primitiveWrapperMapping[java.lang.Integer::class.java] = Int::class.javaPrimitiveType!!
        primitiveWrapperMapping[java.lang.Float::class.java] = Float::class.javaPrimitiveType!!
        primitiveWrapperMapping[java.lang.Double::class.java] = Double::class.javaPrimitiveType!!
        primitiveWrapperMapping[java.lang.Long::class.java] = Long::class.javaPrimitiveType!!
        primitiveWrapperMapping[java.lang.Boolean::class.java] = Boolean::class.javaPrimitiveType!!
    }

    /**
     * javadoc primitive
     * @apiNote 判断是否是原生类型, 包含void.class
     *
     * @param clazz 目标class
     * @return boolean
     * @author zhang yebai
     * @date 2021/7/30 4:42 PM
     **/
    @JvmStatic
    fun primitive(clazz: Class<*>): Boolean = clazz.isPrimitive

    /**
     * javadoc primitiveWrapper
     * @apiNote 判断是否是原生类型的包装类, 包含Void.class
     *
     * @param clazz 目标class
     * @return boolean
     * @author zhang yebai
     * @date 2021/7/30 4:43 PM
     **/
    @JvmStatic
    fun primitiveWrapper(clazz: Class<*>): Boolean = primitiveWrapperMapping.containsKey(clazz)

    /**
     * javadoc primitiveOrPrimitiveWrapper
     * @apiNote 判断是否是原生类型或者是原生类型的包装类, 包含void.class 及 Void.class
     *
     * @param clazz 目标class
     * @return boolean
     * @author zhang yebai
     * @date 2021/7/30 4:44 PM
     **/
    @JvmStatic
    fun primitiveOrPrimitiveWrapper(clazz: Class<*>): Boolean = clazz.isPrimitive || primitiveWrapper(clazz)

    /**
     * javadoc primitiveArray
     * @apiNote 判断是否是原生类型的数组, 从数组的定义上来看, 是不包含 void[].class 的
     *
     * @param clazz 目标class
     * @return boolean
     * @author zhang yebai
     * @date 2021/7/30 4:44 PM
     **/
    @JvmStatic
    fun primitiveArray(clazz: Class<*>): Boolean = clazz.isArray && clazz.componentType.isPrimitive

    /**
     * javadoc primitiveWrapperArray
     * @apiNote 判断是否是原生类型包装类的目标数组, 从数组的定义上来看, 是包含Void[].class的, 但是Void[].class在绝大多数情况下毫无意义
     *
     * @param clazz 目标class
     * @return boolean
     * @author zhang yebai
     * @date 2021/7/30 4:45 PM
     **/
    @JvmStatic
    fun primitiveWrapperArray(clazz: Class<*>): Boolean = clazz.isArray && primitiveWrapper(clazz.componentType)
}