public class PTextRenderer implements ITextRenderer {
  
  public void drawUI(String str, Vector2D pos) {
    fill(#000000);
    textFont(black);
    text(str, (float)pos.getX(), (float)pos.getY());
    fill(#FFFFFF);
    textFont(normal);
    text(str, (float)pos.getX(), (float)pos.getY());
  }
  
  public void draw(String str, View view, Vector2D pos) {
    pos = view.screenToWorldPos(pos);
    drawUI(str, pos);
  }
  
}
