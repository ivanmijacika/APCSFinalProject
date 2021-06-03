import java.util.*;

public class PInput implements IInput {
    
    private Set<Integer> heldKeys = new HashSet<Integer>();
    
    void keyPressed() {
        heldKeys.add(keyCode);
    }
    
    void keyReleased() {
        heldKeys.remove(keyCode);
    }
    
    public boolean isHeld(int k) {
        return heldKeys.contains(k);
    }
    
    public Vector2D getMousePos() {
        return new Vector2D(mouseX, mouseY);
    }
    
}
