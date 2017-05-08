package ro.ubb.c04remoting.server.repository.file.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ro.ubb.c04remoting.common.domain.BaseEntity;
import ro.ubb.c04remoting.common.domain.validator.Validator;
import ro.ubb.c04remoting.server.repository.RepositoryException;
import ro.ubb.c04remoting.server.repository.file.InFileRepository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by nicu on 3/28/2017.
 */
public abstract class InXmlFileRepository<ID, T extends BaseEntity<ID>> extends InFileRepository<ID, T> {

    private final String ELEMENTS_TAG_NAME;

    public InXmlFileRepository(Validator<T> validator, String filePath, String elementsTagName) {
        super(validator, filePath);
        ELEMENTS_TAG_NAME = elementsTagName;
    }

    @Override
    public void saveToFile() throws RepositoryException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RepositoryException(e.getMessage());
        }

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("entities");
        doc.appendChild(rootElement);

        entities.forEach((id, entity) -> {
            Element element = doc.createElement(ELEMENTS_TAG_NAME);
            element.setAttribute("id", id.toString());
            rootElement.appendChild(entityToXmlElement(entity, doc, element));
        });

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new RepositoryException(e.getMessage());
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));

        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        System.out.println("File saved!");
    }

    @Override
    public void loadFromFile() throws RepositoryException {
        File fXmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Document document;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(fXmlFile);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new RepositoryException(e.getMessage());
        }
        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName(ELEMENTS_TAG_NAME);
        entities = IntStream
                .range(0, nodeList.getLength())
                .mapToObj(nodeList::item)
                .filter(node -> node.getNodeType() == Node.ELEMENT_NODE)
                .map(node -> ((Element) node))
                .map(this::xmlElementToEntityValue)
                .collect(Collectors.toMap(T::getId, t -> t));
    }

    protected abstract T xmlElementToEntityValue(Element element);

    protected abstract Node entityToXmlElement(T entity, Document document, Element element);
}