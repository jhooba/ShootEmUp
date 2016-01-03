package rtype.entity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import rtype.Main;

import java.util.ArrayList;

/**
 * Created by jhooba on 2016-01-02.
 */
public class OrbBeam extends Entity {
  private static final float SEQ_IN_BEAM = 8.f;
  private final float r;
  private final float g;
  private final float b;
  private final Vector2f[] controlPoint = new Vector2f[3];
  private float minNormalWidth = 0.f;
  private float maxNormalWidth = 0.f;
  private float a;
  private final float disappearanceSpeed;
  private final ArrayList<Vector2f> meltingPoints = new ArrayList<>();
  private final float thickness;

  public OrbBeam(LightningOrb orb, Vector2f[] p, int thickness, boolean way, float r, float g, int b, float a,
                 int modulo, float minimalArcHeight) {
    super(ORB_BEAM, 1.f);
    this.disappearanceSpeed = a * 1.7f;
    this.r = r;
    this.g = g;
    this.b = b;
    this.a = a;
    this.thickness = thickness;
    this.position.x = orb.position.x;
    this.position.y = orb.position.y;
    this.rotation = orb.rotation;
    this.damage = 1000;
    init();

    for (int i = 0, k = 1; i < p.length - 1; ++i, ++k) {
      Vector2f p1 = p[i];
      Vector2f p2 = p[k];
      meltingPoints.add(0, p1);
      Vector2f norm = GLUTILS.makeNormalForPoints(p1, p2);
      if (Logger.isLogActivate) {
        Logger.log("norm " + norm);
      }
      int ran = (int) (Main.RANDOM.nextInt() % modulo + minimalArcHeight);
      Vector2f mid = way
          ? new Vector2f(p1.x + ((p2.x - p1.x) * 0.5f) + norm.x * ran, p1.y + ((p2.y - p1.y) * 0.5f) + norm.y * ran)
          : new Vector2f(p1.x + ((p2.x - p1.x) * 0.5f) - norm.x * ran, p1.y + ((p2.y - p1.y) * 0.5f) - norm.y * ran);
      if (way) {
        if (norm.y * ran > maxNormalWidth) {
          maxNormalWidth = norm.y * ran;
        }
      } else {
        if (-norm.y * ran > minNormalWidth) {
          minNormalWidth = -norm.y * ran;
        }
      }
      way = !way;
      float f = 1.f / (SEQ_IN_BEAM + 1);
      float s = f;
      controlPoint[0] = p1;
      controlPoint[2] = mid;
      controlPoint[1] = p2;
      for (int n = 0; n < SEQ_IN_BEAM; ++n) {
        meltingPoints.add(0, calculatePintOnCurve(controlPoint, s));
        s += f;
      }
    }
  }

  private static Vector2f calculatePintOnCurve(Vector2f[] c, float distance) {
    float a = 1 - distance;
    float b = 1 - a;
    Vector2f p = new Vector2f();
    p.x = c[0].x * a * a + c[2].x * 2 * a * b + c[1].x * b * b;
    p.y = c[0].y * a * a + c[2].y * 2 * a * b + c[1].y * b * b;
    return p;
  }

  @Override
  public boolean collided(Entity entity) {
    return false;
  }

  @Override
  public void draw() {
    if (a < 0) {
      layer.remove(this);
      if (Logger.isLogActivate) {
        Logger.log("OrbBeam removed itself from the layer.");
        return;
      }
      a -= disappearanceSpeed * tick;

      GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureId());
      GL11.glLoadIdentity();
      GL11.glTranslatef(position.x, position.y, Main.DEFAULT_Z);
      GL11.glRotatef(rotation, 0, 0, 1);
      GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);

      GL11.glColor4f(r, g, b, a);
      GL11.glBegin(GL11.GL_QUADS);
      {
        for (int i = 0; i < meltingPoints.size() - 2; ++i) {
          Vector2f p = meltingPoints.get(i);
          Vector2f p2 = meltingPoints.get(i + 1);
          Vector2f p3 = meltingPoints.get(i + 2);
          Vector2f n = (Vector2f) GLUTILS.makeNormalForPoints(p, p2).scale(thickness);
          Vector2f n2 = (Vector2f) GLUTILS.makeNormalForPoints(p2, p3).scale(thickness);

          if (i == meltingPoints.size() - 3) {
            GL11.glColor4f(r, g, b, 0);
          }

          GL11.glTexCoord2f(15 / 32.f, 1);
          GL11.glVertex2f(p2.x + n2.x, p2.y + n2.y);

          GL11.glTexCoord2f(15 / 32.f, 0);
          GL11.glVertex2f(p2.x - n2.x, p2.y - n2.y);

          if (i == meltingPoints.size() - 3) {
            GL11.glColor4f(r, g, b, a);
          }

          GL11.glTexCoord2f(15 / 32.f, 0);
          GL11.glVertex2f(p.x - n.x, p.y + n.y);

          GL11.glTexCoord2f(15 / 32.f, 1);
          GL11.glVertex2f(p.x + n.x, p.y + n.y);
        }
      }
      GL11.glEnd();
      GL11.glColor4f(1, 1, 1, 1);
    }
  }
}
