import javax.xml.bind.util.ValidationEventCollector;
import java.util.*;

public class World {

    private int width;
    private int height;
    private Tile[][] tiles;
    private Set<Entity> entities;
    /*
    private Player player;
    */
    private Vector2D cameraPos;

    public World(long seed) {
        width = 100;
        height = 100;
        tiles = new Tile[height][width];
        for (Tile[] row : tiles) {
            for (int i = 0; i < row.length; i++) {
                row[i] = Tile.AIR;
            }
        }
        entities = new HashSet<Entity>();
        cameraPos = new Vector2D(50, 50);
    }

    public Tile getTile(int x, int y) {
        return tiles[y][x];
    }

    public void setTile(int x, int y, Tile t) {
        tiles[y][x] = t;
    }

    public void tick(int deltaMillis) {
        for (Entity e : entities) {
            e.tick(deltaMillis);
        }
    }

    public void draw() {
        for (Entity e : entities) {
            e.draw();
        }
    }

}
