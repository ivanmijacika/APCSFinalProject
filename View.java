public class View {

    private final Vector2D screenCenter;
    private double scale;

    private Vector2D target = Vector2D.ZERO;

    public View(double width, double height, double scale) {
        screenCenter = new Vector2D(width/2, height/2);
        this.scale = scale;
    }

    public double getWorldScale() {
        return scale;
    }

    public void setWorldScale(double scale) {
        this.scale = scale;
    }

    public Vector2D getTarget() {
        return target;
    }

    public void setTarget(Vector2D newTarget) {
        target = newTarget;
    }

    public Vector2D screenToWorldPos(Vector2D screenPos) {
        return screenPos.subtract(screenCenter).divide(getWorldScale()).add(target);
    }

    public Vector2D worldToScreenPos(Vector2D worldPos) {
        return worldPos.subtract(target).multiply(getWorldScale()).add(screenCenter);
    }

}
