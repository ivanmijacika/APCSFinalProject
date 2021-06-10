public class PNoiseGenerator implements INoiseGenerator{
 
  public double perlin(int seed, double x, double y){
    noiseSeed(seed);
    return noise((float)x, (float)y);
  }
  
}
