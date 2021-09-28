package com.any.common.core.collection

@Suppress("unused")
object ArrayUtil {

    /**
     * javadoc toList
     * @apiNote 数组转list
     *
     * @param array 目标数组
     * @return MutableList
     * @author zhang yebai
     * @date 2021/7/13 6:36 PM
     **/
    @JvmStatic
    fun <T> toList(array: Array<T>?): List<T> = array?.toMutableList() ?: mutableListOf()

    /**
     * javadoc safeGet
     * @apiNote 慎用该方法, 除非明白该方法是想表达什么
     *
     *
     * @param array 目标数组
     * @param idx 获取的位置索引
     * @return T? 数组索引所在位置的内容
     * @author zhang yebai
     * @date 2021/7/13 5:26 PM
     **/
    @JvmStatic
    fun <T> safeGet(array: Array<T>, idx: Int): T? {
        return if (idx >= array.size) {
            null
        } else {
            array[idx]
        }
    }

    /**
     * javadoc safeGenericGet
     * @apiNote 慎用该方法, 除非明白该方法是想表达什么
     *          泛型获取
     *          例如:
     *          {code}
     *          final List<Object> list = Lists.newArrayList(1, "123", 'c');
     *          final char c = ListUtil.safeGenericGet(2);
     *          {code}
     *
     *
     * @param array 目标数组
     * @param idx 获取的位置索引
     * @return T? 数组索引所在位置的内容
     * @author zhang yebai
     * @date 2021/7/13 5:26 PM
     **/
    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    fun <T> safeGenericGet(array: Array<*>, idx: Int): T? {
        return if (idx >= array.size) {
            null
        } else {
            array[idx] as T
        }
    }

    /**
     * javadoc first
     * @apiNote 慎用该方法, 除非明白该方法是想表达什么
     *          获取数组第一个元素
     *
     *
     * @param array 目标数组
     * @return T? 数组索引所在位置的内容
     * @author zhang yebai
     * @date 2021/7/13 5:26 PM
     **/
    @JvmStatic
    fun <T> first(array: Array<T>?): T? {
        return array?.let {
            if (it.isNotEmpty()) {
                it.first()
            } else {
                null
            }
        }
    }

    /**
     * javadoc forceFirst
     * @apiNote 慎用该方法, 除非明白该方法是想表达什么
     *          获取数组第一个元素
     *
     *
     * @param array 目标数组
     * @return T? 数组索引所在位置的内容
     * @author zhang yebai
     * @date 2021/7/13 5:26 PM
     **/
    @JvmStatic
    fun <T> forceFirst(array: Array<T>): T = array.first()

    /**
     * javadoc last
     * @apiNote 慎用该方法, 除非明白该方法是想表达什么
     *          获取数组最后一个元素
     *
     *
     * @param array 目标数组
     * @return T? 数组索引所在位置的内容
     * @author zhang yebai
     * @date 2021/7/13 5:26 PM
     **/
    @JvmStatic
    fun <T> last(array: Array<T>?): T? {
        return array?.let {
            if (it.isNotEmpty()) {
                it.last()
            } else {
                null
            }
        }
    }

    /**
     * javadoc forceLast
     * @apiNote 慎用该方法, 除非明白该方法是想表达什么
     *          获取数组最后一个元素
     *
     *
     * @param array 目标数组
     * @return T? 数组索引所在位置的内容
     * @author zhang yebai
     * @date 2021/7/13 5:26 PM
     **/
    @JvmStatic
    fun <T> forceLast(array: Array<T>): T = array.last()

    /**
     * javadoc beNull
     * @apiNote 数组是否是null
     *
     * @param array 目标数组
     * @return Boolean
     * @author zhang yebai
     * @date 2021/7/13 5:35 PM
     **/
    @JvmStatic
    fun beNull(array: Array<*>?): Boolean = array == null

    /**
     * javadoc beNotNull
     * @apiNote 数组是否不是null
     *
     * @param array 目标数组
     * @return Boolean
     * @author zhang yebai
     * @date 2021/7/13 5:36 PM
     **/
    @JvmStatic
    fun beNotNull(array: Array<*>?): Boolean = array != null

    /**
     * javadoc beEmpty
     * @apiNote 数组是否是空数组
     *
     * @param array 目标数组
     * @return Boolean
     * @author zhang yebai
     * @date 2021/7/13 5:36 PM
     **/
    @JvmStatic
    fun beEmpty(array: Array<*>?): Boolean = array?.isEmpty() ?: true

    /**
     * javadoc beNotEmpty
     * @apiNote 数组是否是非空数组
     *
     * @param array 目标数组
     * @return Boolean
     * @author zhang yebai
     * @date 2021/7/13 5:37 PM
     **/
    @JvmStatic
    fun beNotEmpty(array: Array<*>?): Boolean = array?.isNotEmpty() ?: false

    /**
     * javadoc newArray
     * @apiNote
     *
     * @param type 元素类型
     * @param size 数组大小
     * @param elements 初始化元素列表
     * @return T[]
     * @author zhang yebai
     * @date 2021/7/19 9:55 AM
     **/
    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    fun <T> newArray(size: Int, type: Class<T>,  vararg elements: T?): Array<T>{
        val array = java.lang.reflect.Array.newInstance(type, size)
        val elementSize = elements.size
        val a1 = array as Array<T>
        for ( idx in array.indices){
            if (idx < elementSize){
                val e = elements[idx]
                e?.let { a1[idx] = it }
            }
        }
        return a1
    }

    /**
     * javadoc newArray
     * @apiNote
     *
     * @param type 元素类型
     * @param elements 初始化元素
     * @return T[]
     * @author zhang yebai
     * @date 2021/7/19 9:55 AM
     **/
    @JvmStatic
    fun <T> newArray(type: Class<T>, vararg elements: T?): Array<T>{
        val size = elements.size
        val array = newArray(size, type)
        for( (index, value) in elements.withIndex() ){
            value?.let { array[index] = it }
        }
        return array
    }

    /**
     * javadoc newByteArray
     * @apiNote 字节数组
     *
     * @param size 数组大小
     * @param elements 数组初始化元素
     * @return byte[]
     * @author zhang yebai
     * @date 2021/7/19 10:38 AM
     **/
    @JvmStatic
    fun newByteArray(size: Int, vararg elements: Byte): ByteArray{
        val array = ByteArray(size)
        val length = elements.size
        for( idx in array.indices ){
            if(idx < length){
                array[idx] = elements[idx]
            }
        }
        return array
    }

    /**
     * javadoc newShortArray
     * @apiNote short数组
     *
     * @param size 数组大小
     * @param elements 数组初始化元素
     * @return short[]
     * @author zhang yebai
     * @date 2021/7/19 10:38 AM
     **/
    @JvmStatic
    fun newShortArray(size: Int, vararg elements: Short): ShortArray{
        val array = ShortArray(size)
        val length = elements.size
        for( idx in array.indices ){
            if(idx < length){
                array[idx] = elements[idx]
            }
        }
        return array
    }

    /**
     * javadoc newIntArray
     * @apiNote int数组
     *
     * @param size 数组大小
     * @param elements 数组初始化元素
     * @return int[]
     * @author zhang yebai
     * @date 2021/7/19 10:38 AM
     **/
    @JvmStatic
    fun newIntArray(size: Int, vararg elements: Int): IntArray{
        val array = IntArray(size)
        val length = elements.size
        for( idx in array.indices ){
            if(idx < length){
                array[idx] = elements[idx]
            }
        }
        return array
    }

    /**
     * javadoc newCharArray
     * @apiNote char数组
     *
     * @param size 数组大小
     * @param elements 数组初始化元素
     * @return char[]
     * @author zhang yebai
     * @date 2021/7/19 10:38 AM
     **/
    @JvmStatic
    fun newCharArray(size: Int, vararg elements: Char): CharArray{
        val array = CharArray(size)
        val length = elements.size
        for( idx in array.indices ){
            if(idx < length){
                array[idx] = elements[idx]
            }
        }
        return array
    }

    /**
     * javadoc newDoubleArray
     * @apiNote double数组
     *
     * @param size 数组大小
     * @param elements 数组初始化元素
     * @return double[]
     * @author zhang yebai
     * @date 2021/7/19 10:38 AM
     **/
    @JvmStatic
    fun newDoubleArray(size: Int, vararg elements: Double): DoubleArray{
        val array = DoubleArray(size)
        val length = elements.size
        for( idx in array.indices ){
            if(idx < length){
                array[idx] = elements[idx]
            }
        }
        return array
    }

    /**
     * javadoc newFloatArray
     * @apiNote float数组
     *
     * @param size 数组大小
     * @param elements 数组初始化元素
     * @return float[]
     * @author zhang yebai
     * @date 2021/7/19 10:38 AM
     **/
    @JvmStatic
    fun newFloatArray(size: Int, vararg elements: Float): FloatArray{
        val array = FloatArray(size)
        val length = elements.size
        for( idx in array.indices ){
            if(idx < length){
                array[idx] = elements[idx]
            }
        }
        return array
    }

    /**
     * javadoc newLongArray
     * @apiNote long数组
     *
     * @param size 数组大小
     * @param elements 数组初始化元素
     * @return long[]
     * @author zhang yebai
     * @date 2021/7/19 10:38 AM
     **/
    @JvmStatic
    fun newLongArray(size: Int, vararg elements: Long): LongArray{
        val array = LongArray(size)
        val length = elements.size
        for( idx in array.indices ){
            if(idx < length){
                array[idx] = elements[idx]
            }
        }
        return array
    }

    /**
     * javadoc newBooleanArray
     * @apiNote boolean数组
     *
     * @param size 数组大小
     * @param elements 数组初始化元素
     * @return boolean[]
     * @author zhang yebai
     * @date 2021/7/19 10:38 AM
     **/
    @JvmStatic
    fun newBooleanArray(size: Int, vararg elements: Boolean): BooleanArray{
        val array = BooleanArray(size)
        val length = elements.size
        for( idx in array.indices ){
            if(idx < length){
                array[idx] = elements[idx]
            }
        }
        return array
    }
}