public class PView implements IView {
  
  private final Vector2D screenCenter = new Vector2D(width/2.0, height/2.0);
  private Vector2D target;
  
  public PView() {
    target = new Vector2D(world.getWidth()/2.0, world.getHeight()/2.0);
  }
  
  public Vector2D getTarget() {
    return target;
  }
  
  public void setTarget(Vector2D newTarget) {
    target = newTarget;
  }
  
  public double getWorldScale() {
    return 20;
  }
  
  // For now, this just focuses on the center of the world,
  // with no regard to the player position
  
  public Vector2D screenToWorldPos(Vector2D screenPos) {
    return screenPos.subtract(screenCenter).divide(getWorldScale()).add(target);
  }
  
  public Vector2D worldToScreenPos(Vector2D worldPos) {
    return worldPos.subtract(target).multiply(getWorldScale()).add(screenCenter);
  }
  
}
