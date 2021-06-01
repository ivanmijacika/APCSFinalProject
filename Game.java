public class Game {

    public final View view;
    public final IInput input;

    private World world;

    public Game(View view, IInput input) {
        this.view = view;
        this.input = input;
        world = new World(0);
    }

    public void tick(double deltaTime) {
        world.tick(deltaTime);
    }

}
