public interface IInput {
    
    boolean isHeld(int key);
    Vector2D getMousePos();
    void addMouseListener(IMouseListener mouseListener);
    void removeMouseListener(IMouseListener mouseListener);
    void addKeyListener(IMouseListener mouseListener);
    void removeKeyListener(IMouseListener mouseListener);

}
