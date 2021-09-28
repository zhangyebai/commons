package common;

import com.any.common.core.param.Require;
import com.any.common.core.reflection.Reflections;
import common.domain.Book;
import common.domain.JavaBook;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class TestReflections {

    @Test
    void test() {

        Method[] methods = Reflections.listClassMethods(Book.class);

        System.out.println(Arrays.toString(methods));

        System.out.println(Arrays.toString(Reflections.listClassFields(JavaBook.class)));
        System.out.println(Arrays.toString(JavaBook.class.getDeclaredFields()));
        System.out.println(new JavaBook());

        final JavaBook javabook = new JavaBook().setAuthor("au").setFuture("fu").setPrice(1).setVersion("ve");
        System.out.println(Reflections.reflectionToString(javabook));

        final Integer price = Reflections.getFieldValue(javabook, "price");
        System.out.println("price = " + price);
        Reflections.setFieldValue(javabook, "price", 100);
        final Integer price1 = Reflections.getFieldValue(javabook, "price");
        System.out.println("price = " + price1);

        final Method method = Reflections.findClassMethod(JavaBook.class, "f1");
        System.out.println("method = " + method);
        if (Objects.nonNull(method)) {
            final Integer result = Reflections.invoke(javabook, method);
            System.out.println("result = " + result);
        }

        final Method f2 = Reflections.findClassMethod(JavaBook.class, "f2");
        if (Objects.nonNull(f2)) {
            Object obj = Reflections.invoke(javabook, f2);
            System.out.println("obj = " + obj);
        }

//        final Field field = Reflections.findClassField(JavaBook.class, "author");
//        if(Objects.nonNull(field)){
//            final Require require =  Reflections.findFieldAnnotation(field, Require.class);
//            System.out.println("require = " + require);
//        }

        System.out.println(Arrays.toString(Reflections.listAnnotatedClassFields(JavaBook.class, Require.class)));

        final List<String> messages = new ArrayList<>();
        messages.add("abc");
        messages.add("def");
        messages.add("ghi");
        System.out.println(messages);
        System.out.println(Reflections.reflectionToString(messages));

        String[] arr = new String[2];
        arr[0] = "0";
        arr[1] = "1";

        System.out.println(Reflections.reflectionToString(arr));

        System.out.println(Reflections.reflectionToString('a'));
    }
}
