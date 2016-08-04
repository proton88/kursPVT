import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by User on 28.07.2016.
 */
public class DomWriter {
    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException,SAXException {
        Book book=new Book("Tolkien","Lord of the rings",30,3,"fantastic");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("src/xmlxsd/library.xml");
        //Document document = builder.newDocument();

        /*Element breakfastMenu = document.createElement("breakfast-menu");
        Element food = document.createElement("food");
        food.setAttribute("id", "234");

        Element name = document.createElement("name");
        name.setTextContent("Waffles");

        food.appendChild(name);
        breakfastMenu.appendChild(food);
        document.appendChild(breakfastMenu);*/
        //Element breakfastMenu = document.createElement("breakfast-menu");
        Element listBook=(Element)document.getElementsByTagName("tc:listBook").item(0);
        Element bookXml = document.createElement("Book");
        bookXml.setAttribute("id", Integer.toString(book.getId()));

        Element author = document.createElement("author");
        author.setTextContent(book.getAuthor());

        Element title = document.createElement("title");
        title.setTextContent(book.getTitle());

        Element price = document.createElement("price");
        price.setTextContent(Integer.toString(book.getPrice()));

        Element genre = document.createElement("genre");
        genre.setTextContent(book.getGenre());

        bookXml.appendChild(author);
        bookXml.appendChild(title);
        bookXml.appendChild(price);
        bookXml.appendChild(genre);
        listBook.appendChild(bookXml);
        //document.appendChild(breakfastMenu);



        TransformerFactory transformerFactory =
                TransformerFactory.newInstance();

        Transformer transformer =
                transformerFactory.newTransformer();

        DOMSource source = new DOMSource(document);

        StreamResult result = new StreamResult(new FileWriter("src/xmlxsd/library.xml"));

        transformer.transform(source, result);


    }
}
