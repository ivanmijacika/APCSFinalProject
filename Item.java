public abstract class Item{

  private ISprite sprite;

  public abstract String getName();

  public ISprite getSprite(){
    return sprite;
  }

  public abstract void use(Player player, ItemStack stack, Vector2D mousePos);

  public int getMaxStackSize(){
    return 99; //max size is 99 unless overriden
  }

}
