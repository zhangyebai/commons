package common;

import com.any.common.core.collection.ListBuilder;
import com.any.common.core.domain.AppRestResult;
import com.any.common.core.domain.AppResult;
import com.any.common.core.domain.Meta;
import com.any.common.core.domain.Pagination;
import org.junit.jupiter.api.Test;

import java.util.List;

class TestDomain {

    @Test
    void test() {
        final Meta<String, String> meta = new Meta<>();
        Pagination<String> p = new Pagination<>();
        System.out.println(meta);
    }

    @Test
    void testPagination() {
        final List<Integer> l1 = ListBuilder.<Integer>arrayListBuilder().add(1).add(2).add(3).build();
        final Pagination<Integer> p1 = Pagination.of(1, 10, 1, 3, l1);
        final Pagination<String> p2 = p1.to((e) -> String.valueOf(e * e));
        System.out.println(p2);
    }

    @Test
    void testResult(){
        AppResult result = new AppResult(1, "success");
        AppRestResult<String> appRestResult = new AppRestResult<>(result, new String("my name"));
        System.out.println(appRestResult);
    }
}
