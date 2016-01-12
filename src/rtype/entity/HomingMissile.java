package rtype.entity;

import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import rtype.Layer;
import rtype.Main;

/**
 * Created by jhooba on 2015-12-27.
 */
public class HomingMissile extends AnimatedEntity {
  private static final float SMOKE_GEN_SPEED = 130f;
  private static final float SMOKE_LIMIT = 5;
  private static final Vector2f SPEED_NULL = new Vector2f(0,0);

  private final Entity target;
  private final Vector2f oldPosition = new Vector2f();
  private final Vector2f smokePosition = new Vector2f();
  private final float maxRadianManiAbility;
  private float smokeAccumulator = 0;

  public HomingMissile(Layer targetLayer, float maxRadianManiAbility) {
    super(MISSILE, 0.25f, 40);
    target = acquireNewTarget(targetLayer);
    life = 2;
    this.maxRadianManiAbility = maxRadianManiAbility;
    init();
  }

  @Nullable
  private Entity acquireNewTarget(Layer targetLayer) {
    if (targetLayer.entities.size() == 0) {
      return null;
    }
    return targetLayer.entities.get(0);
  }

  @Override
  public void draw() {
    updateAnimationCursor();
    GL11.glLoadIdentity();
    GL11.glTranslatef(position.x, position.y, Main.DEFAULT_Z);
    GL11.glRotatef(rotation, 0, 0, 1);
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, getCursorAnimationTextureId());
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

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
  }

  @Override
  public void update() {
    oldPosition.x = position.x;
    oldPosition.y = position.y;
    interpolate(position, speed);
    if (frozen) {
      return;
    }
    smokeAccumulator += SMOKE_GEN_SPEED * tick;
    if (smokeAccumulator > SMOKE_LIMIT) {
      Smoke smoke = new Smoke();
      smokePosition.x = position.x - 15;
      smokePosition.y = Main.RANDOM.nextInt(5);
      smokePosition.y = Main.RANDOM.nextInt(2) == 0 ? smokePosition.y : -smokePosition.y;
      smokePosition.y += position.y - 5;
      smoke.spawn(smokePosition, Main.DEFAULT_SCROLLING_SPEED, Main.fx);
      smokeAccumulator = 0;
    }
    if (position.x - width > Main.SCREEN_WIDTH / 2 || position.x + width < - Main.SCREEN_WIDTH / 2) {
      unSpawn();
      if (Logger.isLogActivate) {
        Logger.log(this.getClass().getName()+" died");
      }
      return;
    }
    Vector2f targetCooRelativeToThis = new Vector2f();
    if (target.life <= 0) {
      acquireNewTarget(target.getLayer());
    }
    targetCooRelativeToThis.x = target.position.x - position.x;
    targetCooRelativeToThis.y = target.position.y - position.y;

    float speedAngleInRadians = (float)Math.atan2(speed.y, speed.x);
    if (speedAngleInRadians < 0) {
      speedAngleInRadians += 2 * Math.PI;
    }
    float targetAngleInRadians = (float)Math.atan2(targetCooRelativeToThis.y,targetCooRelativeToThis.x);
    if (targetAngleInRadians < 0) {
      targetAngleInRadians += 2 * Math.PI;
    }
    float angleDifferenceInRadians;
    if (speedAngleInRadians > targetAngleInRadians) {
      angleDifferenceInRadians = speedAngleInRadians - targetAngleInRadians;
      angleDifferenceInRadians = -angleDifferenceInRadians;
    } else {
      angleDifferenceInRadians = targetAngleInRadians-speedAngleInRadians;
    }
    float angleToAdd;
    if (Math.abs(angleDifferenceInRadians) > maxRadianManiAbility) {
      angleToAdd = maxRadianManiAbility;
      if (angleDifferenceInRadians < 0) {
        angleToAdd = -angleToAdd;
      }
    } else {
      angleToAdd =  angleDifferenceInRadians;
    }
    GLUtils.rotateAroundZ(speed, angleToAdd);
    rotation += GLUtils.radianToDegree(angleToAdd);
  }

  @Override
  public boolean collided(Entity entity) {
    life = -1;
    unSpawn();
    Explosion ex = new Explosion(Main.RANDOM.nextInt(2) + IEntity.EXPLOSION_1);
    ex.spawn(position, SPEED_NULL, Main.frontground);
    return true;
  }
}
