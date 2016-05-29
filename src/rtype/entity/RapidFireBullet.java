package rtype.entity;

import rtype.Main;

/**
 * Copied from http://www.fabiensanglard.net/Prototyp/index.php
 * Created by jhooba on 2016-01-01.
 */
public class RapidFireBullet extends Bullet {
  private static final float GHOST_GEN_SPEED = 800f;
  private static final float GHOST_LIMIT = 5;

  private float ghostAccumulator = 0;

  public RapidFireBullet(float rotation) {
    super(BULLET_RAPID_FIRE, 0.5f);
    this.rotation = rotation;
    this.life = 20;
    init();
  }

  @Override
  public void update() {
    interpolate(position, speed);
    ghostAccumulator += GHOST_GEN_SPEED * tick;
    if (ghostAccumulator > GHOST_LIMIT) {
      RapidFireBulletGhost ghost = new RapidFireBulletGhost(rotation);
      ghost.spawn(position, Main.DEFAULT_SCROLLING_SPEED, Main.fx);
      ghostAccumulator = 0;
    }
    if (position.x - width > Main.SCREEN_WIDTH / 2
        || position.x + width < - Main.SCREEN_WIDTH / 2) {
      unSpawn();
      if (Logger.isLogActivate) {
        Logger.log(getClass().getName() + " died");
      }
    }
  }
}
