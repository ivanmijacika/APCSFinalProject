public class TileItem extends Item {
	
	private Tile tile;
	
	public TileItem(Tile tile) {
		super(tile.toString(), tile.getSprite());
		this.tile = tile;
	}
	
	@Override
	public void use(Player player, ItemStack stack, Vector2D mousePos) {
		if (stack.getCount() > 0) {
			TilePos tp = new TilePos(mousePos);
			if (player.getWorld().getTile(tp) == Tile.AIR) {
				Vector2D center = new Vector2D(tp.getX() + 0.5, tp.getY() + 0.5);
				if (center.subtract(player.getPosition()).magnitude() < 5) {
					stack.setCount(stack.getCount() - 1);
					player.getWorld().setTile(tp, tile);
				}
			}
		}
	}
	
}
