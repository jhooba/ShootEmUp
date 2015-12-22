package rtype.entity;

import org.lwjgl.util.vector.Vector2f;
import rtype.Layer;

/**
 * Created by jhooba on 2015-12-20.
 */
public class Entity {
  public Vector2f position = new Vector2f();

  public void spawn(Vector2f position, Vector2f speed, Layer layer) {

  }

  public void unSpawn() {
  }

  public boolean collided(Entity currentEnemy) {
    return false;
  }
}
