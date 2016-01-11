package rtype.entity;

import org.lwjgl.opengl.GL11;
import rtype.Main;

/**
 * Created by jhooba on 2016-01-11.
 */
public class Smoke extends AnimatedEntity {
  public Smoke() {
    super(SMOKE, 1.0f);
    animationSpeed = 16;
    init();
  }

  @Override
  public void draw() {
    if (animationCursor == animationTextures.length - 1) {
      unSpawn();
      if (Logger.isLogActivate) {
        Logger.log("Removed explosion from explosion layer");
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
      GL11.glColor4f(1, 1, 1, 2 / animationCursor);
      GL11.glTexCoord2f(textureRight, textureUp);
      GL11.glVertex2f(width, -height);

      GL11.glTexCoord2f(textureRight, textureUp);
      GL11.glVertex2f(-width, -height);

      GL11.glTexCoord2f(textureRight, textureDown);
      GL11.glVertex2f(-width, height);

      GL11.glTexCoord2f(textureRight, textureDown);
      GL11.glVertex2f(width, height);
    }
    GL11.glEnd();
  }
}
