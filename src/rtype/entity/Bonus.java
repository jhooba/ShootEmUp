package rtype.entity;

import org.lwjgl.opengl.GL11;
import rtype.Main;

/**
 * Created by jhooba on 2015-12-20.
 */
public abstract class Bonus extends AnimatedEntity {
  public static final int BONUS_COUNT = 5;

  protected Bonus(int type) {
    super(type, 0.25f, 15);
  }

  @Override
  public void draw() {
    updateAnimationCursor();

    GL11.glLoadIdentity();
    GL11.glTranslatef(position.x, position.y, Main.DEFAULT_Z);

    GL11.glBindTexture(GL11.GL_TEXTURE_2D, getCursorAnimationTextureId());
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

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
  public boolean collided(Entity entity) {
    // This bonus should only collide with playerShip
    boostPlayerShip((PlayerShip)entity);
    Implosion imp = new Implosion();
    imp.spawn(position, speed, Main.fx);
    unSpawn();
    return true;
  }

  public abstract void boostPlayerShip(PlayerShip ship);

  @Override
  public void update() {
    interpolate(position, speed);
    if (position.x - width > Main.SCREEN_WIDTH / 2
        || position.x + width < - Main.SCREEN_WIDTH / 2) {
      unSpawn();
      if (Logger.isLogActivate) {
        Logger.log(getClass().getName() + " died");
      }
    }
  }
}
