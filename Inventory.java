import java.awt.*;
import java.awt.event.KeyEvent;

public class Inventory extends UIElement implements IKeyListener {
    
    private static final int CLOSED_HEIGHT = 32;
    private static final int OPEN_HEIGHT = 152;
    
    private ISprite sprite;
    private ItemStack[] stacks = new ItemStack[40];
    
    private int selected = -1;
    private boolean isOpen = false;

    public Inventory(ISpriteLoader spriteLoader) {
        super(new Rectangle(10, 10, 392, CLOSED_HEIGHT));
        sprite = spriteLoader.load("uiButton.png", new Vector2D(8, 8), 2);
    }
    
    private void drawSlot(int slot) {
        Rectangle rect = getRect();
        Vector2D pos = new Vector2D(
                rect.getX() + 16 + 40 * (slot % 10),
                rect.getY() + 16 + 40 * (slot / 10));
        sprite.drawUI(pos);
        ItemStack stack = stacks[slot];
        if (stack != null) {
            stack.getItem().drawUI(pos);
        }
    }

    @Override
    public void draw() {
        int slotsShown = isOpen ? 40 : 10;
        for (int i = 0; i < slotsShown; i++) {
            drawSlot(i);
        }
    }
    
    @Override
    public boolean keyPressed(IInput input, int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            isOpen = !isOpen;
            Rectangle rect = getRect();
            rect.setSize(rect.width, isOpen ? OPEN_HEIGHT : CLOSED_HEIGHT);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean keyReleased(IInput input, int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            return true;
        }
        return false;
    }
}
