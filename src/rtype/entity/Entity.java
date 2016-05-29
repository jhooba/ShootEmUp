package rtype.entity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import rtype.Layer;
import rtype.Main;
import rtype.Texture;

/**
 * Created by jhooba on 2015-12-20.
 */
public abstract class Entity implements IEntity {
  private static final int ROTATION_SPEED = 260;
  private static final Vector2f SPEED_UP = new Vector2f(60, 35);
  private static final Vector2f SPEED_DOWN = new Vector2f(30, -23);

  protected final int type;
  protected final float ratio;
  protected Texture texture;
  protected float originalWidth;
  protected float originalHeight;

  public final Vector2f position = new Vector2f();
  public final Vector2f speed = new Vector2f();

  protected Layer layer = null;

  protected float textureDown = 0;
  protected float textureUp = 1;
  protected float textureLeft = 0;
  protected float textureRight = 1;

  protected float tick;

  private float freezeSpeed = 0;
  public float rotation = 0;
  public float width;
  public float height;

  private float rotationSpeed = 0;
  protected float damage = 1;
  protected float life = 1;
  protected boolean freezing = false;
  protected boolean frozen = false;
  private float frozenTickCounter = 0;
  private float freezeDuration = 0;
  private float freezingPercentage = 0;
  protected float distanceFromOrb = 0;

  protected Entity(int type, float ratio) {
    this.type = type;
    this.ratio = ratio;
  }

  protected void init() {
    texture = Main.textureLoader.getTexture(type);
    originalWidth = texture.getTextureWidth();
    originalHeight = texture.getTextureHeight();
    width = originalWidth * ratio;
    height = originalHeight * ratio;
  }

  public void spawn(Vector2f position, Vector2f speed, Layer layer) {
    this.position.x = position.x;
    this.position.y = position.y;

    this.speed.x = speed.x;
    this.speed.y = speed.y;

    this.layer = layer;
    this.layer.add(this);
  }

  public void spawn(Vector2f position, Vector2f speed, Layer layer, float rotationSpeed) {
    spawn(position, speed, layer);
    this.rotationSpeed = rotationSpeed;
  }

  public void unSpawn() {
    layer.remove(this);
  }

  public void draw() {
    GL11.glLoadIdentity();
    GL11.glTranslatef(position.x, position.y, Main.DEFAULT_Z);
    GL11.glRotatef(rotation, 0, 0, 1);
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureId());
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

  protected Vector2f interpolate(Vector2f oldPosition, Vector2f speed) {
    oldPosition.x += tick * speed.x;
    oldPosition.y += tick * speed.y;
    return oldPosition;
  }

  public void flipYAxis() {
    float tmp = textureLeft;
    textureLeft = textureRight;
    textureRight = tmp;
  }

  public void update() {
    interpolate(position, speed);
    rotation += rotationSpeed * tick;
    if (position.x - width > Main.SCREEN_WIDTH / 2
        || position.x + width < - Main.SCREEN_WIDTH / 2) {
      unSpawn();
      if (Logger.isLogActivate) {
        Logger.log(getClass().getName() + " died");
      }
    }
  }

  public boolean collided(Entity entity) {
    if (entity == this) {
      return false;
    }
    life -= entity.damage;
    if (life < 0) {
      unSpawn();

      Explosion ex = new Explosion(Main.RANDOM.nextInt(2) + EXPLOSION_1);
      ex.spawn(position, speed, Main.frontground, Main.RANDOM.nextInt(ROTATION_SPEED));

      EnemyPiece ep = new EnemyPiece(Main.RANDOM.nextInt(8) + ENEMY_PIECE_1);
      ep.spawn(position, SPEED_UP, Main.frontground, Main.RANDOM.nextInt(ROTATION_SPEED));

      ep = new EnemyPiece(Main.RANDOM.nextInt(8) + ENEMY_PIECE_1);
      ep.spawn(position, SPEED_DOWN, Main.frontground, Main.RANDOM.nextInt(ROTATION_SPEED));

      ep = new EnemyPiece(Main.RANDOM.nextInt(8) + ENEMY_PIECE_1);
      ep.spawn(position, new Vector2f(entity.speed.x, speed.y), Main.frontground, Main.RANDOM.nextInt(ROTATION_SPEED));

      ep = new EnemyPiece(Main.RANDOM.nextInt(8) + ENEMY_PIECE_1);
      ep.spawn(position, new Vector2f(entity.speed.x, -speed.y), Main.frontground, Main.RANDOM.nextInt(ROTATION_SPEED));
      return true;
    }
    return false;
  }

  public void updateTick() {
    if (freezing) {
      if (frozen) {
        // You may wonder why I multiply by 10.
        // It is because on very "fast" computer, fps
        // may be so high and tick so small that
        // frozenTickCounter get no updated and
        // unfreeze never happen
        frozenTickCounter += tick * 10;
        if (frozenTickCounter > freezeDuration) {
          frozen = false;
          frozenTickCounter = 0;
          // Have to start defroze
          freezeSpeed = -freezeSpeed;
        } else {
          tick = 0;
        }
      } else {
        // Update tick according to freeze Speed
        // Note this can be the froze or defroze process...
        freezingPercentage += Main.tick * freezeSpeed;
        tick = Main.tick - freezingPercentage * Main.tick;
        if (tick < 0) {
          frozen = true;
          unSpawn();
          spawn(position, speed, Main.bullets);
        }
        if (tick > Main.tick) {
          freezingPercentage = 0;
          freezing = false;
        }
      }
    } else {
      tick = Main.tick;
    }
  }

  protected void freeze(float freezeDuration, float freezeSpeed) {
    this.freezeDuration = freezeDuration;
    this.freezeSpeed = freezeSpeed;
    freezing = true;
  }

  public Layer getLayer() {
    return layer;
  }
}
