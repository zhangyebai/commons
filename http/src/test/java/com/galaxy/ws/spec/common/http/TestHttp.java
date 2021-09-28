package com.galaxy.ws.spec.common.http;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

class TestHttp {

    @Test
    void testClient() throws ExecutionException, InterruptedException {
        /*
        final byte[] bytes = HttpClient.easyGet("https://www.baidu.com");
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
        final byte[] bs = HttpClient.easyPost("https://www.baidu.com");
        System.out.println(new String(bs, StandardCharsets.UTF_8));
        final Map<String, String> cookies = new HashMap<>();
        cookies.put("Y-Auth-Token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtZXJjaGFudElkIjoiMTI1MDIyNzQ4Njk4NzI2NDAwIiwiY3JlYXRlVGltZSI6IjE2MjU3OTk4NTEyMTgiLCJ0eXBlIjowLCJleHAiOjE2MjYwNTkwNTEsInVzZXJJZCI6IjI0MGVjZGFhLWE2MWEtNGRmNi1iMTRjLTY1MGFiZDBhYTlmOCIsImNoYW5uZWxDb2RlIjo2fQ.ddjmjxYY04qwPzvvn2YEejpMEXBNtpDhMu6DD478DGA");
        final byte[] ws = HttpClient.easyGet("https://case-test.wusong.com/api/lj/cs/system/settings/litigation/find", null, null, cookies);
        System.out.println(new String(ws, StandardCharsets.UTF_8));
        */
//        final Future<HttpResponse> future = HttpClient.getAsync("https://www.baidu.com");
//        final HttpResponse result = future.get();
//        System.out.println(result);
//        final byte[] bytes = result.getBody();
//        System.out.println(new String(bytes, StandardCharsets.UTF_8));

    }

    @Test
    void testUrl() {
//        final Map<String, String> params = new HashMap<String, String>();
//        params.put("a", "ac");
//        params.put("b", "bc");
//        final String url = HttpClient.url("baidu.com", params);
//        System.out.println(url);
    }
}
