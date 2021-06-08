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

    private int convertMouseButton(int mB) {
        int button = 0;
        if (mB == LEFT) button = 1;
        else if (mB == RIGHT) button = 2;
        else if (mB == CENTER) button = 3;
        return button;
    }

    void mousePressed() {
        int button = convertMouseButton(mouseButton);
        for (IMouseListener listener : mouseListeners) {
            if (listener.mousePressed(button))
                return;
        }
    }

    void mouseReleased() {
        int button = convertMouseButton(mouseButton);
        for (IMouseListener listener : mouseListeners) {
            if (listener.mouseReleased(button))
                return;
        }
    }

    void mouseWheel(MouseEvent event) {
        int count = event.getCount();
        for (IMouseListener listener : mouseListeners) {
            listener.mouseWheel(count);
        }
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
