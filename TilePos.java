public class TilePos{

  private int x;
  private int y;

  public TilePos(int x, int y){
    this.x = x;
    this.y = y;
  }

  public TilePos(Vector2D p){
    x = (int)(p.getX());
    y = (int)(p.getY());
  }


  public int getX(){
    return x;
  }

  public int getY(){
    return y;
  }

  public TilePos add(int x, int y){
    int sumX = this.x + x; //sum of x coordinates
    int sumY = this.y + y;  //sum of y coordinates
    TilePos t = new TilePos(sumX, sumY);
    return t;
  }

  public boolean equals(Object other) {
    return (other instanceof TilePos) && equals((TilePos)other);
  }

  public boolean equals(TilePos other) {
    return getX() == other.getX() && getY() == other.getY();
  }

  public String toString() {
    return "(" + getX() + ", " + getY() + ")";
  }

}
