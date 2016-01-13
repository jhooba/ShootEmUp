package rtype.entity;

import org.lwjgl.util.vector.Vector2f;
import rtype.Main;

import java.util.ArrayList;

/**
 * Created by jhooba on 2015-12-30.
 */
public class LighteningOrb extends Orb {
  private static final float FIRE_RATE_LIMIT = 2;
  private static final float MIN_BEAM_HEIGHT = 50;
  private static final float BULLET_FIRE_RATE = 40;

  private float bulletsToFire = 0;
  private float bulletTimeCounter = 0;
  private float coefDirecteur = 0;
  private float minOrdonneeOrigine = 0;
  private float maxOrdonneeOrigine = 0;

  public LighteningOrb(PlayerShip playerShip) {
    super(playerShip, BLUE_ORB);
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

    Vector2f[] p2 = new Vector2f[5];
    p2[0] = new Vector2f(0, 0);
    p2[1] = new Vector2f(130, 0);
    p2[2] = new Vector2f(370, 0);
    p2[3] = new Vector2f(870, 0);
    p2[4] = new Vector2f(1970, 0);
    OrbBeam ob2 = new OrbBeam(this, p2, 3, false, 0.3f, 0.8f, 1, 0.85f, 20, MIN_BEAM_HEIGHT);
    ob2.spawn(position, new Vector2f(-75.3f, 0), Main.fx);

    Vector2f[] p3 = new Vector2f[4];
    p3[0] = new Vector2f(0, 0);
    p3[1] = new Vector2f(200, 0);
    p3[2] = new Vector2f(840, 0);
    p3[3] = new Vector2f(940, 0);
    OrbBeam ob3 = new OrbBeam(this, p3, 3, Main.RANDOM.nextInt(2) != 0, 1, 1, 1, 0.85f, 15, MIN_BEAM_HEIGHT - 10);
    ob3.spawn(position, new Vector2f(-75.3f, 0), Main.fx);

    Vector2f[] p4 = new Vector2f[4];
    p4[0] = new Vector2f(0, 0);
    p4[1] = new Vector2f(200, 0);
    p4[2] = new Vector2f(440, 0);
    p4[3] = new Vector2f(940, 0);
    OrbBeam ob4 = new OrbBeam(this, p4, 55, Main.RANDOM.nextInt(2) != 0, 1, 1, 1, 0.3f, 15, MIN_BEAM_HEIGHT - 40);
    ob4.spawn(position, new Vector2f(-75.3f, 0), Main.fx);

    Vector2f[] p6 = new Vector2f[4];
    p6[0] = new Vector2f(0, 0);
    p6[1] = new Vector2f(200, 0);
    p6[2] = new Vector2f(440, 0);
    p6[3] = new Vector2f(940, 0);
    OrbBeam ob6 = new OrbBeam(this, p6, 20, Main.RANDOM.nextInt(2) != 0, 1, 1, 1, 0.8f, 0, MIN_BEAM_HEIGHT - 40);
    ob6.spawn(position, new Vector2f(-75.3f, 0), Main.fx);

    float minY = Math.min(ob.getMinNormalWidth(), ob2.getMinNormalWidth());
    float maxY = Math.min(ob.getMaxNormalWidth(), ob2.getMaxNormalWidth());
    coefDirecteur = (float)Math.tan(rotationRadians);
    minOrdonneeOrigine = (position.y + minY) - coefDirecteur * position.x;
    maxOrdonneeOrigine = (position.y + maxY) - coefDirecteur * position.x;

    // Detect collision here...
    ArrayList<Entity> enemyArray = Main.enemies.entities;
    if (rotationRadians < Math.PI) {
      if (rotationRadians < Math.PI / 2) {
        for (int j = 0; j < enemyArray.size(); ++j) {
          Entity currentEnemy = enemyArray.get(j);
          if (currentEnemy.position.x > position.x && currentEnemy.position.y > position.y) {
            if (betweenTwoLines(currentEnemy)) {
              if (currentEnemy.collided(this)) {
                --j;
              }
            }
          }
        }
      } else {
        for (int j = 0; j < enemyArray.size(); ++j) {
          Entity currentEnemy = enemyArray.get(j);
          if (currentEnemy.position.x < position.x && currentEnemy.position.y > position.y) {
            if (betweenTwoLines(currentEnemy)) {
              if (currentEnemy.collided(this)) {
                --j;
              }
            }
          }
        }
      }
    } else {
      if (rotationRadians > 3 * Math.PI / 2) {
        for (int j = 0; j < enemyArray.size(); ++j) {
          Entity currentEnemy = enemyArray.get(j);
          if (currentEnemy.position.x > position.x && currentEnemy.position.y < position.y) {
            if (betweenTwoLines(currentEnemy)) {
              if (currentEnemy.collided(this)) {
                --j;
              }
            }
          }
        }
      } else {
        for (int j = 0; j < enemyArray.size(); ++j) {
          Entity currentEnemy = enemyArray.get(j);
          if (currentEnemy.position.x < position.x && currentEnemy.position.y < position.y) {
            if (betweenTwoLines(currentEnemy)) {
              if (currentEnemy.collided(this)) {
                --j;
              }
            }
          }
        }
      }
    }
  }

  private boolean betweenTwoLines(Entity entity) {
    return entity.position.y < (coefDirecteur*entity.position.x + maxOrdonneeOrigine)
        && entity.position.y > (coefDirecteur*entity.position.x + minOrdonneeOrigine);
  }

  @Override
  public void fire(float chargePercentage) {
    bulletsToFire = 2;
  }
}
