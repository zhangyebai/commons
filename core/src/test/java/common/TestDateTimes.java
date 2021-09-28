package common;


import com.any.common.core.datetime.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class TestDateTimes {

    @Test
    void test() {

        final LocalDateTime lt1 = LocalDateTime.of(2021, 6, 21, 11, 30, 45);

        Assertions.assertEquals("2021-06-21 11:30:45", DateTimes.datetime(lt1));

        Assertions.assertEquals("2021-06-21 11:30:45", DateTimes.datetime(lt1, "yyyy-MM-dd HH:mm:ss"));

        Assertions.assertEquals("2021-06-21 11:30:45", DateTimes.datetime(lt1, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        Assertions.assertEquals(lt1, DateTimes.toDatetime("2021-06-21 11:30:45"));
        Assertions.assertEquals(lt1, DateTimes.toDatetime("20210621 11:30:45", "yyyyMMdd HH:mm:ss"));
        Assertions.assertEquals(lt1, DateTimes.toDatetime("2021-06-21 113045", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmmss")));
    }

    @Test
    void testDateTimeBetween() {
        final LocalDateTime l1 = LocalDateTime.of(2021, 6, 25, 0, 0, 0);
        final LocalDateTime l2 = LocalDateTime.of(2021, 6, 29, 12, 30, 0);
        Assertions.assertEquals(4, DateTimes.between(l1, l2, ClockUnit.DAYS));
        Assertions.assertEquals(9, DateTimes.between(l1, l2, ClockUnit.HALF_DAYS));
        Assertions.assertEquals(108, DateTimes.between(l1, l2, ClockUnit.HOURS));

        final LocalDate d1 = LocalDate.of(2020, 4, 21);
        final LocalDate d2 = LocalDate.of(2021, 10, 8);
        Assertions.assertEquals(1, Dates.between(d1, d2, ClockUnit.YEARS));
        Assertions.assertEquals(17, Dates.between(d1, d2, ClockUnit.MONTHS));
    }

    @Test
    void testDtPattern() {
        System.out.println(Times.time(LocalTime.now(), DataTimePattern.TIME_CHINA_STANDARD_COMPATIBLE));
        //System.out.println(DateTimes.datetime(LocalDateTime.now(), DataTimePattern.DT_HTTP));
    }

    @Test
    void testDateAdd(){
        final LocalDate date = LocalDate.of(2021, 1, 31);
        final LocalDate d1 = date.plusMonths(1);
        System.out.println(d1);
    }
}
