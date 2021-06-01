public class Renderer{
 
  public void drawSprite(Sprite s, Vector2D p, IView view){
    p = view.worldToScreenPos(p);
    double scale = view.getWorldScale();
    int fx = (int)(p.getX() - s.getPivot().getX()); //finds xcor of top left corner of sprite
    int fy = (int)(p.getY() - s.getPivot().getY()); //finds ycor of top left corner of sprite
    for (int r=0; r<s.getHeight(); r++){
      for (int c=0; c<s.getWidth(); c++){
        set(fx+c, fy+r, s.getPixel(c, r)); //sets screen pixels to color of sprite pixels
      }
    }
  }
  
}
