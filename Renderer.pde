public class Renderer{
 
  void drawSprite(Sprite s, Vector2D p){
    int fx = (int)(p.getX() - s.getPivot().getX());
    int fy = (int)(p.getY() - s.getPivot().getY());
    for (int r=0; r<s.getHeight(); r++){
      for (int c=0; c<s.getWidth(); c++){
        set(fx+c, fy+r, s.getPixel(c, r)); 
      }
    }
  }
  
}
