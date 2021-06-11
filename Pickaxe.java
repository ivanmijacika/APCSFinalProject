public class Pickaxe extends Item {
	
	private ISprite sprite;
	
	public Pickaxe(ISpriteLoader spriteLoader) {
		super("PICKAXE");
		sprite = spriteLoader.load("pickaxe.png", new Vector2D(8, 8), 2);
	}
	
	@Override
	public int getMaxStackSize() {
		return 1;
	}
	
	@Override
	public ISprite getSprite() {
		return sprite;
	}
	
	@Override
	public void drawUI(Vector2D pos) {
		sprite.drawUI(pos);
	}
	
	@Override
	public void use(Player player, ItemStack stack, Vector2D mousePos) {
		World world = player.getWorld();
		TilePos tp = new TilePos(mousePos);
		if (player.inReach(tp)) {
			world.destroy(tp);
		}
	}
	
}
