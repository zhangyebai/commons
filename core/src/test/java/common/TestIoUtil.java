package common;

import com.any.common.core.io.IoUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

class TestIoUtil {

    @Test
    void test() throws IOException {
        try (final InputStream input = new ByteArrayInputStream("hello".getBytes(StandardCharsets.UTF_8));
             final ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            IoUtil.write(input, output);
            final byte[] bytes = output.toByteArray();
            final String writes = new String(bytes, StandardCharsets.UTF_8);
            Assertions.assertEquals("hello", writes);
        }
    }
}
