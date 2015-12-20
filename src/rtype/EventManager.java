package rtype;

/**
 * Created by jhooba on 2015-12-20.
 */
public class EventManager {
  private static EventManager instance;

  public static EventManager instance() {
    if (instance == null) {
      instance = new EventManager();
    }
    return instance;
  }

  public void addListener(int key, KeyListener pauseKeyEvent) {
  }

  public void checkEvents() {

  }
}
