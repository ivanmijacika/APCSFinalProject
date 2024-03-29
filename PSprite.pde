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

  public void setPivot(Vector2D pivot) {
    this.pivot = pivot;
  }

  public int getHeight() {
    return image.height;
  }

  public int getWidth() {
    return image.width;
  }

  public void setScale(double scale) {
    this.scale = scale;
  }

  public double getScale() {
    return scale;
  }
 
  public void drawUI(Vector2D p){
    int scale = (int)getScale();
    float fx = (float)(p.getX() - scale * getPivot().getX()); //finds xcor of top left corner of sprite
    float fy = (float)(p.getY() - scale * getPivot().getY()); //finds ycor of top left corner of sprite

    for (int y = 0; y < getHeight() * scale; y++) {
        for (int x = 0; x < getWidth() * scale; x++) {
            int col = getPixel(x/scale, y/scale);
            if (alpha(col) != 0) {
                set((int)fx+x, (int)fy+y, col);
            }
        }
    }
  } 

  public void draw(View view, Vector2D p) {
    p = view.worldToScreenPos(p);
    int scale = (int)(getScale() * view.getWorldScale());
    float fx = (float)(p.getX() - scale * getPivot().getX()); //finds xcor of top left corner of sprite
    float fy = (float)(p.getY() - scale * getPivot().getY()); //finds ycor of top left corner of sprite
    
    for (int y = 0; y < getHeight() * scale; y++) {
        for (int x = 0; x < getWidth() * scale; x++) {
            int col = getPixel(x/scale, y/scale);
            if (alpha(col) != 0) {
                set((int)fx+x, (int)fy+y, col);
            }
        }
    }
  }

  public ISprite withScale(double scale) {
    return new PSprite(image, getPivot(), scale);
  }
  
  public void drawWithLight(View view, Vector2D p, double brightness){//adjusts darkness
    p = view.worldToScreenPos(p);
    int scale = (int)(getScale() * view.getWorldScale());
    float fx = (float)(p.getX() - scale * getPivot().getX()); //finds xcor of top left corner of sprite
    float fy = (float)(p.getY() - scale * getPivot().getY()); //finds ycor of top left corner of sprite
    
    for (int y = 0; y < getHeight() * scale; y++) {
        for (int x = 0; x < getWidth() * scale; x++) {
            int col = getPixel(x/scale, y/scale);
            int r = (int)(brightness * red(col));
            int g = (int)(brightness * green(col));
            int b = (int)(brightness * blue(col));
            col = color(r, g, b, alpha(col));
            if (alpha(col) != 0) {
                set((int)fx+x, (int)fy+y, col);
            }
        }
    }
  }
  
  public ISprite flipped(){
    PImage imageF = createImage(image.width, image.height, ARGB); //will be flipped image
    for (int y=0; y<image.height; y++){
      for (int x=0; x<image.width; x++){
        imageF.set(x, y, image.get(image.width-x-1,y));
      }
    }
    return new PSprite(imageF, getPivot(), scale);
  }

}
