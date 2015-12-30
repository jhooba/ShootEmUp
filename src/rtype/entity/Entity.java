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
  public final Vector2f position = new Vector2f();
  public final Vector2f speed = new Vector2f();

  private Layer layer = null;

  private float textureDown = 0;
  private float textureUp = 1;
  private float textureLeft = 0;
  private float textureRight = 1;

  private float tick;

  public float rotation = 0;
  public float width;
  public float height;
  private float rotationSpeed = 0;

  private Texture texture;
  private float damage = 1;
  private float life = 1;

  public void spawnint(Vector2f position, Vector2f speed, Layer layer) {
    this.position.x = position.x;
    this.position.y = position.y;

    this.speed.x = speed.x;
    this.speed.y = speed.y;

    this.layer = layer;
    this.layer.add(this);
  }

  public void spawn(Vector2f position, Vector2f speed, float rotationSpeed, Layer layer) {
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

  private Vector2f interpolate(Vector2f oldPosition, Vector2f speed) {
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

      Explosion ex = new Explosion(Main.RANDOM.nextInt(2) + EXPLOSION1);
      ex.spawn(position, speed, Main.RANDOM.nextInt(ROTATION_SPEED), Main.frontground);

      EnemyPiece ep1 = new EnemyPiece(Main.RANDOM.nextInt(8) + ENEMY_PIECE1);
    }
  }

  public void updateTick() {

  }
}
