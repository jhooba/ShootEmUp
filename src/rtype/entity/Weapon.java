package rtype.entity;

/**
 * Created by jhooba on 2015-12-22.
 */
public abstract class Weapon extends AnimatedEntity {
  protected final PlayerShip playerShip;
  protected boolean displayChargeAnimation;
  protected float chargeAnimationSpeed = 4.4f;
  // This field hold the blast charge..
  protected float chargeAnimationCursor;

  protected Weapon(PlayerShip playerShip, int type, float ratio) {
    super(type, ratio);
    this.playerShip = playerShip;
  }

  public abstract void fire(float chargePercentage);
}
