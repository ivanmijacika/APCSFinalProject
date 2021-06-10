import java.awt.*;

public class Inventory extends UIElement {

    private ISprite sprite;
    private ItemStack[] stacks = new ItemStack[40];

    public Inventory(ISpriteLoader spriteLoader) {
        super(new Rectangle(10, 10, 392, 32));
        sprite = spriteLoader.load("uiButton.png", new Vector2D(8, 8), 2);
    }

    @Override
    public void draw() {
        Rectangle rect = getRect();
        Vector2D pos = new Vector2D(rect.getX() + 16, rect.getY() + 16);
        for (int i = 0; i < 10; i++) {
            sprite.drawUI(pos);
            // temp, showcases how items will be drawn in inventory
            Tile.STONE.getItem().drawUI(pos);
            pos = pos.add(new Vector2D(40, 0));
        }
    }

}
