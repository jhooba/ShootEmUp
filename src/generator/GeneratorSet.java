package generator;

import rtype.Main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jhooba on 2015-12-20.
 */
public class GeneratorSet {
  private final List<GeneratorBase> generators = new ArrayList<>();
  private float tickAccumulator = 0;

  public boolean contains(GeneratorBase gen) {
    return generators.indexOf(gen) != -1;
  }

  public void addGenerator(GeneratorBase gen) {
    generators.add(gen);
  }

  public void removeGenerator(GeneratorBase gen) {
    generators.remove(gen);
  }

  public void generate() {
    tickAccumulator += Main.tick;
    for (Iterator<GeneratorBase> iter = generators.iterator(); iter.hasNext(); ) {
      GeneratorBase gen = iter.next();
      if (tickAccumulator > gen.getDelay()) {
        gen.generateEntities();
      }
      if (gen.isDone()) {
        iter.remove();
      }
    }
  }
}
