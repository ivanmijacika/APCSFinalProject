import java.util.*;

public class World {

    public final Game game;

    private int width;
    private int height;
    private Tile[][] tiles;
    private Set<Entity> entities;
    private Player player;

    public World(Game game, int seed) {
        this.game = game;
        width = 2000;
        height = 1000;
        tiles = new Tile[height][width];
        generateWorld(seed);
        entities = new HashSet<Entity>();
        player = new Player(this, new Vector2D(width/2.0, height/2.0 - 5), new Vector2D(0, -10));
        entities.add(player);
    }
    
    private double heightFalloff(int x, int y) {
        double y2 = height/2.0 - y;
        return Math.exp(y2) + 1;
    }
    
    private void generateWorld(int seed) {
        for (Tile[] row : tiles) {
            Arrays.fill(row, Tile.AIR);
        }
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        INoiseGenerator noiseGen = game.noiseGenerator;
        double[][] noiseMap = new double[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double noise = noiseGen.perlin(seed, x*0.0002, y*0.0002);
                noiseMap[y][x] = noise;
                max = Math.max(max, noise);
                min = Math.min(min, noise);
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double noise = noiseMap[y][x];
                noise -= min;
                noise /= (max - min);
                double falloff = heightFalloff(x, y);
                noise *= falloff;
                if (noise < 0.5) {
                    setTile(x, y, Tile.STONE);
                } else if (noise < Math.pow(falloff, 0.975) * 0.6) {
                    setTile(x, y, Tile.DIRT);
                    if (getTile(x, y-1) == Tile.AIR) {
                        setTile(x, y, Tile.GRASS);
                    }
                }
            }
        }
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.AIR;
        return tiles[y][x];
    }

    public Tile getTile(TilePos pos) {
        return getTile(pos.getX(), pos.getY());
    }

    public void setTile(int x, int y, Tile t) {
        if (0 <= x && x < width && 0 <= y && y < height)
            tiles[y][x] = t;
    }

    public void setTile(TilePos pos, Tile t) {
        setTile(pos.getX(), pos.getY(), t);
    }

    public void tick(double deltaTime) {
        for (Entity e : entities) {
            e.tick(deltaTime);
        }
    }

    public void draw() {
        Vector2D center = game.view.getTarget();
        Vector2D topLeft = game.view.screenToWorldPos(Vector2D.ZERO);
        Vector2D botRight = center.add(center.subtract(topLeft));
        TilePos tLTile = new TilePos(topLeft);
        TilePos bRTile = new TilePos(botRight);
        for (int y = tLTile.getY(); y <= bRTile.getY(); y++) {
            for (int x = tLTile.getX(); x <= bRTile.getX(); x++) {
                getTile(x, y).draw(game.view, x, y);
            }
        }
        for (Entity e : entities) {
            e.draw();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public Player getPlayer() {
        return player;
    }

}
