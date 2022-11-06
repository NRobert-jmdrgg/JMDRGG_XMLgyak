package hu.domparse.jmdrgg;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomReadJmdrgg {

  private static final String filename = "./XMLjmdrgg.xml";
  private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

  public static void printCukraszdak(NodeList nl) {
    System.out.println("Cukrászdák: ");
    for (int i = 0; i < nl.getLength(); i++) {
      Element elem = (Element) nl.item(i);

      System.out.println("id: " + elem.getAttribute("c_id"));

      System.out.println(
        "Cukrászda neve: " +
        elem.getElementsByTagName("nev").item(0).getTextContent()
      );

      System.out.println(
        "Címe: " +
        elem.getElementsByTagName("varos").item(0).getTextContent() +
        " " +
        elem.getElementsByTagName("utca").item(0).getTextContent() +
        " " +
        elem.getElementsByTagName("hazszam").item(0).getTextContent()
      );

      System.out.println(
        "Értékelése: " +
        elem.getElementsByTagName("ertekeles").item(0).getTextContent()
      );

      System.out.println(
        "Weboldala: " +
        elem.getElementsByTagName("weboldal").item(0).getTextContent()
      );
    }
  }

  public static void printCukrasz(NodeList nl) {
    System.out.println("Cukrászok: ");

    for (int i = 0; i < nl.getLength(); i++) {
      Element elem = (Element) nl.item(i);

      System.out.println("id: " + elem.getAttribute("cu_id"));

      System.out.println(
        "Cukrász neve: " +
        elem.getElementsByTagName("vezeteknev").item(0).getTextContent() +
        " " +
        elem.getElementsByTagName("keresztnev").item(0).getTextContent()
      );

      System.out.println(
        "Életkora: " +
        elem.getElementsByTagName("eletkor").item(0).getTextContent()
      );

      System.out.println(
        "Neme: " + elem.getElementsByTagName("nem").item(0).getTextContent()
      );
    }
  }

  public static void printSutemenyLista(NodeList nl) {
    System.out.println("Sütemény listák: ");

    for (int i = 0; i < nl.getLength(); i++) {
      Element elem = (Element) nl.item(i);

      System.out.println("id: " + elem.getAttribute("sl_id"));

      System.out.println(
        "Legnépszerűbb sütemény: " +
        elem.getElementsByTagName("legnepszerubb").item(0).getTextContent()
      );

      System.out.println(
        "Legnépszerűtlenebb sütemény: " +
        elem.getElementsByTagName("legnepszerutlenebb").item(0).getTextContent()
      );

      System.out.println(
        "Legutobb frissitve: " +
        elem.getElementsByTagName("legutobb_frissitve").item(0).getTextContent()
      );
    }
  }

  public static void printSutemeny(NodeList nl) {
    System.out.println("Sütemények: ");

    for (int i = 0; i < nl.getLength(); i++) {
      Element elem = (Element) nl.item(i);

      System.out.println("id: " + elem.getAttribute("s_id"));

      System.out.println(
        "Név: " + elem.getElementsByTagName("nev").item(0).getTextContent()
      );

      System.out.println(
        "Ár: " + elem.getElementsByTagName("ar").item(0).getTextContent()
      );

      System.out.println(
        "Kalória: " +
        elem.getElementsByTagName("kaloria").item(0).getTextContent()
      );

      int len = elem.getElementsByTagName("tulajdonsag").getLength();

      System.out.println("tulajdonságok: ");
      for (int j = 0; j < len; j++) {
        System.out.println(
          elem.getElementsByTagName("tulajdonsag").item(j).getTextContent()
        );
      }
    }
  }

  public static void printVasarlo(NodeList nl) {
    System.out.println("Vásárlók: ");

    for (int i = 0; i < nl.getLength(); i++) {
      Element elem = (Element) nl.item(i);

      System.out.println("id: " + elem.getAttribute("v_id"));

      System.out.println(
        "Név: " +
        elem.getElementsByTagName("vezeteknev").item(0).getTextContent() +
        " " +
        elem.getElementsByTagName("keresztnev").item(0).getTextContent()
      );

      System.out.println(
        "Törzsvásárló: " +
        elem.getElementsByTagName("torzsvasarlo").item(0).getTextContent()
      );

      System.out.println(
        "Életkor: " +
        elem.getElementsByTagName("eletkor").item(0).getTextContent()
      );

      System.out.println(
        "Nem: " + elem.getElementsByTagName("nem").item(0).getTextContent()
      );
    }
  }

  public static void printTranzakcio(NodeList nl) {
    System.out.println("Tranzakciók: ");

    for (int i = 0; i < nl.getLength(); i++) {
      Element elem = (Element) nl.item(i);

      System.out.println(
        "Dátum: " + elem.getElementsByTagName("datum").item(0).getTextContent()
      );
    }
  }

  public static void printXml() {
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(filename);

      printCukraszdak(doc.getElementsByTagName("cukraszda"));
      printCukrasz(doc.getElementsByTagName("cukrasz"));
      printSutemenyLista(doc.getElementsByTagName("sutemeny_lista"));
      printSutemeny(doc.getElementsByTagName("sutemeny"));
      printVasarlo(doc.getElementsByTagName("vasarlo"));
      printTranzakcio(doc.getElementsByTagName("tranzakcio"));
    } catch (SAXException | ParserConfigurationException | IOException e) {
      e.printStackTrace();
    }
  }

  public static void printNode(String nodeName, NodeList nodeList) {
    switch (nodeName) {
      case "cukraszda":
        printCukraszdak(nodeList);
      case "cukrasz":
        printCukrasz(nodeList);
      case "sutemeny_lista":
        printSutemenyLista(nodeList);
      case "sutemeny":
        printSutemeny(nodeList);
      case "vasarlo":
        printVasarlo(nodeList);
      default:
        return;
    }
  }
}
