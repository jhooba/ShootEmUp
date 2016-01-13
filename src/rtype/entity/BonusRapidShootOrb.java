package rtype.entity;

/**
 * Created by jhooba on 2015-12-30.
 */
public class BonusRapidShootOrb extends Bonus {
  public BonusRapidShootOrb() {
    super(BONUS_RAPID_SHOOT_ORB);
  }

  @Override
  public void boostPlayerShip(PlayerShip ship) {
    ship.setOrb(new RapidFireOrb(ship));
  }
}
