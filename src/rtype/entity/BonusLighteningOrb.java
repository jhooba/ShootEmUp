package rtype.entity;

/**
 * Copied from http://www.fabiensanglard.net/Prototyp/index.php
 * Created by jhooba on 2015-12-30.
 */
public class BonusLighteningOrb extends Bonus {
  public BonusLighteningOrb() {
    super(BONUS_LIGHTNING_ORB);
    init();
  }

  @Override
  public void boostPlayerShip(PlayerShip ship) {
    ship.setOrb(new LighteningOrb(ship));
  }
}
