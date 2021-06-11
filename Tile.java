public enum Tile {
    AIR, STONE("stone.PNG"), DIRT("dirt.PNG"), GRASS("grass.PNG", DIRT.item),
    WOOD("wood.PNG"),  TREE("tree.PNG", WOOD.item);

    private String spriteFile;
    private ISprite sprite = null;
    private Item item;

    Tile() {
        this(null, null);
    }

    Tile(String filename) {
        this.spriteFile = filename;
        this.item = new TileItem(this);
    }

    Tile(String filename, Item item) {
        this.spriteFile = filename;
        this.item = item;
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
