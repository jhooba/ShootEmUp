package rtype.entity;

import rtype.Main;
import rtype.Texture;

/**
 * Created by jhooba on 2015-12-20.
 */
public abstract class AnimatedEntity extends Entity {
  // this field set if player is charging a blast
  public boolean displayAnimation;
  protected Texture[] animationTextures;
  protected float animationSpeed = 4.4f;
  // This field hold the blast charge..
  protected float animationCursor;

  protected AnimatedEntity(int type, float ratio) {
    super(type, ratio);
  }

  protected void init () {
    animationTextures = Main.textureLoader.getAnimation(type);
    originalWidth = animationTextures[0].getTextureWidth();
    originalHeight = animationTextures[0].getTextureHeight();
  }

  public abstract void draw();

  public void startAnimation() {
    displayAnimation = true;
  }

  public void stopAnimation() {
    displayAnimation = false;
    animationCursor = 0;
  }

  public void startChargingAnimation() {
  }

  public void stopChargeAnimation() {

  }
}
