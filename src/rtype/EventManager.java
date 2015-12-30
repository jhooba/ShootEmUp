package rtype;

import java.util.ArrayList;

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

  private ArrayList<KeyListener> listeners = new ArrayList<>();

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
