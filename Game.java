public class Game {

    public final View view;
    public final IInput input;
    public final ISpriteLoader spriteLoader;

    private World world;

    public Game(View view, IInput input, ISpriteLoader spriteLoader) {
        this.view = view;
        this.input = input;
        this.spriteLoader = spriteLoader;
        Tile.loadSprites(spriteLoader);
        world = new World(this, 0);
    }

    public void tick(double deltaTime) {
        world.tick(deltaTime);
        view.setTarget(world.getPlayer().getPosition());
    }

    public void draw() {
        world.draw();
    }

}
