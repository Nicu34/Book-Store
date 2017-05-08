package ro.ubb.c04remoting.server.repository.file.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import ro.ubb.c04remoting.common.domain.Sale;
import ro.ubb.c04remoting.common.domain.validator.Validator;

/**
 * Created by nicu on 4/4/2017.
 */
public class SaleInXmlFileRepository extends InXmlFileRepository<Long, Sale> {
    public SaleInXmlFileRepository(Validator<Sale> validator, String filePath) {
        super(validator, filePath, "sale");
    }

    @Override
    protected Node entityToXmlElement(Sale sale, Document document, Element element) {
        Element clientId = document.createElement("clientId");
        clientId.appendChild(document.createTextNode(sale.getClientId().toString()));
        element.appendChild(clientId);

        Element bookId = document.createElement("bookId");
        bookId.appendChild(document.createTextNode(sale.getBookId().toString()));
        element.appendChild(bookId);

        return element;
    }

    @Override
    protected Sale xmlElementToEntityValue(Element element) {
        Sale sale = new Sale();

        sale.setId(Long.valueOf(element.getAttribute("id")));
        sale.setBookId(Long.valueOf(element.getElementsByTagName("bookId").item(0).getTextContent()));
        sale.setClientId(Long.valueOf(element.getElementsByTagName("clientId").item(0).getTextContent()));

        return sale;
    }
}
