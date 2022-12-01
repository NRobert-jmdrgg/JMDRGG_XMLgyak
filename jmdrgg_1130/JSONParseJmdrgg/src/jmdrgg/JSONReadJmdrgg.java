package jmdrgg;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReadJmdrgg {

  public static void main(String[] args) {
    JSONParser parser = new JSONParser();
    try {
      JSONObject obj = (JSONObject) parser.parse(
        new FileReader("./vizsgakJmdrgg.json")
      );
      System.out.println(obj.toString());
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
  }
}
