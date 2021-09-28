package com.any.common.jpa;

import com.any.common.jpa.JpaUtil;
import com.any.common.jpa.domain.JpaOrder;
import com.any.common.jpa.domain.JpaPage;
import com.any.common.jpa.domain.enums.JpaOrderModel;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

class TestJpaSort {

    @Test
    void test(){
        final Sort sort = JpaUtil.sort(new JpaOrder("id", JpaOrderModel.ASC), new JpaOrder("ic", JpaOrderModel.DESC));
        System.out.println(sort);

        final JpaPage page = JpaPage.of(1, 20, new JpaOrder("id", JpaOrderModel.ASC));
        System.out.println(page);

        final Pageable pageable = JpaUtil.page(page);
        System.out.println(pageable);
    }
}
