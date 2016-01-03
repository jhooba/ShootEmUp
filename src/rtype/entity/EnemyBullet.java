package rtype.entity;

import org.lwjgl.util.vector.Vector2f;
import rtype.Main;

/**
 * Created by jhooba on 2016-01-03.
 */
public class EnemyBullet extends Entity {
  public EnemyBullet() {
    super(ENEMY_BULLET, 0.3f);
    init();
  }

  @Override
  public void draw() {
    super.draw();
    if (position.x > Main.SCREEN_WIDTH / 2) {
      unSpawn();
      if (Logger.isLogActivate) {
        Logger.log("Enemy Bullet died.");
      }
    }
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
