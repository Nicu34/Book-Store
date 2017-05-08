package ro.ubb.c04remoting.common.domain;

/**
 * Created by Ioana on 3/14/2017.
 */
public class Sale extends BaseEntity<Long> {

    private Long clientId;
    private Long bookId;
    private static final long serialVersionUID=3387652856980209644l;

    public Sale() {
    }

    public Sale(Long aLong) {
        super(aLong);
    }

    public Sale(Long aLong, Long clientId, Long bookId) {
        super(aLong);
        this.clientId = clientId;
        this.bookId = bookId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "clientId=" + clientId +
                ", bookId=" + bookId +
                '}';
    }
}
