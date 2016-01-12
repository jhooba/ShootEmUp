package rtype.entity;

import org.lwjgl.opengl.GL11;
import rtype.Main;

/**
 * Created by jhooba on 2015-12-22.
 */
public class FireBall extends AnimatedEntity {
  private final Orb orb;

  public FireBall(Orb orb) {
    super(FIRE_BALL, 0.3f, 55f);
    this.orb = orb;
    init();
  }

  public void draw(float alphaPercentage) {
    updateAnimationCursor();

    GL11.glLoadIdentity();
    GL11.glTranslatef(orb.position.x, orb.position.y, Main.DEFAULT_Z);
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, getCursorAnimationTextureId());
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    GL11.glColor4f(1, 1, 1, alphaPercentage - 0.35f);

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

    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    GL11.glColor4f(1, 1, 1, 1);
  }

  @Override
  public void draw() {
    draw(1);
  }
}
