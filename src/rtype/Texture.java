package rtype;

/**
 * Created by jhooba on 2015-12-30.
 */
public class Texture {
  private final int textureId;
  private final int textureHeight;
  private final int textureWidth;

  public Texture(int textureId, int textureHeight, int textureWidth) {
    this.textureId = textureId;
    this.textureHeight = textureHeight;
    this.textureWidth = textureWidth;
  }

  public int getTextureId() {
    return textureId;
  }

  public float getTextureWidth() {
    return textureWidth;
  }

  public int getTextureHeight() {
    return textureHeight;
  }
}
