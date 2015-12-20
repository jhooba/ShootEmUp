package generator;

/**
 * Created by jhooba on 2015-12-20.
 */
public class EnemyWave extends GeneratorBase {
  // Rate at which generating entities.
  public static float RATE = 2f;

  public EnemyWave(int x, float delay) {
    super(delay);
  }
}
