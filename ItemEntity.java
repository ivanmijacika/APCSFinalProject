public class ItemEntity extends Entity {
	
	private ItemStack stack;
	private ISprite sprite;
	
	public ItemEntity(World world, Vector2D pos, ItemStack stack) {
		super(world, pos, new Vector2D(10*Math.random()-5, -5), new Vector2D(0.5, 0.5));
		this.stack = stack;
		sprite = stack.getItem().getSprite().withScale(1/16.0);
		sprite.setPivot(new Vector2D(4, 4));
	}
	
	@Override
	public void draw(double brightness) {
		sprite.drawWithLight(getWorld().game.view, getPosition(), brightness);
	}
	
	@Override
	public void tick(double deltaTime) {
		super.tick(deltaTime);
		Vector2D vel = getVelocity();
		
		setVelocity(new Vector2D(approachZero(vel.getX(), 5*deltaTime), vel.getY()));
	}
	
}
