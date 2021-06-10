import java.awt.*;

public abstract class UIElement implements IMouseListener {

    private Rectangle rect;

    public UIElement(Rectangle rect) {
        this.rect = rect;
    }

    public Rectangle getRect() {
        return rect;
    }

    public abstract void draw();

    public boolean contains(Vector2D pos) {
        return getRect().contains(pos.getX(), pos.getY());
    }

    protected boolean[] clicking = new boolean[4];

    @Override
    public boolean mousePressed(IInput input, int button) {
        if (contains(input.getMousePos())) {
            clicking[button] = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(IInput input, int button) {
        if (clicking[button]) {
            clicking[button] = false;
            if (contains(input.getMousePos())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseWheel(IInput input, int button) {
        return false;
    }

}
