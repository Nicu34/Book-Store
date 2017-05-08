package ro.bookstore.server.repository.file.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import ro.bookstore.common.domain.Book;
import ro.bookstore.common.domain.validator.Validator;

/**
 * Created by nicu on 4/4/2017.
 */
public class BookInXmlFileRepository extends InXmlFileRepository<Long, Book>{
    public BookInXmlFileRepository(Validator<Book> validator, String filePath) {
        super(validator, filePath, "book");
    }

    @Override
    protected Node entityToXmlElement(Book book, Document document, Element element) {
        Element name = document.createElement("name");
        name.appendChild(document.createTextNode(book.getName()));
        element.appendChild(name);

        Element writer = document.createElement("writer");
        writer.appendChild(document.createTextNode(book.getWriter()));
        element.appendChild(writer);

        Element price = document.createElement("price");
        price.appendChild(document.createTextNode(book.getPrice().toString()));
        element.appendChild(price);

        Element quantity = document.createElement("quantity");
        quantity.appendChild(document.createTextNode(book.getQuantity().toString()));
        element.appendChild(quantity);

        return element;
    }

    @Override
    protected Book xmlElementToEntityValue(Element element) {
        Book book = new Book();

        book.setId(Long.valueOf(element.getAttribute("id")));
        book.setName(element.getElementsByTagName("name").item(0).getTextContent());
        book.setPrice(Long.valueOf(element.getElementsByTagName("price").item(0).getTextContent()));
        book.setQuantity(Integer.valueOf(element.getElementsByTagName("quantity").item(0).getTextContent()));
        book.setWriter(element.getElementsByTagName("writer").item(0).getTextContent());

        return book;
    }
}
