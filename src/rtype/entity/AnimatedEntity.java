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
  private float animationSpeed = 4.4f;
  // This field hold the blast charge..
  private float animationCursor;

  protected AnimatedEntity(int type, float ratio, float animationSpeed) {
    super(type, ratio);
    this.animationSpeed = animationSpeed;
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

  protected void updateAnimationCursor() {
    if (displayAnimation) {
      animationCursor += animationSpeed * tick;
      animationCursor %= animationTextures.length;
    }
  }

  protected int getCursorAnimationTextureId() {
    return animationTextures[(int)animationCursor].getTextureId();
  }

  protected float getAnimationCursor() {
    return animationCursor;
  }

  protected boolean isLastAnimation() {
    return animationCursor == animationTextures.length - 1;
  }

  protected void setAnimationCursor(float animationCursor) {
    this.animationCursor = animationCursor;
  }
}
