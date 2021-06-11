import java.awt.*;
import java.awt.event.KeyEvent;

public class Inventory extends UIElement implements IKeyListener {
    
    private static final int CLOSED_HEIGHT = 32;
    private static final int OPEN_HEIGHT = 140;
    
    private Player player;
    private IInput input;
    private ISprite sprite;
    private ISprite selectedSprite;
    private ItemStack[] stacks = new ItemStack[40];
    
    private int selected = -1;
    private boolean isOpen = false;

    public Inventory(Player player, IInput input, ISpriteLoader spriteLoader) {
        super(new Rectangle(10, 10, 356, CLOSED_HEIGHT));
        sprite = spriteLoader.load("uiButton.png", new Vector2D(8, 8), 2);
        selectedSprite = spriteLoader.load("uiButtonSelected.png", new Vector2D(8, 8), 2);
        this.input = input;
        this.player = player;
    }
    
    private void drawSlot(int slot) {
        Rectangle rect = getRect();
        Vector2D pos = new Vector2D(
                rect.getX() + 16 + 36 * (slot % 10),
                rect.getY() + 16 + 36 * (slot / 10));
        (slot == player.getSelected() ? selectedSprite : sprite).drawUI(pos);
        if (selected != slot) {
            ItemStack stack = stacks[slot];
            if (stack != null) {
                stack.getItem().drawUI(pos);
            }
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
            merge(stack, inSlot);
        }
        return stack;
    }
    
    private void merge(ItemStack from, ItemStack to) {
        Item item = from.getItem();
        int combineTo = Math.min(to.getCount() + from.getCount(), item.getMaxStackSize());
        int overflow = Math.max(0, to.getCount() + from.getCount() - item.getMaxStackSize());
        to.setCount(combineTo);
        from.setCount(overflow);
    }
    
    private int getSlot(Vector2D uiPos) {
        int x = (int) ((uiPos.getX() - 8) / 36);
        int y = (int) ((uiPos.getY() - 8) / 36);
        return x + 10 * y;
    }
    
    @Override
    public boolean mousePressed(IInput input, int button) {
        if(super.mousePressed(input, button)) {
            Vector2D mP = input.getMousePos();
            int slot = getSlot(mP);
            if (selected == -1) {
                if (getStack(slot) != null) selected = slot;
            } else if (selected == slot) {
                selected = -1;
            } else {
                ItemStack clickedStack = getStack(slot);
                ItemStack selectedStack = getStack(selected);
                // if same item, merge
                if (clickedStack != null && clickedStack.getItem() == selectedStack.getItem()) {
                    merge(selectedStack, clickedStack);
                    if (selectedStack.getCount() <= 0) {
                        setStack(selected, null);
                        selected = -1;
                    }
                } else {  // else, swap the two stacks
                    setStack(selected, clickedStack);
                    setStack(slot, selectedStack);
                    if (clickedStack == null) {
                        selected = -1;
                    }
                }
            }
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
        if (selected != -1) {
            getStack(selected).getItem().drawUI(input.getMousePos());
        }
    }
    
    @Override
    public boolean keyPressed(IInput input, int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            isOpen = !isOpen;
            Rectangle rect = getRect();
            rect.setSize(rect.width, isOpen ? OPEN_HEIGHT : CLOSED_HEIGHT);
            if (!isOpen) selected = -1;
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
