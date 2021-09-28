package common;

import com.any.common.core.event.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class TestEvent {

    @Test
    void test(){
        final IContext<String> context = new EventContext<>();
        context.appendListener(System.out::println);

        context.publish("hello");
    }

    @Test
    void testAsync() {
        final AsyncEventContext<String> context = AsyncEventContext.of(new ThreadPoolExecutor(10, 10, 10, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1000)));
        context.appendListener((String t) -> Assertions.assertEquals("hello", t));
        context.appendListener((String t) -> Assertions.assertEquals("hello", t));
        context.appendListener((String t) -> Assertions.assertEquals("hello", t));
        context.appendListener((String t) -> Assertions.assertEquals("hello", t));
        context.appendListener((String t) -> Assertions.assertEquals("hello", t));
        context.appendListener((String t) -> System.out.println(Thread.currentThread().getId() + "-" + t));
        context.appendListener((String t) -> System.out.println(Thread.currentThread().getId() + "-" + t));
        context.publish("hello");
    }

    @Test
    void testPriority() {
        final IPriorityContext<String> context = new PriorityContext<>();
        context.appendListener((String t) -> {
            System.out.println("i am 4: " + t);
            Assertions.assertEquals("priority", t);
        }, 4);
        context.appendListener((String t) -> System.out.println("i am 1: " + t), 1);
        final IEventListener<String> listener = (String t) -> System.out.println("i am 3: " + t);
        context.appendListener(listener, 3);
        context.appendListener((String t) -> System.out.println("i am 2: " + t), 2);

        context.publish("priority");

        context.removeListener(listener);
        context.publish("priority");
    }
}
