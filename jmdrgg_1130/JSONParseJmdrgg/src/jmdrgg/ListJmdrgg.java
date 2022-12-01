package jmdrgg;

import java.util.ArrayList;
import org.json.simple.JsonArray;

public class ListJmdrgg {

  public static void main(String[] args) {
    var list = new ArrayList<String>();
    list.add("Nagy Róbert");
    list.add("0");
    list.add("21");
    JsonArray ja = new JsonArray(list);
    System.out.println(ja.toString());
  }
}
