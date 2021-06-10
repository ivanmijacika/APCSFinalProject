public abstract class Item{

    private ISprite sprite;
    private String name;

    public Item(String name, ISprite sprite) {
        this.name = name;
        this.sprite = sprite;
    }

    public String getName() {
        return name;
    }

    public ISprite getSprite(){
      return sprite;
    }

    public abstract void use(Player player, ItemStack stack, Vector2D mousePos);

    public int getMaxStackSize(){
      return 99; //max size is 99 unless overridden
    }

}
