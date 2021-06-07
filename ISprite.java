public interface ISprite {

    Vector2D getPivot();
    double getScale();
    void drawUI(Vector2D p);
    void draw(View view, Vector2D p);

}
