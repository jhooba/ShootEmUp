package rtype.entity;

import org.lwjgl.opengl.GL11;
import rtype.Main;

/**
 * Created by jhooba on 2016-01-01.
 */
public class BulletHit extends AnimatedEntity {
  protected BulletHit(int type) {
    super(type, 0.5f);
    init();
    animationSpeed = 15;
  }

  @Override
  public void draw() {
    if (animationCursor == animationTextures.length - 1) {
      unSpawn();
      if (Logger.isLogActivate) {
        Logger.log("Removed explosion from explosion layer.");
      }
      return;
    }
    animationCursor += animationSpeed * tick;
    animationCursor %= animationTextures.length;

    GL11.glLoadIdentity();
    GL11.glTranslatef(position.x, position.y, Main.DEFAULT_Z);
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, animationTextures[(int)animationCursor].getTextureId());
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
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
}
