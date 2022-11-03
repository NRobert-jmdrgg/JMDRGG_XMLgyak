package domjmdrgg;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomReadJmdrgg {

  public static void main(String[] args)
    throws SAXException, ParserConfigurationException, IOException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse("./src/domjmdrgg/usersJMDRGG.xml");

    System.out.println(
      "Root Element: " + doc.getDocumentElement().getNodeName()
    );

    NodeList nList = doc.getElementsByTagName("user");

    for (int i = 0; i < nList.getLength(); i++) {
      System.out.println(
        "id: " + nList.item(i).getAttributes().getNamedItem("id")
      );
      System.out.println(
        "firstname: " +
        ((Element) nList.item(i)).getElementsByTagName("firstname")
          .item(0)
          .getTextContent()
      );
      System.out.println(
        "lastname: " +
        ((Element) nList.item(i)).getElementsByTagName("lastname")
          .item(0)
          .getTextContent()
      );
      System.out.println(
        "profession: " +
        ((Element) nList.item(i)).getElementsByTagName("profession")
          .item(0)
          .getTextContent()
      );
    }
  }
}
