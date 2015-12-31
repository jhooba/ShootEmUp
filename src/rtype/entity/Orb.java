package rtype.entity;

/**
 * Created by jhooba on 2015-12-22.
 */
public abstract class Orb extends Weapon {
  public FireBall fb = new FireBall(this);

  protected Orb(int type) {
    super(type);
  }
}
