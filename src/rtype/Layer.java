package rtype;

import java.util.ArrayList;

import rtype.entity.Entity;

/**
 * Created by jhooba on 2015-12-20.
 */
public class Layer {
  public final ArrayList<Entity> entities = new ArrayList<>();

  public void add(Entity e) {
    entities.add(e);
  }

  public void remove(Entity e) {
    entities.remove(e);
  }

  public void update() {
    //noinspection ForLoopReplaceableByForEach
    for (int i = 0; i < entities.size(); ++i) {
      Entity e = entities.get(i);
      e.updateTick();
      e.update();
    }
  }

  public void render() {
    //noinspection ForLoopReplaceableByForEach
    for (int i = 0; i < entities.size(); ++i) {
      entities.get(i).draw();
    }
  }
}
