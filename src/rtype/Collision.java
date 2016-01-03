package rtype;

import org.jetbrains.annotations.Contract;

import rtype.entity.Entity;

/**
 * Created by jhooba on 2015-12-22.
 */
public class Collision {
  @Contract(pure = true)
  public static boolean boxBoxOverlap(Entity entityA, Entity entityB) {
    if (entityA.position.x + entityA.width < entityB.position.x) {
      return false;
    }
    if (entityA.position.y + entityA.height < entityB.position.y) {
      return false;
    }
    if (entityA.position.x > entityB.position.x + entityB.width) {
      return false;
    }
    return entityA.position.y <= entityB.position.y + entityB.height;
  }
}
