public class Player extends Entity implements IMouseListener {

    private World world;
    private ISprite sprite;
    private IInput input;
    private View view;

    private int selectedItem = 0;
    private boolean mouseDown = false;
    private boolean lastHoldingSpace = false;

    public Player(World world, Vector2D pos, Vector2D vel) {
        super(world, pos, vel, new Vector2D(1.5, 2.5));
        this.world = world;
        sprite = world.game.spriteLoader.load("player.png", new Vector2D(6,10), 1/8.0);
        input = world.game.input;
        view = world.game.view;

        input.addMouseListener(this);
    }

    @Override
    public void draw() {
        sprite.draw(world.game.view, getPosition());
    }

    private double approachZero(double from, double by) {
        return Math.abs(from) <= by ? 0 : from - Math.copySign(by, from);
    }

    private double approach(double from, double to, double by) {
        return approachZero(from - to, by) + to;
    }

    @Override
    public void tick(double deltaTime) {
        super.tick(deltaTime);
        if (input.isHeld(' ')) {
            // if on ground (exactly 0 vertical velocity), jump
            // for now we'll ignore that this will let you cling to ceilings
            if (getVelocity().getY() == 0 && !lastHoldingSpace) {
                setVelocity(new Vector2D(getVelocity().getX(), -14.5));
            }
            // 15% gravity while moving fast upwards & holding space - not *realistic* physics, but it feels better
            if (getVelocity().getY() < -10) {
                setVelocity(getVelocity().subtract(Physics.GRAVITY.multiply(deltaTime*0.85)));
            }
            lastHoldingSpace = true;
        } else {
            lastHoldingSpace = false;
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
            // until we get a working inventory system & items, this'll do:
            TilePos pos = new TilePos(view.screenToWorldPos(input.getMousePos()));
            world.setTile(pos, Tile.values()[selectedItem]);
        }
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
        int n = Tile.values().length;
        selectedItem = ((selectedItem % n) + n) % n;
        // until we have working UI, this'll do:
        System.out.println(Tile.values()[selectedItem]);
        return true;
    }
}
