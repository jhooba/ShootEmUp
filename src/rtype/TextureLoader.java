package rtype;

import rtype.entity.IEntity;

import java.awt.image.BufferedImage;

/**
 * Created by jhooba on 2015-12-20.
 */
public class TextureLoader {
  private Texture[][] animations = new Texture[1024][1024];
  public void init() {
    animations[IEntity.PLAYER_SHIP] = loadAnimation("/graphic/PlayerShip.png", 4, 4, 128, 128);
    animations[IEntity.GREEN_ORB] = loadAnimation("/graphic/Orb1.png", 8, 2, 64, 64);
    animations[IEntity.BLUE_ORB] = loadAnimation("/graphic/Orb1.png", 8, 2, 64, 64, 0, 128);
    animations[IEntity.PINK_ORB] = loadAnimation("/graphic/Orb1.png", 8, 2, 64, 64, 0, 256);
    animations[IEntity.RED_ORB] = loadAnimation("/graphic/Orb1.png", 8, 2, 64, 64, 0, 384);
    animations[IEntity.FORCE_CHARGE] = loadAnimation("/graphic/forceCharge.png", 8, 4, 64, 64);
    animations[IEntity.PLAYER_SPEED] = loadAnimation("/graphic/PlayerSpeed.png", 8, 4, 64, 64);
    animations[IEntity.EXPLOSION_1] = loadAnimation("/graphic/Explosion2.png", 8, 4, 64, 64);
    animations[IEntity.EXPLOSION_2] = loadAnimation("/graphic/ExplosionSmall.png", 8, 4, 128, 128);
    animations[IEntity.LADYBIRD] = loadAnimation("/graphic/Ladybird.png", 4, 4, 64, 64);
    animations[IEntity.FIRE_BALL] = loadAnimation("/graphic/Fireball.png", 4, 4, 128, 128);
    animations[IEntity.FONT] = loadAnimation("/graphic/Fonts.png", 16, 16, 32, 32);
    animations[IEntity.BULLET_HIT_YELLOW] = loadAnimation("/graphic/BulletHit.png", 4, 2, 32, 32);
    animations[IEntity.BULLET_HIT_GREEN]  = loadAnimation("/graphic/BulletHit2.png", 4, 2, 32, 32);
    animations[IEntity.BULLET_HIT_BLUE] = loadAnimation("/graphic/BulletHit3.png", 4, 2, 32, 32);
    animations[IEntity.BIT_UPGRADE] = loadAnimation("/graphic/BitUpgrade.png", 4, 2, 32, 32);
    animations[IEntity.BONUS_MULTI_SHOOT_ORB] = loadAnimation("/graphic/PowerUp1.png", 8, 1, 64, 64, 0, 0);
    animations[IEntity.BONUS_LIGHTNING_ORB] = loadAnimation("/graphic/PowerUp1.png", 8, 1, 64, 64, 0, 64);
    animations[IEntity.BONUS_RAPID_SHOOT_ORB] = loadAnimation("/graphic/PowerUp1.png", 8, 1, 64, 64, 0, 128);
    animations[IEntity.BONUS_GRAVITY_ORB] = loadAnimation("/graphic/PowerUp1.png", 8, 1, 64, 64, 0, 192);
    animations[IEntity.BONUS_SPEED] = loadAnimation("/graphic/PowerUp1.png", 8, 1, 64, 64, 0, 256);
    animations[IEntity.BONUS_CRYSTAL_ORB] = loadAnimation("/graphic/PowerUp1.png", 8, 1, 64, 64, 0, 320);
    animations[IEntity.BONUS_BOOSTER] = loadAnimation("/graphic/PowerUp1.png", 8, 1, 64, 64, 0, 384);
    animations[IEntity.IMPLOSION] = loadAnimation("/graphic/Implosion.png", 8, 4, 64, 64);
    animations[IEntity.MISSILE] = loadAnimation("/graphic/HomingMissile.png", 4, 4, 64, 64);
    animations[IEntity.SMOKE] = loadAnimation("/graphic/SmokePuff.png", 4, 4, 32, 32);
  }

  private Texture[] loadAnimation(String path, int cols, int rows, int texWidth, int texHeight) {
    return loadAnimation(path, cols, rows, texWidth, texHeight, 0, 0);
  }

  private Texture[] loadAnimation(String path, int cols, int rows, int texWidth, int texHeight, int xOffset, int yOffset) {
    Texture[] result = new Texture[cols * rows];
    for (int i = 0; i < rows; ++i) {
      for (int j= 0; j < cols; ++j) {
        result[i * cols + j] = loadTexture(path, j * texWidth + xOffset, i * texHeight + yOffset, texWidth, texHeight);
      }
    }
    return result;
  }

  private Texture loadTexture(String path, int xOffset, int yOffset, int texWidth, int texHeight) {
    BufferedImage buffImage = null;

  }

  public Texture[] getAnimation(int animationId) {
    return animations[animationId];
  }
}
