package rtype.entity;

import org.lwjgl.opengl.GL11;

/**
 * Created by jhooba on 2015-12-27.
 */
public class Planet extends Entity {

  public Planet() {
    super(PLANET, 0.65f);
    rotation = 180;
    init();
  }

  @Override
  public void draw() {
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    super.draw();
  }
}
