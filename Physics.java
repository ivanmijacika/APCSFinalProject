public class Physics {

    public static final Vector2D GRAVITY = new Vector2D(0, 9.81);

    public static void moveEntity(World world, Entity entity, double deltaSec) {
        Vector2D pos = entity.getPosition();
        Vector2D vel = entity.getVelocity();

        entity.setPosition(pos.add(vel.multiply(deltaSec)));

        Vector2D newV = vel.add(GRAVITY.multiply(deltaSec));
        if (newV.getY() < -10) {  // maximum falling speed
            newV = new Vector2D(newV.getX(), -10);
        }
        entity.setVelocity(newV);
    }

}
