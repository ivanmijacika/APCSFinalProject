public class Player extends Entity {

    private World world;
    private ISprite sprite;
    private IInput input;
    private View view;

    public Player(World world, Vector2D pos, Vector2D vel) {
        super(world, pos, vel, new Vector2D(1.5, 2.5));
        this.world = world;
        sprite = world.game.spriteLoader.load("player.png", new Vector2D(6,10), 1/8.0);
        input = world.game.input;
        view = world.game.view;
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
            if (getVelocity().getY() == 0) {
                setVelocity(new Vector2D(getVelocity().getX(), -25));
            }
            // 1/2 gravity while moving upwards & holding space - not *realistic* physics, but it feels better
            if (getVelocity().getY() < -7.5) {
                setVelocity(getVelocity().subtract(Physics.GRAVITY.multiply(deltaTime/2)));
            }
        }
        int moveH = (input.isHeld('D') ? 1 : 0) - (input.isHeld('A') ? 1 : 0);
        Vector2D v = getVelocity();
        if (moveH == 0) {
            double vDelta = 75 * deltaTime;
            if (v.getY() != 0) {
                vDelta *= 0.5;
            }
            setVelocity(new Vector2D(approachZero(v.getX(), vDelta), v.getY()));
        } else {
            double targetSpeed = moveH * 30;
            double vDelta = 50 * deltaTime;
            if (v.getY() != 0) {
                targetSpeed *= 0.5;
                vDelta *= 0.5;
            }
            if (v.getX() * moveH < 5) {
                vDelta *= 2;
            }
            if (v.getX() * moveH < targetSpeed * moveH) {
                setVelocity(new Vector2D(approach(v.getX(), targetSpeed, vDelta), v.getY()));
            }
        }

        // temporary controls for placing & breaking tiles
        if (input.isHeld('Q')) {
            TilePos pos = new TilePos(view.screenToWorldPos(input.getMousePos()));
            world.setTile(pos, Tile.AIR);
        }
        if (input.isHeld('E')) {
            TilePos pos = new TilePos(view.screenToWorldPos(input.getMousePos()));
            world.setTile(pos, Tile.STONE);
        }
    }

}
