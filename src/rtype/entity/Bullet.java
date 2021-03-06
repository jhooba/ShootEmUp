package rtype.entity;

import org.lwjgl.util.vector.Vector2f;
import rtype.Main;

/**
 * Copied from http://www.fabiensanglard.net/Prototyp/index.php
 * Created by jhooba on 2016-01-01.
 */
public class Bullet extends Entity {
  protected Bullet(int type, float ratio) {
    super(type, ratio);
  }

  @Override
  public boolean collided(Entity entity) {
    life -= entity.damage;
    if (life < 0) {
      unSpawn();
      BulletHit hit = new BulletHit(IEntity.BULLET_HIT_YELLOW);
      hit.spawn(position, new Vector2f(0, 0), Main.fx);
      return true;
    }
    return false;
  }
}
