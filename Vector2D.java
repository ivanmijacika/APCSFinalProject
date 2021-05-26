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

  public Vector2D add(Vector2D other){
    double sumX = x + other.getX(); //sum of x components
    double sumY = y + other.getY(); //sum of y components
    Vector2D sum = new Vector2D(sumX, sumY);
    return sum;
  }

  public Vector2D subtract(Vector2D other){
    double difX = x - other.getX(); //difference of x components
    double difY = y - other.getY(); //difference of y components
    Vector2D dif = new Vector2D(difX, difY);
    return dif;
  }



}

