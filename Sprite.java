public class Sprite{

  private int[][] image;
  private Vector2D pivot;

//  public Sprite(Tile tile){}

//  public Sprite(Entity entity){}

  public Sprite(int[][] image, Vector2D pivot){
    this.image = image;
    this.pivot = pivot;
  }

  public int getPixel(int x, int y){
    return image[x][y];
  }

  public Vector2D getPivot(){
    return pivot;
  }

  public int getHeight(){
    return image.length;
  }

  public int getWidth(){
    return image[0].length;
  }

}
