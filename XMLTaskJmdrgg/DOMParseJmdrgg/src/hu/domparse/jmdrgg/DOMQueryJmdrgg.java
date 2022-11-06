package hu.domparse.jmdrgg;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DOMQueryJmdrgg {

  private static final String filename = "./XMLjmdrgg.xml";
  private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

  public static Node queryById(String nodeName, String value) {
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
      try {
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(filename);

        doc.getDocumentElement().normalize();

        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = String.format(
          "/NR_beadando/%s[@%s = '%s']",
          nodeName,
          id,
          value
        );

        NodeList nodeList = (NodeList) xPath
          .compile(expression)
          .evaluate(doc, XPathConstants.NODESET);

        // DomReadJmdrgg.printNode(nodeName, nodeList);

        return nodeList.item(0);
      } catch (
        ParserConfigurationException
        | SAXException
        | IOException
        | XPathExpressionException e
      ) {
        e.printStackTrace();
      }
    }

    return null;
  }

  public static NodeList queryVasarloByName(
    String vezeteknev,
    String keresztnev
  ) {
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();

      Document doc = builder.parse(filename);

      doc.getDocumentElement().normalize();

      XPath xPath = XPathFactory.newInstance().newXPath();
      String expression = String.format(
        "/NR_beadando/vasarlo[nev/vezeteknev='%s' and nev/keresztnev='%s']",
        vezeteknev,
        keresztnev
      );

      NodeList nodeList = (NodeList) xPath
        .compile(expression)
        .evaluate(doc, XPathConstants.NODESET);
      // DomReadJmdrgg.printVasarlo(nodeList);

      return nodeList;
    } catch (
      ParserConfigurationException
      | SAXException
      | IOException
      | XPathExpressionException e
    ) {
      e.printStackTrace();
    }

    return null;
  }

  public static NodeList queryCukraszByName(
    String vezeteknev,
    String keresztnev
  ) {
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();

      Document doc = builder.parse(filename);

      doc.getDocumentElement().normalize();

      XPath xPath = XPathFactory.newInstance().newXPath();
      String expression = String.format(
        "/NR_beadando/cukrasz[nev/vezeteknev='%s' and nev/keresztnev='%s']",
        vezeteknev,
        keresztnev
      );

      NodeList nodeList = (NodeList) xPath
        .compile(expression)
        .evaluate(doc, XPathConstants.NODESET);

      // DomReadJmdrgg.printCukrasz(nodeList);

      return nodeList;
    } catch (
      ParserConfigurationException
      | SAXException
      | IOException
      | XPathExpressionException e
    ) {
      e.printStackTrace();
    }

    return null;
  }

  public static NodeList queryCukraszdaByName(String name) {
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();

      Document doc = builder.parse(filename);

      doc.getDocumentElement().normalize();

      XPath xPath = XPathFactory.newInstance().newXPath();
      String expression = String.format(
        "/NR_beadando/cukraszda[nev='%s']",
        name
      );

      NodeList nodeList = (NodeList) xPath
        .compile(expression)
        .evaluate(doc, XPathConstants.NODESET);

      // DomReadJmdrgg.printCukraszdak(nodeList);

      return nodeList;
    } catch (
      ParserConfigurationException
      | SAXException
      | IOException
      | XPathExpressionException e
    ) {
      e.printStackTrace();
    }

    return null;
  }

  public static NodeList querySutemenyByTulajdonsag(String[] tulajdonsagok) {
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();

      Document doc = builder.parse(filename);

      doc.getDocumentElement().normalize();

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

      XPath xPath = XPathFactory.newInstance().newXPath();
      String expression = String.format(
        "/NR_beadando/sutemeny[%s]",
        sb.toString()
      );

      NodeList nodeList = (NodeList) xPath
        .compile(expression)
        .evaluate(doc, XPathConstants.NODESET);

      // DomReadJmdrgg.printSutemeny(nodeList);

      return nodeList;
    } catch (
      ParserConfigurationException
      | SAXException
      | IOException
      | XPathExpressionException e
    ) {
      e.printStackTrace();
    }

    return null;
  }

  public static NodeList querySutemenyByKaloria(int kaloria) {
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();

      Document doc = builder.parse(filename);

      doc.getDocumentElement().normalize();

      XPath xPath = XPathFactory.newInstance().newXPath();
      String expression = String.format(
        "/NR_beadando/sutemeny[kaloria < '%s']",
        Integer.toString(kaloria)
      );

      NodeList nodeList = (NodeList) xPath
        .compile(expression)
        .evaluate(doc, XPathConstants.NODESET);

      // DomReadJmdrgg.printSutemeny(nodeList);

      return nodeList;
    } catch (
      ParserConfigurationException
      | SAXException
      | IOException
      | XPathExpressionException e
    ) {
      e.printStackTrace();
    }

    return null;
  }
}
