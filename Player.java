import java.util.*;

public class Player extends Entity implements IMouseListener, IKeyListener {
    
    private static final int IDLE = 0;
    private static final int JUMP = 5;
    private static final int WALK_FIRST = 6;
    private static final int WALK_LAST = 18;
    
    
    
    private World world;
    private ISprite[] sprites;
    private ISprite[] spritesFlipped;
    private IInput input;
    private View view;

    private Inventory inventory;
    private int selectedItem = 0;
    private boolean mouseDown = false;
    private boolean jumpNextFrame = false;
    private boolean debugMode = false;

    public Player(World world, Vector2D pos, Vector2D vel) {
        super(world, pos, vel, new Vector2D(1.5, 2.75));
        this.world = world;
        loadSprites();
        input = world.game.input;
        view = world.game.view;

        inventory = new Inventory(this, input, world.game.spriteLoader, world.game.textRenderer);
        world.game.uiManager.addElement(inventory);
        input.addMouseListener(inventory);
        input.addKeyListener(inventory);
        input.addMouseListener(this);
        input.addKeyListener(this);
    }
    
    private void loadSprites() {
        sprites = world.game.spriteLoader.load("playerSpritesheet.png", new Vector2D(10,15), 1/8.0, 19);
        spritesFlipped = new ISprite[sprites.length];
        for (int i = 0; i < spritesFlipped.length; i++) {
            spritesFlipped[i] = sprites[i].flipped();
        }
    }
    
    public Inventory getInventory() {
        return inventory;
    }
    
    public int getSelected() {
        return selectedItem;
    }
    
    private boolean facingRight = true;
    private double walkAmount = 0;
    
    @Override
    public void draw(double brightness) {
        ISprite[] spriteArr = (facingRight ? spritesFlipped : sprites);
        ISprite sprite;
        if (getVelocity().getY() != 0) {
            sprite = spriteArr[JUMP];
        } else if (getVelocity().getX() == 0) {
            sprite = spriteArr[IDLE];
            walkAmount = 0;
        } else {
            int frame = WALK_FIRST + (int)(walkAmount % (WALK_LAST + 1 - WALK_FIRST));
            sprite = spriteArr[frame];
        }
        sprite.drawWithLight(world.game.view, getPosition(), brightness);
    }
    
    private void controlPlayer(double deltaTime) {
        if (jumpNextFrame) {
            // if on ground (exactly 0 vertical velocity), jump
            // for now we'll ignore that this will let you cling to ceilings
            if (getVelocity().getY() == 0) {
                setVelocity(new Vector2D(getVelocity().getX(), -14.6));
            }
            jumpNextFrame = false;
        }
        if (input.isHeld(' ')) {
            // 15% gravity while moving fast upwards & holding space - not *realistic* physics, but it feels better
            if (getVelocity().getY() < -10) {
                setVelocity(getVelocity().subtract(Physics.GRAVITY.multiply(deltaTime * 0.85)));
            }
        }
        int moveH = (input.isHeld('D') ? 1 : 0) - (input.isHeld('A') ? 1 : 0);
        Vector2D v = getVelocity();
        if (moveH == 0) {
            double vDelta = 50 * deltaTime;
            if (v.getY() != 0) {
                vDelta *= 0.5;
            }
            setVelocity(new Vector2D(approachZero(v.getX(), vDelta), v.getY()));
        } else {
            facingRight = moveH == 1;
            double targetSpeed = moveH * 12;
            double vDelta = 20 * deltaTime;
            // slow start
            if (Math.abs(v.getX()) < 2) {
                vDelta *= 0.75;
            } else if (v.getX() * moveH < 5) {  // fast turn around
                vDelta *= 2;
            }
            if (v.getX() * moveH < targetSpeed * moveH) {
                setVelocity(new Vector2D(approach(v.getX(), targetSpeed, vDelta), v.getY()));
            }
        }
    
        if (mouseDown) {
            ItemStack stack = inventory.getStack(selectedItem);
            if (stack != null) {
                stack.getItem().use(this, stack, view.screenToWorldPos(input.getMousePos()));
                if (stack.getCount() <= 0) {
                    inventory.setStack(selectedItem, null);
                }
            }
        }
        walkAmount += 2 * Math.abs(getVelocity().getX()) * deltaTime;
    }
    
    @Override
    public void tick(double deltaTime) {
        if (!debugMode) {
            controlPlayer(deltaTime);
            super.tick(deltaTime);
        } else {
            int moveH = (input.isHeld('D') ? 1 : 0) - (input.isHeld('A') ? 1 : 0);
            int moveW = (input.isHeld('S') ? 1 : 0) - (input.isHeld('W') ? 1 : 0);
            setPosition(getPosition().add(new Vector2D(moveH, moveW).multiply(50*deltaTime)));
    
            if (mouseDown) {
                TilePos pos = new TilePos(view.screenToWorldPos(input.getMousePos()));
                if (selectedItem == 0) {
                    world.destroy(pos);
                } else if (selectedItem < Tile.values().length) {
                    world.setTile(pos, Tile.values()[selectedItem]);
                }
            }
        }
        pickupItems();
    }
    
    private void pickupItems() {
        Collection<Entity> colliding = world.getAllColliding(this);
        for (Entity other : colliding) {
            if (other instanceof ItemEntity) {  // i had to, or else Entity would need a method for "is this an ItemEntity"
                ItemEntity itemEntity = (ItemEntity)other;
                if (inventory.addStack(itemEntity.getStack()).getCount() == 0) {
                    world.removeEntity(other);
                }
            }
        }
    }
    
    public boolean inReach(TilePos tp) {
        Vector2D center = new Vector2D(tp.getX() + 0.5, tp.getY() + 0.5);
        return center.subtract(getPosition()).magnitude() < 6;
    }

    @Override
    public boolean mousePressed(IInput input, int button) {
        if (button == 1) {
            mouseDown = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(IInput input,int button) {
        if (button == 1) {
            mouseDown = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseWheel(IInput input, int amount) {
        selectedItem += amount;
        //int n = Tile.values().length;
        int n = 10;
        selectedItem = ((selectedItem % n) + n) % n;
        System.out.println(selectedItem);
        return true;
    }
    
    @Override
    public boolean keyPressed(IInput input, int keyCode) {
        if (keyCode == ' ') {
            jumpNextFrame = true;
            return true;
        } else if (keyCode == '\n') {  // enter
            debugMode = !debugMode;
            return true;
        } else if ('0' <= keyCode && keyCode <= '9') {
            selectedItem = (keyCode - '0' + 9) % 10;
        }
        return false;
    }
    
    @Override
    public boolean keyReleased(IInput input, int keyCode) {
        return false;
    }
    
    @Override
    public boolean stepsUp() {
        return true;
    }
    
}
