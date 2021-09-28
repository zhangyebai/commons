package common.domain;


import com.any.common.core.param.Require;

import java.util.Objects;

public class Book {

    @Require
    private Integer price;

    @Require(notEmpty = true)
    private String author;

    public Integer getPrice() {
        return price;
    }

    public Book setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getPrice().equals(book.getPrice()) && getAuthor().equals(book.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), getAuthor());
    }

    @Override
    public String toString() {
        return "Book{" +
                "price=" + price +
                ", author='" + author + '\'' +
                '}';
    }

    public int f1() {
        f2();
        return 1;
    }

    private void f2() {
        System.out.println("I'm f2");
    }
}