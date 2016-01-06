package rtype.entity;

import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import rtype.Layer;
import rtype.Main;

/**
 * Created by jhooba on 2015-12-27.
 */
public class HomingMissile extends AnimatedEntity {
  private final Entity target;

  public HomingMissile(Layer targetLayer, float maxRadianManiability) {
    super(MISSILE, 0.25f);
    target = acquireNewTarget(targetLayer);
    init();
  }

  @Nullable
  private Entity acquireNewTarget(Layer targetLayer) {
    if (targetLayer.entities.size() == 0) {
      return null;
    }
    return targetLayer.entities.get(0);
  }

  @Override
  public void draw() {
    animationCursor += animationSpeed * tick;
    animationCursor %= animationTextures.length;

    GL11.glLoadIdentity();
    GL11.glTranslatef(position.x, position.y, Main.DEFAULT_Z);
    GL11.glRotatef(rotation, 0, 0, 1);
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, animationTextures[(int)animationCursor].getTextureId());
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
  public void update() {
    super.update();
  }
}
