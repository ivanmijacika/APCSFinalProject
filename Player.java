public class Player extends Entity {

    private World world;
    private ISprite sprite;
    private IInput input;

    public Player(World world, Vector2D pos, Vector2D vel) {
        super(world, pos, vel, new Vector2D(1.5, 2.5));
        this.world = world;
        sprite = world.game.spriteLoader.load("player.png", new Vector2D(6,10), 1/8.0);
        input = world.game.input;
    }

    @Override
    public void draw() {
        sprite.draw(world.game.view, getPosition());
    }

    @Override
    public void tick(double deltaTime) {
        super.tick(deltaTime);
        if (input.isHeld(' ')) {
            System.out.print("SPACE");
        }
    }

}
