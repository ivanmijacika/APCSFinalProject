import java.util.*;

public class PInput implements IInput {
    
    private Set<Integer> heldKeys = new HashSet<Integer>();
    // used LinkedHashSet to maintain order
    private Collection<IMouseListener> mouseListeners = new LinkedHashSet<IMouseListener>();
    private Collection<IKeyListener> keyListeners = new LinkedHashSet<IKeyListener>();
    
    void keyPressed() {
        if (key == ESC) key = 0; // stop Processing from quitting
        heldKeys.add(keyCode);
        for (IKeyListener listener : keyListeners) {
            if (listener.keyPressed(this, keyCode))
                return;
        }
    }
    
    void keyReleased() {
        heldKeys.remove(keyCode);
        for (IKeyListener listener : keyListeners) {
            if (listener.keyReleased(this, keyCode))
                return;
        }
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
            if (listener.mousePressed(this, button))
                return;
        }
    }

    void mouseReleased() {
        int button = convertMouseButton(mouseButton);
        for (IMouseListener listener : mouseListeners) {
            if (listener.mouseReleased(this, button))
                return;
        }
    }

    void mouseWheel(MouseEvent event) {
        int count = event.getCount();
        for (IMouseListener listener : mouseListeners) {
            listener.mouseWheel(this, count);
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

    public void addKeyListener(IKeyListener keyListener) {
        keyListeners.add(keyListener);
    }

    public void removeKeyListener(IKeyListener keyListener) {
        keyListeners.remove(keyListener);
    }
    
}
