package generator;

/**
 * Copied from http://www.fabiensanglard.net/Prototyp/index.php
 * Created by jhooba on 2015-12-20.
 */
public abstract class GeneratorBase {
  private float delay = 0;
  private boolean done = false;

  protected GeneratorBase(float delay) {
    this.delay = delay;
  }

  protected GeneratorBase() {
  }

  abstract public void generateEntities();

  public float getDelay() {
    return delay;
  }

  public boolean isDone() {
    return done;
  }

  public void setDone() {
    done = true;
  }
}
