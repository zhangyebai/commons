package common;

import com.any.common.core.net.IpUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestNet {

    @Test
    void test() {
        final String o1 = "192.168.3.125";
        final long t1 = 3232236413L;
        final long t1r = 2097391808L;

        Assertions.assertEquals(t1, IpUtil.ip2long(o1));
        Assertions.assertEquals(t1r, IpUtil.ip2longReverse(o1));

        Assertions.assertEquals(o1, IpUtil.long2ip(t1));
        Assertions.assertEquals(o1, IpUtil.long2ipReverse(t1r));
    }
}
