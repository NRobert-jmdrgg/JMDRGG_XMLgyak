package jmdrgg;

import org.json.simple.JSONObject;

class ObjectJmdrgg {

  public static void main(String[] args) {
    JSONObject obj = new JSONObject();

    obj.put("nev", "Nagy Róbert");
    obj.put("fizetes", "0");
    obj.put("kor", "21");

    System.out.println(obj.toString());
  }
}
