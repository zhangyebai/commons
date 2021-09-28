package common;

import com.any.common.core.collection.MapBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class TestMapBuilder {

    @Test
    void test() {
        final Map<String, Object> o = new HashMap<>();
        o.put("python", "Guido van Rossum");
        o.put("java", "gosling");

        final Map<String, Object> t = MapBuilder.<String, Object>hashMapBuilder().put("c++", "Bjarne Stroustup").put(o).build();
        o.put("c++", "Bjarne Stroustup");
        //(o1, o2) -> o1.getKey().compareTo(o2.getKey())
        //Assertions.assertIterableEquals(o.entrySet().stream().sorted().collect(Collectors.toList()), t.entrySet().stream().sorted().collect(Collectors.toList()));
        Assertions.assertEquals(o, t);
    }
}
