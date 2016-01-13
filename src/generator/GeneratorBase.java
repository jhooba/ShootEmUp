package generator;

/**
 * Created by jhooba on 2015-12-20.
 */
public abstract class GeneratorBase {
  private float delay = 0;
  protected boolean done = false;

  protected GeneratorBase (float delay) {
    this.delay = delay;
  }

  protected GeneratorBase () {
  }

  public abstract void generateEntities();

  public float getDelay() {
    return delay;
  }

  public boolean isDone() {
    return done;
  }

  protected void setDone() {
    done = true;
  }
}
