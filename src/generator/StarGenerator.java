package generator;

import org.lwjgl.util.vector.Vector2f;

import rtype.Main;
import rtype.entity.Star;

/**
 * Created by jhooba on 2015-12-20.
 */
public class StarGenerator extends GeneratorBase{
  private static final float STAR_SPAWNING_RATE = 5f;
  private static final Vector2f DEFAULT_STAR_SPEED = new Vector2f(-34.3f, 0);

  private float starDeltaAcc = 0;

  @Override
  public void generateEntities() {
    starDeltaAcc += Main.tick;
    if (starDeltaAcc > STAR_SPAWNING_RATE) {
      starDeltaAcc = 0;
      int starType = Main.RANDOM.nextInt(6) + 11;
      Star spawningStar = new Star(starType);
      spawningStar.spawn(
          new Vector2f(Main.SCREEN_WIDTH / 2 + 10, Main.RANDOM.nextInt() % Main.SCREEN_HEIGHT / 2),
          DEFAULT_STAR_SPEED, Main.background);
    }
  }
}
