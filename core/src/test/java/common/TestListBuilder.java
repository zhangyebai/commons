package common;

import com.any.common.core.collection.ListBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TestListBuilder {

    @Test
    void test() {
        final List<String> o = new ArrayList<>();
        o.add("hello");
        o.add("world");
        o.add("bro");

        final List<String> t = ListBuilder.<String>arrayListBuilder().add("hello").add("world").add("bro").build();
        Assertions.assertIterableEquals(o, t);
        t.add("no");
        o.add("no");
        Assertions.assertIterableEquals(o, t);
        o.remove("hello");
        t.remove("hello");
        Assertions.assertIterableEquals(o, t);
    }
}
