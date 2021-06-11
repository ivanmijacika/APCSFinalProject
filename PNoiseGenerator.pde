public class PNoiseGenerator implements INoiseGenerator {

  private int lastSeed = -1;

  public PNoiseGenerator() {
    noiseSeed(lastSeed);
    noiseDetail(10, 1.5);
  }


  public double perlin(int seed, double x, double y){
    if (seed != lastSeed) noiseSeed(lastSeed = seed);
    return noise((float)x, (float)y);
  }
  
}
