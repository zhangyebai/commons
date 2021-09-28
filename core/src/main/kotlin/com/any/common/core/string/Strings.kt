package com.any.common.core.string

import com.any.common.core.reflection.Reflections


@Suppress(names = ["unused"])
object Strings {

    const val EMPTY = ""

    /**
     * javadoc remove
     * @apiNote 批量移除字符串中命中的串
     *
     * @param s 源字符串
     * @param r 被移除的字符串
     * @param rs 被移除的字符串列表
     * @return java.lang.String
     * @author zhang yebai
     * @date 2021/4/25 4:55 PM
     */
    @JvmStatic
    fun remove(s: String, r: String, vararg rs: String): String {
        return multipleRemove(false, s, r, *rs)
    }

    /**
     * javadoc removeIgnoreCaseSensitive
     * @apiNote 批量移除字符串中命中的串
     *
     * @param s 源字符串
     * @param r 被移除的字符串
     * @param rs 被移除的字符串列表
     * @return java.lang.String
     * @author zhang yebai
     * @date 2021/4/25 4:55 PM
     */
    @JvmStatic
    fun removeIgnoreCaseSensitive(s: String, r: String, vararg rs: String): String {
        return multipleRemove(true, s, r, *rs)
    }

    /**
     * javadoc multiRemove
     * @apiNote 批量移除字符串中命中的串
     *
     * @param ignoreCaseSensitive 忽略大消息
     * @param s 源字符串
     * @param r 被移除的字符串
     * @param rs 被移除的字符串列表
     * @return java.lang.String
     * @author zhang yebai
     * @date 2021/4/25 4:55 PM
     */
    private fun multipleRemove(ignoreCaseSensitive: Boolean, s: String, r: String, vararg rs: String): String {
        var source = s
        if (r.isNotEmpty()) {
            source = source.replace(r, "", ignoreCaseSensitive)
        }
        for (re in rs) {
            if (re.isNotEmpty()) {
                source = source.replace(re, "", ignoreCaseSensitive)
            }
        }
        return source
    }

    /**
     * javadoc format
     * @apiNote 格式化字符串
     *          例如:
     *              "我是{}, 我年龄{}岁, 我读过这些书: {}", "wxy", 20, ["java", "go", "python"]
     *              => "我是wxy, 我年龄20岁, 我读过这些书: [java, go, python]"
     *
     *              如果模板中无{}则直接返回template
     *              如果模板中有多余的{}, 而参数不足, 则多出的{}不作变动
     *              如果参数比模板中的{}多, 则多余的参数不作处理
     *              对于原生类型, 包装类型, 直接调用toString处理args
     *              对于集合类型, 调用集合类的contentToString()处理args
     *              对于数组类型, 调用Arrays.toString()处理args
     *              对于其他类型, 通过反射获取其属性及值, 并附加class name
     *
     *              虽然该方法某些情况下表现的很像json, 但它不是json, 不是json, 不是json
     *              不要依赖该方法去格式化json, 如果想得到的格式化参数是json, 请手动调用json相关方法现将参数转换成json字符串
     *
     * @param template 待格式化模板
     * @param args 格式化参数
     * @return String
     * @author zhang yebai
     * @date 2021/7/7 5:24 PM
     **/
    @JvmStatic
    fun format(template: String, vararg args: Any?): String {

        val selectors = template.split("{}").toList()
        if (selectors.isEmpty()) {
            return template
        }
        val length = args.size
        return selectors.mapIndexed { idx, it ->
            when (idx) {
                selectors.size - 1 -> it
                else -> it + if (idx < length) args[idx]?.let { Reflections.reflectionToString(it) } ?: "null" else "{}"
            }
        }.joinToString("") { it }
    }

    /**
     * javadoc split
     * @apiNote 拆分字符串
     *          当 src == null 返回空list
     *          当 src == '' 返回空list
     *          当 src != '' 且无法被分割是, 返回src自身 List.of(src)
     *
     *          本方法永不返回null
     *
     * @param src 待拆分字符串
     * @param separator 分割符字符串
     * @return List<String>
     * @author zhang yebai
     * @date 2021/7/8 4:51 PM
     **/
    @JvmStatic
    fun split(src: String?, separator: String): List<String> {
        if (src == null || src.isEmpty()) {
            return mutableListOf()
        }
        val splits = src.split(separator).toMutableList()
        return if (splits.isEmpty()) {
            mutableListOf(src)
        } else {
            splits
        }
    }

    /**
     * javadoc split
     * @apiNote 拆分字符串
     *          当 src == null 返回空list
     *          当 src == '' 返回空list
     *          当 src != '' 且无法被分割是, 返回src自身 List.of(src)
     *
     *          本方法永不返回null
     *
     * @param src 待拆分字符串
     * @param separator 分割符字符串
     * @return List<String>
     * @author zhang yebai
     * @date 2021/7/8 4:51 PM
     **/
    @JvmStatic
    fun split(src: String?, separator: Char): List<String> {
        if (src == null || src.isEmpty()) {
            return mutableListOf()
        }
        val splits = src.split(separator).toMutableList()
        return if (splits.isEmpty()) {
            mutableListOf(src)
        } else {
            splits
        }
    }

    /**
     * javadoc join
     * @apiNote 拼接字符串
     *
     * @param separator 拼接符
     * @param args 待拼接的字符串
     * @return String 拼接后的字符串
     * @author zhang yebai
     * @date 2021/7/9 3:15 PM
     **/
    @JvmStatic
    fun join(separator: String, vararg args: String?): String = args.filterNotNull().joinToString(separator) { it }

    /**
     * javadoc repeat
     * @apiNote repeat字符串
     *
     * @param str 源字符串
     * @param count 重复次数, 必须大于等于0, 否则异常
     * @return String
     * @author zhang yebai
     * @date 2021/7/13 7:47 PM
     **/
    @JvmStatic
    fun repeat(str: String, count: Int): String = str.repeat(count)

    /**
     * javadoc repeat
     * @apiNote repeat字符串
     *
     * @param ch 源字符
     * @param count 重复次数, 必须大于等于0, 否则异常
     * @return String
     * @author zhang yebai
     * @date 2021/7/13 7:47 PM
     **/
    @JvmStatic
    fun repeat(ch: Char, count: Int): String {
        val chars = CharArray(count)
        for (idx in 0 until count) {
            chars[idx] = ch
        }
        return String(chars)
    }
}