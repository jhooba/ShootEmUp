package rtype;

import rtype.entity.Bonus;
import rtype.entity.BonusBooster;
import rtype.entity.IEntity;

/**
 * Created by jhooba on 2015-12-26.
 */
public class BonusFactory {
  public static Bonus createBonus(int type) {
    Bonus b = null;
    switch (type) {
      case IEntity.BONUS_BOOSTER : b = new BonusBooster(); break;
    }
    return b;
  }
}
