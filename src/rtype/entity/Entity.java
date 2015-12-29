package rtype.entity;

import org.lwjgl.util.vector.Vector2f;
import rtype.Layer;

/**
 * Created by jhooba on 2015-12-20.
 */
public abstract class Entity implements IEntity {
  public Vector2f position = new Vector2f();
  public float rotation = 0;

  public void spawn(Vector2f position, Vector2f speed, Layer layer) {

  }

  public void spawn(Vector2f vector2f, Vector2f vector2f1, float v, Layer frontground) {

  }

  public void unSpawn() {
  }

  public boolean collided(Entity currentEnemy) {
    return false;
  }

  public void updateTick() {

  }

  public void draw(float v) {
  }

  public void flipYAxis() {
  }

  public void update() {
  }

  public void draw() {
  }
}
