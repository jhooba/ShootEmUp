package generator;

/**
 * Created by jhooba on 2015-12-20.
 */
abstract public class GeneratorBase {
  private float delay = 0;
  protected boolean done;

  protected GeneratorBase (float delay) {
    this.delay = delay;
  }

  protected GeneratorBase () {
  }

  abstract public void generateEntities();

  public float getDelay() {
    return delay;
  }

  public boolean isDone() {
    return done;
  }
}
