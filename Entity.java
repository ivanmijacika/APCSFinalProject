public abstract class Entity {

    public static final Vector2D GRAVITY = new Vector2D(0, 9.81);

    private Vector2D position;
    private Vector2D velocity;

    public Entity(Vector2D pos, Vector2D vel) {
        position = pos;
        velocity = vel;
    }

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
        /* replace once implemented:
        Physics.moveEntity(world, this);
         */
        double deltaSec = deltaMillis/1000.0;
        setPosition(getPosition().add(getVelocity().multiply(deltaSec)));
        
        Vector2D v = getVelocity().add(GRAVITY.multiply(deltaSec));
        if (v.getY() < -10) {  // maximum falling speed
            v = new Vector2D(v.getX(), -10);
        }
        setVelocity(v);
    }

}
