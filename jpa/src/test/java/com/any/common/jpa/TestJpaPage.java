package com.any.common.jpa;

import com.any.common.core.domain.Pagination;
import com.any.common.jpa.domain.JpaEq;
import com.any.common.jpa.domain.JpaNull;
import com.any.common.jpa.domain.JpaPagination;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TestJpaPage {

    @Test
    void test(){
        final Pagination<String> pagination = new JpaPagination<>(1, 2, 3L, 4L, new ArrayList<>());
        System.out.println(pagination);

        final JpaEq<String> eq = new JpaEq<>("123");
        System.out.println(eq);

        final JpaNull jpaNull = new JpaNull();

        final List<String> l1 = new ArrayList<>();
        l1.add("1");
        l1.add("2");
        final Pagination<String> p1 = new JpaPagination<>(1, 2, 3L, 4L, l1);
        final Pagination<Integer> p2 = p1.to((element) -> {
            final int i = Integer.parseInt(element);
            if(i == 2){
                return null;
            }
            return i;
        });
        System.out.println(p2);
    }
}
