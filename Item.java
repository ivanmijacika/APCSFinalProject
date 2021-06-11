public abstract class Item{
    
    private ISprite sprite;
    private String name;
    
    public Item(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public abstract ISprite getSprite();
    
    public abstract void drawUI(Vector2D pos);
    
    public abstract void use(Player player, ItemStack stack, Vector2D mousePos);
    
    public int getMaxStackSize(){
      return 99; //max size is 99 unless overridden
    }
    
}
