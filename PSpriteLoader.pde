public class PSpriteLoader implements ISpriteLoader {

    public ISprite load(String filename, Vector2D pivot, double scale) {
        PImage image = loadImage(filename);
        return new PSprite(image, pivot, scale);
    }
    
    public ISprite[] load(String filename, Vector2D pivot, double scale, int count){
        PImage image = loadImage(filename);
        int w = image.width/count;
        int h = image.height;
        PSprite[] spriteArr = new PSprite[count];
        for (int i=0; i<count; i++){
          spriteArr[i] = new PSprite(image.get(i*w, 0, w, h), pivot, scale);
        }
        return spriteArr;
    }

}
