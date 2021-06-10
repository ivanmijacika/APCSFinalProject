public interface ISprite extends Cloneable {
    
    void setPivot(Vector2D pivot);
    Vector2D getPivot();
    void setScale(double scale);
    double getScale();
    void drawUI(Vector2D p);
    void draw(View view, Vector2D p);
    public ISprite withScale(double scale);
    void drawWithLight(View veiw, Vector2D p, double brightness);

}
