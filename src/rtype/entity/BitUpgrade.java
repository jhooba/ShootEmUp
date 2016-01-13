package rtype.entity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import rtype.Layer;
import rtype.Main;

/**
 * Created by jhooba on 2016-01-06.
 */
public class BitUpgrade extends Weapon {
  private static final float VELOCITY = 2;

  private float xDiff = 0;
  private float yDiff = 0;
  private final Vector2f positionRelativeToPlayerShip = new Vector2f(0,0);

  public BitUpgrade(PlayerShip playerShip) {
    super(playerShip, BIT_UPGRADE, 0.40f, 30f);
    init();
  }

  @Override
  public void draw() {
    updateAnimationCursor();

    GL11.glLoadIdentity();
    GL11.glTranslatef(position.x, position.y, Main.DEFAULT_Z);
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, getCursorAnimationTextureId());
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

  @Override
  public void update() {
    // Modify speed
    xDiff = playerShip.position.x + positionRelativeToPlayerShip.x - position.x;
    yDiff = playerShip.position.y + positionRelativeToPlayerShip.y - position.y;
    float distanceFromShip = (float)Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    if (distanceFromShip > 10) {
      speed.x = xDiff * VELOCITY;
      speed.y = yDiff * VELOCITY;
    } else {
      speed.x = 0;
      speed.y = 0;
    }
    interpolate(position,speed);
  }

  @Override
  public boolean collided(Entity entity) {
    // Make undestructable
    return false;
  }

  @Override
  public void spawn(Vector2f position, Vector2f spawningPlace, Layer layer) {
    positionRelativeToPlayerShip.x = position.x;
    positionRelativeToPlayerShip.y = position.y;
    position.x = spawningPlace.x;
    position.y = spawningPlace.y;
    this.layer = layer;
    layer.add(this);
  }

  @Override
  public void startChargingAnimation() {
  }

  @Override
  public void stopChargeAnimation() {
  }

  @Override
  public void fire(float chargePercentage) {
    if (Main.enemies.entities.size() != 0) {

      HomingMissile m = new HomingMissile(Main.enemies, (float) Math.PI / 1000);
      m.spawn(position, new Vector2f(400, 0), Main.bullets);
    }
  }
}
