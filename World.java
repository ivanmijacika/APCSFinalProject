import java.util.*;

public class World {

    public final Game game;

    private int width;
    private int height;
    private Tile[][] tiles;
    private Set<Entity> entities;
    private Player player;

    public World(Game game, long seed) {
        this.game = game;
        width = 100;
        height = 100;
        tiles = new Tile[height][width];
        for (Tile[] row : tiles) {
            for (int i = 0; i < row.length; i++) {
                row[i] = Tile.AIR;
            }
        }
        generateWorld(seed);
        entities = new HashSet<Entity>();
        player = new Player(this, new Vector2D(width/2.0, height/2.0), new Vector2D(0, -10));
        entities.add(player);
    }

    // temporary stone platform from (40, 55) to (60, 55)
    private void generateWorld(long seed) {
        for (int x = 40; x <= 60; x++) {
            tiles[55][x] = Tile.STONE;
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
        // should change to only region visible in view if it gets too slow
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
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
