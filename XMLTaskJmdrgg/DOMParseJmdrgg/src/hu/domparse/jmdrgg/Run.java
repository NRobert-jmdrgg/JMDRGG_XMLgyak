package hu.domparse.jmdrgg;

import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class Run {

  public static void main(String[] args) {
    try {
      final String filename = "./XMLjmdrgg.xml";
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();

      Document doc = builder.parse(filename);

      DomReadJmdrgg dr = new DomReadJmdrgg(doc);
      DomQueryJmdrgg dq = new DomQueryJmdrgg(doc);
      DomModifyJmdrgg dm = new DomModifyJmdrgg(doc, filename);
      // dr.writeToTxt();
      // dq.queryById("vasarlo", "v1");
      // dq.queryVasarloByName("Nagy", "Róbert");
      // dq.queryCukraszByName("Tompa", "Tamás");
      // dq.querySutemenyByKaloria(400);
      // dm.addVasarlo("Pista", "Laci", false, 40, "férfi");
      // dm.removeById("vasarlo", "v4");
      // String[] tuls = { "klasszikus", "édes" };
      // dq.querySutemenyByTulajdonsag(tuls);
      // System.out.println("elotte: ");
      // dr.printNodes();
      // dm.removeByQuery(dq.querySutemenyByTulajdonsag(tuls));
      // System.out.println("utanna: ");
      // dr.printNodes(dq.querySutemenyByTulajdonsag(tuls));
      // Random r = new Random();
      // dm.modifyNode(
      //   dq.querySutemenyByKaloria(200),
      //   "kaloria",
      //   Integer.toString((r.nextInt(100) + 200))
      // );

      // dr.printXml();

      dq.queryCukraszdaByName("asd");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
