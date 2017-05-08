package ro.bookstore.common.domain;

/**
 * Created by MuresanN on 3/8/2017.
 */
public class Book extends BaseEntity<Long> {
    private String name;
    private String writer;
    private Long price;
    private Integer quantity;
    private static final long serialVersionUID = -2604256670185854666L;

    public String getName() {
        return name;
    }

    public Book() {
    }

    public Book(Long id, String name, String writer, Long price, Integer quantity) {
        super(id);
        this.name = name;
        this.writer = writer;
        this.price = price;
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
