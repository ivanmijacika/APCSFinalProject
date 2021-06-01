public interface ISprite {

    Vector2D getPivot();
    double getScale();
    void draw(View view, Vector2D p);

}
