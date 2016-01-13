package rtype;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import rtype.entity.BonusBooster;
import rtype.entity.BonusCrystalOrb;
import rtype.entity.BonusLighteningOrb;
import rtype.entity.BonusMagneticOrb;
import rtype.entity.BonusRapidShootOrb;
import rtype.entity.CrystalOrb;
import rtype.entity.LighteningOrb;
import rtype.entity.MagneticOrb;
import rtype.entity.PlayerShip;
import rtype.entity.RapidFireOrb;
import rtype.entity.Text;

/**
 * Created by jhooba on 2015-12-20.
 */
public class BonusDesc {
  private static final int ORIGIN_X = -55;
  private static final int ORIGIN_Y = 170;
  private static final Vector2f IMMOBILE = new Vector2f(0, 0);
  private static final int INTERSPACE_X = 100;
  private static final int INTERSPACE_Y = 60;

  private Layer layer = new Layer();
  private boolean bonusOn = true;

  public BonusDesc() {
    KeyListener space = new KeyListener() {
      @Override
      public void onKeyUp() {
        bonusOn = false;
        Main.timer.resume();
      }
    };
    EventManager.instance().addListener(Keyboard.KEY_SPACE, space);

    int pointX = ORIGIN_X;
    int pointY = ORIGIN_Y;

    PlayerShip lightning = new PlayerShip();
    LighteningOrb lorb = new LighteningOrb(lightning);

    PlayerShip rapid = new PlayerShip();
    RapidFireOrb rforb = new RapidFireOrb(rapid);

    PlayerShip magnetic = new PlayerShip();
    MagneticOrb morb = new MagneticOrb(magnetic);

    PlayerShip crystal = new PlayerShip();
    CrystalOrb corb = new CrystalOrb(crystal);

    PlayerShip booster = new PlayerShip();

    BonusLighteningOrb lBonus = new BonusLighteningOrb();
    BonusRapidShootOrb rBonus = new BonusRapidShootOrb();
    BonusMagneticOrb mBonus = new BonusMagneticOrb();
    BonusCrystalOrb cBonus = new BonusCrystalOrb();
    BonusBooster bBonus = new BonusBooster();

    Text commandLabel = new Text("One more thing...");
    Text lightningLabel = new Text("Lightning Orb : ");
    Text rapidLabel =     new Text("RapidFire Orb : ");
    Text magneticLabel =  new Text("Magnetic  Orb : ");
    Text crystalLabel =   new Text("Crystal   Orb : ");
    Text boosterLabel =   new Text("Booster   Orb : ");

    commandLabel.spawn(new Vector2f(pointX, pointY), IMMOBILE, layer);

    pointX = ORIGIN_X - INTERSPACE_X * 2;
    pointY = ORIGIN_Y;

    pointY -= INTERSPACE_Y;
    lightningLabel.spawn(new Vector2f(pointX, pointY), IMMOBILE, layer);

    pointY -= INTERSPACE_Y;
    rapidLabel.spawn(new Vector2f(pointX, pointY), IMMOBILE, layer);

    pointY -= INTERSPACE_Y;
    magneticLabel.spawn(new Vector2f(pointX, pointY), IMMOBILE, layer);

    pointY -= INTERSPACE_Y;
    crystalLabel.spawn(new Vector2f(pointX, pointY), IMMOBILE, layer);

    pointY -= INTERSPACE_Y;
    boosterLabel.spawn(new Vector2f(pointX, pointY), IMMOBILE, layer);

    pointX = ORIGIN_X + 50;
    pointY = ORIGIN_Y;

    pointY -= INTERSPACE_Y;
    lBonus.spawn(new Vector2f(pointX, pointY), IMMOBILE, layer);

    pointY -= INTERSPACE_Y;
    rBonus.spawn(new Vector2f(pointX, pointY), IMMOBILE, layer);

    pointY -= INTERSPACE_Y;
    mBonus.spawn(new Vector2f(pointX, pointY), IMMOBILE, layer);

    pointY -= INTERSPACE_Y;
    cBonus.spawn(new Vector2f(pointX, pointY), IMMOBILE, layer);

    pointY -= INTERSPACE_Y;
    bBonus.spawn(new Vector2f(pointX, pointY), IMMOBILE, layer);

    pointX = ORIGIN_X + INTERSPACE_X + 50;
    pointY = ORIGIN_Y;

    pointY -= INTERSPACE_Y;
    Layer nullLayer = new Layer();
    lightning.spawn(new Vector2f(pointX, pointY), IMMOBILE, nullLayer);
    lorb.spawn(new Vector2f(pointX - 400, pointY), IMMOBILE, layer);

    pointY -= INTERSPACE_Y;
    rapid.spawn(new Vector2f(pointX, pointY), IMMOBILE, nullLayer);
    rforb.spawn(new Vector2f(pointX - 400, pointY), IMMOBILE, layer);

    pointY -= INTERSPACE_Y;
    magnetic.spawn(new Vector2f(pointX, pointY), IMMOBILE, nullLayer);
    morb.spawn(new Vector2f(pointX - 400, pointY), IMMOBILE, layer);

    pointY -= INTERSPACE_Y;
    crystal.spawn(new Vector2f(pointX, pointY), IMMOBILE, nullLayer);
    corb.spawn(new Vector2f(pointX - 400, pointY), IMMOBILE, layer);

    pointY -= INTERSPACE_Y;
    booster.spawn(new Vector2f(pointX, pointY), IMMOBILE, nullLayer);
    booster.addBooster(layer);
    booster.addBooster(layer);
  }

  public void play() {
    while (bonusOn) {
      Main.heartBeat();
      layer.update();

      Main.render();
      layer.render();

      Display.update();

      if (Main.exitRequested()) {
        bonusOn = false;
        Main.gameOff = true;
      }
      EventManager.instance().checkEvents();
    }
    EventManager.instance().clear();
  }
}
