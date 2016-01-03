package rtype.entity;

import rtype.Main;

/**
 * Created by jhooba on 2015-12-20.
 */
public class TextEntityCounter extends Text {
  public TextEntityCounter() {
    super("");
  }

  @Override
  public void update() {
    int entitiesCounter = 0;
    entitiesCounter += Main.bullets.entities.size();
    entitiesCounter += Main.enemies.entities.size();
    entitiesCounter += Main.fx.entities.size();
    entitiesCounter += Main.background.entities.size();
    entitiesCounter += Main.frontground.entities.size();
    setString("Entities #:" + entitiesCounter);
  }
}
