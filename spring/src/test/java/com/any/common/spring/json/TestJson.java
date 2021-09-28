package com.any.common.spring.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.any.common.spring.json.domain.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


class TestJson {

    @Test
    void test() {
        final Student student = new Student().setAge(12).setName("han mei mei");
        List<String> labels = new ArrayList<>();
        labels.add("ball");
        labels.add("book");
        student.setLabels(labels);
        final String json = Jsons.toJsonString(student);
        final Student s1 = Jsons.parseObject(json, Student.class);
        Assertions.assertEquals(student, s1);

        final Student s2 = Jsons.parseObject(null, Student.class);
        Assertions.assertNull(s2);

        final String h = "hello world";
        final String h1 = Jsons.toJsonString(h); // h1 for "\"hello world\""
        Assertions.assertNotEquals(h, h1);
        final String h2 = Jsons.parseObject(h1, String.class);
        Assertions.assertEquals(h, h2);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testJson(){
        final String json = "{\n    \"test\":[\"my\", \"test\"],\n    \"count\": 100\n}";
        final Map<String, Object> map  = Objects.requireNonNull(Jsons.parseObject(json, new TypeReference<Map<String, Object>>(){}));
        Assertions.assertEquals(100, map.get("count"));
        final List<String> tests = (List<String>)map.get("test");
        Assertions.assertTrue(tests.contains("my"));
        Assertions.assertTrue(tests.contains("test"));
    }
}
