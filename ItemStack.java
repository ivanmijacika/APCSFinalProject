public class ItemStack{

  private Item item;
  private int count;

  public ItemStack(Item item, int count){
    this.item = item;
    this.count = count;
  }

  public ItemStack(ItemStack other){
    item = other.getItem();
    count = other.getCount();
  }

  public Item getItem(){
    return item;
  }

  public int getCount(){
    return count;
  }

  public void setCount(int c){
    count = c;
  }

}
