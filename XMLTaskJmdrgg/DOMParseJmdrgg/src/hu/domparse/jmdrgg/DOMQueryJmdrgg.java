package hu.domparse.jmdrgg;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomQueryJmdrgg {

  private Document doc;
  private XPath xPath;

  public DomQueryJmdrgg(Document doc) {
    this.doc = doc;
    this.xPath = XPathFactory.newInstance().newXPath();
  }

  public Node queryById(String nodeName, String value) {
    String id = null;
    switch (nodeName) {
      case "cukraszda":
        id = "c_id";
        break;
      case "cukrasz":
        id = "cu_id";
        break;
      case "sutemeny_lista":
        id = "sl_id";
        break;
      case "sutemeny":
        id = "s_id";
        break;
      case "vasarlo":
        id = "v_id";
        break;
    }

    if (id != null) {
      String expression = String.format(
        "/NR_beadando/%s[@%s = '%s']",
        nodeName,
        id,
        value
      );

      try {
        NodeList nodeList = (NodeList) xPath
          .compile(expression)
          .evaluate(doc, XPathConstants.NODESET);

        DomReadJmdrgg dr = new DomReadJmdrgg(doc);
        dr.printNode(nodeName, nodeList);

        return nodeList.item(0);
      } catch (XPathExpressionException e) {
        e.printStackTrace();
      }
    }

    return null;
  }

  public NodeList queryVasarloByName(String vezeteknev, String keresztnev) {
    String expression = String.format(
      "/NR_beadando/vasarlo[nev/vezeteknev='%s' and nev/keresztnev='%s']",
      vezeteknev,
      keresztnev
    );
    try {
      NodeList nodeList = (NodeList) xPath
        .compile(expression)
        .evaluate(doc, XPathConstants.NODESET);

      DomReadJmdrgg dr = new DomReadJmdrgg(doc);
      dr.printVasarlo(nodeList);

      return nodeList;
    } catch (XPathExpressionException e) {
      e.printStackTrace();
    }

    return null;
  }

  public NodeList queryCukraszByName(String vezeteknev, String keresztnev) {
    String expression = String.format(
      "/NR_beadando/cukrasz[nev/vezeteknev='%s' and nev/keresztnev='%s']",
      vezeteknev,
      keresztnev
    );
    try {
      NodeList nodeList = (NodeList) xPath
        .compile(expression)
        .evaluate(doc, XPathConstants.NODESET);

      // DomReadJmdrgg.printCukrasz(nodeList);

      return nodeList;
    } catch (XPathExpressionException e) {
      e.printStackTrace();
    }

    return null;
  }

  public NodeList queryCukraszdaByName(String name) {
    String expression = String.format("/NR_beadando/cukraszda[nev='%s']", name);

    try {
      NodeList nodeList = (NodeList) xPath
        .compile(expression)
        .evaluate(doc, XPathConstants.NODESET);

      // DomReadJmdrgg.printCukraszdak(nodeList);

      return nodeList;
    } catch (XPathExpressionException e) {
      e.printStackTrace();
    }

    return null;
  }

  public NodeList querySutemenyByTulajdonsag(String[] tulajdonsagok) {
    StringBuilder sb = new StringBuilder();
    if (tulajdonsagok.length == 1) {
      sb.append("tulajdonsag='");
      sb.append(tulajdonsagok[0] + "'");
    } else {
      for (int i = 0; i < tulajdonsagok.length - 1; i++) {
        sb.append("tulajdonsag='");
        sb.append(tulajdonsagok[i]);
        sb.append("' or ");
      }
      sb.append("tulajdonsag='");
      sb.append(tulajdonsagok[tulajdonsagok.length - 1] + "'");
    }

    String expression = String.format(
      "/NR_beadando/sutemeny[%s]",
      sb.toString()
    );
    try {
      NodeList nodeList = (NodeList) xPath
        .compile(expression)
        .evaluate(doc, XPathConstants.NODESET);

      // DomReadJmdrgg dr = new DomReadJmdrgg(doc);
      // dr.printSutemeny(nodeList);

      return nodeList;
    } catch (XPathExpressionException e) {
      e.printStackTrace();
    }

    return null;
  }

  public NodeList querySutemenyByKaloria(int kaloria) {
    String expression = String.format(
      "/NR_beadando/sutemeny[kaloria < '%s']",
      Integer.toString(kaloria)
    );
    try {
      NodeList nodeList = (NodeList) xPath
        .compile(expression)
        .evaluate(doc, XPathConstants.NODESET);

      // DomReadJmdrgg.printSutemeny(nodeList);

      return nodeList;
    } catch (XPathExpressionException e) {
      e.printStackTrace();
    }

    return null;
  }
}
