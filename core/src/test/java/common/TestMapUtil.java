package common;

import com.any.common.core.collection.MapBuilder;
import com.any.common.core.collection.MapUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class TestMapUtil {

    @Test
    void test() {
        final Map<String, Object> map = MapBuilder.<String, Object>hashMapBuilder().put("1", 1).put("123", "123").build();
        final int count = MapUtil.forceGet(map, "1");
        final String name = MapUtil.get(map, "123");
        final char prefix = MapUtil.get(map, "456", 'a');
        Assertions.assertEquals(1, count);
        Assertions.assertEquals("123", name);
        Assertions.assertEquals('a', prefix);
    }
}
