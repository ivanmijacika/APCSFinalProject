import java.util.Random;

public class Game {

    public final View view;
    public final IInput input;
    public final ISpriteLoader spriteLoader;
    public final ITextRenderer textRenderer;
    public final UIManager uiManager;
    public final INoiseGenerator noiseGenerator;
    public final Random random = new Random();

    private World world;

    public Game(View view, IInput input, ISpriteLoader spriteLoader, INoiseGenerator noiseGenerator, ITextRenderer textRenderer) {
        this.view = view;
        this.input = input;
        this.spriteLoader = spriteLoader;
        this.textRenderer = textRenderer;
        this.noiseGenerator = noiseGenerator;
        this.uiManager = new UIManager(this);
        Tile.loadSprites(spriteLoader);
        world = new World(this, random.nextInt());
        Pickaxe pick = new Pickaxe(spriteLoader);
        world.getPlayer().getInventory().addStack(new ItemStack(pick, 1));
    }

    public void tick(double deltaTime) {
        world.tick(deltaTime);
        view.setTarget(world.getPlayer().getPosition());
    }

    public void draw() {
        world.draw();
        uiManager.draw();
    }

}
