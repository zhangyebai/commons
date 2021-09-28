package common;

import com.any.common.core.collection.ArrayUtil;
import com.any.common.core.collection.ListBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


class TestArrayUtil {

    @Test
    void test() {
        final List<Object> list = ListBuilder.arrayListBuilder().add(1).add("123").add('c').build();
        final Object[] l = new Object[]{1, "123", 'c'};
        Assertions.assertEquals('c', ArrayUtil.last(l));
        Assertions.assertEquals(list, ArrayUtil.toList(l));

        final Character c = ArrayUtil.safeGenericGet(l, 2);
        Assertions.assertEquals('c', c);

        final String[] ll = new String[]{"123", "4", "5", "567"};
        Assertions.assertEquals("123", ArrayUtil.first(ll));
        Assertions.assertEquals("567", ArrayUtil.last(ll));

        Assertions.assertTrue(ArrayUtil.beNotEmpty(ll));
    }

    @Test
    void testNewArray(){

        final Integer[] a = ArrayUtil.newArray(3, Integer.class);
        a[0] = 1;
        a[1] = 2;
        a[2] = 3;
        System.out.println(Arrays.toString(a));

        final Integer[] a1 = ArrayUtil.newArray(Integer.class, 1, 2, 3);
        Assertions.assertArrayEquals(a, a1);

        final Integer[] a2 = ArrayUtil.newArray(4, Integer.class);
        Assertions.assertEquals(4, a2.length);

        final String[] a3 = ArrayUtil.newArray(5, String.class, "a");
        Assertions.assertEquals(5, a3.length);
        System.out.println(Arrays.toString(a3));

        final int[] ai = ArrayUtil.newIntArray(5, 1, 2, 3);
        System.out.println(Arrays.toString(ai));
        final int[] ai1 = ArrayUtil.newIntArray(4);
    }
}
