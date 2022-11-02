package domjmdrgg;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class DomReadJmdrgg {
  public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
    try {
      File inputFile = new File("usersJmdrgg.xml");
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(inputFile);
      doc.getDocumentElement().normalize();
      System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
      NodeList nList = doc.getElementsByTagName("users");

      for (int i = 0; i < nList.getLength(); i++) {
        Node nNode = nList.item(i);
        System.out.println("\nCurrent Element :" + nNode.getNodeName());

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          Element elem = (Element) nNode;
          String uid = elem.getElementsByTagName("uid").item(i).getTextContent();
          String fname = elem.getElementsByTagName("firstname").item(i).getTextContent();
          String lname = elem.getElementsByTagName("lastname").item(i).getTextContent();
          String pname = elem.getElementsByTagName("profession").item(i).getTextContent();

          System.out.println("uid: " + uid);
          System.out.println("firstname" + fname);
          System.out.println("lastname" + lname);
          System.out.println("profession" + pname);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
