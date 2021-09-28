package com.any.common.core.reflection

import java.lang.reflect.Field
import java.lang.reflect.Method


/**
 * javadoc Reflections
 * <p>
 * <p>
 * @author zhang yebai
 * @date 2021/7/6 3:21 PM
 * @version 1.0.0
 **/
@Suppress(names = ["unused"])
object Reflections {

    /**
     * javadoc listClassMethods
     * @apiNote 获取目标class的所有方法, 包含父类
     *
     * @param clazz 目标class
     * @return Array<Method>
     * @author zhang yebai
     * @date 2021/7/6 4:00 PM
     **/
    @JvmStatic
    fun listClassMethods(clazz: Class<*>): Array<Method> {
        val methods = mutableListOf<Method>()
        var c = clazz
        while (c != Object::class.java) {
            methods.addAll(c.declaredMethods)
            c = c.superclass
        }
        return methods.toTypedArray()
    }

    /**
     * javadoc listAnnotatedClassMethods
     * @apiNote 枚举被某注解注解过的类方法
     *
     * @param clazz 目标class
     * @param annotation 注解class
     * @return Array<Method>
     * @author zhang yebai
     * @date 2021/7/7 10:36 AM
     **/
    @JvmStatic
    fun <T : Annotation> listAnnotatedClassMethods(clazz: Class<*>, annotation: Class<T>): Array<Method> {
        val methods = mutableListOf<Method>()
        var c = clazz
        while (c != Object::class.java) {
            for (method in c.declaredMethods) {
                if (c.isAnnotationPresent(annotation)) {
                    methods.add(method)
                }
            }
            c = c.superclass
        }
        return methods.toTypedArray()
    }

    /**
     * javadoc findClassMethod
     * @apiNote 查询对象的方法
     *
     * @param clazz 对象java class
     * @return Method?
     * @author zhang yebai
     * @date 2021/7/6 8:15 PM
     **/
    @JvmStatic
    fun findClassMethod(clazz: Class<*>, name: String): Method? {
        var c = clazz
        while (c != Object::class.java) {
            for (m in c.declaredMethods) {
                if (m.name == name) {
                    return m
                }
            }
            c = c.superclass
        }
        return null
    }

    /**
     * javadoc findMethods
     * @apiNote 查询对象的方法 (包含重载的方法)
     *
     * @param clazz 对象java class
     * @return Array<Method>
     * @author zhang yebai
     * @date 2021/7/6 8:15 PM
     **/
    @JvmStatic
    fun findClassMethods(clazz: Class<*>, name: String): Array<Method> {
        val methods = mutableListOf<Method>()
        var c = clazz
        while (c != Object::class.java) {
            for (m in c.declaredMethods) {
                if (m.name == name) {
                    methods.add(m)
                }
            }
            c = c.superclass
        }
        return methods.toTypedArray()
    }
//
//    @JvmStatic
//    @Suppress(names = ["UNCHECKED_CAST"])
//    fun <T: Annotation> findClassAnnotation(clazz: Class<*>, annotation: Class<T>): T?{
//        return clazz.getAnnotation(annotation)
//    }
//
//    @JvmStatic
//    @Suppress(names = ["UNCHECKED_CAST"])
//    fun <T: Annotation> findFieldAnnotation(field: Field, annotation: Class<T>): T?{
//        return field.getAnnotation(annotation)
//    }

    /**
     * javadoc invoke
     * @apiNote 调用对象的方法
     *
     * @param obj 实例对象
     * @param method 对象方法
     * @param args 方法参数
     * @return T? 方法结果, 可为void
     * @author zhang yebai
     * @date 2021/7/6 8:27 PM
     **/
    @JvmStatic
    @Suppress(names = ["UNCHECKED_CAST"])
    fun <T> invoke(obj: Any, method: Method, vararg args: Any?): T? {
        method.isAccessible = true
        val value = method.invoke(obj, *args)
        return value?.let { it as T }
    }

    /**
     * javadoc invoke
     * @apiNote 调用对象方法
     *
     * @param obj 实例对象
     * @param methodName 对象方法名
     * @param args 方法参数
     * @return T? 方法返回值
     * @author zhang yebai
     * @date 2021/7/13 10:05 AM
     **/
    @JvmStatic
    fun <T> invoke(obj: Any, methodName: String, vararg args: Any?): T? {
        return findClassMethod(obj.javaClass, methodName)?.let { invoke(obj, it, *args) }
    }

    /**
     * javadoc listClassFields
     * @apiNote 枚举对象的属性信息
     *
     * @param clazz 对象的java class
     * @return Field[]
     * @author zhang yebai
     * @date 2021/7/6 8:15 PM
     **/
    @JvmStatic
    fun listClassFields(clazz: Class<*>): Array<Field> {
        val fields = mutableListOf<Field>()
        var c = clazz
        while (c != Object::class.java) {
            fields.addAll(c.declaredFields)
            c = c.superclass
        }
        return fields.toTypedArray()
    }

    /**
     * javadoc listAnnotatedClassFields
     * @apiNote 枚举被某注解注解过的class属性
     *
     * @param clazz 目标class
     * @param annotation 注解class
     * @return Array<Field>
     * @author zhang yebai
     * @date 2021/7/7 10:31 AM
     **/
    @JvmStatic
    fun <T : Annotation> listAnnotatedClassFields(clazz: Class<*>, annotation: Class<T>): Array<Field> {
        val fields = mutableListOf<Field>()
        var c = clazz
        while (c != Object::class.java) {
            for (field in c.declaredFields) {
                if (field.isAnnotationPresent(annotation)) {
                    fields.add(field)
                }
            }
            c = c.superclass
        }
        return fields.toTypedArray()
    }

    /**
     * javadoc findField
     * @apiNote 查询对象的属性信息
     *
     * @param clazz 目标对象java class
     * @return Field? java属性描述class
     * @author zhang yebai
     * @date 2021/7/6 8:14 PM
     **/
    @JvmStatic
    fun findClassField(clazz: Class<*>, name: String): Field? {
        var c = clazz
        while (c != Object::class.java) {
            for (f in c.declaredFields) {
                if (f.name == name) {
                    return f
                }
            }
            c = c.superclass
        }
        return null
    }

    /**
     * javadoc getFieldValue
     * @apiNote 获取对象属性值
     *
     * @param obj 实例对象
     * @param name 对象属性名
     * @return T?
     * @author zhang yebai
     * @date 2021/7/6 7:50 PM
     **/
    @Suppress(names = ["UNCHECKED_CAST"])
    @JvmStatic
    fun <T> getFieldValue(obj: Any, name: String): T? {
        return findClassField(obj.javaClass, name)?.let { it ->
            it.isAccessible = true
            it[obj]?.let { value -> value as T }
        }
    }

    /**
     * javadoc setFieldValue
     * @apiNote 设置对象属性值
     *
     * @param obj 实例对象
     * @param name 属性名称
     * @param value 属性值
     * @author zhang yebai
     * @date 2021/7/6 8:04 PM
     **/
    @JvmStatic
    fun setFieldValue(obj: Any, name: String, value: Any?) {
        for (f in listClassFields(obj.javaClass)) {
            if (f.name == name) {
                f.isAccessible = true
                f.set(obj, value)
            }
        }
    }

    /**
     * javadoc reflectionToString
     * @apiNote 强属性一致性toString()
     *          // todo: 需要递归去处理 反射得到的 field 值
     *
     * @param obj toString 对象
     * @return String
     * @author zhang yebai
     * @date 2021/7/6 7:50 PM
     **/
    @JvmStatic
    fun reflectionToString(obj: Any): String {

        return when (obj) {
            is Number, is Map<*, *>, is Collection<*>, is CharSequence, is Char -> obj.toString()
            is Array<*> -> obj.contentToString()
            else -> {
                val values: MutableList<String> = ArrayList()
                val fields = listClassFields(obj.javaClass)
                for (field in fields) {
                    field.isAccessible = true
                    values.add("${field.name}=${field[obj]}")
                }
                val names = obj::class.java.name.split(".")
                val name = if (names.isNotEmpty()) names.last() else obj::class.java.name
                "${name}{ " +
                        values.joinToString(", ") { it } +
                        " }"
            }
        }
    }

}