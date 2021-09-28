package common;

import com.any.common.core.enums.Enums;
import common.domain.MyEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class TestEnum {

    @Test
    void test() {
        final Optional<MyEnum> opt = Enums.find("1", MyEnum.class);
        Assertions.assertTrue(opt.isPresent());

        Assertions.assertEquals(MyEnum.A, opt.get());

        final List<MyEnum> l1 = new ArrayList<>();
        l1.add(MyEnum.B);
        Assertions.assertEquals(l1, Enums.listEnums(MyEnum.class, "3"));
    }
}
