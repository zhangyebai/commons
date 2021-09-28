package common;

import com.any.common.core.codec.Codecs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestCodec {

    @Test
    void test() {
        final String lowerCaseMd5 = Codecs.md5("public class Person{private int age; private int gender;}");

        final String upperCaseMd5 = Codecs.md5("public class Person{private int age; private int gender;}", false);

        Assertions.assertEquals(lowerCaseMd5.toUpperCase(), upperCaseMd5);
    }
}
