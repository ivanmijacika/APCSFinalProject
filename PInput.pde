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
    
    public Vector2D getMouseScreenPos() {
        return new Vector2D(mouseX, mouseY);
    }
    
    public Vector2D getMouseWorldPos() {
        return new Vector2D(0, 0);
    }
    
}
