public class PSprite implements ISprite {

  private PImage image;
  private Vector2D pivot;
  private double scale;

  public PSprite(PImage image, Vector2D pivot, double scale) {
    this.image = image;
    this.pivot = pivot;
    this.scale = scale;
  }

  public int getPixel(int x, int y) {
    return image.get(x, y);
  }

  public Vector2D getPivot() {
    return pivot;
  }

  public int getHeight() {
    return image.height;
  }

  public int getWidth() {
    return image.width;
  }

  public double getScale() {
    return scale;
  }

  public void draw(View view, Vector2D p) {
    p = view.worldToScreenPos(p);
    float scale = (float)(getScale() * view.getWorldScale());
    float fx = (float)(p.getX() - scale * getPivot().getX()); //finds xcor of top left corner of sprite
    float fy = (float)(p.getY() - scale * getPivot().getY()); //finds ycor of top left corner of sprite
    noStroke();
    for (int r = 0; r < getHeight(); r++){
      for (int c = 0; c < getWidth(); c++){
        fill(getPixel(c, r));
        square(fx + c*scale, fy + r*scale, scale); //instead of coloring one pixel, a square of size 'extent' is drawn
      }
    }
  }

}
