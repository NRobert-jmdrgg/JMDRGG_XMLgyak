package jmdrgg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.json.*;

class ObjectJmdrgg {

  public static void main(String[] args) {
    try {
      String json = Files.readString(Path.of("./JSONjmdrgg.json"));
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
