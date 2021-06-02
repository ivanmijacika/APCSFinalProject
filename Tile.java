public enum Tile {
    AIR, STONE("placeholder.png"), DIRT, GRASS;

    private String spriteFile;
    private ISprite sprite = null;

    Tile() {
        this(null);
    }

    Tile(String filename) {
        this.spriteFile = filename;
    }

    public static void loadSprites(ISpriteLoader loader) {
        for (Tile t : Tile.values()) {
            if (t.spriteFile != null) {
                t.sprite = loader.load(t.spriteFile, Vector2D.ZERO, 1 / 8.0);
            }
        }
    }

}
