package rtype.entity;

/**
 * Created by jhooba on 2015-12-30.
 */
public class BonusMagneticOrb extends Bonus {
  public BonusMagneticOrb() {
    super(BONUS_GRAVITY_ORB);
    init();
  }

  @Override
  public void boostPlayerShip(PlayerShip ship) {
    ship.setOrb(new MagneticOrb(ship));
  }
}
