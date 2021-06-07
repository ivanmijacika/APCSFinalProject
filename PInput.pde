import java.util.*;

public class PInput implements IInput {
    
    private Set<Integer> heldKeys = new HashSet<Integer>();
    // used LinkedHashSet to maintain order
    private Set<IMouseListener> mouseListeners = new LinkedHashSet<IMouseListener>();
    
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

    public void addMouseListener(IMouseListener mouseListener) {
        mouseListeners.add(mouseListener);
    }

    public void removeMouseListener(IMouseListener mouseListener) {
        mouseListeners.remove(mouseListener);
    }
    
}
