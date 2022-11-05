package hu.domparse.jmdrgg;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomReadJmdrgg {

  private final String filename = "./XMLjmdrgg.xml";
  private final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

  private void printCukraszdak(Document doc) {
    System.out.println("Cukrászdák: ");

    NodeList cukraszdak = doc.getElementsByTagName("cukraszda");

    for (int i = 0; i < cukraszdak.getLength(); i++) {
      Element elem = (Element) cukraszdak.item(i);

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

  private void printCukrasz(Document doc) {
    System.out.println("Cukrászok: ");

    NodeList cukraszok = doc.getElementsByTagName("cukrasz");

    for (int i = 0; i < cukraszok.getLength(); i++) {
      Element elem = (Element) cukraszok.item(i);

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

  private void printSutemenyLista(Document doc) {
    System.out.println("Sütemény listák: ");

    NodeList sutemenyListak = doc.getElementsByTagName("sutemeny_lista");

    for (int i = 0; i < sutemenyListak.getLength(); i++) {
      Element elem = (Element) sutemenyListak.item(i);

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

  private void printSutemeny(Document doc) {
    System.out.println("Sütemények: ");

    NodeList sutemenyek = doc.getElementsByTagName("sutemeny");

    for (int i = 0; i < sutemenyek.getLength(); i++) {
      Element elem = (Element) sutemenyek.item(i);

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

  private void printVasarlo(Document doc) {
    System.out.println("Vásárlók: ");

    NodeList vasarlok = doc.getElementsByTagName("vasarlo");

    for (int i = 0; i < vasarlok.getLength(); i++) {
      Element elem = (Element) vasarlok.item(i);

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

  private void printTranzakcio(Document doc) {
    System.out.println("Tranzakciók: ");

    NodeList tranzakciok = doc.getElementsByTagName("tranzakcio");

    for (int i = 0; i < tranzakciok.getLength(); i++) {
      Element elem = (Element) tranzakciok.item(i);

      System.out.println(
        "Dátum: " + elem.getElementsByTagName("datum").item(0).getTextContent()
      );
    }
  }

  public void printXml() {
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(filename);

      printCukraszdak(doc);
      printCukrasz(doc);
      printSutemenyLista(doc);
      printSutemeny(doc);
      printVasarlo(doc);
      printTranzakcio(doc);
    } catch (SAXException | ParserConfigurationException | IOException e) {
      e.printStackTrace();
    }
  }
}
