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
 
  public void drawUI(Vector2D p){
    float fx = (float)(p.getX() - getPivot().getX()); //finds xcor of top left corner of sprite
    float fy = (float)(p.getY() - getPivot().getY()); //finds ycor of top left corner of sprite
    /* just draw image
    int newW = image.width;
    int newH = image.height;
    image(image, fx, fy, newW, newH);
    //*/
    /* draw using `square`   => not really useful for drawUI
    noStroke();
    for (int r = 0; r < getHeight(); r++){
      for (int c = 0; c < getWidth(); c++){
        fill(getPixel(c, r));
        square(fx + c, fy + r, 1); //instead of coloring one pixel, a square of size 'extent' is drawn
      }
    }
    //*/
    //* set each pixel
    for (int y = 0; y < getHeight(); y++) {
        for (int x = 0; x < getWidth(); x++) {
            int col = getPixel(x, y);
            if (alpha(col) != 0) {
                set((int)fx+x, (int)fy+y, col);
            }
        }
    }
    //*/
  } 

  public void draw(View view, Vector2D p) {
    p = view.worldToScreenPos(p);
    int scale = (int)(getScale() * view.getWorldScale());
    float fx = (float)(p.getX() - scale * getPivot().getX()); //finds xcor of top left corner of sprite
    float fy = (float)(p.getY() - scale * getPivot().getY()); //finds ycor of top left corner of sprite
    /* just draw image
    int newW = image.width * scale;
    int newH = image.height * scale;
    image(image, fx, fy, newW, newH);
    //*/
    /* draw using `square`
    noStroke();
    for (int r = 0; r < getHeight(); r++){
      for (int c = 0; c < getWidth(); c++){
        fill(getPixel(c, r));
        square(fx + c*scale, fy + r*scale, scale); //instead of coloring one pixel, a square of size 'extent' is drawn
      }
    }
    //*/
    //* set each pixel
    for (int y = 0; y < getHeight() * scale; y++) {
        for (int x = 0; x < getWidth() * scale; x++) {
            int col = getPixel(x/scale, y/scale);
            if (alpha(col) != 0) {
                set((int)fx+x, (int)fy+y, col);
            }
        }
    }
    //*/
  }

}
