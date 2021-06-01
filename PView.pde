public class PView implements IView {
  
  private final Vector2D screenCenter = new Vector2D(width/2.0, height/2.0);
  private World world;
  private Vector2D target;
  
  public PView(World world) {
    this.world = world;
    target = new Vector2D(world.getWidth()/2.0, world.getHeight()/2.0);
  }
  
  double getWorldScale() {
    return 4;
  }
  
  // For now, this just focuses on the center of the world,
  // with no regard to the player position
  
  Vector2D screenToWorldPos(Vector2D screenPos) {
    return screenPos.subtract(screenCenter).divide(getWorldScale()).add(target);
  }
  
  Vector2D worldToScreenPos(Vector2D worldPos) {
    return worldPos.subtract(target).multiply(getWorldScale()).add(screenCenter);
  }
  
}
