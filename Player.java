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

    @Override
    public void tick(double deltaTime) {
        super.tick(deltaTime);
        if (input.isHeld(' ')) {
            // if on ground (exactly 0 vertical velocity), jump
            if (getVelocity().getY() == 0) {
                setVelocity(new Vector2D(getVelocity().getX(), -20));
            }
            // 1/2 gravity while moving upwards & holding space - not *realistic* physics, but it feels better
            if (getVelocity().getY() < 0) {
                setVelocity(getVelocity().subtract(Physics.GRAVITY.multiply(deltaTime/2)));
            }
        }
        int moveH = (input.isHeld('D') ? 1 : 0) - (input.isHeld('A') ? 1 : 0);
        Vector2D v = getVelocity();
        if (moveH == 0) {
            setVelocity(new Vector2D(v.getX()*0.8, v.getY()));
        } else {
            setVelocity(new Vector2D(v.getX()+moveH, v.getY()));
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
