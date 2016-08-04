import javax.xml.stream.*;
import java.io.*;

/**
 * Created by User on 28.07.2016.
 */
public class StaxWriter {
    public static void main(String[] args) throws XMLStreamException,IOException{
        Book book=new Book("Tolkien","Lord of the rings",30,3,"fantastic");

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();

        XMLInputFactory inputFactory=XMLInputFactory.newInstance();
        InputStream input=new FileInputStream("src/xmlxsd/library.xml");
        XMLStreamReader reader=inputFactory.createXMLStreamReader(input);

        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        //RandomAccessFile raf=new RandomAccessFile("src/xmlxsd/library.xml","rw");
        FileWriter fw=new FileWriter("src/xmlxsd/library.xml",true);
        XMLStreamWriter writer = factory.createXMLStreamWriter(fw);
        //writer.

        //writer.writeStartDocument();
        writer.writeStartElement("Book");
        writer.writeAttribute("id", Integer.toString(book.getId()));

        writer.writeStartElement("author");
        writer.writeCharacters(book.getAuthor());
        writer.writeEndElement();

        writer.writeStartElement("title");
        writer.writeCharacters(book.getTitle());
        writer.writeEndElement();

        writer.writeStartElement("price");
        writer.writeCharacters(Integer.toString(book.getPrice()));
        writer.writeEndElement();

        writer.writeStartElement("genre");
        writer.writeCharacters(book.getGenre());
        writer.writeEndElement();

        writer.writeEndElement();
        writer.writeEndDocument();

        writer.flush();
        writer.close();
    }
}
