package hu.domparse.jmdrgg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomModifyJmdrgg {

  private Document doc;
  private String outputFilename;

  /**
   * Cukrászda xml fájl módosítására szolgáló objektum
   *
   * @param doc
   * @param outputFilename kimeneti xml fájl neve
   */
  public DomModifyJmdrgg(Document doc, String outputFilename) {
    this.doc = doc;
    this.outputFilename = outputFilename;
  }

  /**
   * Várárló hozzáadása
   *
   * @param vezeteknev
   * @param keresztnev
   * @param torzsvasarlo
   * @param eletkor
   * @param nem
   */
  public void addVasarlo(
    String vezeteknev,
    String keresztnev,
    boolean torzsvasarlo,
    int eletkor,
    String nem
  ) {
    Element root = doc.getDocumentElement();

    NodeList vasarlok = doc.getElementsByTagName("vasarlo");

    // új vásárló elem építés
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

    // id inkrementálása
    Node lastVasarlo = vasarlok.item(vasarlok.getLength() - 1);

    String lastId = lastVasarlo
      .getAttributes()
      .getNamedItem("v_id")
      .getTextContent();
    String newId =
      "v" + (Integer.parseInt(lastId.substring(lastId.length() - 1)) + 1);
    newVasarlo.setAttribute("v_id", newId);

    // az előző vásárló után beillesztés
    root.insertBefore(newVasarlo, lastVasarlo.getNextSibling());

    // írás az outputfileba
    writeXml();
  }

  /**
   * Cukrász hozzáadása
   *
   * @param vezeteknev
   * @param keresztnev
   * @param eletkor
   * @param nem
   * @param cu_c
   */
  public void addCukrasz(
    String vezeteknev,
    String keresztnev,
    int eletkor,
    String nem,
    String cu_c
  ) {
    Element root = doc.getDocumentElement();

    NodeList cukraszok = doc.getElementsByTagName("cukrasz");
    // új Cukrász elem építés
    Element newCukrasz = doc.createElement("cukrasz");

    Element nev = doc.createElement("nev");
    Element vezeteknevElem = doc.createElement("vezeteknev");
    vezeteknevElem.setTextContent(vezeteknev);
    Element keresztnevElem = doc.createElement("keresztnev");
    keresztnevElem.setTextContent(keresztnev);
    nev.appendChild(vezeteknevElem);
    nev.appendChild(keresztnevElem);
    Element eletkorElem = doc.createElement("eletkor");
    eletkorElem.setTextContent(Integer.toString(eletkor));
    Element nemElem = doc.createElement("nem");
    nemElem.setTextContent(nem);

    newCukrasz.appendChild(nev);
    newCukrasz.appendChild(eletkorElem);
    newCukrasz.appendChild(nemElem);

    // id inkrementálása
    Node lastCukrasz = cukraszok.item(cukraszok.getLength() - 1);

    String lastId = lastCukrasz
      .getAttributes()
      .getNamedItem("cu_id")
      .getTextContent();
    String newId =
      "cu" + (Integer.parseInt(lastId.substring(lastId.length() - 1)) + 1);
    newCukrasz.setAttribute("cu_id", newId);

    // foreign key
    newCukrasz.setAttribute("cu_c", cu_c);

    // az előző cukrász után beillesztés
    root.insertBefore(newCukrasz, lastCukrasz.getNextSibling());

    // output fileba kiírás
    writeXml();
  }

  /**
   * Cukrászda hozzáadása
   *
   * @param nev
   * @param varos
   * @param utca
   * @param hazszam
   * @param ertekeles
   * @param tulajdonos
   * @param weboldal
   */
  public void addCukraszda(
    String nev,
    String varos,
    String utca,
    String hazszam,
    String ertekeles,
    String tulajdonos,
    String weboldal
  ) {
    Element root = doc.getDocumentElement();

    NodeList cukraszdak = doc.getElementsByTagName("cukraszda");
    Element newCukraszda = doc.createElement("cukraszda");

    Element nevElem = doc.createElement("nev");
    nevElem.setTextContent(nev);
    Element hely = doc.createElement("hely");
    Element varosElem = doc.createElement("varos");
    varosElem.setTextContent(varos);
    Element utcaElem = doc.createElement("utca");
    utcaElem.setTextContent(utca);
    Element hazszamElem = doc.createElement("hazszam");

    hely.appendChild(varosElem);
    hely.appendChild(utcaElem);
    hely.appendChild(hazszamElem);

    Element ertekelesElem = doc.createElement("ertekeles");
    ertekelesElem.setTextContent(ertekeles);
    Element tulajdonosElem = doc.createElement("tulajdonos");
    tulajdonosElem.setTextContent(tulajdonos);
    Element weboldalElem = doc.createElement("weboldal");
    weboldalElem.setTextContent(weboldal);

    newCukraszda.appendChild(nevElem);
    newCukraszda.appendChild(hely);
    newCukraszda.appendChild(ertekelesElem);
    newCukraszda.appendChild(tulajdonosElem);
    newCukraszda.appendChild(weboldalElem);

    Node lastCukraszda = cukraszdak.item(cukraszdak.getLength() - 1);

    String lastId = lastCukraszda
      .getAttributes()
      .getNamedItem("c_id")
      .getTextContent();
    String newId =
      "c" + (Integer.parseInt(lastId.substring(lastId.length() - 1)) + 1);
    newCukraszda.setAttribute("c_id", newId);

    root.insertBefore(newCukraszda, lastCukraszda.getNextSibling());

    writeXml();
  }

  /**
   * Sütemény hozzáadása
   *
   * @param nev
   * @param ar
   * @param kaloria
   * @param tulajdonsagok
   * @param s_sl
   */
  public void addSutemeny(
    String nev,
    int ar,
    int kaloria,
    String[] tulajdonsagok,
    String s_sl
  ) {
    Element root = doc.getDocumentElement();

    NodeList sutemenyek = doc.getElementsByTagName("sutemeny");
    Element newSutemeny = doc.createElement("sutemeny");

    Element nevElem = doc.createElement("nev");
    nevElem.setTextContent(nev);
    Element arElem = doc.createElement("ar");
    arElem.setTextContent(Integer.toString(ar));
    Element kaloriaElem = doc.createElement("kaloria");
    kaloriaElem.setTextContent(Integer.toString(kaloria));

    newSutemeny.appendChild(nevElem);
    newSutemeny.appendChild(arElem);
    newSutemeny.appendChild(kaloriaElem);

    for (String tul : tulajdonsagok) {
      Element tulajdonsagElem = doc.createElement("tulajdonsag");
      tulajdonsagElem.setTextContent(tul);
      newSutemeny.appendChild(tulajdonsagElem);
    }

    Node lastSutemeny = sutemenyek.item(sutemenyek.getLength() - 1);

    String lastId = lastSutemeny
      .getAttributes()
      .getNamedItem("s_id")
      .getTextContent();
    String newId =
      "s" + (Integer.parseInt(lastId.substring(lastId.length() - 1)) + 1);
    newSutemeny.setAttribute("s_id", newId);

    // foreign key
    newSutemeny.setAttribute("s_sl", s_sl);

    root.insertBefore(newSutemeny, lastSutemeny.getNextSibling());

    writeXml();
  }

  /**
   * Sütemény lista hozzáadása
   *
   * @param legnepszerubb
   * @param legnepszerutlenebb
   * @param legutobbFrissitve
   * @param sl_c
   */
  public void addSutemenyLista(
    String legnepszerubb,
    String legnepszerutlenebb,
    String legutobbFrissitve,
    String sl_c
  ) {
    Element root = doc.getDocumentElement();

    NodeList sutemenyListak = doc.getElementsByTagName("sutemeny_lista");
    Element newSutemenyLista = doc.createElement("sutemeny_lista");

    Element legnepszerubbElem = doc.createElement("legnepszerubb");
    legnepszerubbElem.setTextContent(legnepszerubb);
    Element legnepszerutlenebbElem = doc.createElement("legnepszerutlenebb");
    legnepszerutlenebbElem.setTextContent(legnepszerutlenebb);
    Element legutobbFrissitveElem = doc.createElement("legutobb_frissitve");
    legutobbFrissitveElem.setTextContent(legutobbFrissitve);

    newSutemenyLista.appendChild(legnepszerubbElem);
    newSutemenyLista.appendChild(legnepszerutlenebbElem);
    newSutemenyLista.appendChild(legutobbFrissitveElem);

    Node lastSutemenyLista = sutemenyListak.item(
      sutemenyListak.getLength() - 1
    );

    String lastId = lastSutemenyLista
      .getAttributes()
      .getNamedItem("sl_id")
      .getTextContent();
    String newId =
      "sl" + (Integer.parseInt(lastId.substring(lastId.length() - 1)) + 1);
    newSutemenyLista.setAttribute("sl_id", newId);

    // foreign key
    newSutemenyLista.setAttribute("sl_c", sl_c);

    root.insertBefore(newSutemenyLista, lastSutemenyLista.getNextSibling());

    writeXml();
  }

  /**
   * NodeLista törlése
   *
   * @param nodeList
   */
  public void removeByQuery(NodeList nodeList) {
    if (nodeList != null) {
      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);
        node.getParentNode().removeChild(node);
      }
    }

    writeXml();
  }

  /**
   * Elem törlése id alapján
   *
   * @param nodeType
   * @param id
   */
  public void removeById(String nodeType, String id) {
    DomQueryJmdrgg dq = new DomQueryJmdrgg(doc);
    Node node = dq.queryById(nodeType, id);

    if (node != null) {
      node.getParentNode().removeChild(node);
    }

    writeXml();
  }

  /**
   * Elemek módosítása tag alapján
   *
   * @param nodeList
   * @param tagName
   * @param newValue
   */
  public void modifyNode(NodeList nodeList, String tagName, String newValue) {
    if (nodeList != null) {
      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);
        Element elem = (Element) node;
        try {
          elem.getElementsByTagName(tagName).item(0).setTextContent(newValue);
        } catch (DOMException e) {
          e.printStackTrace();
        }
      }
    }

    writeXml();
  }

  /**
   * Elemek módosítása tag alapján
   *
   * @param node
   * @param tagName
   * @param newValue
   */
  public void modifyNode(Node node, String tagName, String newValue) {
    if (node != null) {
      Element elem = (Element) node;
      try {
        elem.getElementsByTagName(tagName).item(0).setTextContent(newValue);
      } catch (DOMException e) {
        e.printStackTrace();
      }
    }

    writeXml();
  }

  /**
   * Módosítások kiírása az outputfileba
   */
  private void writeXml() {
    try (FileOutputStream output = new FileOutputStream(outputFilename)) {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer(
        new StreamSource(new File("./stylesheet.xslt"))
      );
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(output);

      transformer.transform(source, result);
    } catch (TransformerException | IOException e) {
      e.printStackTrace();
    }
  }
}
