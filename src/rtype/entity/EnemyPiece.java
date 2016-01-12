package rtype.entity;

import org.lwjgl.opengl.GL11;
import rtype.Main;

/**
 * Created by jhooba on 2015-12-31.
 */
public class EnemyPiece extends Entity {
  private float alpha = 1;
  private static final float DISAPPEARANCE_SPEED = 0.65f;

  public EnemyPiece(int type) {
    super(type, 0.13f);
    init();
  }

  @Override
  public void draw() {
    if (alpha < 0) {
      unSpawn();
      if (Logger.isLogActivate) {
        Logger.log("Removed enemy-piece from explosion layer");
      }
      return;
    }
    alpha -= DISAPPEARANCE_SPEED * tick;

    GL11.glLoadIdentity();
    GL11.glTranslatef(position.x, position.y, Main.DEFAULT_Z);
    GL11.glRotatef(rotation, 0, 0, 1);

    GL11.glColor4f(1, 1, 1, alpha);
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureId());
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
    GL11.glColor4f(1, 1, 1, 1);
  }
}
