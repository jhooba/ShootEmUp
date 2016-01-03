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
    for (Entity e : entities) {
      e.updateTick();
      e.update();
    }
  }

  public void render() {
    for (Entity e : entities) {
      e.draw();
    }
  }
}
