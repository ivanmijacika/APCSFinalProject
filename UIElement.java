import java.awt.*;

public abstract class UIElement implements IMouseListener {

    private Rectangle rect;

    public UIElement(Rectangle rect) {
        this.rect = rect;
    }

    // still figuring out how exactly this should all work
    // i better get back to the UML...
}
