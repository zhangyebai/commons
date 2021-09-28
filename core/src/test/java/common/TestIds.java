package common;

import com.any.common.core.id.Ids;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestIds {

    @Test
    void test() {
        System.out.println(Ids.balancedId());
        Assertions.assertNotNull(Ids.id());
        Assertions.assertNotNull(Ids.balancedId());
    }
}
