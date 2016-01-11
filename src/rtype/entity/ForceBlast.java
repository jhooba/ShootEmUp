package rtype.entity;

import org.lwjgl.opengl.GL11;

/**
 * Created by jhooba on 2016-01-03.
 */
public class ForceBlast extends Entity {
  public ForceBlast(float chargePercentage) {
    super(FORCE_BLAST, chargePercentage / 1.8f);
    life = 300 * chargePercentage;
    damage = 300 * chargePercentage;
    init();
  }

  @Override
  public void draw() {
    GL11.glColor4f(1, 1, 1, 0.85f);
    super.draw();
  }
}
