public class Vector2D{

  private double x;
  private double y;

  public Vector2D(double x, double y){
    this.x = x;
    this.y = y;
  }

  public double getX(){
    return x;
  }

  public double getY(){
    return y;
  }

  public double magnitude(){
    double s = Math.pow(x, 2) + Math.pow(y, 2);//sum of the squares of x and y
    return Math.sqrt(s); //square root of the sum of the squares
  }

}
