package rtype.entity;

import org.lwjgl.opengl.GL11;
import rtype.Main;

/**
 * Created by jhooba on 2015-12-22.
 */
public abstract class Orb extends Weapon {
  public static final int STICKED = 0;
  public static final int ADJUSTING = 1;
  protected static final float DEFAULT_DISTANCE_FROM_SHIP = 60;
  private static final float VELOCITY = 1f;

  public final FireBall fb = new FireBall(this);
  protected float xDiffWithPlayerShip = 0;
  protected float yDiffWithPlayerShip = 0;
  protected float distanceFromShip = 0;
  private int mode = STICKED;
  protected float distanceFromShipRequested = DEFAULT_DISTANCE_FROM_SHIP;
  protected double rotationRadians = 0;

  protected Orb(PlayerShip playerShip, int type) {
    super(playerShip, type, 0.35f);
    animationSpeed = 20f;
    chargeAnimationSpeed = 0.9f;
  }

  @Override
  public void startChargingAnimation() {
    displayChargeAnimation = true;
    fb.startAnimation();
  }

  @Override
  public void stopChargeAnimation() {
    displayChargeAnimation = false;
    chargeAnimationCursor = 0;
    fb.stopAnimation();
  }

  @Override
  public void draw() {
    animationCursor += animationSpeed * tick;
    animationCursor %= animationTextures.length;

    GL11.glLoadIdentity();
    GL11.glTranslatef(position.x, position.y, Main.DEFAULT_Z);
    GL11.glRotatef(rotation, 0f, 0f, 1f);
    GL11.glColor4f(1, 1, 1, 1);
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, animationTextures[(int)animationCursor].getTextureId());

    GL11.glBegin(GL11.GL_QUADS);
    {
      GL11.glTexCoord2f(textureRight, textureUp);
      GL11.glVertex2f(width, -height);

      GL11.glTexCoord2f(textureLeft, textureUp);
      GL11.glVertex2f(-width, -height);

      GL11.glTexCoord2f(textureLeft, textureDown);
      GL11.glVertex2f(-width, height);

      GL11.glTexCoord2f(textureRight, textureDown);
      GL11.glVertex2f(width, height);
    }
    GL11.glEnd();

    if (displayChargeAnimation) {
      chargeAnimationCursor += chargeAnimationSpeed * tick;
      if (chargeAnimationCursor >= animationTextures.length) {
        chargeAnimationCursor = animationTextures.length - 1;
      }
      float alphaPercentage = chargeAnimationCursor / (animationTextures.length - 1);
      alphaPercentage *= 2.5;
      if (alphaPercentage > 0.6f) {
        alphaPercentage = 0.6f;
      } else if (alphaPercentage < 0.15f) {
        alphaPercentage = 0;
      }
      Main.fadeAlpha = alphaPercentage;
    } else {
      Main.fadeAlpha = 0;
    }
  }

  @Override
  public boolean collided(Entity entity) {
    return false;
  }

  @Override
  public void update() {
    calculateDistanceFromShip();
    if (mode == ADJUSTING || distanceFromShip > distanceFromShipRequested) {
      if (distanceFromShip > distanceFromShipRequested) {
        speed.x = xDiffWithPlayerShip * VELOCITY;
        speed.y = yDiffWithPlayerShip * VELOCITY;
      } else {
        speed.x = 0;
        speed.y = 0;
      }
      interpolate(position, speed);
      updateOrbAngle();
    } else {
      interpolate(position, playerShip.speed);
    }
    super.update();
  }

  protected void updateOrbAngle() {
    float xDiff = position.x - playerShip.position.x;
    float yDiff = position.y - playerShip.position.y;
    rotationRadians  = Math.atan(yDiff / xDiff);
    if (xDiff < 0) {
      rotationRadians += Math.PI;
    }
    if (rotationRadians < 0)  {
      rotationRadians = 2 * Math.PI + rotationRadians;
    }
    rotation = GLUtils.radianToDegree((float)rotationRadians);
  }

  protected void calculateDistanceFromShip() {
    xDiffWithPlayerShip = playerShip.position.x - position.x;
    yDiffWithPlayerShip =  playerShip.position.y - position.y;
    distanceFromShip = (float)Math.sqrt(xDiffWithPlayerShip * xDiffWithPlayerShip + yDiffWithPlayerShip * yDiffWithPlayerShip);
  }

  public void setMove(int mode) {
    this.mode = mode;
  }
}
