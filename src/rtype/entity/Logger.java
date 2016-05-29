package rtype.entity;

/**
 * Copied from http://www.fabiensanglard.net/Prototyp/index.php
 * Created by jhooba on 2015-12-30.
 */
public class Logger {
  public static boolean isLogActivate = false;

  public static void log(String str) {
    if (isLogActivate) {
      System.out.println(str);
    }
  }
}
