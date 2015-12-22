package rtype.entity;

import rtype.entity.AnimatedEntity;

/**
 * Created by jhooba on 2015-12-20.
 */
public class Text extends AnimatedEntity {
  private String string;

  public Text(String str) {
  }

  public void setString(String string) {
    this.string = string;
  }
}
