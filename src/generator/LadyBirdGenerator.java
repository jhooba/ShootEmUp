package generator;

import org.lwjgl.util.vector.Vector2f;

import rtype.Main;
import rtype.entity.LadyBird;

/**
 * Copied from http://www.fabiensanglard.net/Prototyp/index.php
 * Created by jhooba on 2015-12-20.
 */
public class LadyBirdGenerator extends GeneratorBase {
  private static final float INTERVAL = 0.005f;
  private static final Vector2f DEFAULT_LADY_SPEED = new Vector2f(-56.3f, 0);

  private float interval = 0;

  public LadyBirdGenerator(float delay) {
    super(delay);
  }

  @Override
  public void generateEntities() {
    interval += Main.tick;
    if (interval > INTERVAL) {
      interval = 0;
      LadyBird lady = new LadyBird();
      lady.spawn(new Vector2f(Main.SCREEN_WIDTH / 2 + 10, Main.RANDOM.nextInt() % Main.SCREEN_HEIGHT / 2),
                 DEFAULT_LADY_SPEED, Main.enemies);
    }
  }
}
