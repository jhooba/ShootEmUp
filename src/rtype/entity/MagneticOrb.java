package rtype.entity;

import rtype.Main;

import java.util.List;

/**
 * Created by jhooba on 2015-12-30.
 */
public class MagneticOrb extends Orb {
  private boolean attracting = false;
  private float attractionDuration = 10f;
  private float attractionTickCounter = 0;
  private boolean startedAttraction = false;
  private float attractionStrength = 300f;

  public MagneticOrb(PlayerShip playerShip) {
    super(playerShip, RED_ORB);
    init();
  }

  @Override
  public void update() {
    if (attracting) {
      if (attractionTickCounter > attractionDuration) {
        attractionTickCounter = 0;
        attracting = false;
      } else {
        calculateDistanceFromShip();
        if (startedAttraction || distanceFromShip > 250) {
          startedAttraction = true;
          List<Entity> enemies = Main.enemies.entities;
          attractionTickCounter += tick;
          for (Entity enemy : enemies) {
            float diffX = position.x - enemy.position.x;
            float diffY = position.y - enemy.position.y;

            float diffXcarre = diffX * diffX;
            if (diffXcarre > 40000) {
              continue;
            }
            float diffYcarre = diffY * diffY;
            if (diffYcarre > 40000) {
              continue;
            }
            float distanceFromOrb = (float)Math.sqrt(diffXcarre * diffYcarre);
            if (distanceFromOrb < 200) {
              diffX *= attractionStrength / distanceFromOrb * tick;
              diffY *= attractionStrength / distanceFromOrb * tick;

              enemy.speed.x += diffX;
              enemy.speed.y += diffY;
            }
          }
        } else {
          speed.x = -xDiffWithPlayerShip * 2;
          speed.y = -yDiffWithPlayerShip * 2;
          interpolate(position, speed);
          updateOrbAngle();
        }
      }
    } else {
      super.update();
    }
  }

  @Override
  public void fire(float chargePercentage) {
    attractionDuration = 10 * chargePercentage;
    attracting = true;
  }
}
