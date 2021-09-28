package common;

import com.any.common.core.currency.Cny;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

class TestCurrency {

    @Test
    void test() {
        final long cents = 10018L;
        final String t1 = Cny.cent2unitString(cents);
        Assertions.assertEquals("100.18", t1);

        final String t2 = Cny.cent2unitString(cents, new DecimalFormat("#.#"));
        Assertions.assertEquals("100.2", t2);

        final String unit = null;
        Assertions.assertEquals(0, Cny.unit2cents(unit));
    }
}
