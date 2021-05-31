public abstract class Entity {

    private World world;
    private Vector2D position;
    private Vector2D velocity;

    public Entity(World world, Vector2D pos, Vector2D vel) {
        this.world = world;
        position = pos;
        velocity = vel;
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

    public void tick(int deltaMillis) {
        Physics.moveEntity(world, this, deltaMillis/1000.0);
    }

}
