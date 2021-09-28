package common;

import com.any.common.core.exception.AnyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestException {

    @Test
    void test(){
        Assertions.assertThrows(AnyException.class, () -> {throw new AnyException("exception");});
    }
}
