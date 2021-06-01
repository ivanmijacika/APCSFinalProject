public abstract class Entity {

    private World world;
    private Vector2D position;  // center
    private Vector2D velocity;
    private Vector2D size;      // bounding box (total width & height)

    public Entity(World world, Vector2D pos, Vector2D vel, Vector2D size) {
        this.world = world;
        position = pos;
        velocity = vel;
        this.size = size;
    }

    public abstract void draw();

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public Vector2D getSize() {
        return size;
    }

    public void setSize(Vector2D size) {
        this.size = size;
    }

    public void tick(int deltaMillis) {
        Physics.MoveResult result = Physics.moveEntity(world, this, deltaMillis/1000.0);
        if (result.x) setVelocity(new Vector2D(0, getVelocity().getY()));
        if (result.y) setVelocity(new Vector2D(getVelocity().getX(), 0));
    }

}
