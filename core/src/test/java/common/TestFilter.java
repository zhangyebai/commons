package common;

import com.any.common.core.filter.BlackWhiteFilter;
import com.any.common.core.filter.IFilter;
import com.any.common.core.filter.strategy.FilterStrategy;
import com.any.common.core.filter.strategy.IStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


class TestFilter {

    @Test
    @SuppressWarnings(value = "unchecked")
    void test(){
        final IStrategy<String> black = new FilterStrategy<String>().add("hello", "world", "python");
        final IStrategy<String> white = new FilterStrategy<String>().add("c++", "python", "c#");
//        for(int idx = 0; idx < 100_000; ++ idx){
//            black.add(Randoms.strings(4));
//        }
//
//        for(int idx = 0; idx < 100_000; ++idx){
//            white.add(Randoms.strings(4));
//        }

        final IFilter<String> filter = BlackWhiteFilter.of(black, white);

        Assertions.assertTrue(filter.pass("python"));
        Assertions.assertFalse(filter.pass("world"));
        //System.out.println(filter.pass("1234"));

        final IFilter<String> f1 = (IFilter<String>) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{IFilter.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(method.getName().equalsIgnoreCase("pass")){
                    final String p = (String)args[0];
                    if("hello".equalsIgnoreCase(p)){
                        return true;
                    }else{
                        return true;
                    }
                }
                return null;
            }
        });
        Assertions.assertTrue(f1.pass("hello"));
    }
}
