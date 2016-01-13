package rtype;

import rtype.entity.Bonus;
import rtype.entity.BonusBooster;
import rtype.entity.BonusCrystalOrb;
import rtype.entity.BonusLighteningOrb;
import rtype.entity.BonusMagneticOrb;
import rtype.entity.BonusRapidShootOrb;
import rtype.entity.IEntity;

/**
 * Created by jhooba on 2015-12-26.
 */
public class BonusFactory {
  public static Bonus createBonus(int type) {
    Bonus b = null;
    switch (type) {
      case IEntity.BONUS_BOOSTER :
        b = new BonusBooster();
        break;
      case IEntity.BONUS_LIGHTNING_ORB :
        b = new BonusLighteningOrb();
        break;
      case IEntity.BONUS_GRAVITY_ORB :
        b = new BonusMagneticOrb();
        break;
      case IEntity.BONUS_RAPID_SHOOT_ORB :
        b = new BonusRapidShootOrb();
        break;
      case IEntity.BONUS_CRYSTAL_ORB :
        b = new BonusCrystalOrb();
        break;
    }
    return b;
  }
}
