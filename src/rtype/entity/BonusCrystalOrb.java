package rtype.entity;

/**
 * Created by jhooba on 2015-12-30.
 */
public class BonusCrystalOrb extends Bonus {
  public BonusCrystalOrb() {
    super(BONUS_CRYSTAL_ORB);
    init();
  }

  @Override
  public void boostPlayerShip(PlayerShip ship) {
    ship.setOrb(new CrystalOrb(ship));
  }
}
