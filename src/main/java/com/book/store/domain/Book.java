package com.book.store.domain;

/**
 * Created by MuresanN on 3/8/2017.
 */
public class Book extends BaseEntity<Long> {
    private String name;
    private String writer;
    private Long price;
    private Integer quantity;

    public String getName() {
        return name;
    }

    public Book(Long id, String name, String writer, Long price, Integer quantity) {
        super(id);
        this.name = name;
        this.writer = writer;
        this.price = price;
        this.quantity = quantity;
    }

    public Book setName(String name) {
        this.name = name;
        return this;
    }

    public String getWriter() {
        return writer;
    }

    public Book setWriter(String writer) {
        this.writer = writer;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public Book setPrice(Long price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Book setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public String toString() {
        return "Book{" + super.toString() +
                "name='" + name + '\'' +
                ", writer='" + writer + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
