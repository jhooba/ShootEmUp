package rtype.entity;

import org.lwjgl.util.vector.Vector2f;
import rtype.Main;

/**
 * Created by jhooba on 2015-12-30.
 */
public class RapidFireOrb extends Orb {
  private static final float FIRE_RATE_LIMIT = 2;
  private static final float BULLET_FIRE_RATE = 30;

  private float bulletsToFire = 0;
  private float bulletTimeCounter = 0;

  public RapidFireOrb(PlayerShip player) {
    super(player, PINK_ORB);
    init();
  }

  @Override
  public void update() {
    super.update();
    if (bulletsToFire > 0) {
      bulletTimeCounter += BULLET_FIRE_RATE * tick;
      if (bulletTimeCounter > FIRE_RATE_LIMIT) {
        --bulletsToFire;
        bulletTimeCounter = 0;
        RapidFireBullet bu = new RapidFireBullet(rotation);
        final float BULLETS_SPEED = 1000;
        bu.spawn(position,
            new Vector2f((float)(Math.cos(rotationRadians) * BULLETS_SPEED),
                (float)(Math.cos(rotationRadians) * BULLETS_SPEED)),
            Main.bullets);
      }
      if (bulletsToFire < 0) {
        distanceFromShipRequested = DEFAULT_DISTANCE_FROM_SHIP;
      }
    }
  }
}
