package rtype.entity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import rtype.Main;

/**
 * Copied from http://www.fabiensanglard.net/Prototyp/index.php
 * Created by jhooba on 2016-01-02.
 */
public class Blaster extends Weapon {
  public Blaster(PlayerShip playerShip) {
    super(playerShip, FORCE_CHARGE, 0.55f, 28.4f);
    init();
  }

  @Override
  public void draw() {
    if (!displayChargeAnimation) {
      return;
    }
    GL11.glColor4f(0, 1, 1, 1);

    updateAnimationCursor();

    GL11.glLoadIdentity();
    GL11.glTranslatef(playerShip.position.x + width + 13, playerShip.position.y + 3, Main.DEFAULT_Z);
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, getCursorAnimationTextureId());
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

  public void fire(float chargePercentage) {
    ForceBlast fb = new ForceBlast(chargePercentage * 1.3f);
    fb.spawn(playerShip.position, new Vector2f(770, 0), Main.bullets);
  }

  @Override
  public void startChargingAnimation() {
    displayChargeAnimation = true;
  }

  @Override
  public void stopChargeAnimation() {
    displayChargeAnimation = false;
    animationCursor = 0;
  }
}
