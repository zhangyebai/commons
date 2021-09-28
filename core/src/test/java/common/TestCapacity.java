package common;

import com.any.common.core.capacity.IoCapacityUtil;
import com.any.common.core.number.Numbers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.RoundingMode;

class TestCapacity {

    @Test
    void test() {
        final String kb1 = Numbers.string(IoCapacityUtil.b2kb(111024L));
        final String kb2 = IoCapacityUtil.b2kbs(111024L);
        Assertions.assertEquals(kb1, kb2);
        Assertions.assertEquals("108.42", kb2);

        Assertions.assertEquals("108.422", IoCapacityUtil.b2kbs(111024L, 3, RoundingMode.HALF_UP));

        Assertions.assertEquals("108.4219", IoCapacityUtil.b2kbs(111024L, 4, RoundingMode.HALF_UP));
    }
}
