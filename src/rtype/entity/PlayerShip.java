package rtype.entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;
import rtype.EventManager;
import rtype.KeyListener;
import rtype.Layer;
import rtype.Main;

/**
 * Created by jhooba on 2015-12-20.
 */
public class PlayerShip extends AnimatedEntity {
  public static final float MAX_POWER = 4.f;
  private static final float ACCELLERATION = 1000;
  private static final float MAX_SPEED = 230;
  private static final float DEFAULT_DECELERATION = 700;

  private final Blaster concentrateAnimation = new Blaster(this);
  private final PlayerSpeed accelerateEntity = new PlayerSpeed(this);
  private Orb orb = null;
  private float xDeceleration = 0;
  private float yDeceleration = 0;

  public PlayerShip() {
    super(PLAYER_SHIP, 0.25f);
    init();
    concentrateAnimation.spawn(new Vector2f(0, 0), new Vector2f(0, 0), Main.fx);
    accelerateEntity.spawn(new Vector2f(0, 0), new Vector2f(0, 0), Main.fx);
    addEventListeners();
  }

  public void addEventListeners() {
    KeyListener fire2KeyEvent = new KeyListener() {
      @Override
      public void keyPressed() {
        if (orb != null) {
          orb.setMove(Orb.ADJUSTING);
        }
      }
      @Override
      public void onKeyUp() {
        if (orb != null) {
          orb.setMove(Orb.STICKED);
        }
      }
    };
    EventManager.instance().addListener(Keyboard.KEY_X, fire2KeyEvent);

    KeyListener upKeyEvent = new KeyListener() {
      @Override
      public void keyPressed() {
        speed.y += ACCELLERATION * tick;
        if (speed.y > MAX_SPEED) {
          speed.y = MAX_SPEED;
        }
      }
      @Override
      public void onKeyUp() {
        yDeceleration = -DEFAULT_DECELERATION;
      }
    };
    EventManager.instance().addListener(Keyboard.KEY_UP, upKeyEvent);

    KeyListener downKeyEvent = new KeyListener() {
      @Override
      public void keyPressed() {
        speed.y -= ACCELLERATION * tick;
        if (speed.y < -MAX_SPEED) {
          speed.y = -MAX_SPEED;
        }
      }
      @Override
      public void onKeyUp() {
        yDeceleration = DEFAULT_DECELERATION;
      }
    };
    EventManager.instance().addListener(Keyboard.KEY_DOWN, downKeyEvent);

    KeyListener leftKeyEvent = new KeyListener() {
      @Override
      public void keyPressed() {
        speed.x -= ACCELLERATION * tick;
        if (speed.x < -MAX_SPEED) {
          speed.x = -MAX_SPEED;
        }
      }
      @Override
      public void onKeyUp() {
        xDeceleration = DEFAULT_DECELERATION;
      }
    };
    EventManager.instance().addListener(Keyboard.KEY_LEFT, leftKeyEvent);

    KeyListener rightKeyEvent = new KeyListener() {
      @Override
      public void keyPressed() {
        speed.x += ACCELLERATION * tick;
        if (speed.x > MAX_SPEED) {
          speed.x = MAX_SPEED;
          accelerateEntity.startAnimation();
        }
      }
      @Override
      public void onKeyUp() {
        accelerateEntity.stopAnimation();
        xDeceleration = -DEFAULT_DECELERATION;
      }
    };
    EventManager.instance().addListener(Keyboard.KEY_RIGHT, rightKeyEvent);

    KeyListener fire1KeyEvent = new KeyListener() {
      @Override
      public void onKeyDown() {
        concentrateAnimation.fire(0.1f);
        concentrateAnimation.startChargingAnimation();
        if (orb != null) {
          orb.startChargingAnimation();
        }
      }
      @Override
      public void keyPressed() {
      }
      @Override
      public void onKeyUp() {
      }
    };
    EventManager.instance().addListener(Keyboard.KEY_SPACE, fire1KeyEvent);
  }

  public void addBooster() {
  }

  public void addBooster(Layer layer) {
    
  }

  @Override
  public void draw() {

  }
}
