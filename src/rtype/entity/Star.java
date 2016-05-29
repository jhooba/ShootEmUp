package rtype.entity;

import org.lwjgl.opengl.GL11;

/**
 * Copied from http://www.fabiensanglard.net/Prototyp/index.php
 * Created by jhooba on 2015-12-28.
 */
public class Star extends Entity {
  public Star(int type) {
    super(type, 1.0f);
    init();
  }

  @Override
  public void draw() {
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    super.draw();
  }
}
