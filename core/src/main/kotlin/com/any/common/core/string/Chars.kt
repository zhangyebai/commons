package com.any.common.core.string

/**
 * javadoc Chars
 * <p>
 *     特殊字符表
 *     来自hu-tool
 *     <link>https://github.com/dromara/hutool</link>
 * <p>
 * @author zhang yebai
 * @date 2021/7/13 8:32 PM
 * @version 1.0.0
 **/
@Suppress("unused")
object Chars {

    /**
     * 字符常量：空格符 `' '`
     */
    const val SPACE = ' '

    /**
     * 字符常量：制表符 `'\t'`
     */
    const val TAB = '	'

    /**
     * 字符常量：点 `'.'`
     */
    const val DOT = '.'

    /**
     * 字符常量：斜杠 `'/'`
     */
    const val SLASH = '/'

    /**
     * 字符常量：反斜杠 `'\\'`
     */
    const val BACKSLASH = '\\'

    /**
     * 字符常量：回车符 `'\r'`
     */
    const val CR = '\r'

    /**
     * 字符常量：换行符 `'\n'`
     */
    const val LF = '\n'

    /**
     * 字符常量：减号（连接符） `'-'`
     */
    const val DASHED = '-'

    /**
     * 字符常量：下划线 `'_'`
     */
    const val UNDERLINE = '_'

    /**
     * 字符常量：逗号 `','`
     */
    const val COMMA = ','

    /**
     * 字符常量：花括号（左） `'{'`
     */
    const val DELIM_START = '{'

    /**
     * 字符常量：花括号（右） `'}'`
     */
    const val DELIM_END = '}'

    /**
     * 字符常量：中括号（左） `'['`
     */
    const val BRACKET_START = '['

    /**
     * 字符常量：中括号（右） `']'`
     */
    const val BRACKET_END = ']'

    /**
     * 字符常量：双引号 `'"'`
     */
    const val DOUBLE_QUOTES = '"'

    /**
     * 字符常量：单引号 `'\''`
     */
    const val SINGLE_QUOTE = '\''

    /**
     * 字符常量：与 `'&'`
     */
    const val AMP = '&'

    /**
     * 字符常量：冒号 `':'`
     */
    const val COLON = ':'

    /**
     * 字符常量：艾特 `'@'`
     */
    const val AT = '@'

    private const val A = 'A'

    private const val Z = 'Z'

    private const val a = 'a'

    private const val z = 'z'

    /**
     * javadoc isChineseLetter
     * @apiNote 判断字符是否是中文字符
     *
     * @param ch 字符
     * @return Boolean
     * @author zhang yebai
     * @date 2021/7/19 4:26 PM
     **/
    @JvmStatic
    fun isChineseLetter(ch: Char): Boolean = (ch.code in 0X4E00..0X9FA5)

    /**
     * javadoc isEnglishLetter
     * @apiNote 判断字符是否是英文字符
     *
     * @param ch 字符
     * @return Boolean
     * @author zhang yebai
     * @date 2021/7/19 4:26 PM
     **/
    @JvmStatic
    fun isEnglishLetter(ch: Char): Boolean = (ch.code in 0x0041..0x005A) || (ch.code in 0x0061..0x007A)

    /**
     * javadoc isUpperCaseLetter
     * @apiNote 是否是大写字母
     *
     * @param ch 目标字符
     * @return Boolean
     * @author zhang yebai
     * @date 2021/7/19 4:47 PM
     **/
    @JvmStatic
    fun isUpperCaseLetter(ch: Char): Boolean = ch.code in 0x0041..0x005A

    /**
     * javadoc isLowCaseLetter
     * @apiNote 是否是小写字母
     *
     * @param ch 目标字符
     * @return Boolean
     * @author zhang yebai
     * @date 2021/7/19 4:48 PM
     **/
    @JvmStatic
    fun isLowCaseLetter(ch: Char): Boolean = ch.code in 0x0061..0x007A

    /**
     * javadoc isDigitLetter
     * @apiNote 判断字符是否是数字字符
     *
     * @param ch 字符
     * @return Boolean
     * @author zhang yebai
     * @date 2021/7/19 4:27 PM
     **/
    @JvmStatic
    fun isDigitLetter(ch: Char): Boolean = ch.isDigit()

    /**
     * javadoc toUpperCase
     * @apiNote 小写转大写, 不在范围内原样返回
     *
     * @param ch 字符
     * @return Char
     * @author zhang yebai
     * @date 2021/7/19 4:46 PM
     **/
    @JvmStatic
    fun toUpperCase(ch: Char): Char = if (isLowCaseLetter(ch)) Char(ch.code - 32) else ch

    /**
     * javadoc toLowCase
     * @apiNote 大写转小写, 不在范围内原样返回
     *
     * @param ch 字符
     * @return Char
     * @author zhang yebai
     * @date 2021/7/19 4:46 PM
     **/
    @JvmStatic
    fun toLowCase(ch: Char): Char = if (isUpperCaseLetter(ch)) Char(ch.code + 32) else ch

    /**
     * javadoc toFullWidth
     * @apiNote 半角字符转全角字符
     *
     * @param ch 半角字符
     * @return Char
     * @author zhang yebai
     * @date 2021/7/19 4:35 PM
     **/
    @JvmStatic
    fun toFullWidth(ch: Char): Char = if (ch.code in 33..126) Char(ch.code + 65248) else ch

    /**
     * javadoc toHalfWidth
     * @apiNote 全角 to 半角
     *
     * @param ch 全角字符
     * @return Char
     * @author zhang yebai
     * @date 2021/7/19 4:37 PM
     **/
    @JvmStatic
    fun toHalfWidth(ch: Char): Char = if (ch.code in 65281..65374) Char(ch.code - 65248) else ch
}