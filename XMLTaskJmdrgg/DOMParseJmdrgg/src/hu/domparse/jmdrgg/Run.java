package hu.domparse.jmdrgg;

public class Run {

  public static void main(String[] args) {
    // DOMQueryJmdrgg.queryById("vasarlo", "v1");
    // DOMQueryJmdrgg.queryVasarloByName("Nagy", "Róbert");
    // DOMQueryJmdrgg.queryCukraszByName("Tompa", "Tamás");
    // String[] tuls = { "klasszikus", "édes" };
    // DOMQueryJmdrgg.querySutemenyByTulajdonsag(tuls);
    // DOMQueryJmdrgg.querySutemenyByKaloria(400);
    DomModifyJmdrgg.addVasarlo("Pista", "Laci", false, 40, "ferfi");
    // DomReadJmdrgg.printXml();
  }
}
