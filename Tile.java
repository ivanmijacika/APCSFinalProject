public enum Tile {
    AIR, STONE("stone.PNG"), DIRT("dirt.PNG"), GRASS("grass.PNG", DIRT.item),
    WOOD("wood.PNG"),  TREE("tree.PNG", WOOD.item, false),
    LEAVES("leaves.png", false);
    
    private boolean solid;
    private String spriteFile;
    private ISprite sprite = null;
    private Item item;

    Tile() {
        this(null, null, false);
    }

    Tile(String filename) {
        this.spriteFile = filename;
        this.item = new TileItem(this);
        this.solid = true;
    }
    
    Tile(String filename, boolean solid) {
        this.spriteFile = filename;
        this.item = null;
        this.solid = solid;
    }

    Tile(String filename, Item item) {
        this.spriteFile = filename;
        this.item = item;
        this.solid = true;
    }
    
    Tile(String filename, Item item, boolean solid) {
        this.spriteFile = filename;
        this.item = item;
        this.solid = solid;
    }
    
    public boolean isSolid() {
        return solid;
    }
    
    public Item getItem() {
        return item;
    }

    public ISprite getSprite() {
        return sprite;
    }

    public static void loadSprites(ISpriteLoader loader) {
        for (Tile t : Tile.values()) {
            if (t.spriteFile != null) {
                t.sprite = loader.load(t.spriteFile, Vector2D.ZERO, 1 / 8.0);
            }
        }
    }

    public void draw(View view, int x, int y, double brightness) {
        if (sprite != null)
            sprite.drawWithLight(view, new Vector2D(x, y), brightness);
    }

}
