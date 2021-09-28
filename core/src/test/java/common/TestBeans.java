package common;

import com.any.common.core.bean.Beans;
import common.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class TestBeans {

    @Test
    void test() {
        final Book b1 = new Book().setPrice(1289).setAuthor("Gosling");
        final Map<String, Object> map = Beans.bean2map(b1);
        final Book b2 = Beans.map2bean(map, Book.class);
        Assertions.assertEquals(b1, b2);
    }
}
