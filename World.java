import java.util.*;

public class World {

    public final Game game;

    private int width;
    private int height;
    private Tile[][] tiles;
    private double[][] lighting;
    private Set<Entity> entities;
    private Player player;

    public World(Game game, int seed) {
        this.game = game;
        
        width = 200;
        height = 200;
        tiles = new Tile[height][width];
        generateWorld(seed);
        
        lighting = new double[height][width];
        recalculateLighting();
        
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
        if (0 <= x && x < width && 0 <= y && y < height) {
            tiles[y][x] = t;
            if (lighting != null) {
                if (t == Tile.AIR && y == 0) {
                    updateLighting(x, y, 1);
                } else {
                    updateLighting(x, y);
                }
            }
        }
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
                getTile(x, y).draw(game.view, x, y, getLight(x, y));
            }
        }
        for (Entity e : entities) {
            e.draw(getLight(new TilePos(e.getPosition())));
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
    
    public double getLight(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return 0;
        return lighting[y][x];
    }
    
    public double getLight(TilePos t) {
        return getLight(t.getX(), t.getY());
    }
    
    private double getLightFrom(TilePos from, TilePos to) {
        double out = getLight(from);
        if (getTile(from) == Tile.AIR) {
            // to make cave illumination a feature instead of a bug
            //if (from.getX() != to.getX()) out -= 0.1;
        } else {
            out -= 0.2;
        }
        return out;
    }
    
    public void recalculateLighting() {
        for (int x = 0; x < width; x++) {
            if (getTile(x, 0) == Tile.AIR) {
                updateLighting(x, 0, 1);
            }
        }
    }
    
    public boolean inBounds(int x, int y) {
        return 0 <= x && x < width && 0 <= y && y < height;
    }
    
    public boolean inBounds(TilePos pos) {
        return inBounds(pos.getX(), pos.getY());
    }
    
    public void updateLighting(int x, int y) {
        updateLighting(x, y, getMaxLight(new TilePos(x, y)));
    }
    
    private double getMaxLight(TilePos tp) {
        double best = 0;
        for (TilePos neighbor : tp.neighbors()) {
            best = Math.max(best, getLightFrom(neighbor, tp));
        }
        return best;
    }
    
    public void updateLighting(int x, int y, double light) {
        TilePos start = new TilePos(x, y);
        lighting[y][x] = light;
        
        Queue<TilePos> toUpdate = new ArrayDeque<TilePos>(start.neighbors());
        
        while (!toUpdate.isEmpty()) {
            TilePos pos = toUpdate.remove();
            if (inBounds(pos)) {
                double l = getMaxLight(pos);
                if (l != getLight(pos)) {
                    lighting[pos.getY()][pos.getX()] = l;
                    toUpdate.addAll(pos.neighbors());
                }
            }
        }
    }
    
}
