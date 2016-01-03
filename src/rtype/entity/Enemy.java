package rtype.entity;

/**
 * Created by jhooba on 2015-12-25.
 */
public abstract class Enemy extends AnimatedEntity {
  protected Enemy(int type, float ratio) {
    super(type, ratio);
  }
}
