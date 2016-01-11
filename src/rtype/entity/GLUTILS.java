package rtype.entity;

import org.jetbrains.annotations.Contract;
import org.lwjgl.util.vector.Vector2f;

/**
 * Created by jhooba on 2016-01-01.
 */
public class GLUtils {
  @Contract(pure = true)
  public static float radianToDegree(float radian) {
    return (float)(360 * radian / (2 * Math.PI));
  }

  @Contract("_, _ -> !null")
  public static Vector2f makeNormalForPoints(Vector2f p1, Vector2f p2) {
    return new Vector2f((p1.y - p2.y) / (p2.x - p1.x), 1);
  }

  public static void rotateAroundZ(Vector2f vector, float angleRadian) {
    double cosAngle = Math.cos(angleRadian);
    double sinAngle = Math.sin(angleRadian);
    float temp = vector.x;
    vector.x = (float)(vector.x * cosAngle - vector.y * sinAngle);
    vector.y = (float)(temp * sinAngle + vector.y * cosAngle);
  }
}
