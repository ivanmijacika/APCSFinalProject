public interface ISpriteLoader {

    ISprite load(String filename, Vector2D pivot, double scale);
    ISprite[] load(String filename, Vector2D pivot, double scale, int count);

}
