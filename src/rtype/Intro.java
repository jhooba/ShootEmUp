package rtype;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import rtype.entity.Planet;
import rtype.entity.Text;

/**
 * Created by jhooba on 2015-12-20.
 */
public class Intro {
  private static final Vector2f IMMOBILE = new Vector2f(0, 0);

  private final Layer layer;
  private boolean introOn = true;

  public Intro() {
    Planet planet = new Planet();
    planet.spawn(new Vector2f(320, 0), new Vector2f(-7, 0), Main.background);
    layer = new Layer();

    Text title = new Text(" . Shoot 'Em Up .");
    Text commandLabel = new Text("- Commands -");
    Text commandLabel0 = new Text("P         : Pause");
    Text commandLabel1 = new Text("F1        : Start homing missile");
    Text commandLabel2 = new Text("F2        : Start enemy waves");
    Text commandLabel3 = new Text("X         : Detach/Move Orb");
    Text commandLabel4 = new Text("Arrow Key : Move");
    Text commandLabel5 = new Text("Space     : Fire ( maintain to charge)");
    Text commandLabel6 = new Text("Press SPACE to Start !!");

    int pointY = 240;
    int pointX = -270;
    final int INTERSPACE = 20;

    title.spawn(new Vector2f(pointX + 160, pointY -= INTERSPACE * 4), IMMOBILE, layer);
    commandLabel.spawn(new Vector2f(pointX + 200, pointY -= INTERSPACE * 4), IMMOBILE, layer);
    commandLabel0.spawn(new Vector2f(pointX, pointY -= INTERSPACE * 2), IMMOBILE, layer);
    commandLabel1.spawn(new Vector2f(pointX, pointY -= INTERSPACE), IMMOBILE, layer);
    commandLabel2.spawn(new Vector2f(pointX, pointY -= INTERSPACE), IMMOBILE, layer);
    commandLabel3.spawn(new Vector2f(pointX, pointY -= INTERSPACE), IMMOBILE, layer);
    commandLabel4.spawn(new Vector2f(pointX, pointY -= INTERSPACE), IMMOBILE, layer);
    commandLabel5.spawn(new Vector2f(pointX, pointY -= INTERSPACE), IMMOBILE, layer);
    commandLabel6.spawn(new Vector2f(pointX, pointY - INTERSPACE), IMMOBILE, layer);

    KeyListener space = new KeyListener() {
      @Override
      public void onKeyUp() {
        introOn = false;
        Main.timer.resume();
      }
    };
    EventManager.instance().addListener(Keyboard.KEY_SPACE, space);
  }

  public void play() {
    Main.timer.pause();
    while (introOn) {
      Main.update();
      Main.render();
      layer.render();

      Display.update();

      if (Main.exitRequested()) {
        introOn = false;
        Main.gameOff = true;
      }
      EventManager.instance().checkEvents();
    }
    EventManager.instance().clear();
  }
}
