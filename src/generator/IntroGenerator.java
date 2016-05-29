package generator;

import org.lwjgl.util.vector.Vector2f;
import rtype.Main;
import rtype.entity.IEntity;
import rtype.entity.Planet;
import rtype.entity.SpaceTrash;
import rtype.entity.Star;

/**
 * Copied from http://www.fabiensanglard.net/Prototyp/index.php
 * Created by jhooba on 2015-12-20.
 */
public class IntroGenerator extends GeneratorBase {
  private static final float B_S = 7f;

  @Override
  public void generateEntities() {
    Planet planet= new Planet();
    planet.spawn(new Vector2f(320, 0), new Vector2f(B_S * -1, 0), Main.background);

    SpaceTrash trash = new SpaceTrash(IEntity.SPACE_TRASH_5);
    trash.rotation = 90;
    trash.spawn(new Vector2f(-20, 100), new Vector2f(B_S * -6, 0), Main.frontground);

    trash = new SpaceTrash(IEntity.SPACE_TRASH_5);
    trash.spawn(new Vector2f(100, 115), new Vector2f(B_S * -5, 0), Main.frontground, 10);

    trash = new SpaceTrash(IEntity.SPACE_TRASH_3);
    trash.rotation = 90;
    trash.spawn(new Vector2f(-20, -100), new Vector2f(B_S * -7.5f, 0), Main.frontground, 2);

    trash = new SpaceTrash(IEntity.SPACE_TRASH_3);
    trash.spawn(new Vector2f(100, -115), new Vector2f(B_S * -4.5f, 0), Main.background, 2);

    trash = new SpaceTrash(IEntity.SPACE_TRASH_1);
    trash.spawn(new Vector2f(-200, 200), new Vector2f(B_S * -4.5f, 0), Main.frontground);

    trash = new SpaceTrash(IEntity.SPACE_TRASH_4);
    trash.flipYAxis();
    trash.spawn(new Vector2f(-100, 100), new Vector2f(B_S * -4.5f, 0), Main.background, -4);

    trash = new SpaceTrash(IEntity.SPACE_TRASH_4);
    trash.flipYAxis();
    trash.spawn(new Vector2f(100, 115), new Vector2f(B_S * -4.5f, 0), Main.frontground, 3);

    trash = new SpaceTrash(IEntity.SPACE_TRASH_4);
    trash.flipYAxis();
    trash.spawn(new Vector2f(50, 0), new Vector2f(B_S * -4.5f, 0), Main.background, -2);

    trash = new SpaceTrash(IEntity.SPACE_TRASH_4);
    trash.flipYAxis();
    trash.spawn(new Vector2f(-200, -200), new Vector2f(B_S * -4.5f, 0), Main.frontground, -2);

    trash = new SpaceTrash(IEntity.SPACE_TRASH_4);
    trash.flipYAxis();
    trash.rotation = 200;
    trash.spawn(new Vector2f(300, -250), new Vector2f(B_S * -4.5f, 0), Main.background, 2);

    trash = new SpaceTrash(IEntity.SPACE_TRASH_4);
    trash.flipYAxis();
    trash.rotation = 20;
    trash.spawn(new Vector2f(300, -100), new Vector2f(B_S * -4.5f, 0), Main.background);

    trash = new SpaceTrash(IEntity.SPACE_TRASH_1);
    trash.spawn(new Vector2f(5, 8), new Vector2f(-70.3f, 0), Main.frontground, 2);

    Star star = new Star(IEntity.STAR_1);
    star.spawn(new Vector2f(60, -100), new Vector2f(-34.3f, 0), Main.background);

    star = new Star(IEntity.STAR_2);
    star.spawn(new Vector2f(60, -120), new Vector2f(-30.3f, 0), Main.frontground);

    star = new Star(IEntity.STAR_3);
    star.spawn(new Vector2f(60, -140), new Vector2f(-25.3f, 0), Main.background);

    star = new Star(IEntity.STAR_4);
    star.spawn(new Vector2f(360, -160), new Vector2f(-20.3f, 0), Main.frontground);

    star = new Star(IEntity.STAR_5);
    star.spawn(new Vector2f(360, -180), new Vector2f(-15.3f, 0), Main.background);

    star = new Star(IEntity.STAR_6);
    star.spawn(new Vector2f(360, -200), new Vector2f(-50.3f, 0), Main.frontground);

    trash = new SpaceTrash(IEntity.SPACE_TRASH_2);
    trash.spawn(new Vector2f(250, 20), new Vector2f(-40.3f, 0), Main.background);

    trash = new SpaceTrash(IEntity.SPACE_TRASH_3);
    trash.spawn(new Vector2f(350, -100), new Vector2f(-25.3f, 0), Main.frontground, 2);

    trash = new SpaceTrash(IEntity.SPACE_TRASH_3);
    trash.spawn(new Vector2f(350, 150), new Vector2f(-27.3f, 0), Main.frontground, -2);

    trash = new SpaceTrash(IEntity.SPACE_TRASH_3);
    trash.spawn(new Vector2f(420, 50), new Vector2f(-30.3f, 0), Main.frontground);

    trash = new SpaceTrash(IEntity.SPACE_TRASH_3);
    trash.spawn(new Vector2f(380, -50), new Vector2f(-34.3f, 0), Main.frontground);

    trash = new SpaceTrash(IEntity.SPACE_TRASH_4);
    trash.spawn(new Vector2f(225, -2), new Vector2f(-60.3f, 0), Main.background);

    setDone();
  }
}
