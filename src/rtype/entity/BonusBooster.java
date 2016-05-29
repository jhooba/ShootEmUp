package rtype.entity;

/**
 * Copied from http://www.fabiensanglard.net/Prototyp/index.php
 * Created by jhooba on 2015-12-26.
 */
public class BonusBooster extends Bonus {
  public BonusBooster() {
    super(BONUS_BOOSTER);
    init();
  }

  @Override
  public void boostPlayerShip(PlayerShip ship) {
    ship.addBooster();
  }
}
