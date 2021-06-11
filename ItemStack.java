public class ItemStack {
	
	private Item item;
	private Inventory inventory;
	private int count;
	
	public ItemStack(Item item, int count) {
		this(item, count, null);
	}
	
	public ItemStack(Item item, int count, Inventory inv) {
		this.item = item;
		this.count = count;
		this.inventory = inv;
	}
	
	public ItemStack(ItemStack other) {
		this(other.getItem(), other.getCount());
	}
	
	public ItemStack(ItemStack other, Inventory inv) {
		this(other.getItem(), other.getCount(), inv);
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public void setInventory(Inventory inv) {
		inventory = inv;
	}
	
	public Item getItem() {
		return item;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int c) {
		count = c;
	}
	
	public String toString() {
		return "ItemStack(" + getItem() + ", " + getCount() + ")";
	}
	
}
