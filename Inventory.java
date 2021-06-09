import org.w3c.dom.css.Rect;

import java.awt.*;

public class Inventory extends UIElement {

    ISprite sprite;

    public Inventory(ISpriteLoader spriteLoader) {
        super(new Rectangle(10, 10, 392, 32));
        sprite = spriteLoader.load("uiButton.png", Vector2D.ZERO, 2);
    }

    @Override
    public void draw() {
        Rectangle rect = getRect();
        Vector2D pos = new Vector2D(rect.getX(), rect.getY());
        for (int i = 0; i < 10; i++) {
            sprite.drawUI(pos);
            pos = pos.add(new Vector2D(40, 0));
        }
    }

}
