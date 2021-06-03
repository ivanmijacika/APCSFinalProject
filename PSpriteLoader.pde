public class PSpriteLoader implements ISpriteLoader {

    public ISprite load(String filename, Vector2D pivot, double scale) {
        PImage image = loadImage(filename);
        return new PSprite(image, pivot, scale);
    }

}
