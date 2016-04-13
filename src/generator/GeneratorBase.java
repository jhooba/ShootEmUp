package generator;

/**
 * Created by jhooba on 2015-12-20.
 */
public abstract class GeneratorBase {
  private float delay = 0;
  private boolean done = false;

  GeneratorBase(float delay) {
    this.delay = delay;
  }

  GeneratorBase() {
  }

  abstract void generateEntities();

  float getDelay() {
    return delay;
  }

  boolean isDone() {
    return done;
  }

  void setDone() {
    done = true;
  }
}
