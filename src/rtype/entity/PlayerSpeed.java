package rtype.entity;

import org.lwjgl.opengl.GL11;
import rtype.Main;

/**
 * Copied from http://www.fabiensanglard.net/Prototyp/index.php
 * Created by jhooba on 2016-01-02.
 */
public class PlayerSpeed extends AnimatedEntity {
  private final PlayerShip playerShip;

  public PlayerSpeed(PlayerShip playerShip) {
    super(PLAYER_SPEED, 0.75f, 30.4f);
    this.playerShip = playerShip;
    setAnimationCursor(1);
    init();
  }

  @Override
  public void draw() {
    updateAnimationCursor();
    GL11.glColor4f(1, 1, 1, 1);
    GL11.glLoadIdentity();
    GL11.glTranslatef(playerShip.position.x - 58, playerShip.position.y - 10, Main.DEFAULT_Z);
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

  @Override
  public void stopAnimation() {
    displayAnimation = false;
    setAnimationCursor(1);
  }
}
