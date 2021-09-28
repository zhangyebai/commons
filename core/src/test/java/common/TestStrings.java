package common;

import com.any.common.core.collection.ListBuilder;
import com.any.common.core.string.Strings;
import common.domain.JavaBook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TestStrings {

    @Test
    void test() {
        final String source = "12345aabcdeef";
        final String d1 = Strings.remove(source, "1", "d", "e");
        Assertions.assertEquals("2345aabcf", d1);
        final String d2 = Strings.removeIgnoreCaseSensitive(source, "1", "D", "E");
        Assertions.assertEquals(d1, d2);

        Assertions.assertEquals("", Strings.EMPTY);
    }

    @Test
    void testFormat() {
        final String s1 = Strings.format("{}, {}-{}", "a", "b", "c");
        Assertions.assertEquals("a, b-c", s1);
        final List<Integer> l = ListBuilder.<Integer>arrayListBuilder().add(1).add(2).add(3).build();
        System.out.println(Strings.format("{}, {}-{}: {}!!!", "a", l, 129, new JavaBook()));
    }

    @Test
    void testSplit() {
        System.out.println(Strings.split(" ", '-'));
        System.out.println(Strings.split("1-2-33", "-"));
        System.out.println(Strings.split(null, "-"));
    }

    @Test
    void testJoin() {
        Assertions.assertEquals("1-2-3", Strings.join("-", "1", "2", null, "3", null));
        Assertions.assertEquals("", Strings.join(""));
    }

    @Test
    void testRepeat() {
        final String r1 = Strings.repeat('a', 5);
        Assertions.assertEquals("aaaaa", r1);

        final String r2 = Strings.repeat("12", 4);
        Assertions.assertEquals(Strings.format("{}{}{}{}", 12, 12, 12, 12), r2);

        Assertions.assertEquals("", Strings.repeat("", 0));
    }
}
