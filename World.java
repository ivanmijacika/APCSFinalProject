import java.util.*;

public class World {

    public final Game game;

    private int width;
    private int height;
    private Tile[][] tiles;
    private Set<Entity> entities;
    /*
    private Player player;
    */

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
        entities = new HashSet<Entity>();
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.AIR;
        return tiles[y][x];
    }

    public void setTile(int x, int y, Tile t) {
        tiles[y][x] = t;
    }

    public void tick(double deltaTime) {
        for (Entity e : entities) {
            e.tick(deltaTime);
        }
    }

    public void draw() {
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

}
