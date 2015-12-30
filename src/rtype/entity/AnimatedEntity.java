package rtype.entity;

import rtype.Main;
import rtype.Texture;

/**
 * Created by jhooba on 2015-12-20.
 */
public abstract class AnimatedEntity extends Entity {
  // this field set if player is charging a blast
  public boolean displayAnimation;
  private Texture[] animationTextures;

  protected AnimatedEntity(int type) {
    super(type);
  }

  protected void init () {
    animationTextures = Main.textureLoader.getAnimation(type);
  }

  public abstract void draw();
}
