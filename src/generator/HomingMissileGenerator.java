package generator;

import org.lwjgl.util.vector.Vector2f;
import rtype.Main;
import rtype.entity.HomingMissile;

/**
 * Created by jhooba on 2015-12-20.
 */
public class HomingMissileGenerator extends GeneratorBase {
  private static final float INTERVAL = 1f;  // Rate at which generating entities.
  private static final Vector2f DEFAULT_MISSILE_SPEED = new Vector2f(-600.3f, 0);

  private float interval = 0;

  @Override
  void generateEntities() {
    interval += Main.tick;
    if (interval > INTERVAL) {
      interval = 0;
      HomingMissile hMissile = new HomingMissile(Main.enemies, (float) Math.PI / 1000);
      hMissile.spawn(
          new Vector2f(Main.SCREEN_WIDTH / 2 + 10, Main.RANDOM.nextInt() % Main.SCREEN_WIDTH / 2),
          DEFAULT_MISSILE_SPEED,
          Main.enemies);
    }
  }
}
