package com.any.common.poi.excel;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class TestExcels {

    @Test
    void test() {
        final Book book = new Book().setSales(1000).setPrice(78.09).setAuthor("gosling").setCreateTime("202-07-05 12:30:00");

        final List<Book> contents = new ArrayList<>();
        contents.add(book);
        final byte[] bytes = Excels.writeBytes(contents, Book.class);
        final List<Map<String, String>> maps = Excels.read(bytes, true, false);
        System.out.println(maps);

        final byte[] bytex = Excels.writeBytes(contents, Book.class, true);
        final List<Map<String, String>> mapx = Excels.read(bytex, true, true);
        System.out.println(mapx);
    }
}
