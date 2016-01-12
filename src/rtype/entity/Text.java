package rtype.entity;

import org.lwjgl.opengl.GL11;

import rtype.Main;

/**
 * Created by jhooba on 2015-12-20.
 */
public class Text extends AnimatedEntity {
  private String string;

  public Text(String string) {
    super(FONT, 0.4f, 0f);
    setString(string);
    init();
  }

  public void setString(String string) {
    this.string = string;
  }

  @Override
  public void update() {
  }

  @Override
  public void draw() {
    GL11.glLoadIdentity();
    GL11.glTranslatef(position.x, position.y, Main.DEFAULT_Z);
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    for (char c : string.toCharArray()) {
      drawChar(c);
      GL11.glTranslatef(width + 1, 0, 0);
    }
  }

  private void drawChar(char c) {
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, animationTextures[c].getTextureId());
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
