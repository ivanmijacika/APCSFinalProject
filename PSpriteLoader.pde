public class PSpriteLoader implements ISpriteLoader {

    public ISprite load(String filename, Vector2D pivot, double scale) {
        PImage image = loadImage(filename);
        int[][] colors = new int[image.height][image.width];
        for (int y = 0; y < image.height; y++) {
            for (int x = 0; x < image.width; x++) {
                colors[y][x] = image.get(x, y);
            }
        }
        return new PSprite(colors, pivot, scale);
    }

}
