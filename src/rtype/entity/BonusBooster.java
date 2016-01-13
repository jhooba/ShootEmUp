package rtype.entity;

/**
 * Created by jhooba on 2015-12-26.
 */
public class BonusBooster extends Bonus {
  public BonusBooster() {
    super(BONUS_BOOSTER);
  }

  @Override
  public void boostPlayerShip(PlayerShip ship) {
    ship.addBooster();
  }
}
