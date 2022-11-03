package hu.domparse.jmdrgg;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomReadJmdrgg {

  private String filename;

  public DomReadJmdrgg(String filename) {
    this.filename = filename;
  }

  public void printDomElements() {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(this.filename);
      NodeList nodeList = doc.getElementsByTagName("NR_beadando");

      for (int i = 0; i < nodeList.getLength(); i++) {}
    } catch (SAXException | ParserConfigurationException | IOException e) {
      e.printStackTrace();
    }
  }
}
