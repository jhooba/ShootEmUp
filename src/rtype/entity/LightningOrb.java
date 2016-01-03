package rtype.entity;

import org.lwjgl.util.vector.Vector2f;
import rtype.Main;

/**
 * Created by jhooba on 2015-12-30.
 */
public class LightningOrb extends Orb {
  private static final float FIRE_RATE_LIMIT = 2;
  private static final float MIN_BEAM_HEIGHT = 50;

  private float bulletsToFire = 0;
  private float bulletFireRate = 40;
  private float bulletTimeCounter = 0;

  public LightningOrb(PlayerShip playerShip) {
    super(playerShip, BLUE_ORB);
    init();
  }

  @Override
  public void update() {
    super.update();
    if (bulletsToFire > 0) {
      bulletTimeCounter += bulletFireRate * tick;
      if (bulletTimeCounter > FIRE_RATE_LIMIT) {
        --bulletsToFire;
        bulletTimeCounter = 0;
        fireBeam();
        fireBeam();
      }
    }
  }

  private void fireBeam() {
    Vector2f[] p = new Vector2f[5];
    p[0] = new Vector2f(0, 0);
    p[1] = new Vector2f(130, 0);
    p[2] = new Vector2f(350, 0);
    p[3] = new Vector2f(850, 0);
    p[4] = new Vector2f(1950, 0);

    OrbBeam ob = new OrbBeam(this, p, 2, true, 0.3f, 0.8f, 1, 0.85f, 20, MIN_BEAM_HEIGHT);
    ob.spawn(position, new Vector2f(-75.3f, 0), Main.fx);
  }
}
