import java.awt.*;
import java.awt.event.KeyEvent;

public class Inventory extends UIElement implements IKeyListener {
    
    private static final int CLOSED_HEIGHT = 32;
    private static final int OPEN_HEIGHT = 140;
    
    private ISprite sprite;
    private ItemStack[] stacks = new ItemStack[40];
    
    private int selected = -1;
    private boolean isOpen = false;

    public Inventory(IInput input, ISpriteLoader spriteLoader) {
        super(new Rectangle(10, 10, 356, CLOSED_HEIGHT));
        sprite = spriteLoader.load("uiButton.png", new Vector2D(8, 8), 2);
    }
    
    private void drawSlot(int slot) {
        Rectangle rect = getRect();
        Vector2D pos = new Vector2D(
                rect.getX() + 16 + 36 * (slot % 10),
                rect.getY() + 16 + 36 * (slot / 10));
        sprite.drawUI(pos);
        ItemStack stack = stacks[slot];
        if (stack != null) {
            stack.getItem().drawUI(pos);
        }
    }
    
    public ItemStack getStack(int slot) {
        return stacks[slot];
    }
    
    public void setStack(int slot, ItemStack stack) {
        stacks[slot] = stack;
    }
    
    // returns remaining items
    public ItemStack addStack(ItemStack stack) {
        for (int i = 0; i < stacks.length && stack.getCount() > 0; i++) {
            stack = addStack(stack, i);
        }
        return stack;
    }
    
    public ItemStack addStack(ItemStack stack, int slot) {
        Item item = stack.getItem();
        ItemStack inSlot = stacks[slot];
        if (inSlot == null) {
            stacks[slot] = new ItemStack(stack, this);
            stack.setCount(0);
        } else if (inSlot.getItem() == item) {
            int combineTo = Math.min(inSlot.getCount() + stack.getCount(), item.getMaxStackSize());
            int overflow = Math.max(0, inSlot.getCount() + stack.getCount() - item.getMaxStackSize());
            inSlot.setCount(combineTo);
            stack.setCount(overflow);
        }
        return stack;
    }
    
    @Override
    public boolean mousePressed(IInput input, int button) {
        if(super.mousePressed(input, button)) {
            return true;
        }
        return false;
    }
    
    @Override
    public boolean mouseReleased(IInput input, int button) {
        if(super.mouseReleased(input, button)) {
            return true;
        }
        return false;
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
