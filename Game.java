public class Game {

    public final View view;
    public final IInput input;
    public final ISpriteLoader spriteLoader;

    private World world;

    public Game(View view, IInput input, ISpriteLoader spriteLoader) {
        this.view = view;
        this.input = input;
        this.spriteLoader = spriteLoader;
        world = new World(this, 0);
    }

    public void tick(double deltaTime) {
        world.tick(deltaTime);
    }

    public void draw() {
        world.draw();
    }

}
