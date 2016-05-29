package rtype.entity;

/**
 * Copied from http://www.fabiensanglard.net/Prototyp/index.php
 * Created by jhooba on 2015-12-25.
 */
public abstract class Enemy extends AnimatedEntity {
  protected Enemy(int type, float ratio, float animationSpeed) {
    super(type, ratio, animationSpeed);
  }
}
