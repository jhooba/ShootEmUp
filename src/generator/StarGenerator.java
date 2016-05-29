package generator;

import org.lwjgl.util.vector.Vector2f;

import rtype.Main;
import rtype.entity.IEntity;
import rtype.entity.Star;

/**
 * Created by jhooba on 2015-12-20.
 */
public class StarGenerator extends GeneratorBase{
  private static final float INTERVAL = 5f;
  private static final Vector2f DEFAULT_STAR_SPEED = new Vector2f(-34.3f, 0);

  private float interval = 0;

  @Override
  public void generateEntities() {
    interval += Main.tick;
    if (interval > INTERVAL) {
      interval = 0;
      int starType = IEntity.STAR_1 + Main.RANDOM.nextInt(6);
      Star spawningStar = new Star(starType);
      spawningStar.spawn(new Vector2f(Main.SCREEN_WIDTH / 2 + 10, Main.RANDOM.nextInt() % Main.SCREEN_HEIGHT / 2),
                         DEFAULT_STAR_SPEED, Main.background);
    }
  }
}
