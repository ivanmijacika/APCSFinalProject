public class Renderer{
 
  public void drawSprite(Sprite s, Vector2D p, IView view){
    p = view.worldToScreenPos(p);
    int scale = (int)view.getWorldScale();
    int fx = (int)(p.getX() - scale * s.getPivot().getX()); //finds xcor of top left corner of sprite
    int fy = (int)(p.getY() - scale * s.getPivot().getY()); //finds ycor of top left corner of sprite
    for (int r=0; r<s.getHeight()*scale; r+=scale){
      for (int c=0; c<s.getWidth()*scale; c+=scale){
        fill(s.getPixel(c/scale, r/scale));
        square(fx, fy, scale); //instead of coloring one pixel, a square of size 'extent' is drawn
      }
    }
  }
  
}
