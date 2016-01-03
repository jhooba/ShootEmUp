package rtype.entity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import rtype.BonusFactory;
import rtype.Main;

/**
 * Created by jhooba on 2015-12-25.
 */
public class LadyBird extends Enemy {
  private static final int BONUS_RANGE = 600;
  private static final int BONUS_LIMIT = 1;

  private int lastFireCounter;
  private Bonus presetBonus;
  private float fireSpeed = 0.05f;

  public LadyBird() {
    super(LADYBIRD, 0.45f);
    animationSpeed = 15f;
    init();
    flipYAxis();
    lastFireCounter = Main.RANDOM.nextInt();
    animationCursor = System.currentTimeMillis() % (animationTextures.length - 1);
  }

  public void setPresetBonus(Bonus presetBonus) {
    this.presetBonus = presetBonus;
  }

  @Override
  public void draw() {
    animationCursor += animationSpeed * tick;
    animationCursor %= animationTextures.length;

    GL11.glLoadIdentity();
    GL11.glTranslatef(position.x, position.y, Main.DEFAULT_Z);
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, animationTextures[(int)animationCursor].getTextureId());
    if (freezing) {
      GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    } else {
      GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }
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
    super.update();
    lastFireCounter += fireSpeed * tick;
    if (lastFireCounter > 1) {
      lastFireCounter = 0;
      fire();
    }
  }

  private void fire() {
    // Fire a bullet to player's ship
    Vector2f directionToPlayer = new Vector2f(Main.player.position.x - position.x, Main.player.position.y - position.y);
    directionToPlayer.normalise();
    directionToPlayer.x *= 50;
    directionToPlayer.y *= 50;

    EnemyBullet b = new EnemyBullet();
    b.spawn(this.position, directionToPlayer, Main.enemies);
  }

  @Override
  public boolean collided(Entity entity) {
    if (super.collided(entity)) {
      Bonus bonus = null;
      if (presetBonus != null) {
        bonus = presetBonus;
      }
      if (bonus != null && Main.RANDOM.nextInt(BONUS_RANGE) <= BONUS_LIMIT) {
        bonus = BonusFactory.createBonus(Main.RANDOM.nextInt(Bonus.BONUS_COUNT) + BONUS_BOOSTER);
      }
      if (bonus != null) {
        bonus.spawn(position, speed, Main.bonus);
      }
      return true;
    }
    return false;
  }
}
