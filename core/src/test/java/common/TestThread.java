package common;

import com.any.common.core.thread.Threads;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

class TestThread {

    @Test
    void test() throws InterruptedException, ExecutionException {
        final Thread thread = Threads.thread(
                () -> {
                    final int sum = IntStream.range(0, 10).sum();
                    Assertions.assertEquals(45, sum);
                }
        );
        thread.start();
        thread.join();

        final Future<Integer> future = Executors.newSingleThreadExecutor().submit(Threads.callable(() -> 1));
        Assertions.assertEquals(1, future.get());
    }
}
