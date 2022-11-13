package hu.domparse.jmdrgg;

import java.io.FileWriter;
import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DomReadJmdrgg {

  private Document doc;

  /**
   * Beadandó feladat xml dokumentum kiírására szolgáló Objektum
   * @param doc Dokumentum Objektum
   */
  public DomReadJmdrgg(Document doc) {
    this.doc = doc;
  }

  /**
   * Cukrászdák kiírása
   * @param nl Cukrászdák NodeList objektum
   */
  public void printCukraszdak(NodeList nl) {
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
      System.out.println("");
    }
  }

  private void writeCukraszdaToTxt(NodeList nl) {
    try (FileWriter fw = new FileWriter("text.txt", true)) {
      fw.write("Cukraszdak: \n");

      for (int i = 0; i < nl.getLength(); i++) {
        Element elem = (Element) nl.item(i);

        fw.write("id: " + elem.getAttribute("c_id") + "\n");

        fw.write(
          "Cukrászda neve: " +
          elem.getElementsByTagName("nev").item(0).getTextContent() +
          "\n"
        );

        fw.write(
          "Címe: " +
          elem.getElementsByTagName("varos").item(0).getTextContent() +
          " " +
          elem.getElementsByTagName("utca").item(0).getTextContent() +
          " " +
          elem.getElementsByTagName("hazszam").item(0).getTextContent() +
          "\n"
        );

        fw.write(
          "Értékelése: " +
          elem.getElementsByTagName("ertekeles").item(0).getTextContent() +
          "\n"
        );

        fw.write(
          "Weboldala: " +
          elem.getElementsByTagName("weboldal").item(0).getTextContent() +
          "\n"
        );
        fw.write("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Cukrászok kiírása
   * @param nl Cukrászok NodeList objektum
   */
  public void printCukrasz(NodeList nl) {
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
      System.out.println("");
    }
  }

  private void writeCukraszToTxt(NodeList nl) {
    try (FileWriter fw = new FileWriter("text.txt", true)) {
      fw.write("Cukrászok: \n");

      for (int i = 0; i < nl.getLength(); i++) {
        Element elem = (Element) nl.item(i);

        fw.write("id: " + elem.getAttribute("cu_id") + "\n");

        fw.write(
          "Cukrász neve: " +
          elem.getElementsByTagName("vezeteknev").item(0).getTextContent() +
          " " +
          elem.getElementsByTagName("keresztnev").item(0).getTextContent() +
          "\n"
        );

        fw.write(
          "Életkora: " +
          elem.getElementsByTagName("eletkor").item(0).getTextContent() +
          "\n"
        );

        fw.write(
          "Neme: " +
          elem.getElementsByTagName("nem").item(0).getTextContent() +
          "\n"
        );
        fw.write("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Sütemény listák kiírása
   * @param nl Sütemény lista NodeList objektum
   */
  public void printSutemenyLista(NodeList nl) {
    System.out.println("Sütemény listák: \n");

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
      System.out.println("");
    }
  }

  private void writeSutemenyListaToTxt(NodeList nl) {
    try (FileWriter fw = new FileWriter("text.txt", true)) {
      fw.write("Sütemény listák: \n");

      for (int i = 0; i < nl.getLength(); i++) {
        Element elem = (Element) nl.item(i);

        fw.write("id: " + elem.getAttribute("sl_id") + "\n");

        fw.write(
          "Legnépszerűbb sütemény: " +
          elem.getElementsByTagName("legnepszerubb").item(0).getTextContent() +
          "\n"
        );

        fw.write(
          "Legnépszerűtlenebb sütemény: " +
          elem
            .getElementsByTagName("legnepszerutlenebb")
            .item(0)
            .getTextContent() +
          "\n"
        );

        fw.write(
          "Legutobb frissitve: " +
          elem
            .getElementsByTagName("legutobb_frissitve")
            .item(0)
            .getTextContent() +
          "\n"
        );
        fw.write("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Sütemények kiírása
   * @param nl Sütemény NodeList objektum
   */
  public void printSutemeny(NodeList nl) {
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
      System.out.println("");
    }
  }

  private void writeSutemenyToTxt(NodeList nl) {
    try (FileWriter fw = new FileWriter("text.txt", true)) {
      fw.write("Sütemények: \n");

      for (int i = 0; i < nl.getLength(); i++) {
        Element elem = (Element) nl.item(i);

        fw.write("id: " + elem.getAttribute("s_id") + "\n");

        fw.write(
          "Név: " +
          elem.getElementsByTagName("nev").item(0).getTextContent() +
          "\n"
        );

        fw.write(
          "Ár: " +
          elem.getElementsByTagName("ar").item(0).getTextContent() +
          "\n"
        );

        fw.write(
          "Kalória: " +
          elem.getElementsByTagName("kaloria").item(0).getTextContent() +
          "\n"
        );

        int len = elem.getElementsByTagName("tulajdonsag").getLength();

        fw.write("tulajdonságok: \n");
        for (int j = 0; j < len; j++) {
          fw.write(
            elem.getElementsByTagName("tulajdonsag").item(j).getTextContent() +
            "\n"
          );
        }
        fw.write("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Vásárlók kiírása
   * @param nl Vásárló NodeList objektum
   */
  public void printVasarlo(NodeList nl) {
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
      System.out.println("");
    }
  }

  private void writeVasarloToTxt(NodeList nl) {
    try (FileWriter fw = new FileWriter("text.txt", true)) {
      fw.write("Vásárlók: \n");

      for (int i = 0; i < nl.getLength(); i++) {
        Element elem = (Element) nl.item(i);

        fw.write("id: " + elem.getAttribute("v_id") + "\n");

        fw.write(
          "Név: " +
          elem.getElementsByTagName("vezeteknev").item(0).getTextContent() +
          " " +
          elem.getElementsByTagName("keresztnev").item(0).getTextContent() +
          "\n"
        );

        fw.write(
          "Törzsvásárló: " +
          elem.getElementsByTagName("torzsvasarlo").item(0).getTextContent() +
          "\n"
        );

        fw.write(
          "Életkor: " +
          elem.getElementsByTagName("eletkor").item(0).getTextContent() +
          "\n"
        );

        fw.write(
          "Nem: " +
          elem.getElementsByTagName("nem").item(0).getTextContent() +
          "\n"
        );
        fw.write("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Tranzakciók kiírása
   * @param nl Tranzakció NodeList objektum
   */
  public void printTranzakcio(NodeList nl) {
    System.out.println("Tranzakciók: ");

    for (int i = 0; i < nl.getLength(); i++) {
      Element elem = (Element) nl.item(i);

      System.out.println(
        "Dátum: " + elem.getElementsByTagName("datum").item(0).getTextContent()
      );
      System.out.println("");
    }
  }

  private void writeTranzakcioToTxt(NodeList nl) {
    try (FileWriter fw = new FileWriter("text.txt", true)) {
      fw.write("Tranzakciók: \n");

      for (int i = 0; i < nl.getLength(); i++) {
        Element elem = (Element) nl.item(i);

        fw.write(
          "Dátum: " +
          elem.getElementsByTagName("datum").item(0).getTextContent() +
          "\n"
        );
        fw.write("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Teljes Dokumentum kiírása
   */
  public void printXml() {
    printCukraszdak(doc.getElementsByTagName("cukraszda"));
    printCukrasz(doc.getElementsByTagName("cukrasz"));
    printSutemenyLista(doc.getElementsByTagName("sutemeny_lista"));
    printSutemeny(doc.getElementsByTagName("sutemeny"));
    printVasarlo(doc.getElementsByTagName("vasarlo"));
    printTranzakcio(doc.getElementsByTagName("tranzakcio"));
  }

  public void writeToTxt() {
    writeCukraszdaToTxt(doc.getElementsByTagName("cukraszda"));
    writeCukraszToTxt(doc.getElementsByTagName("cukrasz"));
    writeSutemenyListaToTxt(doc.getElementsByTagName("sutemeny_lista"));
    writeSutemenyToTxt(doc.getElementsByTagName("sutemeny"));
    writeVasarloToTxt(doc.getElementsByTagName("vasarlo"));
    writeTranzakcioToTxt(doc.getElementsByTagName("tranzakcio"));
  }

  /**
   * Tetszőleges gyökérelem kiírása
   * @param nodeList gyökérelem
   */
  public void printNodes(NodeList nodeList) {
    try {
      switch (nodeList.item(0).getNodeName()) {
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
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }
}
