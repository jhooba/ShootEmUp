package generator;

/**
 * Created by jhooba on 2015-12-20.
 */
abstract public class GeneratorBase {
  private float delay = 0;

  protected GeneratorBase (float delay) {
    this.delay = delay;
  }

  protected GeneratorBase () {
  }
}
