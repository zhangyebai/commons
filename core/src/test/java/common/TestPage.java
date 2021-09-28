package common;

import com.any.common.core.domain.Pagination;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TestPage {

    @Test
    void test() {
        final Pagination<Object> pagination = Pagination.of(1, 10, 10, 100, new ArrayList<>());
        System.out.println(pagination);
        System.out.println(Pagination.empty());
        System.out.println(Pagination.page(null));
        System.out.println(Pagination.size(-1));
    }
}
