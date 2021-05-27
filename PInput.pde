public class PInput implements IInput {
    
    public boolean isHeld(int key) {
        return false;
    }
    
    public Vector2D getMouseScreenPos() {
        return new Vector2D(mouseX, mouseY);
    }
    
    public Vector2D getMouseWorldPos() {
        return new Vector2D(0, 0);
    }
    
}
