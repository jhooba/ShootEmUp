package rtype.entity;

/**
 * Created by jhooba on 2015-12-30.
 */
public class BonusLighteningOrb extends Bonus {
  public BonusLighteningOrb() {
    super(BONUS_LIGHTNING_ORB);
  }

  @Override
  public void boostPlayerShip(PlayerShip ship) {
    ship.setOrb(new LighteningOrb(ship));
  }
}
