public class Vector2D{

    public static final Vector2D ZERO = new Vector2D(0,0);

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

  public double sqrMagnitude() {
    return x*x + y*y;
  }
  
  public double magnitude(){
    return Math.sqrt(sqrMagnitude());
  }

  public Vector2D add(Vector2D other){
    double sumX = x + other.getX(); //sum of x components
    double sumY = y + other.getY(); //sum of y components
    Vector2D res = new Vector2D(sumX, sumY);
    return res; //return resultant
  }

  public Vector2D subtract(Vector2D other){
    double difX = x - other.getX(); //difference of x components
    double difY = y - other.getY(); //difference of y components
    Vector2D res = new Vector2D(difX, difY);
    return res;
  }

  public Vector2D multiply(double factor){
    double proX = x * factor; //product of x component and factor
    double proY = y * factor; //product of y component and factor
    Vector2D res = new Vector2D(proX, proY);
    return res;
  }

  public Vector2D divide(double factor){
    double quoX = x / factor; //quotient of x component and factor
    double quoY = y / factor; //quotient of y component and factor
    Vector2D res = new Vector2D(quoX, quoY);
    return res;
  }
  
  public Vector2D scale(double x, double y) {
    return new Vector2D(getX()*x, getY()*y);
  }

  public Vector2D normalized(){
    double m = magnitude();
    return divide(m);
  }

  public boolean equals(Object other) {
    return (other instanceof Vector2D) && equals((Vector2D)other);
  }

  public boolean equals(Vector2D other) {
    return getX() == other.getX() && getY() == other.getY();
  }

  public String toString() {
    return "(" + getX() + ", " + getY() + ")";
  }

}

