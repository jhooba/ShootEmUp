package rtype.entity;

import org.lwjgl.opengl.GL11;

/**
 * Copied from http://www.fabiensanglard.net/Prototyp/index.php
 * Created by jhooba on 2015-12-27.
 */
public class SpaceTrash extends Entity {
  public SpaceTrash(int type) {
    super(type, 0.5f);
    init();
  }

  @Override
  public void draw() {
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    super.draw();
  }
}
