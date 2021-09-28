package com.any.common.poi.excel

import com.any.common.poi.excel.annos.Column
import org.apache.poi.hssf.usermodel.HSSFRichTextString
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFRichTextString
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.lang.reflect.Field
import java.text.SimpleDateFormat
import java.util.*
import java.util.stream.Collectors


/**
 * javadoc Excels
 * <p>
 *     excel utils
 * <p>
 * @author zhang yebai
 * @date 2021/7/2 4:11 PM
 * @version 1.0.0
 **/
@Suppress(names = ["unused"])
object Excels {

    private val log: Logger = LoggerFactory.getLogger(Excels.javaClass)

    /**
     * javadoc read
     * @apiNote 读取excel
     *          注1: 如果excel有标题, 则返回的每一个map的key都为对应的标题名
     *               如果excel 无标题, 则返回的每一个map的key都有列对应的索引值
     *          注2: 日期-时间类型的列, 均以 yyyy-MM-dd HH:mm:ss 形式返回字符串
     *
     *          注3: 所有无值 空值的列, 无论是何种数据类型, 均返回空字符串 ""
     *          注4: bool类型 返回字符串形式的 "false" "true"
     *
     * @param byteArray excel 字节数组
     * @param xlsx excel 是否是xlsx类型
     * @param hasTitle excel 是否有标题行
     * @return List<Map<String, String>> excel中的每一行
     * @author zhang yebai
     * @date 2021/7/2 4:52 PM
     **/
    @JvmStatic
    @JvmOverloads
    fun read(byteArray: ByteArray, hasTitle: Boolean = false, xlsx: Boolean = false): List<Map<String, String>> {
        return if (xlsx) {
            readXlsx(byteArray, hasTitle)
        } else {
            readXls(byteArray, hasTitle)
        }
    }

    /**
     * javadoc readHeaders
     * @apiNote 读取excel表头
     *
     * @param byteArray excel 内容
     * @param xlsx 是否是xlsx类型
     * @return Map<String, String> 表头kv
     * @author zhang yebai
     * @date 2021/7/5 11:54 AM
     **/
    @JvmStatic
    @JvmOverloads
    fun readHeaders(byteArray: ByteArray, xlsx: Boolean = false): Map<String, String> {
        if (xlsx) {
            try {
                ByteArrayInputStream(byteArray).use { ins ->
                    XSSFWorkbook(ins).use { book ->
                        val sheet = book.getSheetAt(0)
                        val titleRow = sheet.getRow(0)
                        val titles = HashMap<String, String>()
                        readTitles(titles, titleRow)
                        return titles
                    }
                }
            } catch (ex: Exception) {
                throw RuntimeException(ex)
            }
        } else {
            try {
                ByteArrayInputStream(byteArray).use { ins ->
                    HSSFWorkbook(ins).use { book ->
                        val sheet = book.getSheetAt(0)
                        val titleRow = sheet.getRow(0)
                        val titles = HashMap<String, String>()
                        readTitles(titles, titleRow)
                        return titles
                    }
                }
            } catch (ex: Exception) {
                throw RuntimeException(ex)
            }
        }
    }

    private fun readXlsx(byteArray: ByteArray, hasTitle: Boolean): List<Map<String, String>> {
        try {
            ByteArrayInputStream(byteArray).use { ins ->
                XSSFWorkbook(ins).use { book ->
                    return read(
                        book, hasTitle
                    )
                }
            }
        } catch (ex: Exception) {
            throw RuntimeException(ex)
        }
    }


    private fun readXls(byteArray: ByteArray, hasTitle: Boolean): List<Map<String, String>> {
        try {
            ByteArrayInputStream(byteArray).use { ins ->
                HSSFWorkbook(ins).use { book ->
                    return read(
                        book, hasTitle
                    )
                }
            }
        } catch (ex: Exception) {
            throw RuntimeException(ex)
        }
    }

    private fun read(book: Workbook, hasTitle: Boolean = true): List<Map<String, String>> {
        val rows = mutableListOf<Map<String, String>>()
        val titles = HashMap<String, String>()
        val sheet: Sheet = book.getSheetAt(0)
        for (idx in 0 until sheet.physicalNumberOfRows) {
            val row = sheet.getRow(idx)
            if (idx == 0 && hasTitle) {
                readTitles(titles, row)
            } else {
                rows.add(readRow(hasTitle, titles, row))
            }
        }
        return rows
    }

    private fun readRow(hasTitle: Boolean, titles: Map<String, String>, row: Row): Map<String, String> {
        val content = mutableMapOf<String, String>()
        val len: Int = row.physicalNumberOfCells
        for (idx in 0 until len) {
            val cell: Cell? = row.getCell(idx)
            cell?.let {
                val value = readCellValue(cell)
                if (hasTitle) {
                    val key: String = titles[idx.toString()] ?: ""
                    content[key] = value
                } else {
                    content[idx.toString()] = value
                }
            }
        }
        return content
    }

    private fun readTitles(titles: MutableMap<String, String>, title: Row) {
        val len: Int = title.physicalNumberOfCells
        for (idx in 0 until len) {
            val cell: Cell = title.getCell(idx)
            if (Objects.nonNull(cell)) {
                val value = cell.stringCellValue
                if (Objects.nonNull(value)) {
                    titles[idx.toString()] = value.trim { it <= ' ' }.replace("\n", "")
                }
            }
        }
    }


    private fun readCellValue(cell: Cell): String {
        if (Objects.isNull(cell)) {
            return ""
        }
        return when (cell.cellType) {
            CellType.NUMERIC -> if (DateUtil.isCellDateFormatted(cell)) {
                val dateTime = cell.dateCellValue
                if (Objects.nonNull(dateTime)) {
                    date(dateTime)
                } else {
                    ""
                }
            } else {
                cell.numericCellValue.toString()
            }
            CellType.STRING, CellType.BLANK -> cell.stringCellValue ?: ""
            CellType.FORMULA -> cell.cellFormula ?: ""
            CellType.BOOLEAN -> cell.booleanCellValue.toString()
            else -> ""
        }
    }

    private fun date(date: Date?): String = date?.let { SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(it) } ?: ""


    /**************************************************************************************************************/

    /**
     * javadoc writeBytes
     * @apiNote 将内容列表写入excel, 并输出字节数组
     *
     * @param contents 写入的内容列表
     * @param clazz 目标obj的java class
     * @param xlsx 是否写入xlsx类型
     * @param hasHeader 是否写入header
     * @param title 写入的title, 如果是null则不写入title
     * @return ByteArray byte[]
     * @author zhang yebai
     * @date 2021/7/5 10:42 AM
     **/
    @JvmStatic
    @JvmOverloads
    fun writeBytes(
        contents: List<Any>,
        clazz: Class<*>,
        xlsx: Boolean = false,
        hasHeader: Boolean = true,
        title: String? = null
    ): ByteArray {
        write(contents, clazz, xlsx, hasHeader, title).use { book ->
            val output = ByteArrayOutputStream()
            book.write(output)
            val bytes = output.toByteArray()
            output.close()
            return bytes
        }
    }

    /**
     * javadoc write
     * @apiNote 将内容列表写入excel
     *
     * @param contents 写入的内容列表
     * @param clazz 目标obj的java class
     * @param xlsx 是否写入xlsx类型
     * @param hasHeader 是否写入header
     * @param title 写入的title, 如果是null则不写入title
     * @return Workbook
     * @author zhang yebai
     * @date 2021/7/5 10:42 AM
     **/
    @JvmStatic
    @JvmOverloads
    fun write(
        contents: List<Any>,
        clazz: Class<*>,
        xlsx: Boolean = false,
        hasHeader: Boolean = true,
        title: String? = null
    ): Workbook {
        return if (xlsx) {
            writeXlsx(contents, clazz, title, hasHeader)
        } else {
            writeXls(contents, clazz, title, hasHeader)
        }
    }

    private fun writeXlsx(
        contents: List<Any>,
        clazz: Class<*>,
        title: String? = null,
        hasHeader: Boolean = true
    ): XSSFWorkbook {
        val book = XSSFWorkbook()
        writeExcel(contents, clazz, true, title, hasHeader, book)
        return book
    }

    private fun writeXls(
        contents: List<Any>,
        clazz: Class<*>,
        title: String?,
        hasHeader: Boolean = true
    ): HSSFWorkbook {
        val book = HSSFWorkbook()
        writeExcel(contents, clazz, false, title, hasHeader, book)
        return book
    }

    private fun writeExcel(
        contents: List<Any>,
        clazz: Class<*>,
        xlsx: Boolean,
        title: String?,
        hasHeader: Boolean = true,
        book: Workbook
    ) {
        var idx = 0
        val sheet: Sheet = book.createSheet()
        val headers = headers(clazz)
        if (title != null) {
            writeTitle(book, sheet, idx, title, headers.size)
            ++idx
        }
        if (hasHeader) {
            writeHeader(book, sheet, idx, headers, xlsx)
            ++idx
        }
        for (content in contents) {
            writeRow(sheet, idx, content)
            ++idx
        }
    }

    private fun headers(clazz: Class<*>): List<ExcelHeader> {
        val annotatedHeaders: MutableList<ExcelHeader> = ArrayList<ExcelHeader>()
        val unannotatedHeaders: MutableList<ExcelHeader> = ArrayList<ExcelHeader>()
        val fields: Array<Field> = clazz.declaredFields
        for (field in fields) {
            field.isAccessible = true
            if (field.isAnnotationPresent(Column::class.java)) {
                val annotation: Column = field.getAnnotation(Column::class.java)
                val header = ExcelHeader(annotation.name, annotation.order)
                if (header.order > 0) {
                    annotatedHeaders.add(header)
                } else {
                    unannotatedHeaders.add(header)
                }
            } else {
                unannotatedHeaders.add(
                    ExcelHeader(field.name, 0)
                )
            }
        }
        if (unannotatedHeaders.isNotEmpty()) {
            annotatedHeaders.addAll(unannotatedHeaders)
        }
        return annotatedHeaders
    }

    /**
     * javadoc 在表头之前写入标题
     * @apiNote
     *
     * @param book Workbook
     * @param sheet Sheet
     * @param idx 标题所在的行位置
     * @param title 标题内容
     * @param length 标题所占的列长度, 默认充满整个列
     * @author zhang yebai
     * @date 2021/7/5 9:31 AM
     **/
    private fun writeTitle(book: Workbook, sheet: Sheet, idx: Int, title: String, length: Int) {
        val titleRow: Row = sheet.createRow(idx)
        titleRow.heightInPoints = 30f
        val cell = titleRow.createCell(0)
        val style: CellStyle = book.createCellStyle()
        style.alignment = HorizontalAlignment.CENTER
        style.verticalAlignment = VerticalAlignment.CENTER
        sheet.addMergedRegion(CellRangeAddress(0, 0, 0, length - 1))
        val font: Font = book.createFont()
        font.bold = true
        font.fontHeightInPoints = 16.toShort()
        cell.setCellValue(title)
        cell.cellStyle = style
    }

    private fun writeHeader(book: Workbook, sheet: Sheet, idx: Int, headers: List<ExcelHeader>, xlsx: Boolean) {
        val style: CellStyle = book.createCellStyle()
        style.borderBottom = BorderStyle.THIN
        style.borderLeft = BorderStyle.THIN
        style.borderRight = BorderStyle.THIN
        style.borderTop = BorderStyle.THIN
        style.alignment = HorizontalAlignment.CENTER
        val row: Row = sheet.createRow(idx)
        for (index in headers.indices) {
            val cell = row.createCell(index)
            cell.cellStyle = style
            val text = if (xlsx) {
                XSSFRichTextString(headers[index].name)
            } else HSSFRichTextString(headers[index].name)
            cell.setCellValue(text)
        }
    }

    private fun writeRow(sheet: Sheet, idx: Int, content: Any) {
        val row: Row = sheet.createRow(idx)
        val clazz: Class<*> = content.javaClass
        val fields = clazz.declaredFields
        val unAnnotatedContents: MutableList<String> = ArrayList()
        val annotatedContents: MutableList<ColumnValue> = ArrayList()
        for (field in fields) {
            field.isAccessible = true
            val value = if (Objects.nonNull(field[content])) field[content].toString() else ""
            if (field.isAnnotationPresent(Column::class.java)) {
                val annotation: Column = field.getAnnotation(Column::class.java)
                if (0 != annotation.order) {
                    annotatedContents.add(ColumnValue(value, annotation.order))
                } else {
                    unAnnotatedContents.add(value)
                }
            } else {
                unAnnotatedContents.add(value)
            }
        }
        var index = 0
        val first = annotatedContents.stream()
            .sorted(Comparator.comparingInt(ColumnValue::order))
            .map<String>(ColumnValue::value)
            .collect(Collectors.toList())
        for (c in first) {
            val cell = row.createCell(index)
            cell.setCellValue(c)
            ++index
        }

        for (c in unAnnotatedContents) {
            val cell = row.createCell(index)
            cell.setCellValue(c)
            ++index
        }
    }

}