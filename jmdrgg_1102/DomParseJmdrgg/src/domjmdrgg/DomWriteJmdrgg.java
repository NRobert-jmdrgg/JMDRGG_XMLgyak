package domjmdrgg;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DomWriteJmdrgg {

  public static void main(String[] args)
    throws ParserConfigurationException, TransformerException, DOMException {
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

    Document doc = docBuilder.newDocument();
    Element users = doc.createElement("users");
    users.setAttribute("xmlns", "domjmdrgg");

    String[] firstNames = { "Róbert", "Karcsi", "Tamás" };
    String[] lastNames = { "Nagy", "Pista", "Zucc" };
    String[] professions = { "Student", "Munkanélküli", "Programozó" };

    for (int i = 0; i < 3; i++) {
      Element user = doc.createElement("user");
      user.setAttribute("id", Integer.toString(i + 1));

      Element firstname = doc.createElement("firstname");
      firstname.setTextContent(firstNames[i]);
      Element lastname = doc.createElement("lastname");
      lastname.setTextContent(lastNames[i]);
      Element profession = doc.createElement("profession");
      profession.setTextContent(professions[i]);

      user.appendChild(firstname);
      user.appendChild(lastname);
      user.appendChild(profession);

      users.appendChild(user);
    }

    doc.appendChild(users);

    try (FileOutputStream output = new FileOutputStream("./users1jmdrgg.xml")) {
      writeXml(doc, output);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void writeXml(Document doc, OutputStream output)
    throws TransformerException {
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(output);

    transformer.transform(source, result);
  }
}
