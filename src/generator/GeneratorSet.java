package generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhooba on 2015-12-20.
 */
public class GeneratorSet {
  private List<GeneratorBase> generators = new ArrayList<>();

  public boolean contains(GeneratorBase gen) {
    return false;
  }

  public void addGenerator(GeneratorBase gen) {
    generators.add(gen);
  }

  public void removeGenerator(GeneratorBase gen) {
  }

  public void generate() {
  }
}
