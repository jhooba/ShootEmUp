package generator;

import org.lwjgl.util.vector.Vector2f;

import rtype.Main;
import rtype.entity.LadyBird;

/**
 * Created by jhooba on 2015-12-20.
 */
public class LadyBirdGenerator extends GeneratorBase {
  private float ladyDeltaAcc = 0;

  public LadyBirdGenerator(float delay) {
    super(delay);
  }

  @Override
  public void generateEntities() {
    final float LADY_SPAWNING_RATE = 0.005f;
    final Vector2f DEFAULT_LADY_SPEED = new Vector2f(-56.3f, 0);
    ladyDeltaAcc += Main.tick;
    if (ladyDeltaAcc > LADY_SPAWNING_RATE) {
      ladyDeltaAcc = 0;
      LadyBird lad = new LadyBird();
      lad.spawn(
          new Vector2f(Main.SCREEN_WIDTH / 2 + 10, Main.RANDOM.nextInt() % Main.SCREEN_HEIGHT / 2),
          DEFAULT_LADY_SPEED, Main.enemies);
    }
  }
}
