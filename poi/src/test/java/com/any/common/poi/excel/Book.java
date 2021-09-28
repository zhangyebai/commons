package com.any.common.poi.excel;

import com.any.common.poi.excel.annos.Column;

public class Book {

    @Column(name = "销售额", order = 1)
    private int sales;

    @Column(name = "价格", order = 3)
    private double price;

    @Column(name = "作者", order = 2)
    private String author;

    @Column(name = "上市时间", order = 4)
    private String createTime;

    public int getSales() {
        return sales;
    }

    public Book setSales(int sales) {
        this.sales = sales;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Book setPrice(double price) {
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

    public String getCreateTime() {
        return createTime;
    }

    public Book setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "Book{" +
                "sales=" + sales +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
