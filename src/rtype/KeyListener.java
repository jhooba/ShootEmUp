package rtype;

import org.lwjgl.input.Keyboard;

/**
 * Created by jhooba on 2015-12-20.
 */
abstract public class KeyListener {
  private int keyMonitored;
  private boolean keyMonitoredWasPressed;

  public void setKeyMonitored(int key) {
    keyMonitored = key;
  }

  public void checkKey() {
    if (Keyboard.isKeyDown(keyMonitored)) {
      if (keyMonitoredWasPressed) {
        keyPressed();
      } else {
        keyMonitoredWasPressed = true;
        onKeyDown();
      }
    } else {
      if (keyMonitoredWasPressed) {
        onKeyUp();
        keyMonitoredWasPressed = false;
      }
    }
  }

  public void onKeyDown() {}
  public void onKeyUp() {}
  public void keyPressed() {}
}
