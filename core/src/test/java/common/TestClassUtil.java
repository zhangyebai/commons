package common;

import com.any.common.core.reflection.ClassUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class TestClassUtil {

    @Test
    void testClassUtils(){
        Assertions.assertTrue(ClassUtil.primitive(void.class));
        Assertions.assertTrue(ClassUtil.primitive(short.class));
        Assertions.assertTrue(ClassUtil.primitive(byte.class));
        Assertions.assertTrue(ClassUtil.primitive(int.class));
        Assertions.assertTrue(ClassUtil.primitive(float.class));
        Assertions.assertTrue(ClassUtil.primitive(double.class));
        Assertions.assertTrue(ClassUtil.primitive(long.class));
        Assertions.assertTrue(ClassUtil.primitive(char.class));
        Assertions.assertTrue(ClassUtil.primitive(boolean.class));


        Assertions.assertTrue(ClassUtil.primitiveWrapper(Void.class));
        Assertions.assertTrue(ClassUtil.primitiveWrapper(Short.class));
        Assertions.assertTrue(ClassUtil.primitiveWrapper(Byte.class));
        Assertions.assertTrue(ClassUtil.primitiveWrapper(Character.class));
        Assertions.assertTrue(ClassUtil.primitiveWrapper(Float.class));
        Assertions.assertTrue(ClassUtil.primitiveWrapper(Double.class));
        Assertions.assertTrue(ClassUtil.primitiveWrapper(Integer.class));
        Assertions.assertTrue(ClassUtil.primitiveWrapper(Long.class));
        Assertions.assertTrue(ClassUtil.primitiveWrapper(Boolean.class));

        Assertions.assertTrue(ClassUtil.primitiveOrPrimitiveWrapper(int.class));
        Assertions.assertTrue(ClassUtil.primitiveOrPrimitiveWrapper(Integer.class));

        Assertions.assertTrue(ClassUtil.primitiveArray(int[].class));
        Assertions.assertTrue(ClassUtil.primitiveWrapperArray(Integer[].class));
    }
}
