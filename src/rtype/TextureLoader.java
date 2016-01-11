package rtype;

import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

import rtype.entity.IEntity;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * Created by jhooba on 2015-12-20.
 */
public class TextureLoader {
  private final Texture[] textures =  new Texture[1024];
  private final Texture[][] animations = new Texture[1024][1024];
  private final HashMap<String, BufferedImage> imageCache = new HashMap<>();

  public void init() {
    textures[IEntity.PLANET] = loadTexture("/graphic/Stage1Layer1.png", 510, 510, 512, 512);
    textures[IEntity.SPACE_TRASH_1] = loadTexture("/graphic/Stage1Layer1.png", 256, 438, 256, 256);
    textures[IEntity.SPACE_TRASH_2] = loadTexture("/graphic/Stage1Layer1.png", 257, 700, 256, 256);
    textures[IEntity.SPACE_TRASH_3] = loadTexture("/graphic/Stage1Layer1.png", 373, 956, 64, 64);
    textures[IEntity.SPACE_TRASH_4] = loadTexture("/graphic/Stage1Layer1.png", 492, 102, 256, 128);
    textures[IEntity.SPACE_TRASH_5] = loadTexture("/graphic/Stage1Layer1.png", 148, 721, 64, 64);
    textures[IEntity.STAR_1] = loadTexture("/graphic/Stage1Layer1.png", 612, 1011, 8, 8);
    textures[IEntity.STAR_2] = loadTexture("/graphic/Stage1Layer1.png", 649, 990, 16, 32);
    textures[IEntity.STAR_3] = loadTexture("/graphic/Stage1Layer1.png", 674, 994, 16, 16);
    textures[IEntity.STAR_4] = loadTexture("/graphic/Stage1Layer1.png", 716, 1004, 16, 16);
    textures[IEntity.STAR_5] = loadTexture("/graphic/Stage1Layer1.png", 738, 990, 32, 32);
    textures[IEntity.STAR_6] = loadTexture("/graphic/Stage1Layer1.png", 780, 994, 16, 16);
    textures[IEntity.ENEMY_PIECE_1] = loadTexture("/graphic/SpaceTrash.png", 0, 0, 32, 32);
    textures[IEntity.ENEMY_PIECE_2] = loadTexture("/graphic/SpaceTrash.png", 32, 0, 32, 32);
    textures[IEntity.ENEMY_PIECE_3] = loadTexture("/graphic/SpaceTrash.png", 64, 0, 32, 32);
    textures[IEntity.ENEMY_PIECE_4] = loadTexture("/graphic/SpaceTrash.png", 96, 0, 32, 32);
    textures[IEntity.ENEMY_PIECE_5] = loadTexture("/graphic/SpaceTrash.png", 0, 32, 32, 32);
    textures[IEntity.ENEMY_PIECE_6] = loadTexture("/graphic/SpaceTrash.png", 32, 32, 32, 32);
    textures[IEntity.ENEMY_PIECE_7] = loadTexture("/graphic/SpaceTrash.png", 64, 32, 32, 32);
    textures[IEntity.ENEMY_PIECE_8] = loadTexture("/graphic/SpaceTrash.png", 96, 32, 32, 32);
    textures[IEntity.BULLET_RAPID_FIRE] = loadTexture("/graphic/BulletSet1.png", 0, 64, 32, 32);
    textures[IEntity.ORB_BEAM] = loadTexture("/graphic/GlowBullets.png", 32, 0, 32, 32);
    textures[IEntity.ENEMY_BULLET] = loadTexture("/graphic/BulletSet1.png", 0, 0, 32, 32);
    textures[IEntity.FORCE_BLAST] = loadTexture("/graphic/ForceBlast.png",0, 0, 128, 64);
    textures[IEntity.BULLET_RAPID_FIRE] = loadTexture("/graphic/BulletSet1.png", 0, 64, 32, 32);

    animations[IEntity.PLAYER_SHIP] = loadAnimation("/graphic/PlayerShip.png", 4, 4, 128, 128);
    animations[IEntity.GREEN_ORB] = loadAnimation("/graphic/Orb1.png", 8, 2, 64, 64);
    animations[IEntity.BLUE_ORB] = loadAnimation("/graphic/Orb1.png", 8, 2, 64, 64, 0, 128);
    animations[IEntity.PINK_ORB] = loadAnimation("/graphic/Orb1.png", 8, 2, 64, 64, 0, 256);
    animations[IEntity.RED_ORB] = loadAnimation("/graphic/Orb1.png", 8, 2, 64, 64, 0, 384);
    animations[IEntity.FORCE_CHARGE] = loadAnimation("/graphic/ForceCharge.png", 8, 4, 64, 64);
    animations[IEntity.EXPLOSION_1] = loadAnimation("/graphic/Explosion2.png", 8, 4, 64, 64);
    animations[IEntity.EXPLOSION_2] = loadAnimation("/graphic/ExplosionSmall.png", 8, 4, 128, 128);
    animations[IEntity.FONT] = loadAnimation("/graphic/Fonts.png", 16, 16, 32, 32);
    animations[IEntity.PLAYER_SPEED] = loadAnimation("/graphic/PlayerSpeed.png", 8, 4, 64, 64);
    animations[IEntity.LADYBIRD] = loadAnimation("/graphic/Ladybird.png", 4, 4, 64, 64);
    animations[IEntity.FIRE_BALL] = loadAnimation("/graphic/Fireball.png", 4, 4, 128, 128);
    animations[IEntity.BULLET_HIT_YELLOW] = loadAnimation("/graphic/BulletHit.png", 4, 2, 32, 32);
    animations[IEntity.BULLET_HIT_GREEN]  = loadAnimation("/graphic/BulletHit2.png", 4, 2, 32, 32);
    animations[IEntity.BULLET_HIT_BLUE] = loadAnimation("/graphic/BulletHit3.png", 4, 2, 32, 32);
    animations[IEntity.BIT_UPGRADE] = loadAnimation("/graphic/BitUpgrade.png", 4, 2, 32, 32);
    animations[IEntity.IMPLOSION] = loadAnimation("/graphic/Implosion.png", 8, 4, 64, 64);
    animations[IEntity.MISSILE] = loadAnimation("/graphic/HomingMissile.png", 4, 4, 64, 64);
    animations[IEntity.SMOKE] = loadAnimation("/graphic/SmokePuff.png", 4, 4, 32, 32);
    animations[IEntity.BONUS_BOOSTER] = loadAnimation("/graphic/PowerUp1.png", 8, 1, 64, 64, 0, 384);
    animations[IEntity.BONUS_LIGHTNING_ORB] = loadAnimation("/graphic/PowerUp1.png", 8, 1, 64, 64, 0, 64);
    animations[IEntity.BONUS_GRAVITY_ORB] = loadAnimation("/graphic/PowerUp1.png", 8, 1, 64, 64, 0, 192);
    animations[IEntity.BONUS_RAPID_SHOOT_ORB] = loadAnimation("/graphic/PowerUp1.png", 8, 1, 64, 64, 0, 128);
    animations[IEntity.BONUS_CRYSTAL_ORB] = loadAnimation("/graphic/PowerUp1.png", 8, 1, 64, 64, 0, 320);
    animations[IEntity.BONUS_MULTI_SHOOT_ORB] = loadAnimation("/graphic/PowerUp1.png", 8, 1, 64, 64, 0, 0);
    animations[IEntity.BONUS_SPEED] = loadAnimation("/graphic/PowerUp1.png", 8, 1, 64, 64, 0, 256);
  }

  // Offset are in term off pixel, not byte, the image loader figure out alone what is the bytesPerPixel
  @Nullable
  private Texture loadTexture(String path, int xOffset, int yOffset, int texWidth, int texHeight) {
    BufferedImage buffImage = null;
    if (imageCache.get(path) != null) {
      buffImage = imageCache.get(path);
    } else {
      System.out.println("Loading image : " + path);
      try {
        buffImage = ImageIO.read(getClass().getResource(path));
        byte[] data = ((DataBufferByte)buffImage.getRaster().getDataBuffer()).getData();
        switch (buffImage.getType()) {
          case BufferedImage.TYPE_4BYTE_ABGR:
            convertFromABGRToRGBA(data);
            break;
          case BufferedImage.TYPE_3BYTE_BGR:
            convertFromBGRToRGB(data);
            break;
          default:
            System.out.println("Unknown type : " + buffImage.getType());
            break;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      imageCache.put(path, buffImage);
    }
    if (buffImage == null) {
      return null;
    }
    int bytesPerPixel = buffImage.getColorModel().getPixelSize() / 8;
    ByteBuffer scratch = ByteBuffer.allocateDirect(texWidth * texHeight * bytesPerPixel)
        .order(ByteOrder.nativeOrder());
    DataBufferByte dataBufferByte = (DataBufferByte)buffImage.getRaster().getDataBuffer();
    for (int i = 0; i < texHeight; ++i) {
      scratch.put(dataBufferByte.getData(),
          (xOffset + (yOffset + i) * buffImage.getWidth()) * bytesPerPixel,
          texWidth * bytesPerPixel);
    }
    scratch.rewind();

    // Create A IntBuffer For Image Address In Memory
    IntBuffer buf = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer();
    GL11.glGenTextures(buf);  // Create Texture In OpenGL

    // Create Nearest Filtered Texture
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, buf.get(0));
    GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
    GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
    GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, texWidth, texHeight, 0, GL11.GL_RGBA,
        GL11.GL_UNSIGNED_BYTE, scratch);

    return new Texture(buf.get(0), texHeight, texWidth);
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

  private static void convertFromABGRToRGBA(byte[] data) {
    for (int i = 0; i <  data.length; i += 4) {
			byte a = data[i];
      byte b = data[i + 1];
      byte g = data[i + 2];
      byte r = data[i + 3];
      if (data[i] != r) {
			  data[i] = r;
      }
      if (data[i + 1] != g) {
        data[i + 1] = g;
      }
      if (data[i + 2] != b) {
        data[i + 2] = b;
      }
      if (data[i + 3] != a) {
			  data[i + 3] = a;
      }
    }
  }

  private static void convertFromBGRToRGB(byte[] data) {
    for (int i = 0; i < data.length; i += 3) {
      byte b = data[i];
      byte g = data[i + 1];
      byte r = data[i + 2];
      if (data[i] != r) {
        data[i] = r;
      }
      if (data[i + 1] != g) {
        data[i + 1] = g;
      }
      if (data[i + 2] != b) {
        data[i + 2] = b;
      }
    }
  }

  public Texture[] getAnimation(int animationId) {
    return animations[animationId];
  }

  public Texture getTexture(int textureId) {
    return textures[textureId];
  }
}
