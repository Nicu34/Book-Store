package ro.bookstore.server.repository.file.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import ro.bookstore.common.domain.Client;
import ro.bookstore.common.domain.validator.Validator;

/**
 * Created by nicu on 4/4/2017.
 */
public class ClientInXmlFileRepository extends InXmlFileRepository<Long, Client> {

    public ClientInXmlFileRepository(Validator<Client> validator, String filePath) {
        super(validator, filePath, "client");
    }

    @Override
    protected Node entityToXmlElement(Client client, Document document, Element element) {
        Element name = document.createElement("name");
        name.appendChild(document.createTextNode(client.getName()));
        element.appendChild(name);

        Element moneyAmount = document.createElement("moneyAmount");
        moneyAmount.appendChild(document.createTextNode(client.getMoneyAmount().toString()));
        element.appendChild(moneyAmount);

        return element;
    }

    @Override
    protected Client xmlElementToEntityValue(Element element) {
        Client client = new Client();

        client.setId(Long.valueOf(element.getAttribute("id")));
        client.setName(element.getElementsByTagName("name").item(0).getTextContent());
        client.setMoneyAmount(Long.valueOf(element.getElementsByTagName("moneyAmount").item(0).getTextContent()));

        return client;
    }
}
