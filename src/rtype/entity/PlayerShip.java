package rtype.entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
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
  private static final float ACCELERATION = 1000;
  private static final float MAX_SPEED = 230;
  private static final float DEFAULT_DECELERATION = 700;
  private static final float POWER_SPEED = 2f;

  private final Blaster concentrateAnimation = new Blaster(this);
  private final PlayerSpeed accelerateEntity = new PlayerSpeed(this);
  public Orb orb = null;
  private float xDeceleration = 0;
  private float yDeceleration = 0;
  public float power = 0;
  public int hiScore = 1;
  private BitUpgrade booster1 = null;
  private BitUpgrade booster2 = null;

  public PlayerShip() {
    super(PLAYER_SHIP, 0.25f, 0f);
    init();
    concentrateAnimation.spawn(new Vector2f(0, 0), new Vector2f(0, 0), Main.fx);
    accelerateEntity.spawn(new Vector2f(0, 0), new Vector2f(0, 0), Main.fx);
    addEventListeners();
  }

  public void addBooster() {
    addBooster(Main.bullets);
  }

  public void addBooster(Layer layer) {
    if (booster1 == null) {
      booster1 = new BitUpgrade(this);
      booster1.spawn(new Vector2f(5, 20), new Vector2f(500, 0), layer);
      return;
    }
    if (booster2 == null) {
      booster2 = new BitUpgrade(this);
      booster2.spawn(new Vector2f(5, -20), new Vector2f(500, 0), layer);
    }
  }

  @Override
  public void draw() {
    GL11.glLoadIdentity();
    // TODO 왜 넣었다가 빼는 거냐
    GL11.glPushMatrix();
    GL11.glPopMatrix();

    GL11.glTranslatef(position.x, position.y, Main.DEFAULT_Z);
    int animationFrame = (int)(speed.y * 4 / MAX_SPEED);
    animationFrame += 4;

    GL11.glBindTexture(GL11.GL_TEXTURE_2D, animationTextures[animationFrame].getTextureId());
    GL11.glColor4f(1, 1, 1, 1);
    GL11.glBegin(GL11.GL_QUADS);
    {
      GL11.glTexCoord2f(textureRight, textureUp);
      GL11.glVertex2f(width, -height);

      GL11.glTexCoord2f(textureLeft, textureUp);
      GL11.glVertex2f(-width, -height);

      GL11.glTexCoord2f(textureLeft, textureDown);
      GL11.glVertex2f(-width, height);

      GL11.glTexCoord2f(textureRight, textureDown);
      GL11.glVertex2f(width, height);
    }
    GL11.glEnd();
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
        speed.y += ACCELERATION * tick;
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
        speed.y -= ACCELERATION * tick;
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
        speed.x -= ACCELERATION * tick;
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
        speed.x += ACCELERATION * tick;
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
        if (booster1 != null) {
          booster1.fire(0);
        }
        if (booster2 != null) {
          booster2.fire(0);
        }
      }
      @Override
      public void keyPressed() {
        power += POWER_SPEED * Main.tick;
        if (power > MAX_POWER) {
          power = MAX_POWER;
        }
      }
      @Override
      public void onKeyUp() {
        // Fire blast if power is high enough...
        if (power > MAX_POWER / 10) {
          concentrateAnimation.fire(power / MAX_POWER);
          if (orb != null) {
            orb.fire(power / MAX_POWER);
          }
        }
        power = 0;
        concentrateAnimation.stopChargeAnimation();
        if (orb != null) {
          orb.stopChargeAnimation();
        }
      }
    };
    EventManager.instance().addListener(Keyboard.KEY_SPACE, fire1KeyEvent);
  }

  @Override
  public boolean collided(Entity entity) {
    return false;
  }

  @Override
  protected Vector2f interpolate(Vector2f oldPosition, Vector2f speed) {
    oldPosition.x = oldPosition.x + tick * speed.x;
    if (oldPosition.x > Main.SCREEN_WIDTH / 2) {
      oldPosition.x = Main.SCREEN_WIDTH / 2;
    } else if (oldPosition.x < -Main.SCREEN_WIDTH / 2) {
      oldPosition.x = -Main.SCREEN_WIDTH / 2;
    }

    oldPosition.y = oldPosition.y + tick * speed.y;
    if (oldPosition.y > Main.SCREEN_HEIGHT / 2) {
      oldPosition.y = Main.SCREEN_HEIGHT / 2;
    } else if (oldPosition.y < -Main.SCREEN_HEIGHT / 2) {
      oldPosition.y = -Main.SCREEN_HEIGHT /2;
    }
    return oldPosition;
  }

  @Override
  public void update() {
    interpolate(position,speed);

    float oldSpeedX = speed.x;
    speed.x += xDeceleration * tick;
    if (oldSpeedX * speed.x < 0) {
      xDeceleration = 0;
      speed.x = 0;
    }
    float oldSpeedY = speed.y;
    speed.y += yDeceleration * tick;
    if (oldSpeedY * speed.y < 0) {
      yDeceleration = 0;
      speed.y = 0;
    }
  }

  public void setOrb(Orb orb) {
    if (this.orb != null) {
      if (this.orb.type == orb.type) {
        return;
      }
      this.orb.unSpawn();
    }
    this.orb = orb;
    this.orb.spawn(new Vector2f(-430, 0), new Vector2f(0, 0), Main.bullets);
  }
}
