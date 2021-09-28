package common;


import com.any.common.core.domain.Tuple;
import com.any.common.core.file.Files;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestFiles {

    @Test
    void test() {
        final String o1 = "python.pdf";
        Assertions.assertEquals("pdf", Files.suffix(o1));
        Assertions.assertEquals("python", Files.prefix(o1));

        final String o2 = "java-pdf";
        Assertions.assertEquals("pdf", Files.suffix(o2, "-"));
        Assertions.assertEquals("java", Files.prefix(o2, "-"));
        final Tuple<String, String> t1 = Files.prefixAndSuffix(o1);
        Assertions.assertEquals("python", t1.getK());
        Assertions.assertEquals("pdf", t1.getV());

        final Tuple<String, String> t2 = Files.prefixAndSuffix(o2, "-");
        Assertions.assertEquals("java", t2.getK());
        Assertions.assertEquals("pdf", t2.getV());
    }
}
