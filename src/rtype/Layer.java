package rtype;

import java.util.ArrayList;

import rtype.entity.Entity;

/**
 * Created by jhooba on 2015-12-20.
 */
public class Layer {
  public ArrayList<Entity> entities = new ArrayList<>();

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
