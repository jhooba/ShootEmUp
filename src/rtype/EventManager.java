package rtype;

import java.util.ArrayList;

/**
 * Created by jhooba on 2015-12-20.
 */
public class EventManager {
  private static EventManager instance;

  private final ArrayList<KeyListener> listeners = new ArrayList<>();

  public static EventManager instance() {
    if (instance == null) {
      instance = new EventManager();
    }
    return instance;
  }

  public void addListener(int key, KeyListener listener) {
    listener.setKeyMonitored(key);
    listeners.add(listener);
  }

  public void clear() {
    listeners.clear();
  }

  public void checkEvents() {
    for (KeyListener listener : listeners) {
      listener.checkKey();
    }
  }
}
