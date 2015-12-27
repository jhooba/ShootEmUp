package generator;

import org.lwjgl.util.vector.Vector2f;
import rtype.Main;
import rtype.entity.IEntity;
import rtype.entity.Planet;
import rtype.entity.SpaceTrash;

/**
 * Created by jhooba on 2015-12-20.
 */
public class IntroGenerator extends GeneratorBase {
  private static final float B_S = 7f;
  @Override
  public void generateEntities() {
    Planet planet= new Planet();
    planet.spawn(new Vector2f(320, 0), new Vector2f(B_S * -1,0), Main.background);

    SpaceTrash trash = new SpaceTrash(IEntity.SPACE_TRASH_5);
    trash.rotation = 90;
    trash.spawn(new Vector2f(-20, 100),new Vector2f(B_S * -6,0), Main.frontground);

    trash = new SpaceTrash(IEntity.SPACE_TRASH_5);
    trash.spawn(new Vector2f(100, 115),new Vector2f(B_S * -5,0), 10f, Main.frontground);
  }
}
