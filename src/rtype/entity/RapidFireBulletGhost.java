package rtype.entity;

import org.lwjgl.opengl.GL11;
import rtype.Main;

/**
 * Copied from http://www.fabiensanglard.net/Prototyp/index.php
 * Created by jhooba on 2016-01-01.
 */
public class RapidFireBulletGhost extends Entity {
  private static final float ALPHA_SPEED = 9f;
  private int alphaCursor = 1;

  public RapidFireBulletGhost(float rotation) {
    super(BULLET_RAPID_FIRE, 0.5f);
    this.rotation = rotation;
    init();
  }

  @Override
  public void draw() {
    if (alphaCursor < 0) {
      unSpawn();
      if (Logger.isLogActivate) {
        Logger.log("Removed explosion from explosion layer.");
      }
      return;
    }
    alphaCursor -= ALPHA_SPEED * tick;

    GL11.glLoadIdentity();
    GL11.glTranslatef(position.x, position.y, Main.DEFAULT_Z);
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureId());
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    GL11.glBegin(GL11.GL_QUADS);
    {
      GL11.glColor4f(1, 1, 1, alphaCursor);
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
}
