public class TileItem extends Item {
	
	private final Tile tile;
	private ISprite sprite = null;
	
	public TileItem(Tile tile) {
		super(tile.toString());
		this.tile = tile;
	}
	
	public Tile getTile() {
		return tile;
	}
	
	@Override
	public ISprite getSprite() {
		if (sprite == null) {
			sprite = tile.getSprite().withScale(2);
			sprite.setPivot(new Vector2D(4, 4));
		}
		return sprite;
	}
	
	@Override
	public void drawUI(Vector2D pos) {
		getSprite().drawUI(pos);
	}
	
	@Override
	public void use(Player player, ItemStack stack, Vector2D mousePos) {
		World world = player.getWorld();
		TilePos tp = new TilePos(mousePos);
		if (stack.getCount() > 0 && world.getTile(tp) == Tile.AIR && !world.freeFloating(tp)
		&& player.inReach(tp) && !Physics.intersectsTile(player, tp)) {
			stack.setCount(stack.getCount() - 1);
			player.getWorld().setTile(tp, getTile());
		}
	}
	
}
