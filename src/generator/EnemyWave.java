package generator;

import org.lwjgl.util.vector.Vector2f;
import rtype.BonusFactory;
import rtype.Main;
import rtype.entity.Bonus;
import rtype.entity.LadyBird;

/**
 * Created by jhooba on 2015-12-20.
 */
public class EnemyWave extends GeneratorBase {
  public static final float INTERVAL = 2f;  // Rate at which generating entities.
  private static final Vector2f DEFAULT_LADY_SPEED = new Vector2f(-76.3f,0);
  private static final int MAX_UNIT_GENERATED = 5;

  private final int x;
  private float interval = 0;
  private int generatedUnitCounter = 1;

  public EnemyWave(int x, float delay) {
    super(delay);
    this.x = x;
  }

  @Override
  public void generateEntities() {
    interval += Main.tick;
    if (interval > INTERVAL) {
      interval = 0;
      LadyBird lady = new LadyBird();
      if (generatedUnitCounter == MAX_UNIT_GENERATED) {
        lady.setPresetBonus(BonusFactory.createBonus(Main.RANDOM.nextInt(Bonus.BONUS_COUNT)));
      }
      //noinspection SuspiciousNameCombination
      lady.spawn(new Vector2f(Main.SCREEN_WIDTH / 2 + 10, x), DEFAULT_LADY_SPEED, Main.enemies);
      ++generatedUnitCounter;
    }
    if (generatedUnitCounter > MAX_UNIT_GENERATED) {
      setDone();
    }
  }
}
