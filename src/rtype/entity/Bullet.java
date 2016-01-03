package rtype.entity;

import org.lwjgl.util.vector.Vector2f;
import rtype.Main;

/**
 * Created by jhooba on 2016-01-01.
 */
public class Bullet extends Entity {
  private int life = 20;

  protected Bullet(int type) {
    super(type, 0.5f);
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
