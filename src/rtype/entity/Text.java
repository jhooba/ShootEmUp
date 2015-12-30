package rtype.entity;

/**
 * Created by jhooba on 2015-12-20.
 */
public class Text extends AnimatedEntity {
  private String string;

  public Text(String string) {
    super(FONT);
    setString(string);
    init();
    setRatio(0.4f);
  }

  public void setString(String string) {
    this.string = string;
  }

  @Override
  public void update() {
  }

  @Override
  public void draw() {

  }
}
