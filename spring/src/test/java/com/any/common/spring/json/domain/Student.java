package com.any.common.spring.json.domain;

import java.util.List;
import java.util.Objects;

public class Student {

    private Integer age;

    private String name;

    private List<String> labels;

    public Integer getAge() {
        return age;
    }

    public Student setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getLabels() {
        return labels;
    }

    public Student setLabels(List<String> labels) {
        this.labels = labels;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(getAge(), student.getAge()) && Objects.equals(getName(), student.getName()) && Objects.equals(getLabels(), student.getLabels());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAge(), getName(), getLabels());
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", labels=" + labels +
                '}';
    }
}
