public interface IView {

    double getWorldScale();
    Vector2D screenToWorldPos(Vector2D screenPos);
    Vector2D worldToScreenPos(Vector2D worldPos);

}
