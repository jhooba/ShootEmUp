package rtype.entity;

import rtype.Main;

import java.util.ArrayList;

/**
 * Copied from http://www.fabiensanglard.net/Prototyp/index.php
 * Created by jhooba on 2015-12-30.
 */
public class CrystalOrb extends Orb {
  private static final float DEFAULT_FREEZING_RANGE = 250;
  private static final float FREEZE_DURATION = 2;

  private boolean freezing = false;
  private float freezingRange = 0;
  private float freezeTickCounter = 0;
  private boolean startedFreezeProcess = false;
  private ArrayList<Entity> enemiesToFreeze = null;

  public CrystalOrb(PlayerShip playerShip) {
    super(playerShip, GREEN_ORB);
    init();
  }

  @Override
  public void fire(float chargePercentage) {
    freezing = true;
    freezingRange = DEFAULT_FREEZING_RANGE * chargePercentage ;
  }

  @Override
  public void update() {
    if (freezing) {
      if (freezeTickCounter > FREEZE_DURATION) {
        freezeTickCounter = 0;
        freezing = false;
        startedFreezeProcess = false;
        enemiesToFreeze = null;
      } else {
        calculateDistanceFromShip();
        if (startedFreezeProcess || distanceFromShip > 250 ) {
          startedFreezeProcess = true;
          if (enemiesToFreeze == null) {
            buildListOfEnemiesToFreeze();
          }
          freezeTickCounter += tick ;
          for (Entity e : enemiesToFreeze) {
            e.freeze(0.3f,0.6f);
          }
        } else {
          speed.x = -xDiffWithPlayerShip * 2;
          speed.y = -yDiffWithPlayerShip * 2;
          interpolate(position,speed);
          updateOrbAngle();
        }
      }
    }
    else {
      super.update();
    }
  }

  private void buildListOfEnemiesToFreeze() {
    enemiesToFreeze = new ArrayList<>();
    ArrayList<Entity> enemies = Main.enemies.entities;
    for (Entity enemy : enemies) {
      float diffX = position.x - enemy.position.x ;
      float diffY = position.y - enemy.position.y ;
      enemy.distanceFromOrb = (float)Math.sqrt(diffX * diffX + diffY * diffY);
      if (enemy.distanceFromOrb < freezingRange) {
        insertInDescDistanceFromOrb(enemy);
      }
    }
  }

  private void insertInDescDistanceFromOrb(Entity enemy) {
    int i = 0;
    for (Entity e : enemiesToFreeze) {
      if (e.distanceFromOrb > enemy.distanceFromOrb) {
        break;
      }
      ++i;
    }
    enemiesToFreeze.add(i, enemy);
  }
}
