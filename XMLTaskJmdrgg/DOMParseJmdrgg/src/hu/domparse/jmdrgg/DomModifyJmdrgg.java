package hu.domparse.jmdrgg;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.text.AbstractDocument.ElementEdit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomModifyJmdrgg {

  private static final String filename = "./XMLjmdrgg.xml";
  private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

  public static void addVasarlo(
    String vezeteknev,
    String keresztnev,
    boolean torzsvasarlo,
    int eletkor,
    String nem
  ) {
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(filename);

      Element root = doc.getDocumentElement();

      NodeList vasarlok = doc.getElementsByTagName("vasarlo");
      Element newVasarlo = doc.createElement("vasarlo");

      Element nev = doc.createElement("nev");
      Element vezeteknevElem = doc.createElement("vezeteknev");
      vezeteknevElem.setTextContent(vezeteknev);
      Element keresztnevElem = doc.createElement("keresztnev");
      keresztnevElem.setTextContent(keresztnev);
      nev.appendChild(vezeteknevElem);
      nev.appendChild(keresztnevElem);
      Element torzsvasarloElem = doc.createElement("torzsvasarlo");
      torzsvasarloElem.setTextContent(torzsvasarlo ? "igen" : "nem");
      Element eletkorElem = doc.createElement("eletkor");
      eletkorElem.setTextContent(Integer.toString(eletkor));
      Element nemElem = doc.createElement("nem");
      nemElem.setTextContent(nem);

      newVasarlo.appendChild(nev);
      newVasarlo.appendChild(torzsvasarloElem);
      newVasarlo.appendChild(eletkorElem);
      newVasarlo.appendChild(nemElem);

      root.insertBefore(
        newVasarlo,
        vasarlok.item(vasarlok.getLength() - 1).getNextSibling()
      );

      try (FileOutputStream output = new FileOutputStream(filename)) {
        writeXml(doc, output);
      } catch (TransformerException e) {
        e.printStackTrace();
      }
    } catch (
      DOMException | ParserConfigurationException | SAXException | IOException e
    ) {
      e.printStackTrace();
    }
  }

  private static void writeXml(Document doc, OutputStream output)
    throws TransformerException {
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty(
      "{http://xml.apache.org/xslt}indent-amount",
      "2"
    );
    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(output);

    transformer.transform(source, result);
  }
}
