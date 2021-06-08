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
        return rect.contains(pos.getX(), pos.getY());
    }

    protected boolean clicking = false;

    @Override
    public boolean mousePressed(IInput input, int button) {
        if (contains(input.getMousePos())) {
            clicking = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(IInput input, int button) {
        if (clicking) {
            clicking = false;
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
