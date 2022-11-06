package hu.domparse.jmdrgg;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class Run {

  private static final String filename = "./XMLjmdrgg.xml";
  private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

  public static void main(String[] args) {
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();

      Document doc = builder.parse(filename);

      DomReadJmdrgg dr = new DomReadJmdrgg(doc);
      DomQueryJmdrgg dq = new DomQueryJmdrgg(doc);
      DomModifyJmdrgg dm = new DomModifyJmdrgg(doc, filename);
      // dr.printXml();
      // dq.queryById("vasarlo", "v4");
      // dq.queryVasarloByName("Nagy", "Róbert");
      // dq.queryCukraszByName("Tompa", "Tamás");
      // String[] tuls = { "klasszikus", "édes" };
      // dq.querySutemenyByTulajdonsag(tuls);
      // dq.querySutemenyByKaloria(400);
      // dm.addVasarlo("Pista", "Laci", false, 40, "ferfi");
      dm.removeById("vasarlo", "v4");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
