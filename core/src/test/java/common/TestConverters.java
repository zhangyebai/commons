package common;

import com.any.common.core.convert.Converters;
import com.any.common.core.datetime.Dates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

class TestConverters {

    @Test
    void test() {
        final String o1 = null;
        final LocalDate t1 = Converters.to(o1, Dates::toDate);
        Assertions.assertNull(t1);

        final LocalDate ot1 = LocalDate.of(2021, 6, 21);
        final LocalDate t2 = Converters.<LocalDate, String>to(() -> Objects.nonNull(o1) ? o1 : "2021-06-21", Dates::toDate);
        Assertions.assertEquals(ot1, t2);

        final String o2 = null;
        final Optional<Integer> opt1 = Converters.opt(o2, Integer::valueOf);

        Assertions.assertFalse(opt1.isPresent());

        final Optional<Integer> opt2 = Converters.<Integer, String>opt(() -> Objects.nonNull(o2) ? o2 : "10", Integer::valueOf);
        Assertions.assertTrue(opt2.isPresent());
        Assertions.assertEquals(10, opt2.get());
    }
}
